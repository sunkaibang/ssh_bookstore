package book.store.admin.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import book.store.domain.Order;
import book.store.service.OrderService;

import com.opensymphony.xwork2.ActionSupport;

public class AdminOrderAction extends ActionSupport {

	private static final long serialVersionUID = -4469912646679414985L;

	private OrderService orderService;
	private List<Order> orderList;
	private Integer oid;
	private Order order;
	private String msg;

	// ��ѯ���ж���
	public String findAllOrder() {
		orderList = orderService.findAllOrders();
		return "admin_order_list";
	}

	// ���ն���״̬���Ҷ���
	public String findOrderByState() {
		Integer state = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("state"));
		switch (state) {
		case 1:
			orderList = orderService.findOrderByState(1);
			break;
		case 2:
			orderList = orderService.findOrderByState(2);
			break;
		case 3:
			orderList = orderService.findOrderByState(3);
			break;
		case 4:
			orderList = orderService.findOrderByState(4);
			break;
		default:
			break;
		}
		return "admin_order_list";
	}
	
	// ����
	public String sendGoods() {
		orderService.modifyOrderState(oid, 3);
		return "redirect_admin_order_list";
	}
	
	// ɾ������
	public String deleteOrder() {
		orderService.deleteOrder(oid);
		return "redirect_admin_order_list";
	}
	
	// �޸Ķ���֮��һ������תҳ��
	public String updateOrderFirst() {
		order = orderService.findOrderByOid(oid);
		return "order_detail";
	}
	
	// �޸Ķ���
	public String updateOrder() {
		order = orderService.updateOrder(oid, order);
		msg = "�޸ĳɹ�";
		return "order_detail";
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
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

	public Order getOrder() {
		return order;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}

	public String getMsg() {
		return msg;
	}
	
}
