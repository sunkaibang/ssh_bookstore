package book.store.tools;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class BaseDaoImpl<T> extends HibernateDaoSupport {
	
	private Class<T> pClass;

	public BaseDaoImpl() {
		//�õ���ǰ���class
		Class clazz=this.getClass();
		
		//�õ�������ĸ���Ĳ��������ͣ����磺BaseDaoImpl<Customer>
		Type Type= clazz.getGenericSuperclass();
		//��Typeת�ɳ��õ��ӽӿ�
		ParameterizedType pType=(ParameterizedType) Type;
		
		//�õ�ʵ�����Ͳ���������customer
		Type []types=pType.getActualTypeArguments();
		//type�ӿڵ�ʵ�������Class
		pClass=(Class) types[0];
		
//		this.pClass =(Class)((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	//���
	public void add(T t) {
		this.getHibernateTemplate().save(t);
		
	}

	//�޸�
	public void update(T t) {
		this.getHibernateTemplate().update(t);
		
	}

	//ɾ��
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
		
	}

	@SuppressWarnings("all")
	//��id��ѯ
	public T findOne(int id) {
		return (T) this.getHibernateTemplate().get(pClass, id);
	}

	@SuppressWarnings("all")
	//��ѯ����
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find("from "+pClass.getSimpleName());
	}

}
