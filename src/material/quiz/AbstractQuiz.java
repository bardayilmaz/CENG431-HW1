package material.quiz;

import java.util.List;
import java.util.Objects;

import material.question.AbstractQuestion;

public abstract class AbstractQuiz {

	private String name;
	private List<AbstractQuestion> questions;
	
	public AbstractQuiz() {
		name = "";
		questions = null;
	}
	
	public AbstractQuiz(String name, List<AbstractQuestion> questions) {
		this.name = name;
		this.questions = questions;
	}
	
	public AbstractQuiz(AbstractQuiz abstractQuiz) {
		this.name = abstractQuiz.getName();
		this.questions = abstractQuiz.getQuestions();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AbstractQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(List<AbstractQuestion> questions) {
		this.questions = questions;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, questions);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractQuiz other = (AbstractQuiz) obj;
		return Objects.equals(name, other.name) && Objects.equals(questions, other.questions);
	}

	@Override
	public String toString() {
		return "AbstractQuiz [name=" + name + "\n\tquestions=" + questions + "]";
	}	
	
	
	
}
