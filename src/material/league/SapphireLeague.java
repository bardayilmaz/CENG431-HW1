package material.league;

import material.language.AbstractLanguage;
import user.AbstractStudentUser;

import java.util.List;
import java.util.stream.Collectors;

public class SapphireLeague extends AbstractLeague{

    public SapphireLeague() {
    }

    public SapphireLeague(AbstractLanguage language, List<AbstractStudentUser> users) {
        super(language, users);
    }

    public SapphireLeague(AbstractLeague abstractLeague) {
        super(abstractLeague);
    }

    @Override
    public List<AbstractStudentUser> getRisingStudents() {
        return getUsers().stream().filter(abstractStudentUser -> {
            return abstractStudentUser.getStreak() >= 30 &&
                    (abstractStudentUser.getScore() > 5000
                            || Integer.parseInt(abstractStudentUser.getCurrentUnit().getName().split(" ")[1]) >= 10);
        }).collect(Collectors.toList());
    }
}
