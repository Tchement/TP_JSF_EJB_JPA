package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

@ManagedBean
@SessionScoped
public class LanguageBean implements Serializable {

	/**
	 * Attirbuts
	 */
	private static final long serialVersionUID = 1L;

	private static Map<String, Object> localites;
	private Locale local = FacesContext.getCurrentInstance().getViewRoot().getLocale();
	private String localCode = local.toString();

	/**
	 * Constructeur vide
	 */
	public LanguageBean() {
	}

	/**
	 * Constructeur statique
	 */
	static {
		localites = new HashMap<String, Object>();
		localites.put("francais", Locale.FRANCE);
		localites.put("anglais", Locale.US);
	}

	/**
	 * Getters et Setters
	 */
	/**
	 * @return the localites
	 */
	public Map<String, Object> getLocalites() {
		return localites;
	}

	/**
	 * @param localites
	 *            the localites to set
	 */
	public void setLocalites(Map<String, Object> localites) {
		LanguageBean.localites = localites;
	}

	/**
	 * @return the local
	 */
	public Locale getLocal() {
		return local;
	}

	/**
	 * @param local
	 *            the local to set
	 */
	public void setLocal(Locale local) {
		this.local = local;
	}

	/**
	 * @return the localCode
	 */
	public String getLocalCode() {
		return localCode;
	}

	/**
	 * @param localCode
	 *            the localCode to set
	 */
	public void setLocalCode(String localCode) {
		this.localCode = localCode;
	}

	/**
	 * Méthode métier
	 */
	/**
	 * Changer la langue
	 */
	public void changerLoc(ValueChangeEvent event) {
		String nouvelleLocale = (String) event.getNewValue();
		for (Map.Entry<String, Object> entry : localites.entrySet()) {
			if (nouvelleLocale.equals(entry.getValue().toString())) {

				FacesContext context = FacesContext.getCurrentInstance();

				UIViewRoot page = context.getViewRoot();

				page.setLocale((Locale) entry.getValue());

				this.setLocalCode(entry.getValue().toString());

				this.local = FacesContext.getCurrentInstance().getViewRoot().getLocale();

			}
		}
	}

}
