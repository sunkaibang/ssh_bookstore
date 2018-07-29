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
	
	// spring 注入CategoryService
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	// 封装categoryList到值栈
	public List<Category> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}
	
	// 查找所有书的种类
	public String findAll() {
		categoryList = categoryService.findAll();
		return "findAllSuccess";
	}

	// 添加书的种类
	public String addCategory() {
		categoryService.addCategory(category);
		return "addCategorySuccess";
	}
	
	// 删除书的种类
	public String delCategory() {
		categoryService.delCategory(category);
		return "delCategorySuccess";
	}
	
	// 修改书的种类第一步,准备信息到页面
	public String modCategoryAfter() {
		category = categoryService.modCategoryAfter(category);
		return "modCategoryAfter";
	}
	
	// 开始修改书的种类
	public String modCategory() {
		categoryService.modCategory(category);
		return "modCategorySuccess";
	}

	// 模型封装Category数据
	public Category getModel() {
		return category;
	}
	
	// 封装Category到值栈
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
}
