package material.unit;

import java.util.List;

import material.quiz.AbstractQuiz;

public abstract class AbstractUnit {
	
	private String name;
	private List<AbstractQuiz> quizzes;
	
	public AbstractUnit() {
		name = "";
		quizzes = null;
	}
	
	public AbstractUnit(String name, List<AbstractQuiz> quizzes) {
		this.name = name;
		this.quizzes = quizzes;
	}
	
	public AbstractUnit(AbstractUnit abstractUnit) {
		this.name = abstractUnit.getName();
		this.quizzes = abstractUnit.getQuizzes();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AbstractQuiz> getQuizzes() {
		return quizzes;
	}

	public void setQuizzes(List<AbstractQuiz> quizzes) {
		this.quizzes = quizzes;
	}

	@Override
	public String toString() {
		return "AbstractUnit [name=" + name + "\n\tquizzes=" + quizzes + "]";
	}
	
	
	
}
