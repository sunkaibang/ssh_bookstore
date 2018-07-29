package book.store.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import book.store.domain.Category;

public class CategoryDao {
	private HibernateTemplate hibernateTemplate;
	
	//spring ע��hibernateTemplate
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	// ��ѯ�����������
	public List<Category> findAll() {
		return (List<Category>) hibernateTemplate.find("from Category", null);
	}
	
	// ����������
	public void addCategory(Category category) {
		hibernateTemplate.save(category);
	}
	
	// ɾ���������
	public void delCategory(Category category) {
		hibernateTemplate.delete(category);
	}
	
	// �޸���������һ��,׼����Ϣ��ҳ��
	public Category modCategoryAfter(Category category) {
		List<Category> categoryList = (List<Category>) hibernateTemplate.find("from Category where cid=?", category.getCid());
		return categoryList.get(0);
	}
	
	// ��ʼ�޸��������
	public void modCategory(Category category) {
		hibernateTemplate.update(category);
	}
}
