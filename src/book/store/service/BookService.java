package book.store.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import book.store.dao.BookDao;
import book.store.domain.Book;
import book.store.domain.PageBean;

@Transactional
public class BookService {
	private BookDao bookDao;
	// 分页时，每页的大小
	private Integer pageSize = 2;
	
	// spring 注入bookDao
	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	// 按书的种类查找书
	public List<Book> findByCategory(String cid) {
		return bookDao.findByCategory(cid);
	}
	
	
	// 按书的id查找书
	public Book findByBid(Integer bid) {
		return bookDao.findByBid(bid);
	}
	
	// 查找所有的图书
	public List<Book> findAll() {
		return bookDao.findAll();
	}
	
	// 添加图书
	public void addBook(Book book) {
		bookDao.addBook(book);
	}
	
	// 删除图书
	public void deleteBook(Book book) {
		bookDao.delete(book);
	}
	
	// 修改图片
	public void modifyBook(Book book) {
		bookDao.modifyBook(book);
	}
	
	// 分类查询，高级查询
	public PageBean findSuper(PageBean pageBean, Book book, String flag) {
		if (pageBean == null) {
			pageBean = new PageBean();
		}
		Integer currentPage = pageBean.getCurrentPage();
		if (currentPage == null) {
			pageBean.setCurrentPage(1);
		} 
		pageBean.setPageSize(pageSize);
		
		if (flag.equals("POST")){
			pageBean.setUrl(getUrl(book));
		} else {
			book = encoding(book);
			pageBean.setUrl(getUrl(book));
		}
		
		int totalCount = bookDao.findCount(book);
		pageBean.setTotalCount(totalCount);
		List<Book> bookList = bookDao.findPage(pageBean.getBegin(), pageBean.getPageSize(), book);
		pageBean.setList(bookList);
		
		return pageBean;
	}
	
	// 得到选择的条件
	private String getUrl(Book book) {
		StringBuilder url = new StringBuilder();
		if (book.getBname() != null && ! book.getBname().isEmpty()) {
			url.append("book.bname=" + book.getBname() + "&");
		}
		if (book.getBauther() != null && ! book.getBauther().isEmpty()) {
			url.append("book.bauther=" + book.getBauther() + "&");
		}
		if (book.getCategory() != null && book.getCategory().getCid() != -1) {
			url.append("category.cid=" + book.getCategory().getCid() + "&");
		}
		return url.toString();
	}
	
	// 处理超链接传递过来的中文乱码问题
	private Book encoding(Book book) {
		try{
			if (book.getBname() != null && ! book.getBname().isEmpty()) {
				byte[] bname = book.getBname().getBytes("iso-8859-1");
				book.setBname(new String(bname, "utf-8"));
			}
			if (book.getBauther() != null && ! book.getBauther().isEmpty()) {
				byte[] bauther = book.getBauther().getBytes("iso-8859-1");
				book.setBauther(new String(bauther, "utf-8"));
			}
		} catch (Exception e){}
		
		return book;
	}
}
