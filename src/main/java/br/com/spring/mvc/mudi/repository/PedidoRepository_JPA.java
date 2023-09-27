package br.com.spring.mvc.mudi.repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.spring.mvc.mudi.model.Pedido;

@Repository
public class PedidoRepository_JPA {
	
	/*
	 * Nesse modelo não esta usando o springDATA
	 * somente usa o JPA
	 */
	
	@PersistenceContext
	private EntityManager entityMananger;
	
	public List<Pedido> recuperaTodosOsPedidos(){
		Query query = entityMananger.createQuery("Select p from Pedido p", Pedido.class);
		return query.getResultList();

	}
}
