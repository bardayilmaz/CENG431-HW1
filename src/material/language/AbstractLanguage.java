package material.language;

import java.util.List;
import java.util.Objects;

import material.unit.AbstractUnit;

public abstract class AbstractLanguage {

	private String name;
	private List<AbstractUnit> units;
	
	public AbstractLanguage() {
		this.name = "";
		this.units = null;
	}

	public AbstractLanguage(String name, List<AbstractUnit> units) {
		this.name = name;
		this.units = units;
	}
	
	public AbstractLanguage(AbstractLanguage abstractLanguage) {
		this.name = abstractLanguage.getName();
		this.units = abstractLanguage.getUnits();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AbstractUnit> getUnits() {
		return units;
	}

	public void setUnits(List<AbstractUnit> units) {
		this.units = units;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, units);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractLanguage other = (AbstractLanguage) obj;
		return Objects.equals(name, other.name) && Objects.equals(units, other.units);
	}

	@Override
	public String toString() {
		return "AbstractLanguage [name=" + name + ", units=" + units + "]";
	}
	
	public int getQuizCount() {
		int count = 0;
		for(AbstractUnit unit : getUnits()) {
			count += unit.getQuizzes().size();
		}
		return count;
	}

}
