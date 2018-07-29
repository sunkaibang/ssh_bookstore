package book.store.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import book.store.dao.CategoryDao;
import book.store.domain.Category;

@Transactional
public class CategoryService {
	private CategoryDao categoryDao;
	
	//spring注入categoryDao
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	// 查询所有书的种类
	public List<Category> findAll() {
		return categoryDao.findAll();
	}
	
	// 添加书的种类
	public void addCategory(Category category) {
		categoryDao.addCategory(category);
	}
	
	// 删除书的种类
	public void delCategory(Category category) {
		categoryDao.delCategory(category);
	}

	// 修改书的种类第一步,准备信息到页面
	public Category modCategoryAfter(Category category) {
		return categoryDao.modCategoryAfter(category);
	}
	
	// 开始修改书的种类
	public void modCategory(Category category) {
		categoryDao.modCategory(category);
	}
	
}
