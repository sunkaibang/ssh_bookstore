package book.store.service;

import org.springframework.transaction.annotation.Transactional;

import book.store.dao.UserDao;
import book.store.domain.User;
import book.store.tools.EmailUtils;

@Transactional
public class UserService {
	
	private UserDao userDao;
	
	//spring×¢ÈëUserDao
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	//×¢²áUser
	public void register(User user) {
		userDao.register(user);
		
		//·¢ËÍ¼¤»îÓÊ¼þ
		EmailUtils emailUtils = new EmailUtils();
		emailUtils.sendEamil(user.getEmail(), new String[]{user.getUid().toString()});
	}
	
	//²éÑ¯User
	public User findUserByUserName(User user) {
		return userDao.findUserByUserName(user);
	}
	
	//µÇÂ½
	public User login(User user) {
		User u = userDao.findUserByUserName(user);
		if (u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())) {
			return u;
		}
		return null;
	}
	
	//ÐÞ¸Ä
	public void edit(User user) {
		
		userDao.edit(user);
	}
	
	//¼¤»îÕËºÅ
	public void active(String uid) {
		
		User user = userDao.findUserByUid(uid);
		user.setState(true);
		userDao.edit(user);
	}

}
