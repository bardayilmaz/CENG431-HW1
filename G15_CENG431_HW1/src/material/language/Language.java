package material.language;

import java.util.List;

import material.unit.AbstractUnit;

public class Language extends AbstractLanguage {

	public Language() {
		super();
	}

	public Language(String name, List<AbstractUnit> units) {
		super(name, units);
	}

	public Language(AbstractLanguage abstractLanguage) {
		super(abstractLanguage);
	}
	

}
