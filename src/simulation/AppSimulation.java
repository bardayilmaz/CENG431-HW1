package simulation;

import fileio.CsvReader;
import fileio.MaterialFileWriter;
import material.language.AbstractLanguage;
import material.language.Language;
import material.league.*;
import material.question.*;
import material.quiz.AbstractQuiz;
import material.quiz.Quiz;
import material.unit.AbstractUnit;
import material.unit.Unit;
import user.AbstractStudentUser;
import util.StudentUserCsvCreator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class AppSimulation implements ISimulation {
    private List<AbstractStudentUser> students;
    private List<AbstractLanguage> languages;

    public AppSimulation() {
        MaterialFileWriter writer = new MaterialFileWriter("languages.csv", initMaterials());
        writer.writeFile();
        CsvReader csvReader = new CsvReader(";");
        StudentUserCsvCreator creator = new StudentUserCsvCreator(csvReader, "users.csv");

        this.students = creator.createStudents();
        this.languages = initMaterials();
        this.userStreakAndLanguageSetter(students, this.languages, 0, 365);
    }
    @Override
    public void startSimulation() {
        this.makeQuizzes(this.students, 6);

        for (AbstractLanguage language : this.languages) {
            List<AbstractLeague> leagues = new ArrayList<>();
            leagues.add(new BronzeLeague(language, new ArrayList<>()));
            leagues.add(new SilverLeague(language));
            leagues.add(new GoldLeague(language));
            leagues.add(new SapphireLeague(language));
            leagues.add(new RubyLeague(language));

            this.arrangeLeagues(leagues,language);

            for (AbstractLeague league : leagues) {
                System.out.println(league + "-------" +language.getName() +"-------------");
                for (AbstractStudentUser student : league.getUsers()) {
                    System.out.println(student.getLanguage().getName() + " " + student.getUsername() + " " + student.getScore() + " " + student.getStreak() + " " + student.getCurrentUnit().getName());
                }
            }
            System.out.println("####################################################################");
        }
    }


    private List<AbstractLanguage> initMaterials() {
        Random randomGenerator = new Random();
        List<String> languageNames = new ArrayList<>(Arrays.asList("Turkish", "German", "Italian", "Spanish"));
        List<AbstractLanguage> languages = new ArrayList<>();

        int minUnitCount = 60;
        int maxUnitCount = 100;
        int minQuizCount = 1;
        int maxQuizCount = 10;
        int minQuestionCount = 8;
        int maxQuestionCount = 15;

        for(String languageName: languageNames) {
            int unitCount = randomGenerator.nextInt(maxUnitCount - minUnitCount + 1) + minUnitCount;
            AbstractLanguage language = new Language(languageName, null);
            List<AbstractUnit> units = new ArrayList<>();
            for(int i = 1; i <= unitCount; i++) {
                int quizCount = randomGenerator.nextInt(maxQuizCount - minQuizCount + 1) + minQuizCount;
                List<AbstractQuiz> quizzes = new ArrayList<>();
                AbstractUnit unit = new Unit("Unit " + i, null);
                for(int j = 1; j <= quizCount; j++) {
                    int questionCount = randomGenerator.nextInt(maxQuestionCount - minQuestionCount + 1) + minQuestionCount;
                    List<AbstractQuestion> questions = new ArrayList<>();
                    AbstractQuiz quiz = new Quiz("Quiz " + j, null);
                    for(int k = 1; k <= questionCount; k++) {
                        AbstractQuestion question =  initQuestion();
                        questions.add(question);
                    }
                    quiz.setQuestions(questions);
                    quizzes.add(quiz);
                }
                unit.setQuizzes(quizzes);
                units.add(unit);
            }
            language.setUnits(units);
            languages.add(language);
        }
        return languages;
    }

    private void arrangeLeagues(List<AbstractLeague> leagues,AbstractLanguage language){
        for (AbstractStudentUser student : this.students) {
            if (student.getLanguage().equals(language)) {
                leagues.get(0).getUsers().add(student);
            }
        }
        for(int j = 0; j<leagues.size()-1;j++){
            List<AbstractStudentUser> risers;
            risers=leagues.get(j).getRisingStudents();
            leagues.get(j+1).setUsers(risers);
            leagues.get(j).getUsers().removeAll(risers);
        }
    }

    private AbstractQuestion initQuestion(){
        Random randomGenerator = new Random();
        int questionType = randomGenerator.nextInt(4);
        AbstractQuestion question;
        switch (questionType) {
            case 0 -> question = new ReadingQuestion(generateRandomString(24), generateRandomString(24));
            case 1 -> question = new ListeningQuestion(generateRandomString(24), randomGenerator.nextInt(301));
            case 2 -> question = new SpeakingQuestion(randomGenerator.nextInt(301), randomGenerator.nextInt(301));
            case 3 -> question = new WordMatchingQuestion(generateRandomString(24), generateRandomString(24));
            default ->
                // if there is an error while creation random number, return readingQuestion as default.
                    question = new ReadingQuestion(generateRandomString(24), generateRandomString(24));
        }
        return question;
    }

    private void userStreakAndLanguageSetter(List<AbstractStudentUser> students, List<AbstractLanguage> languages, int streakLowerLimit, int streakUpperLimit) {
        Random rnd = new Random();
        for(AbstractStudentUser student : students) {
            int streak = rnd.nextInt(streakUpperLimit - streakLowerLimit) + streakLowerLimit;
            AbstractLanguage language = languages.get(rnd.nextInt(languages.size()));
            student.setLanguage(language);
            student.setStreak(streak);
        }
    }

    private void makeQuizzes(List<AbstractStudentUser> students, int quizLowerLimit) {
        Random rnd = new Random();
        for(AbstractStudentUser student : students) {
            int remainingQuizCount = rnd.nextInt(student.getLanguage().getQuizCount() - quizLowerLimit + 1) + quizLowerLimit;
            unitLoop:
            for(AbstractUnit unit : student.getLanguage().getUnits()) {
                student.setCurrentUnit(unit); // this is for simulation
                for (AbstractQuiz quiz : unit.getQuizzes()) {
                    int quizScore = 0;
                    for (AbstractQuestion question : quiz.getQuestions()) {
                        if (rnd.nextBoolean()) {
                            quizScore += question.getPoint();
                        }
                    }
                    student.setScore(student.getScore() + quizScore);
                    remainingQuizCount--;
                    if (remainingQuizCount == 0) {
                        break unitLoop;
                    }
                }
            }
        }
    }
    private static String generateRandomString(int length) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }

        return sb.toString();
    }

}

