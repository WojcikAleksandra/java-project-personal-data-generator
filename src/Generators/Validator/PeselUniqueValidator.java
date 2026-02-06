package Generators.Validator;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import Generators.PeselGeneratorIfc;

public class PeselUniqueValidator {
	private Set<String> generatedPesels = new HashSet<String>();
	
	private static PeselUniqueValidator peselUniqueValidator;
	
	private PeselUniqueValidator() {}
	
	public static synchronized PeselUniqueValidator getInstance() {
		if(Objects.isNull(peselUniqueValidator))
			peselUniqueValidator = new PeselUniqueValidator();
		return peselUniqueValidator;
	}
	
	public String validate(LocalDate birthDate, String gender, PeselGeneratorIfc action) {
		String pesel = action.generatePesel(birthDate, gender);
		if(!generatedPesels.add(pesel))
			validate(birthDate, gender, action);
		return pesel;
	}
}
