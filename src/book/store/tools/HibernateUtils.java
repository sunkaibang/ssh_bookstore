package book.store.tools;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * 
 * ��hibernate�����У�����һ����Ŀһ�㴴��һ��sessionFactory����
 * ʵ�ַ���
 */
public class HibernateUtils {
	private static Configuration cfg=null;
	private static SessionFactory sessionFactory=null;
	static{
		//���غ��������ļ�
		cfg=new Configuration();
		cfg.configure();
		sessionFactory=cfg.buildSessionFactory();
	}
	
	//�ṩ��������sessionFactory
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	//�ṩ�����뱾���̰߳󶨵�session�ķ���
	public static Session getSessionObject(){
		return sessionFactory.getCurrentSession();
	}
	
}
