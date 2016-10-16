package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Agent;
import fr.adaming.model.User;
import fr.adaming.service.IUserService;

@ManagedBean(name = "userMB")
@SessionScoped
public class UserManagedBean implements Serializable {

	/**
	 * Attributs
	 */
	private static final long serialVersionUID = 1L;

	private User user;
	private Agent agent;

	@EJB
	IUserService userService;

	HttpSession session;

	/**
	 * Récupération de la session à la construction du managedBean
	 */
	@PostConstruct
	private void init() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(false);

		agent = (Agent) session.getAttribute("agent");
	}

	/**
	 * Constructeur vide
	 */
	public UserManagedBean() {
		user = new User();
	}

	/**
	 * Getters et Setters
	 */
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Méthodes métier
	 */

	/**
	 * Ajouter un utilisateur
	 */
	public String addUser() {
		user.setAgent(agent);
		userService.addUserService(user);
		List<User> listeUsers = new ArrayList<User>();
		listeUsers = userService.getUsersByIdAgentService(agent);
		session.setAttribute("listeUsers", listeUsers);
		return "accueil";
	}

	/**
	 * Suprimer un utilisateur
	 */
	public String deleteUser() {
		user.setAgent(agent);
		if (userService.deleteUserByIdService(user.getId_user(), agent) == 1) {
			List<User> listeUsers = new ArrayList<User>();
			listeUsers = userService.getUsersByIdAgentService(agent);
			session.setAttribute("listeUsers", listeUsers);
			return "accueil";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Cet utilisateur n'existe pas ou ne vous appartient pas"));
			return "supprimerUtilisateur";
		}
	}

	/**
	 * Modifier un utilisateur
	 */
	public String updateUser() {
		user.setAgent(agent);
		if (userService.updateUserService(user, agent) == 1) {
			List<User> listeUsers = new ArrayList<User>();
			listeUsers = userService.getUsersByIdAgentService(agent);
			session.setAttribute("listeUsers", listeUsers);
			return "accueil";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Cet utilisateur n'existe pas ou ne vous appartient pas"));
			return "modifierUtilisateur";
		}
	}

}
