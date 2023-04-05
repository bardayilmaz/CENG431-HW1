package material.league;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import material.language.AbstractLanguage;
import user.AbstractStudentUser;

public abstract class AbstractLeague implements ILeague {

	private AbstractLanguage language;
	private List<AbstractStudentUser> users;
	
	public AbstractLeague() {
		
	}
	
	public AbstractLeague(AbstractLanguage language, List<AbstractStudentUser> users) {
		this.language = language;
		this.users = users;
	}
	
	public AbstractLeague(AbstractLeague abstractLeague) {
		this.language = abstractLeague.language;
		this.users = abstractLeague.users;
	}
	
	public AbstractLanguage getLanguage() {
		return language;
	}

	public void setLanguage(AbstractLanguage language) {
		this.language = language;
	}

	public List<AbstractStudentUser> getUsers() {
		return users;
	}

	public void setUsers(List<AbstractStudentUser> users) {
		this.users = users;
	}
	
	public List<AbstractStudentUser> getsortedUsers() {
		return getUsers().stream().sorted(new Comparator<AbstractStudentUser>() {
			@Override
			public int compare(AbstractStudentUser o1, AbstractStudentUser o2) {
				if (o1.getScore() > o2.getScore()) {
					return 1;
				} else if (o2.getScore() > o1.getScore()) {
					return -1;
				} else {
					return Integer.compare(o1.getStreak(), o2.getStreak());
				}
			}}).collect(Collectors.toList());
	}
	

}
