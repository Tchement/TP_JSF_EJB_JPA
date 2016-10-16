package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Agent;
import fr.adaming.model.User;

/**
 * Interface disponible uniquement à partir du serveur
 */
@Local
public interface IUserDao {

	public List<User> getAllUsers();

	public List<User> getUsersByIdAgent(Agent agent);

	public User getUserById(Long id_user);

	public void addUser(User u);

	public int updateUser(User u, Agent agent);

	public int deleteUserById(Long id_user, Agent agent);

}
