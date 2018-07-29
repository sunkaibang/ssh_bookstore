package book.store.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import book.store.domain.Cart;
import book.store.domain.User;
import book.store.service.UserService;
import book.store.tools.CommonsUtils;
import book.store.tools.PictureCompress;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private User user = new User();
	private String registerMessage; // 操作的结果显示

	private File headFile;
	private String headFileFileName;
	private String headFileContentType;

	// 注册User
	public String register() {
		User u = userService.findUserByUserName(user);
		if (u == null) {
			user.setState(false);
			user.setHeadPicture("user_image/no-img.jpg");
			userService.register(user);
			registerMessage = "注册成功，请登陆";
			return "registerSuccess";
		} else {
			user = u;
			registerMessage = "用户名已存在";
			return "registerFail";
		}
	}

	// 登陆
	public String login() {
		User u = userService.login(user);
		if (u != null) {
			if (!u.isState()) {
				registerMessage = "账号没有激活";
				return "loginFail";
			} else {
				registerMessage = "登陆成功";
				// 登录成功分配一个购物车，范围是session
				ServletActionContext.getRequest().getSession()
						.setAttribute("cart", new Cart());
				ServletActionContext.getRequest().getSession()
						.setAttribute("session_user", u);
				return "loginSuccess";
			}
		} else {
			registerMessage = "账号错误";
			return "loginFail";
		}
	}

	// 退出
	public String exit() {
		ServletActionContext.getRequest().getSession().invalidate();
		return "exit";
	}

	// 修改
	public String edit() {
		User oldUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("session_user");
		if (headFile != null) {
			headFileFileName = modifyFileName(headFileFileName);
			long fileSize = headFile.length();
			if (fileSize > 2000000) {
				registerMessage = "文件大于2M了";
				return "editSuccess";
			}
			if (!headFileFileName.endsWith("jpg")
					&& !headFileFileName.endsWith("png")) {
				registerMessage = "要是图片";
				return "editSuccess";
			}

			String path = ServletActionContext.getRequest().getSession()
					.getServletContext().getRealPath("")
					+ "/user_image/" + headFileFileName;

			try {
				// 调整图像的大小
				File newFile = new File(path);
				FileUtils.copyFile(headFile, newFile);
				PictureCompress pc = new PictureCompress(newFile, path);
				pc.resize(100, 100);

				// 删除之前的头像文件
				File oldFile = new File(ServletActionContext.getRequest()
						.getSession().getServletContext().getRealPath("")
						+ "/" + oldUser.getHeadPicture());
				System.out.println(oldFile.getName());
				if (oldFile.exists()
						&& !oldFile.getName().endsWith("no-img.jpg")) {
					oldFile.delete();
				}

				oldUser.setHeadPicture("user_image/" + headFileFileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		user.setUid(oldUser.getUid());
		user.setHeadPicture(oldUser.getHeadPicture());
		user.setState(oldUser.isState());
		ServletActionContext.getRequest().getSession()
				.setAttribute("session_user", user);
		userService.edit(user);
		registerMessage = "修改成功";
		return "editSuccess";
	}

	// 激活账号
	public String active() {

		String uid = ServletActionContext.getRequest().getParameter("uid");
		userService.active(uid);
		return "activeSuccess";
	}

	// 重新修改上传图片的文件名
	public String modifyFileName(String fileName) {
		int suffixIndex = fileName.lastIndexOf(".");
		return CommonsUtils.uuid() + fileName.substring(suffixIndex);
	}

	// spring注入UserService
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// 向值栈中放入是否注册成功的信息
	public String getRegisterMessage() {
		return registerMessage;
	}

	// 模型驱动封装User数据
	public User getModel() {
		return user;
	}

	public User getUser() {
		return user;
	}

	public File getHeadFile() {
		return headFile;
	}

	public void setHeadFile(File headFile) {
		this.headFile = headFile;
	}

	public String getHeadFileFileName() {
		return headFileFileName;
	}

	public void setHeadFileFileName(String headFileFileName) {
		this.headFileFileName = headFileFileName;
	}

	public String getHeadFileContentType() {
		return headFileContentType;
	}

	public void setHeadFileContentType(String headFileContentType) {
		this.headFileContentType = headFileContentType;
	}

}
