package model;

public class Register {
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String FirstName) {
		this.FirstName = FirstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String LastName) {
		this.LastName = LastName;
	}
	public String getEmailId() {
		return EmailId;
	}
	public void setEmailId(String EmailId) {
		this.EmailId = EmailId;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String Location) {
		this.Location = Location;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String Username) {
		this.Username = Username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String Password) {
		this.Password = Password;
	}
	public String getConfirmPassword() {
		return ConfirmPassword;
	}
	public void setConfirmPassword(String ConfirmPassword) {
		this.ConfirmPassword = ConfirmPassword;
	}
	public String getUsertype() {
		return Usertype;
	}
	public void setUsertype(String Usertype) {
		this.Usertype = Usertype;
	}
	
	String FirstName;
	String LastName;
	String EmailId;
	String Location;
	String Username;
	String Password;
	String ConfirmPassword;
	String Usertype;
}
