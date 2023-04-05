package material.question;

import java.util.Arrays;

public class ListeningQuestion extends AbstractQuestion {
	
	public ListeningQuestion(String targetLanguageString, int lengthInSeconds) {
		super(7);
		this.contents = Arrays.asList(new StringQuestionContent(targetLanguageString), new AudioQuestionContent(lengthInSeconds));
	}

}
