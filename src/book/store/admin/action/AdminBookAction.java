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
	
	
	
	// �����ѯ���߼���ѯ
	public String categoryFind() {
		// �ж��ύ�ķ�ʽ���Ͼ�POST��GET��ʽ��������ǲ�һ����
		String flag = ServletActionContext.getRequest().getMethod();
		if (book == null) {
			book = new Book();
		}
		book.setCategory(category);
		pageBean = bookService.findSuper(pageBean, book, flag);
		
		return "categoryFindSuccess";
	}
	
	
	// �����ѯ��һ��,����������
	public String categoryFindFirst() {
		categoryLists = categoryService.findAll();
		return "categoryFindFirst";
	}
	
	// �޸�ͼ��
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
		msg = "�޸ĳɹ�";
		return "modifyBookSuccess";
	}
	
	// ɾ��ͼ��
	public String deleteBook() {
		bookService.deleteBook(book);
		return "deleteSuccess";
	}
	
	// ��ѯһ��ͼ��
	public String findByBid() {
		categoryLists = categoryService.findAll();
		book = bookService.findByBid(book.getBid());
		return "findByBidSuccess";
	}

	// ��������ͼ��
	public String findAll() {
		listBooks = bookService.findAll();
		return "findAllSuccess";
	}

	// ���ͼ��
	public String addBook() {
		
		book.setCategory(category);
		
		if (bookImageFileName == null) {
			msg = "���ϴ�ͼƬ";
			return "addBookFail";
		}
		
		if (!bookImageFileName.endsWith(".jpg") && !bookImageFileName.endsWith(".bmp")) {
			msg = "���ϴ�ͼƬ";
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
			msg = "��ӳɹ�";
			return "addBookSuccess";
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return NONE;
	}

	// �����޸��ϴ�ͼƬ���ļ���
	public String modifyFileName(String fileName) {
		int suffixIndex = fileName.lastIndexOf(".");
		return CommonsUtils.uuid() + fileName.substring(suffixIndex);
	}

	// ���ͼ��֮ǰ���������������Щ
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

	// ��װBookList��ֵջ
	public List<Book> getListBooks() {
		return listBooks;
	}
	public void setListBooks(List<Book> listBooks) {
		this.listBooks = listBooks;
	}

	// spring ע��BookService
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}


	// ��װCategoryLists��ֵջ
	public List<Category> getCategoryLists() {
		return categoryLists;
	}
	public void setCategoryLists(List<Category> categoryLists) {
		this.categoryLists = categoryLists;
	}

	// springע��categoryService
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	// ��װ������Ϣ��ֵջ
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
	
	
	// �ϴ��ļ�
	public String getBookImageFileName() {
		return bookImageFileName;
	}
	public void setBookImageFileName(String bookImageFileName) {
		this.bookImageFileName = bookImageFileName;
	}
	
	// ���ʽ��װCategory��
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

	// ��װpageBean��ֵջ
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	
	
	
}
