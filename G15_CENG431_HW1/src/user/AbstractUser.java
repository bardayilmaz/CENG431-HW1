package user;

import java.util.Objects;

public abstract class AbstractUser implements IUser{

	private String username;
	private String password;
	
	public AbstractUser() {
		this.username = "";
		this.password = "";
	}
	
	public AbstractUser(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public AbstractUser(AbstractUser abstractUser) {
		this.username = abstractUser.getUsername();
		this.password = abstractUser.getPassword();
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
	public int hashCode() {
		return Objects.hash(password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractUser other = (AbstractUser) obj;
		return Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}
	
}
