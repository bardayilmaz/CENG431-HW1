package user;

import material.language.AbstractLanguage;
import material.league.AbstractLeague;
import material.unit.AbstractUnit;

public abstract class AbstractStudentUser extends AbstractUser {
	
	private AbstractLanguage language;
	private int score;
	private int streak;
	private AbstractLeague league;
	private AbstractUnit currentUnit;
	

	public AbstractStudentUser() {
		super();
		this.language = null;
		this.score = -1;
		this.streak = -1;
		this.league = null;
	}

	public AbstractStudentUser(String username, String password, int score, int streak, AbstractLanguage language, AbstractLeague league) {
		super(username, password);
		this.score = score;
		this.streak = streak;
		this.language = language;
		this.league = league;
	}
	
	public AbstractStudentUser(String username, String password) {
		super(username, password);
		this.score = -1;
		this.streak = -1;
	}

	public AbstractStudentUser(AbstractStudentUser abstractStudentUser) {
		super(abstractStudentUser);
		this.score = abstractStudentUser.getScore();
		this.streak = abstractStudentUser.getStreak();
		this.language = abstractStudentUser.getLanguage();
		this.league = abstractStudentUser.getLeague();
	}

	public AbstractLanguage getLanguage() {
		return language;
	}

	public void setLanguage(AbstractLanguage language) {
		this.language = language;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getStreak() {
		return streak;
	}

	public void setStreak(int streak) {
		this.streak = streak;
	}

	public AbstractLeague getLeague() {
		return league;
	}

	public void setLeague(AbstractLeague league) {
		this.league = league;
	}

	public AbstractUnit getCurrentUnit() {
		return currentUnit;
	}

	public void setCurrentUnit(AbstractUnit currentUnit) {
		this.currentUnit = currentUnit;
	}

}
