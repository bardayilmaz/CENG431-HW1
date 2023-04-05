package material.league;

import java.util.List;
import java.util.stream.Collectors;

import material.language.AbstractLanguage;
import user.AbstractStudentUser;

public class GoldLeague extends AbstractLeague {

	public GoldLeague() {
		// TODO Auto-generated constructor stub
	}

	public GoldLeague(AbstractLanguage language, List<AbstractStudentUser> users) {
		super(language, users);
		// TODO Auto-generated constructor stub
	}

	public GoldLeague(AbstractLeague abstractLeague) {
		super(abstractLeague);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<AbstractStudentUser> getRisingStudents() {
		return getsortedUsers().subList(0, 5).stream()
				.filter(abstractStudentUser -> abstractStudentUser.getStreak() >= 7)
				.collect(Collectors.toList());
	}

}
