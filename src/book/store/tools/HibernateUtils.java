package book.store.tools;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * 
 * 在hibernate操作中，建议一个项目一般创建一个sessionFactory对象
 * 实现方法
 */
public class HibernateUtils {
	private static Configuration cfg=null;
	private static SessionFactory sessionFactory=null;
	static{
		//加载核心配置文件
		cfg=new Configuration();
		cfg.configure();
		sessionFactory=cfg.buildSessionFactory();
	}
	
	//提供方法返回sessionFactory
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	//提供返回与本地线程绑定的session的方法
	public static Session getSessionObject(){
		return sessionFactory.getCurrentSession();
	}
	
}
