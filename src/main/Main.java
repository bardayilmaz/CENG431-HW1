package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import fileio.CsvReader;
import fileio.MaterialFileWriter;
import material.language.AbstractLanguage;
import material.language.Language;
import material.league.AbstractLeague;
import material.league.BronzeLeague;
import material.league.GoldLeague;
import material.league.RubyLeague;
import material.league.SapphireLeague;
import material.league.SilverLeague;
import material.question.AbstractQuestion;
import material.question.IQuestionContent;
import material.question.ListeningQuestion;
import material.question.ReadingQuestion;
import material.question.SpeakingQuestion;
import material.question.WordMatchingQuestion;
import material.quiz.AbstractQuiz;
import material.quiz.Quiz;
import material.unit.AbstractUnit;
import material.unit.Unit;
import user.AbstractStudentUser;
import user.StudentUser;
import util.StudentUserCsvCreator;

public class Main {

	public static void main(String[] args) {
		MaterialFileWriter writer = new MaterialFileWriter("languages.csv", initMaterials());
		writer.writeFile();
		CsvReader csvReader = new CsvReader(";");
		StudentUserCsvCreator creator = new StudentUserCsvCreator(csvReader, "users.csv");
		List<AbstractStudentUser> students = creator.createStudents();
		List<AbstractLanguage> languages = initMaterials();
		userStreakAndLanguageSetter(students, languages, 0, 365);
		makeQuizzes(students, 6);
		

		AbstractLeague bronze = new BronzeLeague(languages.get(0), new ArrayList<>());
		
		for(AbstractStudentUser student : students) {
			if(student.getLanguage().equals(languages.get(0))) {
				bronze.getUsers().add(student);
			}
		}
		
		List<AbstractStudentUser> risers = bronze.getRisingStudents();
		AbstractLeague silver = new SilverLeague(languages.get(0), risers);
		bronze.getUsers().removeAll(risers);
		risers = silver.getRisingStudents();
		AbstractLeague gold = new GoldLeague(languages.get(0), risers);
		silver.getUsers().removeAll(risers);
		
		risers = gold.getRisingStudents();
		AbstractLeague sapphire = new SapphireLeague(languages.get(0), risers);
		gold.getUsers().removeAll(risers);
		
		
		risers = sapphire.getRisingStudents();
		AbstractLeague ruby = new RubyLeague(languages.get(0), risers);
		sapphire.getUsers().removeAll(risers);
		
		
		for(AbstractStudentUser student : bronze.getUsers()) {
			System.out.println(student.getUsername());
		}
		System.out.println("##############  SILVER  #########################");
		for(AbstractStudentUser student : silver.getUsers()) {
			System.out.println(student.getUsername());
		}
		System.out.println("##############  gold  #########################");
		for(AbstractStudentUser student : gold.getUsers()) {
			System.out.println(student.getUsername());
		}
		System.out.println("################ saphire #######################");
		for(AbstractStudentUser student : sapphire.getUsers()) {
			System.out.println(student.getUsername());
		}
		System.out.println("################# ruby ######################");
		for(AbstractStudentUser student : ruby.getUsers()) {
			System.out.println(student.getUsername());
		}
		
	}
	
	private static List<AbstractLanguage> initMaterials() {
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
						int questionType = randomGenerator.nextInt(4);
						AbstractQuestion question;
						switch (questionType) {
						case 0:
							question = new ReadingQuestion(generateRandomString(24), generateRandomString(24));
							break;
						case 1:
							question = new ListeningQuestion(generateRandomString(24), randomGenerator.nextInt(301));
							break;
						case 2:
							question = new SpeakingQuestion(randomGenerator.nextInt(301), randomGenerator.nextInt(301));
							break;
						case 3:
							question = new WordMatchingQuestion(generateRandomString(24), generateRandomString(24));
							break;
						default:
							// if there is an error while creation random number, return readingQuestion as default.
							question = new ReadingQuestion(generateRandomString(24), generateRandomString(24));
							break;
						}
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
	
	private static String generateRandomString(int length) {
	      String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	      
	      StringBuilder sb = new StringBuilder();
	      Random random = new Random();
	      
	      for (int i = 0; i < length; i++) {
	         int index = random.nextInt(alphabet.length());
	         char randomChar = alphabet.charAt(index);
	         sb.append(randomChar);
	      }
	      
	      String randomString = sb.toString();
	      return randomString;
   }
	
	private static void userStreakAndLanguageSetter(List<AbstractStudentUser> students, List<AbstractLanguage> languages, int streakLowerLimit, int streakUpperLimit) {
		Random rnd = new Random();
		for(AbstractStudentUser student : students) {
			int streak = rnd.nextInt(streakUpperLimit - streakLowerLimit) + streakLowerLimit;
			AbstractLanguage language = languages.get(rnd.nextInt(languages.size()));
			student.setLanguage(language);
			student.setStreak(streak);
		}
	}
	
	private static void makeQuizzes(List<AbstractStudentUser> students, int quizLowerLimit) {
		Random rnd = new Random();
		for(AbstractStudentUser student : students) {
			int remainingQuizCount = rnd.nextInt(student.getLanguage().getQuizCount() - quizLowerLimit + 1) + quizLowerLimit;
			unitLoop:
			for(AbstractUnit unit : student.getLanguage().getUnits()) {
				student.setCurrentUnit(unit); // this is for simulation
				for(AbstractQuiz quiz : unit.getQuizzes()) {
					int quizScore = 0;
					for(AbstractQuestion question : quiz.getQuestions()) {
						if(rnd.nextBoolean()) {
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
			System.out.println(student.getPassword() + " " + student.getCurrentUnit().getName() + " " + student.getScore() + " " + student.getStreak());
		}
	}
	
	
}
