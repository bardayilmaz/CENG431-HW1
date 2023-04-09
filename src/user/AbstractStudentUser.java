package user;

import material.language.AbstractLanguage;
import material.league.AbstractLeague;
import material.unit.AbstractUnit;

public abstract class AbstractStudentUser extends AbstractUser {
	
	private AbstractLanguage language;
	private int score;
	private int streak;
	private int solvedQuizzes;
	private AbstractLeague league;
	private AbstractUnit currentUnit;
	

	public AbstractStudentUser() {
		super();
		this.language = null;
		this.score = 0;
		this.streak = 0;
		this.solvedQuizzes = 0;
		this.league = null;
	}

	public AbstractStudentUser(String username, String password, int score, int streak, int solvedQuizzes,AbstractLanguage language, AbstractLeague league) {
		super(username, password);
		this.score = score;
		this.streak = streak;
		this.language = language;
		this.league = league;
		this.solvedQuizzes = solvedQuizzes;
	}
	
	public AbstractStudentUser(String username, String password) {
		super(username, password);
		this.score = 0;
		this.streak = 0;
		this.solvedQuizzes = 0;
	}

	public AbstractStudentUser(AbstractStudentUser abstractStudentUser) {
		super(abstractStudentUser);
		this.score = abstractStudentUser.getScore();
		this.streak = abstractStudentUser.getStreak();
		this.language = abstractStudentUser.getLanguage();
		this.league = abstractStudentUser.getLeague();
		this.solvedQuizzes = abstractStudentUser.getSolvedQuizzes();
		this.currentUnit = abstractStudentUser.getCurrentUnit();
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

	public int getSolvedQuizzes() {
		return solvedQuizzes;
	}

	public void setSolvedQuizzes(int solvedQuizzes) {
		this.solvedQuizzes = solvedQuizzes;
	}
}
