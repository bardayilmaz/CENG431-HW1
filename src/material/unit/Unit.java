package material.unit;

import java.util.List;

import material.quiz.AbstractQuiz;

public class Unit extends AbstractUnit {

	public Unit() {
		super();
	}
	
	public Unit(String name, List<AbstractQuiz> quizzes) {
		super(name, quizzes);
	}
	
	public Unit(Unit unit) {
		super(unit);
	}
	
}
