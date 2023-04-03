package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import fileio.MaterialFileReader;
import fileio.MaterialFileWriter;
import material.language.AbstractLanguage;
import material.language.Language;
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

public class Main {

	public static void main(String[] args) {
		//System.out.println("berke");
		//AbstractUnit abstractUnit = new Unit("opa", 
			//	Arrays.asList(new Quiz("quiz", 
				//		Arrays.asList(new ReadingQuestion("merhba", "zort"), new ReadingQuestion("merhba2", "zort2")))));
		//System.out.println(generateRandomString(5));
		//System.out.println();
		//AbstractQuestion question = new ReadingQuestion("zort", "toksugeki");
		//System.out.println(question.getClass().getName());
		MaterialFileWriter writer = new MaterialFileWriter("languages.csv", initMaterials());
		writer.writeFile();
		/*for(AbstractLanguage language : initMaterials()) {
			System.out.println(language.getName());
			for(AbstractUnit unit : language.getUnits()) {
				System.out.println(unit.getName());
				for(AbstractQuiz quiz : unit.getQuizzes()) {
					System.out.print(quiz.getName() + " ");
					System.out.println(quiz.getQuestions().size());
				}
			}
		}*/
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
}
