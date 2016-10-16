package fr.adaming.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "users")
public class User implements Serializable {

	/**
	 * Attirbuts
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_u")
	private Long id_user;
	@Column(name = "nom")
	private String nom_user;
	@Column(name = "prénom")
	private String prenom_user;
	@Column(name = "date_de_naissance")
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "agent_id", referencedColumnName = "id_a")
	private Agent agent;

	/**
	 * Constructeur vide
	 */
	public User() {
		super();
	}

	/**
	 * Constructeur avec paramètres sans id
	 * 
	 * @param nom_user
	 * @param prenom_user
	 * @param dateNaissance
	 */
	public User(String nom_user, String prenom_user, Date dateNaissance) {
		super();
		this.nom_user = nom_user;
		this.prenom_user = prenom_user;
		this.dateNaissance = dateNaissance;
	}

	/**
	 * Constructeurs avec paramètres et id
	 * 
	 * @param id_user
	 * @param nom_user
	 * @param prenom_user
	 * @param dateNaissance
	 */
	public User(Long id_user, String nom_user, String prenom_user, Date dateNaissance) {
		super();
		this.id_user = id_user;
		this.nom_user = nom_user;
		this.prenom_user = prenom_user;
		this.dateNaissance = dateNaissance;
	}

	/**
	 * Getters et Setters
	 */
	/**
	 * @return the id_user
	 */
	public Long getId_user() {
		return id_user;
	}

	/**
	 * @param id_user
	 *            the id_user to set
	 */
	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}

	/**
	 * @return the nom_user
	 */
	public String getNom_user() {
		return nom_user;
	}

	/**
	 * @param nom_user
	 *            the nom_user to set
	 */
	public void setNom_user(String nom_user) {
		this.nom_user = nom_user;
	}

	/**
	 * @return the prenom_user
	 */
	public String getPrenom_user() {
		return prenom_user;
	}

	/**
	 * @param prenom_user
	 *            the prenom_user to set
	 */
	public void setPrenom_user(String prenom_user) {
		this.prenom_user = prenom_user;
	}

	/**
	 * @return the dateNaissance
	 */
	public Date getDateNaissance() {
		return dateNaissance;
	}

	/**
	 * @param dateNaissance
	 *            the dateNaissance to set
	 */
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
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
	 * Redéfinition de toString
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id_user=" + id_user + ", nom_user=" + nom_user + ", prenom_user=" + prenom_user
				+ ", dateNaissance=" + dateNaissance + "]";
	}

}
