package mz.humansolutions.models.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import mz.humansolutions.models.Distrito;
import mz.humansolutions.models.dao.DistritoDao;
import mz.humansolutions.utils.JPAUtil;

public class DistritoJpaDao implements DistritoDao {

	private EntityManager entityManager = (EntityManager) new JPAUtil().getEntityManager();

	@Override
	public void create(Distrito distrito) {
		entityManager.getTransaction().begin();
		entityManager.persist(distrito);
		entityManager.getTransaction().commit();
	}

	@Override
	public Distrito find(Long id, String nome) {
		entityManager.getTransaction().begin();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Distrito> query = criteriaBuilder.createQuery(Distrito.class);

		Root<Distrito> root = query.from(Distrito.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		Path<Long> idPath = root.<Long>get("id");
		Path<String> nomePath = root.<String>get("nome");

		if (id != null) {
			Predicate predicate = criteriaBuilder.equal(idPath, id);
			predicates.add(predicate);
		}

		if (nome != null) {
			if (!nome.isEmpty()) {
				Predicate predicate = criteriaBuilder.like(nomePath, "%" + nome + "%");
				predicates.add(predicate);
			}
		}

		query.where(predicates.toArray(new Predicate[predicates.size()]));

		TypedQuery<Distrito> typedQuery = entityManager.createQuery(query);
		entityManager.getTransaction().commit();
		List<Distrito> distritos = typedQuery.getResultList();
		if (distritos != null) {
			return distritos.get(0);
		}
		return null;
	}

	@Override
	public void update(Distrito distrito) {
		entityManager.getTransaction().begin();
		entityManager.merge(distrito);
		entityManager.getTransaction().commit();
		
	}

	@Override
	public List<Distrito> find(Boolean active) {
		entityManager.getTransaction().begin();
		TypedQuery<Distrito> query = entityManager
				.createQuery("select distrito from Distrito distrito" + " where distrito.active = :active", Distrito.class);
		query.setParameter("active", active);
		List<Distrito> distritos = query.getResultList();
		entityManager.getTransaction().commit();
		if (distritos.isEmpty())
			return null;
		return distritos;
	}

	@Override
	public List<Distrito> findDistritos(String nome, Boolean active) {
		entityManager.getTransaction().begin();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Distrito> query = criteriaBuilder.createQuery(Distrito.class);

		Root<Distrito> root = query.from(Distrito.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		Path<String> nomePath = root.<String>get("nome");
		Path<Boolean> activePath = root.<Boolean>get("active");
		
		if (nome != null) {
			if (!nome.isEmpty()) {
				Predicate predicate = criteriaBuilder.like(nomePath, "%" + nome + "%");
				predicates.add(predicate);
			}
		}
		
		if (active != null) {
			Predicate predicate = criteriaBuilder.equal(activePath, active);
			predicates.add(predicate);
		}
		
		query.where(predicates.toArray(new Predicate[predicates.size()]));

		TypedQuery<Distrito> typedQuery = entityManager.createQuery(query);
		entityManager.getTransaction().commit();
		
		List<Distrito> distritos = typedQuery.getResultList();
		if(distritos.isEmpty())
			return null;
		else
			return distritos;

	}

}
