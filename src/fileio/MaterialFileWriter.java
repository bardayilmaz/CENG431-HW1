package fileio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import material.language.AbstractLanguage;
import material.question.AbstractQuestion;
import material.quiz.AbstractQuiz;
import material.unit.AbstractUnit;

public class MaterialFileWriter implements IFileWriter {

	private String fileName;
	private List<AbstractLanguage> languages;
	
	public MaterialFileWriter(String fileName, List<AbstractLanguage> languages) {
		this.fileName = fileName;
		this.languages = languages;
	}

	@Override
	public void writeFile() {
		File file = new File(getFileName());
		if(file.exists()) {
			return;
		}
		try {
			file.createNewFile();
			FileWriter fileWriter = new FileWriter(file);
			for(AbstractLanguage language : getLanguages()) {
				String line = language.getName() + ",";
				for(AbstractUnit unit : language.getUnits()) {
					line = line.concat(unit.getName() + ",");
					for(AbstractQuiz quiz : unit.getQuizzes()) {
						line = line.concat(quiz.getName() + ",");
						for(AbstractQuestion question : quiz.getQuestions()) {
							line = line.concat(getQuestionCountAndFirstLetterAsString(quiz, "material.question.ReadingQuestion") + ":");
							line = line.concat(getQuestionCountAndFirstLetterAsString(quiz, "material.question.ListeningQuestion") + ":");
							line = line.concat(getQuestionCountAndFirstLetterAsString(quiz, "material.question.SpeakingQuestion") + ":");
							line = line.concat(getQuestionCountAndFirstLetterAsString(quiz, "material.question.WordMatchingQuestion") + ",");
						}
					}
				}
				line = line.substring(0, line.length()-1); // excluding last comma
				fileWriter.append(line).append("\n");
				fileWriter.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private String getQuestionCountAndFirstLetterAsString(AbstractQuiz quiz, String classPath) {
		int count = 0;
		for(AbstractQuestion question : quiz.getQuestions()) {
			if(question.getClass().getName().equals(classPath)) {
				count++;
			}
		}
		String[] classPathArr = classPath.split("\\.");
		return count + classPathArr[classPathArr.length-1].substring(0, 1); // 5R
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<AbstractLanguage> getLanguages() {
		return languages;
	}

	public void setLanguages(List<AbstractLanguage> languages) {
		this.languages = languages;
	}
	
	

}
