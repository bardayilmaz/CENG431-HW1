package material.league;

import java.util.List;

import material.language.AbstractLanguage;
import user.AbstractStudentUser;

public class RubyLeague extends AbstractLeague {

	public RubyLeague() {
	}

	public RubyLeague(AbstractLanguage language, List<AbstractStudentUser> users) {
		super(language, users);
	}

	public RubyLeague(AbstractLeague abstractLeague) {
		super(abstractLeague);
	}

	public RubyLeague(AbstractLanguage language) {
		super(language);
	}

	@Override
	public String toString() {
		return "RubyLeague";
	}

	@Override
	public List<AbstractStudentUser> getRisingStudents() {
		return null;
	}

}
