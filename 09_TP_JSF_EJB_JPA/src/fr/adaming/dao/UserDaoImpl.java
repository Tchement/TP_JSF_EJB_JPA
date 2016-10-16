package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Agent;
import fr.adaming.model.User;

@Stateful
public class UserDaoImpl implements IUserDao {

	/**
	 * Connexion à la base de données simplifiée avec les EJB
	 */
	@PersistenceContext(unitName = "PU_TP_JSF_EJB_JPA")
	EntityManager em;

	/**
	 * Récupération de tous les utilisateurs
	 */
	@Override
	public List<User> getAllUsers() {

		String req = "select u from User u";

		Query query = em.createQuery(req);

		@SuppressWarnings("unchecked")
		List<User> listeUsers = query.getResultList();

		for (User u : listeUsers) {
			System.out.println(u);
		}
		return listeUsers;
	}

	/**
	 * Récupération de tous les utilisateurs d'un agent par son id
	 */
	@Override
	public List<User> getUsersByIdAgent(Agent agent) {

		String req = "select u from User u where u.agent.id_agent=?1";

		Query query = em.createQuery(req);
		query.setParameter(1, agent.getId_agent());

		@SuppressWarnings("unchecked")
		List<User> listeUsers = query.getResultList();

		return listeUsers;
	}

	/**
	 * Récupération un utilisateur par son id
	 */
	@Override
	public User getUserById(Long id_user) {

		String req = "SELECT u FROM User u WHERE u.id_user=?1";

		Query query = em.createQuery(req);

		query.setParameter(1, id_user);

		User user = (User) query.getSingleResult();

		return user;
	}

	/**
	 * Ajouter un utilisateur
	 */
	@Override
	public void addUser(User u) {

		em.merge(u);

	}

	/**
	 * Modifier un utilisateur qui appartient à l'agent
	 */
	@Override
	public int updateUser(User u, Agent agent) {

		try {
			String req = "select u from User u where u.id_user=?1 and u.agent.id_agent=?2";

			Query query = em.createQuery(req);
			query.setParameter(1, u.getId_user());
			query.setParameter(2, agent.getId_agent());

			User userToModify = (User) query.getSingleResult();

			userToModify.setNom_user(u.getNom_user());
			userToModify.setPrenom_user(u.getPrenom_user());
			userToModify.setDateNaissance(u.getDateNaissance());

			em.merge(u);

			return 1;
		} catch (Exception e) {
		}
		return 0;
	}

	/**
	 * Supprimer un utilisateur qui appartient à l'agent
	 */
	@Override
	public int deleteUserById(Long id_user, Agent agent) {

		try {
			String req = "select u from User u where u.id_user=?1 and u.agent.id_agent=?2";

			Query query = em.createQuery(req);
			query.setParameter(1, id_user);
			query.setParameter(2, agent.getId_agent());

			User user = (User) query.getSingleResult();

			em.remove(user);

			return 1;
		} catch (Exception e) {
		}
		return 0;

	}

}
