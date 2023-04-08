package material.league;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import material.language.AbstractLanguage;
import user.AbstractStudentUser;

public class BronzeLeague extends AbstractLeague {

	public BronzeLeague() {

	}

	@Override
	public String toString() {
		return "BronzeLeague";
	}

	public BronzeLeague(AbstractLanguage language, List<AbstractStudentUser> users) {
		super(language, users);
	}

	public BronzeLeague(AbstractLeague abstractLeague) {
		super(abstractLeague);
	}

	@Override
	public List<AbstractStudentUser> getRisingStudents() {
		return getsortedUsers().subList(0, 15);
	}

}
