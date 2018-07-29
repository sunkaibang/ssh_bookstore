package book.store.admin.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import book.store.domain.Book;
import book.store.domain.Category;
import book.store.domain.PageBean;
import book.store.service.BookService;
import book.store.service.CategoryService;
import book.store.tools.CommonsUtils;
import book.store.tools.PictureCompress;

import com.opensymphony.xwork2.ActionSupport;

public class AdminBookAction extends ActionSupport {

	private BookService bookService;
	private CategoryService categoryService;
	private Book book;
	private Category category;
	private PageBean pageBean;
	private List<Book> listBooks;
	private List<Category> categoryLists;
	private String msg;
	

	private File bookImage;
	private String bookImageFileName;
	
	
	
	// 分类查询，高级查询
	public String categoryFind() {
		// 判断提交的方式，毕竟POST和GET方式处理编码是不一样的
		String flag = ServletActionContext.getRequest().getMethod();
		if (book == null) {
			book = new Book();
		}
		book.setCategory(category);
		pageBean = bookService.findSuper(pageBean, book, flag);
		
		return "categoryFindSuccess";
	}
	
	
	// 分类查询第一步,填充书的类型
	public String categoryFindFirst() {
		categoryLists = categoryService.findAll();
		return "categoryFindFirst";
	}
	
	// 修改图书
	public String modifyBook() {
		if (bookImage != null) {
			bookImageFileName = modifyFileName(bookImageFileName);
			String fileDir = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/book_image");
			String newFileName = fileDir + "/" + bookImageFileName;
			File newFile = new File(newFileName);
			try {
				FileUtils.copyFile(bookImage, newFile);
				PictureCompress pc = new PictureCompress(newFile, newFileName);
				pc.resize(150, 250);
				File oldFile = new File(ServletActionContext.getRequest()
						.getSession().getServletContext().getRealPath("")
						+ "/" + book.getBimage());
				if (oldFile != null) {
					oldFile.delete();
				}
				book.setBimage("book_image/" + bookImageFileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		book.setCategory(category);
		bookService.modifyBook(book);
		msg = "修改成功";
		return "modifyBookSuccess";
	}
	
	// 删除图书
	public String deleteBook() {
		bookService.deleteBook(book);
		return "deleteSuccess";
	}
	
	// 查询一个图书
	public String findByBid() {
		categoryLists = categoryService.findAll();
		book = bookService.findByBid(book.getBid());
		return "findByBidSuccess";
	}

	// 查找所有图书
	public String findAll() {
		listBooks = bookService.findAll();
		return "findAllSuccess";
	}

	// 添加图书
	public String addBook() {
		
		book.setCategory(category);
		
		if (bookImageFileName == null) {
			msg = "请上传图片";
			return "addBookFail";
		}
		
		if (!bookImageFileName.endsWith(".jpg") && !bookImageFileName.endsWith(".bmp")) {
			msg = "请上传图片";
			return "addBookFail";
		}
		
		String fileDir = ServletActionContext.getRequest().getSession()
				.getServletContext().getRealPath("/book_image");
		bookImageFileName = modifyFileName(bookImageFileName);
		String fileName = fileDir + "/" +bookImageFileName;
		File newFile = new File(fileName);
		try {
			FileUtils.copyFile(bookImage, newFile);
			PictureCompress pc = new PictureCompress(newFile, fileName);
			pc.resizeFix(150, 250);
			book.setBimage("book_image/" + bookImageFileName);
			
			bookService.addBook(book);
			msg = "添加成功";
			return "addBookSuccess";
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return NONE;
	}

	// 重新修改上传图片的文件名
	public String modifyFileName(String fileName) {
		int suffixIndex = fileName.lastIndexOf(".");
		return CommonsUtils.uuid() + fileName.substring(suffixIndex);
	}

	// 添加图书之前查找书的种类有哪些
	public String addBookAfter() {
		categoryLists = categoryService.findAll();
		return "addBookAfter";
	}

	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}

	// 封装BookList到值栈
	public List<Book> getListBooks() {
		return listBooks;
	}
	public void setListBooks(List<Book> listBooks) {
		this.listBooks = listBooks;
	}

	// spring 注入BookService
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}


	// 封装CategoryLists到值栈
	public List<Category> getCategoryLists() {
		return categoryLists;
	}
	public void setCategoryLists(List<Category> categoryLists) {
		this.categoryLists = categoryLists;
	}

	// spring注入categoryService
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	// 封装反馈信息到值栈
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public File getBookImage() {
		return bookImage;
	}

	public void setBookImage(File bookImage) {
		this.bookImage = bookImage;
	}
	
	
	// 上传文件
	public String getBookImageFileName() {
		return bookImageFileName;
	}
	public void setBookImageFileName(String bookImageFileName) {
		this.bookImageFileName = bookImageFileName;
	}
	
	// 表达式封装Category类
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

	// 封装pageBean到值栈
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	
	
	
}
