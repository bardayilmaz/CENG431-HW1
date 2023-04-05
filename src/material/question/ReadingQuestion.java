package material.question;

import java.util.Arrays;

public class ReadingQuestion extends AbstractQuestion {
	
	public ReadingQuestion(String englishText, String targetLanguageText) {
		super(10);
		this.contents = Arrays.asList(new StringQuestionContent(englishText), new StringQuestionContent(targetLanguageText));
	}

}
