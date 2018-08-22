package be.sdlg.webapps.dao;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomDao<T, ID extends Serializable>  {
	
		@Autowired
		private SessionFactory sessionFactory;

		protected Class<T> persistentClass;
		
		@SuppressWarnings("unchecked")
		public CustomDao() {
			this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		}
		public Session getCurrentSession() {
			return sessionFactory.getCurrentSession();
		}
		@SuppressWarnings("unchecked")
		public T get(ID id) {
			  return	 (T) getCurrentSession().get(persistentClass, id);
		}
		@SuppressWarnings("unchecked")
		public Set<T> findAll() {
			Criteria criteria = getCurrentSession().createCriteria(persistentClass);
			Set<T> result = new HashSet<T>(0); 
			List<T> l = criteria.list();
			for (T t:l) result.add(t);
			return (Set<T>) result;
		}

		public void save(T entity) {
			getCurrentSession().persist(entity);
		}
		public void delete(T entity) {
			getCurrentSession().delete(entity);
			
		}

}
