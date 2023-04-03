package material.question;

import java.util.Objects;

public class AudioQuestionContent implements IQuestionContent {

	private int lengthSeconds;
	
	public AudioQuestionContent() {
		lengthSeconds = -1;
	}
	
	public AudioQuestionContent(int lengthSeconds) {
		this.lengthSeconds = lengthSeconds;
	}
	
	public AudioQuestionContent(AudioQuestionContent audioQuestionContent) {
		this.lengthSeconds = audioQuestionContent.getLengthSeconds();
	}
	
	@Override
	public void display() {
		System.out.println(toString());
	}

	public int getLengthSeconds() {
		return lengthSeconds;
	}

	public void setLengthSeconds(int lengthSeconds) {
		this.lengthSeconds = lengthSeconds;
	}

	@Override
	public String toString() {
		return "Audio's length: " + this.lengthSeconds;
	}

	@Override
	public int hashCode() {
		return Objects.hash(lengthSeconds);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AudioQuestionContent other = (AudioQuestionContent) obj;
		return lengthSeconds == other.lengthSeconds;
	}

}
