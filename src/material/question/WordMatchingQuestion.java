package material.question;

import java.util.Arrays;

public class WordMatchingQuestion extends AbstractQuestion {
	
	public WordMatchingQuestion(String englishKey, String targetLanguageValue) {
		super(5);
		this.contents = Arrays.asList(new StringQuestionContent(englishKey), new StringQuestionContent(targetLanguageValue));
	}

}
