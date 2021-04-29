package classes;

import java.util.Date;

public class Loans {
	
	private int id, userId;
	private Date departureDate;
	private Date maximumDateToReturn;
	private Date returnDate;
	private Date dateOfLoans;
	private Date Dateofdevolution;
	
	
	public Loans() {
	}

	public Loans(int id, int userId, Date departureDate, Date maximumDateToReturn, Date returnDate, Date dateOfLoans,
			Date dateofdevolution) {
		super();
		this.id = id;
		this.userId = userId;
		this.departureDate = departureDate;
		this.maximumDateToReturn = maximumDateToReturn;
		this.returnDate = returnDate;
		this.dateOfLoans = dateOfLoans;
		Dateofdevolution = dateofdevolution;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getMaximumDateToReturn() {
		return maximumDateToReturn;
	}

	public void setMaximumDateToReturn(Date maximumDateToReturn) {
		this.maximumDateToReturn = maximumDateToReturn;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Date getDateOfLoans() {
		return dateOfLoans;
	}

	public void setDateOfLoans(Date dateOfLoans) {
		this.dateOfLoans = dateOfLoans;
	}

	public Date getDateofdevolution() {
		return Dateofdevolution;
	}

	public void setDateofdevolution(Date dateofdevolution) {
		Dateofdevolution = dateofdevolution;
	}

	@Override
	public String toString() {
		return "Loans [id=" + id + ", userId=" + userId + ", departureDate=" + departureDate + ", maximumDateToReturn="
				+ maximumDateToReturn + ", returnDate=" + returnDate + ", dateOfLoans=" + dateOfLoans
				+ ", Dateofdevolution=" + Dateofdevolution + "]";
	}
}
