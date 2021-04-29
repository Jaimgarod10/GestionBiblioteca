package classes;

import java.sql.Date;

public class Books {
	
	private int id,number_of_sheets, Edition, id_instance;
	private String Title,Description,ISBN,Editorial;
	private Date Date_of_publication;
	
	public Books() {
		
	}
	
	public Books(int id, int number_of_sheets, int edition, int id_instance, String title, String description,
			String iSBN, String editorial, Date date_of_publication) {
		super();
		this.id = id;
		this.number_of_sheets = number_of_sheets;
		Edition = edition;
		this.id_instance = id_instance;
		Title = title;
		Description = description;
		ISBN = iSBN;
		Editorial = editorial;
		Date_of_publication = date_of_publication;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumber_of_sheets() {
		return number_of_sheets;
	}
	public void setNumber_of_sheets(int number_of_sheets) {
		this.number_of_sheets = number_of_sheets;
	}
	public int getEdition() {
		return Edition;
	}
	public void setEdition(int edition) {
		Edition = edition;
	}
	public int getId_instance() {
		return id_instance;
	}
	public void setId_instance(int id_instance) {
		this.id_instance = id_instance;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getEditorial() {
		return Editorial;
	}
	public void setEditorial(String editorial) {
		Editorial = editorial;
	}
	public Date getDate_of_publication() {
		return Date_of_publication;
	}
	public void setDate_of_publication(Date date_of_publication) {
		Date_of_publication = date_of_publication;
	}
	@Override
	public String toString() {
		return "Book: [id=" + id + ", number_of_sheets=" + number_of_sheets + ", Edition=" + Edition + ", id_instance="
				+ id_instance + ", Title=" + Title + ", Description=" + Description + ", ISBN=" + ISBN + ", Editorial="
				+ Editorial + ", Date_of_publication=" + Date_of_publication + "]";
	}
	
	
}
