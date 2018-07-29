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

	// spring注入hibernateTemplate
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	// 按书的种类查找书
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

	// 按bid查找图书
	public Book findByBid(Integer bid) {
		List<Book> bookList = (List<Book>) hibernateTemplate.find(
				"from Book where bid=?", bid);
		return bookList.get(0);
	}

	// 查找所有的图书
	public List<Book> findAll() {
		return (List<Book>) hibernateTemplate.find("from Book", null);
	}

	// 添加图书
	public void addBook(Book book) {
		hibernateTemplate.save(book);
	}

	// 删除图书
	public void delete(Book book) {
		hibernateTemplate.delete(book);
	}

	// 修改图片
	public void modifyBook(Book book) {
		hibernateTemplate.update(book);
	}
	
	// 查询总的数据
	public int findCount(Book book) {
//		List<Object> list = (List<Object>) hibernateTemplate.find("select count(*) from Book", null);
//		if (list != null && list.size() > 0 ) {
//			Object count = list.get(0);
//			Long countLong = (Long) count;
//			return countLong.intValue();
//		}
//		return 0;
		
		// 使用离线对象查询（就是和session没有关系）
		DetachedCriteria detachedCriteria = getDeDetachedCriteria(book);
		detachedCriteria.setProjection(Projections.rowCount());
		Object obj = hibernateTemplate.findByCriteria(detachedCriteria).get(0);
		Long lobj = (Long) obj;
		return lobj.intValue();
	}
	
	// 分页查询
	public List<Book> findPage(Integer begin, Integer pageSize, Book book) {
		// 使用hql查询
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
//		
//		Query query = session.createQuery("from Book");
//		query.setFirstResult(begin);
//		query.setMaxResults(pageSize);
//		
//		List<Book> list = query.list();
		
		
		// 使用离线对象查询（就是和session没有关系）
		DetachedCriteria detachedCriteria = getDeDetachedCriteria(book);
		Criteria criteria = detachedCriteria.getExecutableCriteria(session);
		criteria.setFirstResult(begin);
		criteria.setMaxResults(pageSize);
		List<Book> list = criteria.list();
		
		return list;
	}
	
	// 创建离线查询对象
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
