package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Agent;
import fr.adaming.model.User;
import fr.adaming.service.IAgentService;
import fr.adaming.service.IUserService;

@ManagedBean(name = "agentMB")
@SessionScoped
public class AgentManagedBean implements Serializable {

	/**
	 * Attributs
	 */
	private static final long serialVersionUID = 1L;

	private String login;
	private String password;
	private List<User> listeUsers;

	private Agent agent;
	HttpSession session;

	@EJB
	IAgentService agentService;

	@EJB
	IUserService userService;

	/**
	 * Constructeur vide
	 */
	public AgentManagedBean() {
	}

	/**
	 * Getters et Setters
	 */
	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the agent
	 */
	public Agent getAgent() {
		return agent;
	}

	/**
	 * @param agent
	 *            the agent to set
	 */
	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	/**
	 * @return the listeUsers
	 */
	public List<User> getListeUsers() {
		return listeUsers;
	}

	/**
	 * @param listeUsers
	 *            the listeUsers to set
	 */
	public void setListeUsers(List<User> listeUsers) {
		this.listeUsers = listeUsers;
	}

	/**
	 * Méthodes métier
	 */

	/**
	 * Authentification
	 */
	public String isExist() {

		List<Agent> listeAgents = agentService.isExistService(login, password);

		if (listeAgents.size() == 1) {
			agent = listeAgents.get(0);

			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("agent", this.agent);

			listeUsers = userService.getUsersByIdAgentService(this.agent);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeUsers", listeUsers);

			return "succes";

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Authentification erronée"));
			return "failed";
		}

	}

	/**
	 * Déconnexion
	 */
	public String seDeconnecter() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login.xhtml?faces-redirect=true";
	}

}
