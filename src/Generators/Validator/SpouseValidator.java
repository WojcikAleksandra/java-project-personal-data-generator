package Generators.Validator;

import java.util.Objects;

import Generators.PhoneNumberGeneratorIfc;

public class SpouseValidator {
private static SpouseValidator SpouseValidator;
	
	private SpouseValidator() {}
	
	public static synchronized SpouseValidator getInstance() {
		if(Objects.isNull(SpouseValidator))
			SpouseValidator = new SpouseValidator();
		return SpouseValidator;
	}

	// TO DO
//	public String validate(PhoneNumberGeneratorIfc action) {
//		String phone = action.generatePhoneNumber();
//		if()
//			validate(action);
//		return phone;
//	}
}

