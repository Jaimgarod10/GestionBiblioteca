package classes;

public class Authors {
	
	private int Age, Id;
	private String Name, Surname;
	
	public Authors() {
		
	}
	
	public Authors(int id, String name, String surname, int age) {
		super();
		this.Age = age;
		this.Id = id;
		Name = name;
		Surname = surname;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getSurname() {
		return Surname;
	}

	public void setSurname(String surname) {
		Surname = surname;
	}

	@Override
	public String toString() {
		return "Authors [Age=" + Age + ", Id=" + Id + ", Name=" + Name + ", Surname=" + Surname + "]";
	}
}
