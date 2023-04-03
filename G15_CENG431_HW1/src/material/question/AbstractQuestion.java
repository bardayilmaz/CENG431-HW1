package material.question;

import java.util.List;
import java.util.Objects;

public abstract class AbstractQuestion {
	
	int point;
	List<IQuestionContent> contents;
	
	public AbstractQuestion() {
		this.point = -1;
		this.contents = null;
	}
	
	public AbstractQuestion(int point) {
		this.point = point;
	}
	
	public AbstractQuestion(int point, List<IQuestionContent> contents) {
		this.point = point;
		this.contents = contents;
	}
	
	public AbstractQuestion(AbstractQuestion abstractQuestion) {
		this.point = abstractQuestion.getPoint();
		this.contents = abstractQuestion.getContents();
	}
	
	public int getPoint() {
		return point;
	}
	
	public void setPoint(int point) {
		this.point = point;
	}
	
	public List<IQuestionContent> getContents() {
		return contents;
	}
	
	public void setContents(List<IQuestionContent> contents) {
		this.contents = contents;
	}

	@Override
	public int hashCode() {
		return Objects.hash(contents, point);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractQuestion other = (AbstractQuestion) obj;
		return Objects.equals(contents, other.contents) && point == other.point;
	}

	@Override
	public String toString() {
		return "AbstractQuestion [point=" + point + "\n\tcontents=" + contents + "]";
	}
	
	
	
}	
