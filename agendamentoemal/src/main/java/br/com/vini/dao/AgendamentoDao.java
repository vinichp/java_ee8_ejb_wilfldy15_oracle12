package br.com.vini.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.vini.entitiy.DbInfo;

@Stateless
public class AgendamentoDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<DbInfo> listarAgendamentos(){

		return entityManager.createQuery("select a from DbInfo a", DbInfo.class).getResultList();
	}
	
	
	public void salvarAgendamento(DbInfo dbInfo) {
		entityManager.persist(dbInfo);
	}
}
