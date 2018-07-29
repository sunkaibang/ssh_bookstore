package book.store.interceptor;

import org.apache.struts2.ServletActionContext;

import book.store.domain.User;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = 9028307818118766499L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		User user = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("session_user");
		if (user != null) {
			return invocation.invoke();
		} else {
			return "goto_login";
		}
		
	}

}
