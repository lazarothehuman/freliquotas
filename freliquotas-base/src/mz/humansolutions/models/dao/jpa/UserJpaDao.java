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
import mz.humansolutions.models.Profile;
import mz.humansolutions.models.User;
import mz.humansolutions.models.dao.UserDao;
import mz.humansolutions.utils.JPAUtil;

public class UserJpaDao implements UserDao {

	private EntityManager entityManager = (EntityManager) new JPAUtil().getEntityManager();

	@Override
	public void create(User user) {
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
	}

	@Override
	public User find(String username) {
		entityManager.getTransaction().begin();
		TypedQuery<User> query = entityManager
				.createQuery("select user from User user " + " where user.username = :username", User.class);
		query.setParameter("username", username);
		List<User> users = query.getResultList();
		entityManager.getTransaction().commit();
		if (users.isEmpty())
			return null;
		User user = users.get(0);
		return user;
	}

	@Override
	public List<User> find(String username, String name, Profile profile, Boolean active, Distrito distrito) {
		entityManager.getTransaction().begin();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);

		Root<User> root = query.from(User.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		Path<String> nomePath = root.<String>get("name");
		Path<String> usernamePath = root.<String>get("username");
		Path<Profile> profilePath = root.<Profile>get("profile");
		Path<Distrito> distritoPath = root.<Distrito>get("distrito");
		Path<Boolean> activePath = root.<Boolean>get("active");

		if (name != null) {
			if (!name.isEmpty()) {
				Predicate predicate = criteriaBuilder.like(nomePath, "%" + name + "%");
				predicates.add(predicate);
			}
		}

		if (username != null) {
			if (!username.isEmpty()) {
				Predicate predicate = criteriaBuilder.like(usernamePath, "%" + username + "%");
				predicates.add(predicate);
			}
		}

		if (profile != null) {
			Predicate predicate = criteriaBuilder.equal(profilePath, profile);
			predicates.add(predicate);
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

		TypedQuery<User> typedQuery = entityManager.createQuery(query);
		entityManager.getTransaction().commit();
		return typedQuery.getResultList();
	}

	@Override
	public List<User> findAll(Boolean active) {
		entityManager.getTransaction().begin();
		TypedQuery<User> query = entityManager
				.createQuery("select user from User user " + " where user.active = :active", User.class);
		query.setParameter("active", active);
		List<User> users = query.getResultList();
		entityManager.getTransaction().commit();
		if (users.isEmpty())
			return null;
		return users;
	}

	@Override
	public void update(User user) {
		entityManager.getTransaction().begin();
		entityManager.merge(user);
		entityManager.getTransaction().commit();
		
	}

	@Override
	public User find(Long id) {
		entityManager.getTransaction().begin();
		TypedQuery<User> query = entityManager
				.createQuery("select user from User user " + " where user.id = :id", User.class);
		query.setParameter("id", id);
		List<User> users = query.getResultList();
		entityManager.getTransaction().commit();
		if (users.isEmpty())
			return null;
		User user = users.get(0);
		return user;
	}
}
