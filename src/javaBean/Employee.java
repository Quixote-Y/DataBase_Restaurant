package javaBean;

public class Employee {
	private int empId;
	private String name;
	private String pwd;
	private String job;
	public Employee(int empId, String name, String pwd, String job) {
		super();
		this.empId = empId;
		this.name = name;
		this.pwd = pwd;
		this.job = job;
	}
	public Employee() {
		super();
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", name=" + name + ", pwd=" + pwd + ", job=" + job + "]";
	}
	

}
