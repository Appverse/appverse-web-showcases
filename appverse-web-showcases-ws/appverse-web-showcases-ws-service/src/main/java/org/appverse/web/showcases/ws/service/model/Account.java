package org.appverse.web.showcases.ws.service.model;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * Account POJO
 * @author MOCR
 *
 */
public class Account implements Serializable {
	/**
	 * Serial version
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Account id
	 */
	private long id;
	/**
	 * Balance
	 */
	private BigDecimal balance; 
	/**
	 * Customer
	 */
	private Customer customer;
	/**
	 * Get Customer
	 * @return Customer
	 */
	public Customer getCustomer() {
		return customer;
	}
	/**
	 * Set customer
	 * @param customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
    /**
     * Get Account Id	
     * @return long id
     */
	public long getId() {
		return id;
	}
	/**
	 * Set Account Id
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * Get Balance
	 * @return balance as BigDecimal
	 */
	public BigDecimal getBalance() {
		return balance;
	}
	/**
	 * Set Balance 
	 * @param balance
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
}
