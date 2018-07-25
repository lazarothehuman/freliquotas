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
import mz.humansolutions.models.Membro;
import mz.humansolutions.models.dao.MembroDao;
import mz.humansolutions.utils.JPAUtil;


public class MembroJpaDao implements MembroDao {

	private EntityManager entityManager = (EntityManager) new JPAUtil().getEntityManager();

	@Override
	public void create(Membro membro) {
		entityManager.getTransaction().begin();
		entityManager.persist(membro);
		entityManager.getTransaction().commit();
	}

	@Override
	public void update(Membro membro) {
		entityManager.getTransaction().begin();
		entityManager.merge(membro);
		entityManager.getTransaction().commit();

	}

	@Override
	public List<Membro> find(Boolean active) {
		entityManager.getTransaction().begin();
		TypedQuery<Membro> query = entityManager
				.createQuery("select membro from Membro membro" + " where membro.active = :active", Membro.class);
		query.setParameter("active", active);
		List<Membro> membros = query.getResultList();
		entityManager.getTransaction().commit();
		if (membros.isEmpty())
			return null;
		return membros;
	}

	@Override
	public List<Membro> find(String nome, String telefone, String bi, Distrito distrito, Boolean active) {
		entityManager.getTransaction().begin();

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Membro> query = criteriaBuilder.createQuery(Membro.class);

		Root<Membro> root = query.from(Membro.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		Path<String> nomePath = root.<String>get("nome");
		Path<String> telefonePath = root.<String>get("telefone");
		Path<String> biPath = root.<String>get("bi");
		Path<Distrito> distritoPath = root.<Distrito>get("distrito");
		Path<Boolean> activePath = root.<Boolean>get("active");

		if (nome != null) {
			if (!nome.isEmpty()) {
				Predicate predicate = criteriaBuilder.like(nomePath, "%" + nome + "%");
				predicates.add(predicate);
			}
		}

		if (telefone != null) {
			if (!telefone.isEmpty()) {
				Predicate predicate = criteriaBuilder.like(telefonePath, "%" + telefone + "%");
				predicates.add(predicate);
			}
		}

		if (bi != null) {
			if (!bi.isEmpty()) {
				Predicate predicate = criteriaBuilder.like(biPath, "%" + bi + "%");
				predicates.add(predicate);
			}
		}

		if (distrito != null) {
				Predicate predicate = criteriaBuilder.equal(distritoPath, distrito);
				predicates.add(predicate);
		}


		if (active != null) {
			Predicate predicate = criteriaBuilder.equal(activePath, active);
			predicates.add(predicate);
		}

		query.where(predicates.toArray(new Predicate[predicates.size()]));

		TypedQuery<Membro> typedQuery = entityManager.createQuery(query);
		entityManager.getTransaction().commit();
		List<Membro> membros =typedQuery.getResultList();
		if(membros.isEmpty())
			return null;
		else
			return membros;

	}

	@Override
	public Membro find(Long id, String nome) {
		entityManager.getTransaction().begin();

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Membro> query = criteriaBuilder.createQuery(Membro.class);

		Root<Membro> root = query.from(Membro.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		Path<String> nomePath = root.<String>get("nome");
		Path<Long> idPath = root.<Long>get("id");
		
		if(id!=null) {
			Predicate predicate  = criteriaBuilder.equal(idPath, id);
			predicates.add(predicate);
		}
		if(nome!=null) {
			if (!nome.isEmpty()) {
				Predicate predicate = criteriaBuilder.equal(nomePath, nome);
				predicates.add(predicate);
			}
		}
		query.where(predicates.toArray(new Predicate[predicates.size()]));

		TypedQuery<Membro> typedQuery = entityManager.createQuery(query);
		entityManager.getTransaction().commit();
		List<Membro> membros =typedQuery.getResultList();
		if(membros!=null) {
			if(!membros.isEmpty())
				return membros.get(0);
		}
		return null;
	}

}
