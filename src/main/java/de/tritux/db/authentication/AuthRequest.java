package de.tritux.db.authentication;

public class AuthRequest {
    private String mail;
    private String password;
    
    
    
	public AuthRequest(String mail, String password) {
		super();
		this.mail = mail;
		this.password = password;
	}
	public AuthRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

    // Constructeurs, getters, setters
}
