package book.store.admin.action;

import java.util.List;

import book.store.domain.Category;
import book.store.service.CategoryService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CategoryService categoryService;
	private Category category = new Category();
	private List<Category> categoryList;
	
	// spring ע��CategoryService
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	// ��װcategoryList��ֵջ
	public List<Category> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}
	
	// ���������������
	public String findAll() {
		categoryList = categoryService.findAll();
		return "findAllSuccess";
	}

	// ����������
	public String addCategory() {
		categoryService.addCategory(category);
		return "addCategorySuccess";
	}
	
	// ɾ���������
	public String delCategory() {
		categoryService.delCategory(category);
		return "delCategorySuccess";
	}
	
	// �޸���������һ��,׼����Ϣ��ҳ��
	public String modCategoryAfter() {
		category = categoryService.modCategoryAfter(category);
		return "modCategoryAfter";
	}
	
	// ��ʼ�޸��������
	public String modCategory() {
		categoryService.modCategory(category);
		return "modCategorySuccess";
	}

	// ģ�ͷ�װCategory����
	public Category getModel() {
		return category;
	}
	
	// ��װCategory��ֵջ
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
}
