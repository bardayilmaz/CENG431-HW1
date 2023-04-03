package material.question;

import java.util.Arrays;

public class SpeakingQuestion extends AbstractQuestion {
	
	public SpeakingQuestion(int englishAudioContentLength, int targetLanguageContentLength) {
		super(8);
		this.contents = Arrays.asList(new AudioQuestionContent(englishAudioContentLength), new AudioQuestionContent(targetLanguageContentLength));
	}

}
