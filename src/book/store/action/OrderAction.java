package book.store.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import book.store.domain.Cart;
import book.store.domain.CartItem;
import book.store.domain.Order;
import book.store.domain.OrderItem;
import book.store.domain.User;
import book.store.service.OrderService;
import book.store.tools.AlipayConfig;
import book.store.tools.EachoDomain;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.opensymphony.xwork2.ActionSupport;

public class OrderAction extends ActionSupport {

	private static final long serialVersionUID = -6193008735683442685L;

	private OrderService orderService;
	private Order order;
	private List<Order> orderList;
	private Integer oid;

	// 保存订单和订单项
	public String add() {
		Cart cart = (Cart) ServletActionContext.getRequest().getSession()
				.getAttribute("cart");
		User user = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("session_user");
		order = new Order();
		order.setDate(new Date());
		order.setTotal(cart.getTotal());
		order.setState(1);
		order.setAdress(user.getEmail());
		order.setOwner(user);
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem item = new OrderItem();
			item.setCount(cartItem.getCount());
			item.setTotal(cartItem.getTotal());
			item.setBook(cartItem.getBook());
			item.setOrder(order);
			order.getOrderItems().add(item);
		}

		cart.clearCart();
		orderService.saveOrder(order);
		return "order_desc";
	}

	// 按用户查询所有订单
	public String findOrdersByUser() {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("session_user");
		orderList = orderService.findOrdersByUser(user);
		return "order_list";
	}

	// 跳转到付款页面
	public String goPay() {
		order = orderService.findOrderByOid(oid);
		return "order_desc";
	}

	// 支付宝付款
	public String payByAlipay() throws AlipayApiException, IOException {
		String address = ServletActionContext.getRequest().getParameter(
				"address");
		order = orderService.findOrderByOid(oid);
		order.setAdress(address);
		EachoDomain.order = order;

		AlipayClient alipayClient = new DefaultAlipayClient(
				AlipayConfig.gatewayUrl, AlipayConfig.app_id,
				AlipayConfig.merchant_private_key, "json",
				AlipayConfig.charset, AlipayConfig.alipay_public_key,
				AlipayConfig.sign_type);

		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

		String out_trade_no = String.valueOf(order.getOid());
		String total_amount = String.valueOf(order.getTotal());
		String subject = new String("BookStore");
		String body = new String("book");
		String context = "{\"out_trade_no\":\"" + out_trade_no + "\","
				+ "\"total_amount\":\"" + total_amount + "\","
				+ "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body
				+ "\"," + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}";

		alipayRequest.setBizContent(context);
		String result = alipayClient.pageExecute(alipayRequest).getBody();

		

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.getOutputStream().write(
				"<HTML><HEAD><TITLE>付款</TITLE></HEAD><BODY>".getBytes());
		response.getOutputStream().write(result.getBytes());
		response.getOutputStream().write("</BODY></HTML>".getBytes());
		return NONE;
	}

	// 支付宝回掉
	public String returnUrl() throws AlipayApiException {
		Map<String, String> param = new HashMap<String, String>();
		Map<String, String[]> paramRequest = ServletActionContext.getRequest()
				.getParameterMap();

		for (Iterator<String> it = paramRequest.keySet().iterator(); it
				.hasNext();) {
			String name = it.next();
			String[] values = paramRequest.get(name);
			String strValue = "";
			for (int i = 0; i < values.length; i++) {
				strValue = (i == values.length - 1) ? strValue + values[i]
						: strValue + values[i] + ",";
			}
			param.put(name, strValue);
		}

		boolean flag = AlipaySignature.rsaCheckV1(param,
				AlipayConfig.alipay_public_key, AlipayConfig.charset,
				AlipayConfig.sign_type);

		if (flag) {
			// 修改订单的状态
			orderService.pay(EachoDomain.order);
			return "pay_success";
		} else {
			return "pay_failed";
		}
	}

	// 确认收货
	public String confirm() {
		orderService.confirm(oid);
		return "redirt_allOrders";
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public Order getOrder() {
		return order;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}
}
