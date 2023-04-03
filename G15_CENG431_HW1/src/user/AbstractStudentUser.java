package user;

import material.language.AbstractLanguage;

public abstract class AbstractStudentUser extends AbstractUser {
	
	private AbstractLanguage language;
	private int score;
	// lig

	public AbstractStudentUser() {
		// TODO Auto-generated constructor stub
	}

	public AbstractStudentUser(String username, String password) {
		super(username, password);
		// TODO Auto-generated constructor stub
	}

	public AbstractStudentUser(AbstractUser abstractUser) {
		super(abstractUser);
		// TODO Auto-generated constructor stub
	}

}
