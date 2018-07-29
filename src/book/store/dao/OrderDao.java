package book.store.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import book.store.domain.Order;
import book.store.domain.User;

public class OrderDao {

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	// ���涩��
	public void saveOrder(Order order) {
		hibernateTemplate.save(order);
	}
	
	// ���û���ѯ���ж���
	public List<Order> findOrdersByUser(User user) {
		List<Order> orderList = (List<Order>) hibernateTemplate.find("from Order where owner=?", user);
		return orderList;
	}
	
	// ͨ�������Ų�ѯ����
	public Order findOrderByOid(Integer oid) {
		List<Order> orderList = (List<Order>) hibernateTemplate.find("from Order where oid=?", oid);
		if (orderList.size() > 0)
			return orderList.get(0);
		else 
			return null;
	}
	
	
	//�޸Ķ���
	public void updateOrder(Order order) {
		hibernateTemplate.update(order);
	}
	
	
	// ��ѯ���ж���
	public List<Order> findAllOrders() {
		List<Order> orderList = (List<Order>) hibernateTemplate.find("from Order", null);
		return orderList;
	}
	
	// ������״̬���Ҷ���
	public List<Order> findOrderByState(int i) {
		List<Order> orderList = (List<Order>) hibernateTemplate.find("from Order where state=?", i);
		return orderList;
	}
	
	// ɾ������
	public void deleteOrder(Order order) {
		hibernateTemplate.delete(order);
		
	}

}
