/**
 * Author Aryan
 */
package com.gcit.lms.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author Aryan
 *
 */
public class Branch implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2307479587087673426L;
	private Integer branchId;
	private String  branchName;
	private String branchAddr;
	private List<Loans> loans;
	/**
	 * @return the branchId
	 */
	public Integer getBranchId() {
		return branchId;
	}
	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}
	/**
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}
	/**
	 * @param branchName the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	/**
	 * @return the branchAddr
	 */
	public String getBranchAddr() {
		return branchAddr;
	}
	/**
	 * @param branchAddr the branchAddr to set
	 */
	public void setBranchAddr(String branchAddr) {
		this.branchAddr = branchAddr;
	}
	/**
	 * @return the loans
	 */
	public List<Loans> getLoans() {
		return loans;
	}
	/**
	 * @param loans the loans to set
	 */
	public void setLoans(List<Loans> loans) {
		this.loans = loans;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((branchAddr == null) ? 0 : branchAddr.hashCode());
		result = prime * result + ((branchId == null) ? 0 : branchId.hashCode());
		result = prime * result + ((branchName == null) ? 0 : branchName.hashCode());
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
		Branch other = (Branch) obj;
		if (branchAddr == null) {
			if (other.branchAddr != null)
				return false;
		} else if (!branchAddr.equals(other.branchAddr))
			return false;
		if (branchId == null) {
			if (other.branchId != null)
				return false;
		} else if (!branchId.equals(other.branchId))
			return false;
		if (branchName == null) {
			if (other.branchName != null)
				return false;
		} else if (!branchName.equals(other.branchName))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Branch [branchId=" + branchId + ", branchName=" + branchName + ", branchAddr=" + branchAddr + ", loans="
				+ loans + "]";
	}
	

}
