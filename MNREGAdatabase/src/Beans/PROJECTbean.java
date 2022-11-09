package Beans;

public class PROJECTbean {

	String proName;
	int totalCost;
	int employeeRequired;
	String dateOfStrat;
	String dateOfEnd;
	String status;
	
	public PROJECTbean(String proName, int totalCost, int employeeRequired, String dateOfStrat, String dateOfEnd,
			String status) {
		super();
		this.proName = proName;
		this.totalCost = totalCost;
		this.employeeRequired = employeeRequired;
		this.dateOfStrat = dateOfStrat;
		this.dateOfEnd = dateOfEnd;
		this.status = status;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public int getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	public int getEmployeeRequired() {
		return employeeRequired;
	}

	public void setEmployeeRequired(int employeeRequired) {
		this.employeeRequired = employeeRequired;
	}

	public String getDateOfStrat() {
		return dateOfStrat;
	}

	public void setDateOfStrat(String dateOfStrat) {
		this.dateOfStrat = dateOfStrat;
	}

	public String getDateOfEnd() {
		return dateOfEnd;
	}

	public void setDateOfEnd(String dateOfEnd) {
		this.dateOfEnd = dateOfEnd;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "PROJECTbean [proName=" + proName + ", totalCost=" + totalCost + ", employeeRequired=" + employeeRequired
				+ ", dateOfStrat=" + dateOfStrat + ", dateOfEnd=" + dateOfEnd + ", status=" + status + "]";
	}
	
	
}
