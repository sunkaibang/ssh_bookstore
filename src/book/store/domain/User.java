package book.store.domain;

import java.util.HashSet;
import java.util.Set;

public class User {

	private Integer uid;
	private String username;
	private String password;
	private String headPicture;
	private String birthday;
	private String email;
	private boolean state;

	private Set<Order> orders = new HashSet<Order>();

	public User() {
		super();
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHeadPicture() {
		return headPicture;
	}

	public void setHeadPicture(String headPicture) {
		this.headPicture = headPicture;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		if (birthday == null) {
			this.birthday = birthday;
		} else if (!birthday.contains("-")) {
			this.birthday = birthday.replaceAll("(\\d{4})\\D+(\\d+)\\D+(\\d+)",
					"$1-$2-$3");
		} else {
			this.birthday = birthday;
		}

	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

}
