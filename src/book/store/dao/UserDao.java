package book.store.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import book.store.domain.User;

public class UserDao {
	
	private HibernateTemplate hibernateTemplate;

	public void register(User user) {
		hibernateTemplate.save(user);
	}
	
	public User findUserByUserName(User user) {
		List<User> userList = (List<User>) hibernateTemplate.find("from User where username=?", user.getUsername());
		if (userList.size() > 0)
			return userList.get(0);
		else 
			return null;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void edit(User user) {
		hibernateTemplate.update(user);
	}

	public User findUserByUid(String uid) {
		
		List<User> userList = (List<User>) hibernateTemplate.find("from User where uid=?", Integer.parseInt(uid));
		return userList.get(0);
	}
	
	
}
