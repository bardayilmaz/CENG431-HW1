package material.quiz;

import java.util.List;

import material.question.AbstractQuestion;

public class Quiz extends AbstractQuiz {

	public Quiz() {
		super();
	}
	
	public Quiz(String name, List<AbstractQuestion> questions) {
		super(name, questions);
	}
	
	public Quiz(Quiz quiz) {
		super(quiz);
	}

}
