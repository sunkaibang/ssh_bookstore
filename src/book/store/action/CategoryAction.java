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
	
	// ��ѯ���з���
	public String findAll() {
		categoryList = categoryService.findAll();
		return "findAllSuccess";
	}
	
	
	
	
	
	
	//springע��categoryService
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	// ģ������
	public Category getModel() {
		return category;
	}

	// ��װList���ϵ�ֵջ��
	public List<Category> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}
	
	
}
