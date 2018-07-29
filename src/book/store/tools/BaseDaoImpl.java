package book.store.tools;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class BaseDaoImpl<T> extends HibernateDaoSupport {
	
	private Class<T> pClass;

	public BaseDaoImpl() {
		//得到当前类的class
		Class clazz=this.getClass();
		
		//得到运行类的父类的参数化类型，比如：BaseDaoImpl<Customer>
		Type Type= clazz.getGenericSuperclass();
		//把Type转成常用的子接口
		ParameterizedType pType=(ParameterizedType) Type;
		
		//得到实际类型参数，比如customer
		Type []types=pType.getActualTypeArguments();
		//type接口的实现类就是Class
		pClass=(Class) types[0];
		
//		this.pClass =(Class)((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	//添加
	public void add(T t) {
		this.getHibernateTemplate().save(t);
		
	}

	//修改
	public void update(T t) {
		this.getHibernateTemplate().update(t);
		
	}

	//删除
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
		
	}

	@SuppressWarnings("all")
	//按id查询
	public T findOne(int id) {
		return (T) this.getHibernateTemplate().get(pClass, id);
	}

	@SuppressWarnings("all")
	//查询所有
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find("from "+pClass.getSimpleName());
	}

}
