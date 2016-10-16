package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IUserDao;
import fr.adaming.model.Agent;
import fr.adaming.model.User;

@Stateful
public class UserServiceImpl implements IUserService {

	@EJB
	IUserDao userDao;

	@Override
	public List<User> getAllUsersService() {
		return userDao.getAllUsers();
	}

	@Override
	public List<User> getUsersByIdAgentService(Agent agent) {
		return userDao.getUsersByIdAgent(agent);
	}

	@Override
	public User getUserByIdService(Long id_user) {
		return userDao.getUserById(id_user);
	}

	@Override
	public int deleteUserByIdService(Long id_user, Agent agent) {

		return userDao.deleteUserById(id_user, agent);
	}

	@Override
	public void addUserService(User u) {
		userDao.addUser(u);
	}

	@Override
	public int updateUserService(User u, Agent agent) {
		return userDao.updateUser(u, agent);
	}

}
