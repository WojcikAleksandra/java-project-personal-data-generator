package Generators;

import java.time.LocalDate;

public interface PeselGeneratorIfc {
	String generatePesel(LocalDate birthDate, String gender);
}
