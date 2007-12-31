package org.openmrs.notification;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.User;

/**
 * This class is essentially a wrapper for the user object.  The alert
 * is assigned to each recipient.  A recipient then has either
 * "read" the alert or has not.
 * 
 * @see org.openmrs.notification.Alert
 * 
 */
public class AlertRecipient implements Serializable {

	private Log log = LogFactory.getLog(this.getClass());
	private static final long serialVersionUID = -507111109155L;

	private Alert alert;

	private User recipient;

	private Boolean alertRead = false;

	private Date dateChanged;

	// necessary for hql queries
	private Integer recipientId;

	/** Default empty constructor */
	public AlertRecipient() {
	}

	/** Initializes a recipient with the given alert */
	public AlertRecipient(Alert a) {
		this.alert = a;
	}
	
	/** Initializes a recipient with the given alert and recipient/user*/
	public AlertRecipient(Alert a, User recipient) {
		this.alert = a;
		setRecipient(recipient);
	}

	/**
	 * Initializes a recipient with the given user
	 * 
	 * @param read
	 * @param user
	 */
	public AlertRecipient(User user, Boolean read) {
		setRecipient(user);
		this.alertRead = read;
	}

	public boolean equals(Object obj) {
		if (obj instanceof AlertRecipient) {
			AlertRecipient a = (AlertRecipient) obj;
			log.debug("Alert: " + alert);
			log.debug("recipient: " + recipient);
			if (a != null) {
				log.debug("a.alert: " + a.getAlert());
				log.debug("a.recip: " + a.getRecipient());
			}
			if (alert != null && a.getAlert() != null
					&& recipient != null && a.getRecipient() != null)
				return (alert.equals(a.getAlert()) && recipient.equals(a.getRecipient()));
		}
		return false;
	}

	public int hashCode() {
		if (this.getAlert() == null)
			return super.hashCode();
		int hash = 8;
		hash = 31 * this.getAlert().hashCode() + hash;
		hash = 31 * this.getRecipient().hashCode() + hash;
		return hash;
	}

	/**
	 * @return Returns the alert.
	 */
	public Alert getAlert() {
		return alert;
	}

	/**
	 * @param alert
	 *            The alert to set.
	 */
	public void setAlert(Alert alert) {
		this.alert = alert;
	}

	/**
	 * @return Returns the date this alert was changed
	 */
	public Date getDateChanged() {
		return dateChanged;
	}

	/**
	 * @param dateChanged
	 *            The date this alert was changed
	 */
	public void setDateChanged(Date dateChanged) {
		this.dateChanged = dateChanged;
	}

	// @override
	public String toString() {
		return alert + "|" + recipient;
	}

	/**
	 * @return Returns the read status
	 */
	public Boolean isAlertRead() {
		return alertRead;
	}

	/**
	 * @see getAlertRead()
	 */
	public Boolean getAlertRead() {
		return isAlertRead();
	}

	/**
	 * @param alertRead
	 *            The alertRead to set.
	 */
	public void setAlertRead(Boolean alertRead) {
		this.alertRead = alertRead;
	}

	/**
	 * @return Returns the recipient/user.
	 */
	public User getRecipient() {
		return recipient;
	}

	/**
	 * @param user
	 *            The recipient/user to set.
	 */
	public void setRecipient(User user) {
		this.recipient = user;
		setRecipientId(user.getUserId());
	}

	/**
	 * @return Returns the recipientId.
	 */
	@SuppressWarnings("unused")
	private Integer getRecipientId() {
		return recipientId;
	}

	/**
	 * @param recipientId
	 *            The recipientId to set.
	 */
	private void setRecipientId(Integer recipientId) {
		this.recipientId = recipientId;
	}

}