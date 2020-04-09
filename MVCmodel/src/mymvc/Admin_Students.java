package mymvc;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admin_Students {
	
	int devid;
	@Id
	int rollno;
	String stname;
	public int getDevid() {
		return devid;
	}
	public void setDevid(int devid) {
		this.devid = devid;
	}
	public int getRollno() {
		return rollno;
	}
	public void setRollno(int rollno) {
		this.rollno = rollno;
	}
	public String getStname() {
		return stname;
	}
	public void setStname(String stname) {
		this.stname = stname;
	}
}
