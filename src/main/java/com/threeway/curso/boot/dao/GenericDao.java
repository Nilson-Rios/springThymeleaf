package com.threeway.curso.boot.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class GenericDao<T> {

	// nessa classe faço um grande aproveitamento de dados, utilizo o T para chamar
	// os métodos (uma classe genérica para trazer as implementações sem ter que
	// colocar em todas as classes dao) colocando dentro do diamante <> transformo a
	// classe em abstrata/generica utilizo, no caso para colocar codigos que
	// funcionam para todos as classes dao
	// nessa classe vou definir quais as classes vou referenciar para utilizar os
	// métodos aqui definidos. Posso reaproveitar os tipos que vou utilizar no <T>
	// diamante, a variável vai ser dinâmica, dependederá da classe que a executa,
	// por exemplo funcionário, departamento ou cargo.

	@SuppressWarnings("unchecked")
	private final Class<T> entityClass = (Class<T>) ((ParameterizedType) (getClass()).getGenericSuperclass())
			.getActualTypeArguments()[0];

	@PersistenceContext
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void save(T entity) {
		entityManager.merge(entity);
	}
	
	public void update(T entity) {
		entityManager.merge(entity);
	}
	
	public void delete(Long id) {
		entityManager.remove(entityManager.getReference(entityClass, id));
	}
	
	public T findById(Long id) {
		return entityManager.find(entityClass, id);
	
	}
	
	public List<T> findAll(){
		return entityManager.createQuery("from "+ entityClass.getSimpleName(), entityClass).getResultList();
	}


}
