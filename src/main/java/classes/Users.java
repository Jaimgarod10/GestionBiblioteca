package classes;

public class Users {
	
	private int id;
	private String name;
	private String surnames;
	private String email;
	private int phone;
	
	public Users() {
	}

	public Users(int id, String name, String surnames, String email, int phone) {
		super();
		this.id = id;
		this.name = name;
		this.surnames = surnames;
		this.email = email;
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurnames() {
		return surnames;
	}

	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", surnames=" + surnames + ", email=" + email + ", phone=" + phone + "]";
	}
}
