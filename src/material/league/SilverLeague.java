package material.league;

import java.util.List;

import material.language.AbstractLanguage;
import user.AbstractStudentUser;

public class SilverLeague extends AbstractLeague {

	public SilverLeague() {
		// TODO Auto-generated constructor stub
	}

	public SilverLeague(AbstractLanguage language, List<AbstractStudentUser> users) {
		super(language, users);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "SilverLeague";
	}

	public SilverLeague(AbstractLeague abstractLeague) {
		super(abstractLeague);
		// TODO Auto-generated constructor stub
	}

	public SilverLeague(AbstractLanguage language) {
		super(language);
	}

	@Override
	public List<AbstractStudentUser> getRisingStudents() {
		return getsortedUsers().subList(0, getUsers().size() >= 10 ? 10 : getUsers().size()-1);
	}

}
