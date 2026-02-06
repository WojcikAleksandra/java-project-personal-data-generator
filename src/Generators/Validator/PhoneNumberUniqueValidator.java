package Generators.Validator;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import Generators.PhoneNumberGeneratorIfc;

public class PhoneNumberUniqueValidator {
	private Set<String> generatedPhoneNumbers = new HashSet<String>();
	
	private static PhoneNumberUniqueValidator phoneNumberUniqueValidator;
	
	private PhoneNumberUniqueValidator() {}
	
	public static synchronized PhoneNumberUniqueValidator getInstance() {
		if(Objects.isNull(phoneNumberUniqueValidator))
			phoneNumberUniqueValidator = new PhoneNumberUniqueValidator();
		return phoneNumberUniqueValidator;
	}
	
	public String validate(PhoneNumberGeneratorIfc action) {
		String phone = action.generatePhoneNumber();
		if(!generatedPhoneNumbers.add(phone))
			validate(action);
		return phone;
	}
}
