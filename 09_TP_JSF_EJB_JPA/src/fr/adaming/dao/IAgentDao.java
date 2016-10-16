package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Agent;

/**
 * Interface disponible uniquement à partir du serveur
 */
@Local
public interface IAgentDao {

	public List<Agent> isExist(String login, String password);

}
