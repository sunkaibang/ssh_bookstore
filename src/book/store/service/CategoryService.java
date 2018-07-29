package book.store.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import book.store.dao.CategoryDao;
import book.store.domain.Category;

@Transactional
public class CategoryService {
	private CategoryDao categoryDao;
	
	//springע��categoryDao
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	// ��ѯ�����������
	public List<Category> findAll() {
		return categoryDao.findAll();
	}
	
	// ����������
	public void addCategory(Category category) {
		categoryDao.addCategory(category);
	}
	
	// ɾ���������
	public void delCategory(Category category) {
		categoryDao.delCategory(category);
	}

	// �޸���������һ��,׼����Ϣ��ҳ��
	public Category modCategoryAfter(Category category) {
		return categoryDao.modCategoryAfter(category);
	}
	
	// ��ʼ�޸��������
	public void modCategory(Category category) {
		categoryDao.modCategory(category);
	}
	
}
