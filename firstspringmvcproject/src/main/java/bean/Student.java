package bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Student")
public class Student {

	private String studentname;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int sid;

	private String password;
	private double mnumber;

	public String getStudentname() {
		return studentname;
	}

	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getMnumber() {
		return mnumber;
	}

	public void setMnumber(double mnumber) {
		this.mnumber = mnumber;
	}

	public Student() {
		super();
	}

}
