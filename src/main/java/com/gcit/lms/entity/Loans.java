/**
 * Author Aryan
 */
package com.gcit.lms.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * @author Aryan
 *
 */
public class Loans implements Serializable {

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 3294600784538925324L;
	private Date dayOut;
	private Date dueDate;
	private Date dateIn;
	private Book book;
	private Borrower borrower;
	private Branch branches;
	/**
	 * @return the dayOut
	 */
	public Date getDayOut() {
		return dayOut;
	}
	/**
	 * @param dayOut the dayOut to set
	 */
	public void setDayOut(Date dayOut) {
		this.dayOut = dayOut;
	}
	/**
	 * @return the dueDate
	 */
	public Date getDueDate() {
		return dueDate;
	}
	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	/**
	 * @return the dateIn
	 */
	public Date getDateIn() {
		return dateIn;
	}
	/**
	 * @param dateIn the dateIn to set
	 */
	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}

	/**
	 * @return the borrower
	 */
	public Borrower getBorrower() {
		return borrower;
	}
	/**
	 * @param borrower the borrower to set
	 */
	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}
	/**
	 * @return the branches
	 */
	public Branch getBranches() {
		return branches;
	}
	/**
	 * @param branches the branches to set
	 */
	public void setBranches(Branch branches) {
		this.branches = branches;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateIn == null) ? 0 : dateIn.hashCode());
		result = prime * result + ((dayOut == null) ? 0 : dayOut.hashCode());
		result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Loans other = (Loans) obj;
		if (dateIn == null) {
			if (other.dateIn != null)
				return false;
		} else if (!dateIn.equals(other.dateIn))
			return false;
		if (dayOut == null) {
			if (other.dayOut != null)
				return false;
		} else if (!dayOut.equals(other.dayOut))
			return false;
		if (dueDate == null) {
			if (other.dueDate != null)
				return false;
		} else if (!dueDate.equals(other.dueDate))
			return false;
		return true;
	}
	/**
	 * @return the book
	 */
	public Book getBook() {
		return book;
	}
	/**
	 * @param book the book to set
	 */
	public void setBook(Book book) {
		this.book = book;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Loans [dayOut=" + dayOut + ", dueDate=" + dueDate + ", dateIn=" + dateIn + ", book=" + book
				+ ", borrower=" + borrower + ", branches=" + branches + "]";
	}


}
