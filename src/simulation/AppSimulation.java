package simulation;

import fileio.CsvReader;
import fileio.IFileWriter;
import fileio.MaterialFileWriter;
import fileio.StudentUserFileWriter;
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

import java.util.*;


public class AppSimulation implements ISimulation {
    private List<AbstractStudentUser> students;
    private List<AbstractLanguage> languages;
    private IFileWriter fileWriter;

    public AppSimulation() {
        List<AbstractLanguage> languages = initMaterials();
        MaterialFileWriter writer = new MaterialFileWriter("languages.csv", languages);
        writer.writeFile();
        CsvReader csvReader = new CsvReader(";");
        StudentUserCsvCreator creator = new StudentUserCsvCreator(csvReader, "users.csv");

        this.students = creator.createStudents();
        this.languages = languages;
        this.userStreakAndLanguageSetter(students, this.languages, 0, 365);
        fileWriter = new StudentUserFileWriter(students, "users.csv", ";");
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

//            for (AbstractLeague league : leagues) {
//                System.out.println(league + "-------" +language.getName() +"-------------");
//                for (AbstractStudentUser student : league.getUsers()) {
//                    System.out.println(student.getLanguage().getName() + " " + student.getUsername() + " " + student.getScore() + " " + student.getStreak() + " " + student.getCurrentUnit().getName());
//                }
//            }
//            System.out.println("####################################################################");
        }
        fileWriter.writeFile();
        printMostScored();
        printStudentInMostAdvancedUnitInGerman();
        printLanguageWithMaxNumberOfUnits();
        printLanguageWithMaxNumberOfQuizzes();
        printBestThreeOfItalianSilver();
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

    private void arrangeLeagues(List<AbstractLeague> leagues, AbstractLanguage language){
        for (AbstractStudentUser student : this.students) {
            if (student.getLanguage().equals(language)) {
                leagues.get(0).getUsers().add(student);
                student.setLeague(leagues.get(0));
            }
        }
        for(int j = 0; j<leagues.size()-1;j++){
            List<AbstractStudentUser> risers;
            risers = leagues.get(j).getRisingStudents();
            leagues.get(j+1).setUsers(risers);
            leagues.get(j).getUsers().removeAll(risers);
            for(AbstractStudentUser riser : risers) {
                riser.setLeague(leagues.get(j));
            }
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
                    student.setSolvedQuizzes(student.getSolvedQuizzes() + 1);
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

    private void printMostScored() {
        AbstractStudentUser user = getStudents().stream().max(Comparator.comparingInt(AbstractStudentUser::getScore)).get();
        System.out.println(user.getUsername() + " " + user.getScore() + " points");
    }

    private void printStudentInMostAdvancedUnitInGerman() {
        AbstractStudentUser user = getStudents().stream().filter(abstractStudentUser -> abstractStudentUser.getLanguage().getName().equals("German"))
                .max(Comparator.comparingInt(o -> Integer.parseInt(o.getCurrentUnit().getName().split(" ")[1]))).get();
        System.out.println(user.getUsername() + " " + user.getCurrentUnit().getName());
    }

    private void printLanguageWithMaxNumberOfUnits() {
        AbstractLanguage language =  getLanguages().stream().max(Comparator.comparingInt(o -> o.getUnits().size())).get();
        System.out.println(language.getName() + " " + language.getUnits().size() + " Units");
    }

    private void printLanguageWithMaxNumberOfQuizzes() {
        AbstractLanguage language = getLanguages().stream().max(Comparator.comparingInt(AbstractLanguage::getQuizCount)).get();
        System.out.println(language.getName() + " " + language.getQuizCount() + " Quizzes");
    }

    private void printBestThreeOfItalianSilver() {
        AbstractLanguage language = getLanguages().stream().filter(abstractLanguage -> abstractLanguage.getName().equals("Italian"))
                .findFirst().get();
        AbstractLeague silverLeague = this.students.stream().filter(abstractStudentUser -> abstractStudentUser.getLanguage().getName().equals("Italian") &&
                abstractStudentUser.getLeague().getClass().getName().equals("material.league.SilverLeague")).findFirst().get().getLeague();
        int topLimit = Math.min(silverLeague.getUsers().size(), 3);
        List<AbstractStudentUser> topList = silverLeague.getsortedUsers().subList(0, topLimit);
        System.out.print("Italian Silver League Top " + topLimit + ": ");
        int counter = 1;
        for(AbstractStudentUser user : topList) {
            System.out.print(counter + "." + user.getUsername() + " ");
        }

    }

    public List<AbstractStudentUser> getStudents() {
        return students;
    }

    public void setStudents(List<AbstractStudentUser> students) {
        this.students = students;
    }

    public List<AbstractLanguage> getLanguages() {
        return languages;
    }

    public void setLanguages(List<AbstractLanguage> languages) {
        this.languages = languages;
    }
}

