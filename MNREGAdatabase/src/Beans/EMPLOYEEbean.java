package Beans;

public class EMPLOYEEbean {

	String empName;
	String status;
	
	public EMPLOYEEbean(String empName, String status) {
		super();
		this.empName = empName;
		this.status = status;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "EMPLOYEEbean [empName=" + empName + ", status=" + status + "]";
	}
	
}
