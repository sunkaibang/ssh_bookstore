package book.store.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;

import book.store.domain.Book;

public class BookDao {
	private HibernateTemplate hibernateTemplate;
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// springע��hibernateTemplate
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	// ��������������
	public List<Book> findByCategory(String cid) {

		// Session session = sessionFactory.openSession();
		// Transaction tx = session.beginTransaction();
		// Query query = session.createQuery("from Book where cid=?");
		// query.setParameter(0, cid);
		// List<Book> bookList = query.list();
		// tx.commit();

		List<Book> bookList = (List<Book>) hibernateTemplate.find(
				"from Book where cid=?", cid);
		return bookList;
	}

	// ��bid����ͼ��
	public Book findByBid(Integer bid) {
		List<Book> bookList = (List<Book>) hibernateTemplate.find(
				"from Book where bid=?", bid);
		return bookList.get(0);
	}

	// �������е�ͼ��
	public List<Book> findAll() {
		return (List<Book>) hibernateTemplate.find("from Book", null);
	}

	// ���ͼ��
	public void addBook(Book book) {
		hibernateTemplate.save(book);
	}

	// ɾ��ͼ��
	public void delete(Book book) {
		hibernateTemplate.delete(book);
	}

	// �޸�ͼƬ
	public void modifyBook(Book book) {
		hibernateTemplate.update(book);
	}
	
	// ��ѯ�ܵ�����
	public int findCount(Book book) {
//		List<Object> list = (List<Object>) hibernateTemplate.find("select count(*) from Book", null);
//		if (list != null && list.size() > 0 ) {
//			Object count = list.get(0);
//			Long countLong = (Long) count;
//			return countLong.intValue();
//		}
//		return 0;
		
		// ʹ�����߶����ѯ�����Ǻ�sessionû�й�ϵ��
		DetachedCriteria detachedCriteria = getDeDetachedCriteria(book);
		detachedCriteria.setProjection(Projections.rowCount());
		Object obj = hibernateTemplate.findByCriteria(detachedCriteria).get(0);
		Long lobj = (Long) obj;
		return lobj.intValue();
	}
	
	// ��ҳ��ѯ
	public List<Book> findPage(Integer begin, Integer pageSize, Book book) {
		// ʹ��hql��ѯ
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
//		
//		Query query = session.createQuery("from Book");
//		query.setFirstResult(begin);
//		query.setMaxResults(pageSize);
//		
//		List<Book> list = query.list();
		
		
		// ʹ�����߶����ѯ�����Ǻ�sessionû�й�ϵ��
		DetachedCriteria detachedCriteria = getDeDetachedCriteria(book);
		Criteria criteria = detachedCriteria.getExecutableCriteria(session);
		criteria.setFirstResult(begin);
		criteria.setMaxResults(pageSize);
		List<Book> list = criteria.list();
		
		return list;
	}
	
	// �������߲�ѯ����
	private DetachedCriteria getDeDetachedCriteria(Book book) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Book.class);
		if (book.getBname() != null && ! book.getBname().isEmpty()) {
			detachedCriteria.add(Restrictions.like("bname", "%" + book.getBname() + "%"));
		}
		if (book.getBauther() != null && ! book.getBauther().isEmpty()) {
			detachedCriteria.add(Restrictions.like("bauther", "%" + book.getBauther() + "%"));
		}
		if (book.getCategory() != null && book.getCategory().getCid() != -1) {
			detachedCriteria.add(Restrictions.like("category", book.getCategory()));
		}
		return detachedCriteria;
	}

}
