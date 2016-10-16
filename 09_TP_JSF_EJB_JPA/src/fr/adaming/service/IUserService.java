package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Agent;
import fr.adaming.model.User;

@Local
public interface IUserService {

	public List<User> getAllUsersService();

	public List<User> getUsersByIdAgentService(Agent agent);

	public User getUserByIdService(Long id_user);

	public int deleteUserByIdService(Long id_user, Agent agent);

	public void addUserService(User u);

	public int updateUserService(User u, Agent Agent);

}
