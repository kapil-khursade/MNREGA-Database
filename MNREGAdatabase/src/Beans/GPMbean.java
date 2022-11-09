package Beans;

public class GPMbean {

	String gpmName;
	int bdoSupervise;
	String username;
	String password;
	
	public GPMbean(String gpmName, int bdoSupervise, String username, String password) {
		super();
		this.gpmName = gpmName;
		this.bdoSupervise = bdoSupervise;
		this.username = username;
		this.password = password;
	}

	public String getGpmName() {
		return gpmName;
	}

	public void setGpmName(String gpmName) {
		this.gpmName = gpmName;
	}

	public int getBdoSupervise() {
		return bdoSupervise;
	}

	public void setBdoSupervise(int bdoSupervise) {
		this.bdoSupervise = bdoSupervise;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "GPMbean [gpmName=" + gpmName + ", bdoSupervise=" + bdoSupervise + ", username=" + username
				+ ", password=" + password + "]";
	}

}
