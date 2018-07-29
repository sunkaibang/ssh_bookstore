package book.store.tools;

import java.util.List;

/**
 * 
 * ����������T,������������
 * 	 ��������ʹ�ô�д��ĸ��ʾ����һ����T
 *
 * @param <T>
 */
public interface BaseDao<T> {
	
	//���
	void add(T t);
	
	//�޸�
	void update(T t);
	
	//ɾ��
	void delete(T t);
	
	//����id��ѯ
	T findOne(int id);
	
	//��ѯ����
	List<T> findAll();
}
