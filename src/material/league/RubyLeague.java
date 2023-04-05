package material.league;

import java.util.List;

import material.language.AbstractLanguage;
import user.AbstractStudentUser;

public class RubyLeague extends AbstractLeague {

	public RubyLeague() {
		// TODO Auto-generated constructor stub
	}

	public RubyLeague(AbstractLanguage language, List<AbstractStudentUser> users) {
		super(language, users);
		// TODO Auto-generated constructor stub
	}

	public RubyLeague(AbstractLeague abstractLeague) {
		super(abstractLeague);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<AbstractStudentUser> getRisingStudents() {
		// TODO Auto-generated method stub
		return null;
	}

}
