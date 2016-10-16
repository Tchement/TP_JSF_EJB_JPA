package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Agent;

@Stateful
public class AgentDaoImpl implements IAgentDao {

	/**
	 * Connexion à la base de données simplifiée avec les EJB
	 */
	@PersistenceContext(unitName = "PU_TP_JSF_EJB_JPA")
	EntityManager em;

	/**
	 * Recherche de l'agent pour connexion
	 */
	@Override
	public List<Agent> isExist(String login, String password) {

		String req = "SELECT a FROM Agent a WHERE a.login=?1 and a.password=?2";

		Query query = em.createQuery(req);
		query.setParameter(1, login);
		query.setParameter(2, password);

		@SuppressWarnings("unchecked")
		List<Agent> listeAgent = query.getResultList();

		return listeAgent;
	}

}
