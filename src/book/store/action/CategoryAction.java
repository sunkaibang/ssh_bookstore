package book.store.action;

import java.util.List;

import book.store.domain.Category;
import book.store.service.CategoryService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CategoryAction extends ActionSupport implements ModelDriven<Category>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CategoryService categoryService;
	private Category category = new Category();
	private List<Category> categoryList;
	
	// 查询所有分类
	public String findAll() {
		categoryList = categoryService.findAll();
		return "findAllSuccess";
	}
	
	
	
	
	
	
	//spring注入categoryService
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	// 模型驱动
	public Category getModel() {
		return category;
	}

	// 封装List集合到值栈中
	public List<Category> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}
	
	
}
