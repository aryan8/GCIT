/**
 * Author Aryan
 */
package com.gcit.lms.entity;

import java.io.Serializable;

/**
 * @author Aryan
 *
 */
public class Copies implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5799809741079074620L;
	private Book book;
	private Branch branch;
	private  Integer noCopies;
	/**
	 * @return the noCopies
	 */
	public Integer getNoCopies() {
		return noCopies;
	}
	/**
	 * @param noCopies the noCopies to set
	 */
	public void setNoCopies(Integer noCopies) {
		this.noCopies = noCopies;
	}
	/**
	 * @return the branchs
	 */
	public Branch getBranchs() {
		return branch;
	}
	/**
	 * @param branchs the branchs to set
	 */
	public void setBranchs(Branch branchs) {
		this.branch = branchs;
	}
	/**
	 * @return the books
	 */
	public Book getBooks() {
		return book;
	}
	/**
	 * @param books the books to set
	 */
	public void setBooks(Book books) {
		this.book = books;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((noCopies == null) ? 0 : noCopies.hashCode());
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
		Copies other = (Copies) obj;
		if (noCopies == null) {
			if (other.noCopies != null)
				return false;
		} else if (!noCopies.equals(other.noCopies))
			return false;
		return true;
	}
}
