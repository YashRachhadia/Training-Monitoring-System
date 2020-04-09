package mymvc;


import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Admin_Developer{
	@Id
	int id;
	String name;
	String tech;
	int students;
	
	
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
	public String getTech() {
		return tech;
	}
	public void setTech(String tech) {
		this.tech = tech;
	}
	public int getStudents() {
		return students;
	}
	public void setStudents(int students) {
		this.students = students;
	}
	
}
