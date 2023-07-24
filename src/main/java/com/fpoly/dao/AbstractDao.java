package com.fpoly.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

import com.fpoly.util.JpaUtil;

public class AbstractDao<T> {
	private EntityManager entityManager;

	public T findById(Class<T> clazz, Integer id) {
		entityManager = JpaUtil.getEntitymanager();
		try {
			return entityManager.find(clazz, id);
		} finally {
			entityManager.close();
		}
	}

	public List<T> findAll(Class<T> clazz, boolean existIsActive) {
		entityManager = JpaUtil.getEntitymanager();
		try {
			String entityName = clazz.getSimpleName();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT o FROM ").append(entityName).append(" o");
			if (existIsActive) {
				sql.append(" WHERE isActive = 1");
			}
			TypedQuery<T> query = entityManager.createQuery(sql.toString(), clazz);
			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}

	public List<T> findAll(Class<T> clazz, boolean existIsActive, int pageNumber, int pageSize) {
		entityManager = JpaUtil.getEntitymanager();
		try {
			String entityName = clazz.getSimpleName();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT o FROM ").append(entityName).append(" o");
			if (existIsActive) {
				sql.append(" WHERE isActive = 1");
			}
			TypedQuery<T> query = entityManager.createQuery(sql.toString(), clazz);
			query.setFirstResult((pageNumber - 1) * pageSize);
			query.setMaxResults(pageSize);
			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}

	public T findOne(Class<T> clazz, String sql, Object... params) {
		entityManager = JpaUtil.getEntitymanager();
		try {
			TypedQuery<T> query = entityManager.createQuery(sql, clazz);
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
			List<T> result = query.getResultList();
			return result.isEmpty() ? null : result.get(0);
		} finally {
			entityManager.close();
		}
	}

	public List<T> findMany(Class<T> clazz, String sql, Object... params) {
		entityManager = JpaUtil.getEntitymanager();
		try {
			TypedQuery<T> query = entityManager.createQuery(sql, clazz);
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}

	public List<Object[]> findManyByNativeQuery(String sql, Object... params) {
		entityManager = JpaUtil.getEntitymanager();
		try {
			javax.persistence.Query query = entityManager.createNativeQuery(sql);
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}

	public T create(T entity) {
		entityManager = JpaUtil.getEntitymanager();
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new RuntimeException(e);
		} finally {
			entityManager.close();
		}
	}

	public T update(T entity) {
		entityManager = JpaUtil.getEntitymanager();
		try {
			entityManager.getTransaction().begin();
			entity = entityManager.merge(entity);
			entityManager.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new RuntimeException(e);
		} finally {
			entityManager.close();
		}
	}

	public T delete(T entity) {
		entityManager = JpaUtil.getEntitymanager();
		try {
			entityManager.getTransaction().begin();
			entity = entityManager.merge(entity);
			entityManager.remove(entity);
			entityManager.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new RuntimeException(e);
		} finally {
			entityManager.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> callStored(String namedStored, Map<String, Object> params) {
		entityManager = JpaUtil.getEntitymanager();
		try {
			StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery(namedStored);
			params.forEach((key, value) -> query.setParameter(key, value));
			return (List<T>) query.getResultList();
		} finally {
			// Close the EntityManager after completing the stored procedure call
			entityManager.close();
		}
	}
}
