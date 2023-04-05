package user;

import material.language.AbstractLanguage;
import material.league.AbstractLeague;

public class StudentUser extends AbstractStudentUser {

	public StudentUser() {
		super();
	}

	public StudentUser(AbstractStudentUser abstractStudentUser) {
		super(abstractStudentUser);
	}

	public StudentUser(String username, String password, int score, int streak, AbstractLanguage language,
			AbstractLeague league) {
		super(username, password, score, streak, language, league);
	}

	public StudentUser(String username, String password) {
		super(username, password);
	}
	
}
