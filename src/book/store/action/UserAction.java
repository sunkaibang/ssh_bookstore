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
	private String registerMessage; // �����Ľ����ʾ

	private File headFile;
	private String headFileFileName;
	private String headFileContentType;

	// ע��User
	public String register() {
		User u = userService.findUserByUserName(user);
		if (u == null) {
			user.setState(false);
			user.setHeadPicture("user_image/no-img.jpg");
			userService.register(user);
			registerMessage = "ע��ɹ������½";
			return "registerSuccess";
		} else {
			user = u;
			registerMessage = "�û����Ѵ���";
			return "registerFail";
		}
	}

	// ��½
	public String login() {
		User u = userService.login(user);
		if (u != null) {
			if (!u.isState()) {
				registerMessage = "�˺�û�м���";
				return "loginFail";
			} else {
				registerMessage = "��½�ɹ�";
				// ��¼�ɹ�����һ�����ﳵ����Χ��session
				ServletActionContext.getRequest().getSession()
						.setAttribute("cart", new Cart());
				ServletActionContext.getRequest().getSession()
						.setAttribute("session_user", u);
				return "loginSuccess";
			}
		} else {
			registerMessage = "�˺Ŵ���";
			return "loginFail";
		}
	}

	// �˳�
	public String exit() {
		ServletActionContext.getRequest().getSession().invalidate();
		return "exit";
	}

	// �޸�
	public String edit() {
		User oldUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("session_user");
		if (headFile != null) {
			headFileFileName = modifyFileName(headFileFileName);
			long fileSize = headFile.length();
			if (fileSize > 2000000) {
				registerMessage = "�ļ�����2M��";
				return "editSuccess";
			}
			if (!headFileFileName.endsWith("jpg")
					&& !headFileFileName.endsWith("png")) {
				registerMessage = "Ҫ��ͼƬ";
				return "editSuccess";
			}

			String path = ServletActionContext.getRequest().getSession()
					.getServletContext().getRealPath("")
					+ "/user_image/" + headFileFileName;

			try {
				// ����ͼ��Ĵ�С
				File newFile = new File(path);
				FileUtils.copyFile(headFile, newFile);
				PictureCompress pc = new PictureCompress(newFile, path);
				pc.resize(100, 100);

				// ɾ��֮ǰ��ͷ���ļ�
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
		registerMessage = "�޸ĳɹ�";
		return "editSuccess";
	}

	// �����˺�
	public String active() {

		String uid = ServletActionContext.getRequest().getParameter("uid");
		userService.active(uid);
		return "activeSuccess";
	}

	// �����޸��ϴ�ͼƬ���ļ���
	public String modifyFileName(String fileName) {
		int suffixIndex = fileName.lastIndexOf(".");
		return CommonsUtils.uuid() + fileName.substring(suffixIndex);
	}

	// springע��UserService
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// ��ֵջ�з����Ƿ�ע��ɹ�����Ϣ
	public String getRegisterMessage() {
		return registerMessage;
	}

	// ģ��������װUser����
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
