package material.question;

import java.util.Objects;

public class StringQuestionContent implements IQuestionContent {

	private String content;
	
	public StringQuestionContent() {
		this.content = null;
	}
	
	public StringQuestionContent(String content) {
		this.content = content;
	}
	
	public StringQuestionContent(StringQuestionContent stringQuestionContent) {
		this.content = stringQuestionContent.getContent();
	}

	@Override
	public void display() {
		System.out.println(toString());
	}

	
	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public int hashCode() {
		return Objects.hash(content);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StringQuestionContent other = (StringQuestionContent) obj;
		return Objects.equals(content, other.content);
	}

	@Override
	public String toString() {
		return "String question content's content: " + this.content;
	}

	
	
}
