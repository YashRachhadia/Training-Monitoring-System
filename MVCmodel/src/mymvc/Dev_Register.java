package mymvc;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Dev_Register {
	
	@Id
	String name;
	String email;
	String pass;
	String re_pass;
	String phonenumber;
	
	@OneToOne(targetEntity=Admin_Developer.class, cascade=CascadeType.ALL)
	Admin_Developer adv;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getRe_pass() {
		return re_pass;
	}
	public void setRe_pass(String re_pass) {
		this.re_pass = re_pass;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public Admin_Developer getAdv() {
		return adv;
	}
	public void setAdv(Admin_Developer adv) {
		this.adv = adv;
	}
}
