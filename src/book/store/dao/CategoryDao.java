package book.store.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import book.store.domain.Category;

public class CategoryDao {
	private HibernateTemplate hibernateTemplate;
	
	//spring 注入hibernateTemplate
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	// 查询所有书的种类
	public List<Category> findAll() {
		return (List<Category>) hibernateTemplate.find("from Category", null);
	}
	
	// 添加书的种类
	public void addCategory(Category category) {
		hibernateTemplate.save(category);
	}
	
	// 删除书的种类
	public void delCategory(Category category) {
		hibernateTemplate.delete(category);
	}
	
	// 修改书的种类第一步,准备信息到页面
	public Category modCategoryAfter(Category category) {
		List<Category> categoryList = (List<Category>) hibernateTemplate.find("from Category where cid=?", category.getCid());
		return categoryList.get(0);
	}
	
	// 开始修改书的种类
	public void modCategory(Category category) {
		hibernateTemplate.update(category);
	}
}
