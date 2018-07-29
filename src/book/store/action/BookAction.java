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
	
	// 按书的种类查找书
	public String findByCategory() {
		String cid = ServletActionContext.getRequest().getParameter("cid");
		bookList = bookService.findByCategory(cid);
		return "findByCategorySuccess";
	}
	
	// 按id查找书
	public String findBook() {
		book = bookService.findByBid(book.getBid());
		return "findByBidSuccess";
	}
	
	// spring注入bookService
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	// 模型驱动封装表单数据
	public Book getModel() {
		return book;
	}

	// 封装Book数据到值栈
	public List<Book> getBookList() {
		return bookList;
	}
	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}

	// 封装到值栈
	public Book getBook() {
		return book;
	}
	
}
