package book.store.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import book.store.domain.Book;
import book.store.service.BookService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BookAction extends ActionSupport implements ModelDriven<Book>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BookService bookService;
	private Book book = new Book();
	private List<Book> bookList;
	
	// ��������������
	public String findByCategory() {
		String cid = ServletActionContext.getRequest().getParameter("cid");
		bookList = bookService.findByCategory(cid);
		return "findByCategorySuccess";
	}
	
	// ��id������
	public String findBook() {
		book = bookService.findByBid(book.getBid());
		return "findByBidSuccess";
	}
	
	// springע��bookService
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	// ģ��������װ������
	public Book getModel() {
		return book;
	}

	// ��װBook���ݵ�ֵջ
	public List<Book> getBookList() {
		return bookList;
	}
	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}

	// ��װ��ֵջ
	public Book getBook() {
		return book;
	}
	
}
