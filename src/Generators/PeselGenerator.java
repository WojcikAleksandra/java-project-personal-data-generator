package Generators;// package Generators;

import java.time.LocalDate;
import java.util.Random;

public class PeselGenerator extends Generator {
    private static final Random rand = new Random();

    public static String generatePesel(LocalDate birthDate, String gender) {
        int year = birthDate.getYear();
        int month = birthDate.getMonthValue();
        int day = birthDate.getDayOfMonth();

        if (year >= 2000) {
            month += 20; // Dla lat 2000 i późniejszych dodajemy 20 do miesiąca
        }

        String datePart = String.format("%02d%02d%02d", year % 100, month, day);

        // Numer porządkowy powinien być trzycyfrowy
        int orderNum = rand.nextInt(1000);
        // Dla mężczyzn ostatnia cyfra musi być nieparzysta, dla kobiet parzysta
        if (gender.equals("M")) {
            orderNum = orderNum % 2 == 0 ? orderNum + 1 : orderNum;
        } else {
            orderNum = orderNum % 2 == 1 ? orderNum - 1 : orderNum;
        }
        String orderAndGenderPart = String.format("%03d", orderNum);

        String peselWithoutControl = datePart + orderAndGenderPart;
        int controlDigit = calculateControlDigit(peselWithoutControl + "0"); // Dodajemy "0" jako tymczasową cyfrę kontrolną

        return peselWithoutControl + controlDigit;
    }

    private static int calculateControlDigit(String pesel) {
        int[] weights = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
        int sum = 0;

        for (int i = 0; i < 10; i++) { // Ograniczenie pętli do pierwszych 10 cyfr
            sum += Character.getNumericValue(pesel.charAt(i)) * weights[i];
        }

        return (10 - (sum % 10)) % 10;
    }
}

