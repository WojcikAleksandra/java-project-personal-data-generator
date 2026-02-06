package Generators;

import java.time.LocalDate;
import java.time.Month;


public class BirthDateGenerator extends Generator{


    private static final int[] ageRanges = {
            0, 4, 9, 14, 19, 24, 29, 34, 39, 44, 49, 54, 59, 64, 69, 74, 79, 84, 89, 100
    };

    private static final double[] weights = {
            4.97, 5.32, 4.69, 5.22, 6.42, 7.49, 8.45, 8.02, 6.98, 6.05, 6.37, 7.48, 7.02, 5.37, 3.17,
            2.97, 2.24, 1.77, 0.1
    };


    public static LocalDate generateBirthDate() {
        int ageIndex = randomWeightedIndex();

        int minAge = ageRanges[ageIndex];
        int maxAge = ageRanges[ageIndex + 1];

        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int yearOfBirth = currentYear - minAge - rand.nextInt(maxAge - minAge + 1);

        int month;
        int day;
        if (yearOfBirth == currentYear) {
            month = rand.nextInt(currentDate.getMonthValue()) + 1;
            day = month == currentDate.getMonthValue() ?
                    rand.nextInt(currentDate.getDayOfMonth()) + 1 :
                    rand.nextInt(Month.of(month).length(isLeapYear(yearOfBirth))) + 1;
        } else {
            month = rand.nextInt(12) + 1;
            day = rand.nextInt(Month.of(month).length(isLeapYear(yearOfBirth))) + 1;
        }

        return LocalDate.of(yearOfBirth, month, day);
    }




    private static int randomWeightedIndex() {
        double totalWeight = 0;
        for (double weight : BirthDateGenerator.weights) {
            totalWeight += weight;
        }

        double randomIndex = rand.nextDouble() * totalWeight;
        double sum = 0;
        for (int i = 0; i < BirthDateGenerator.weights.length; i++) {
            sum += BirthDateGenerator.weights[i];
            if (randomIndex < sum) {
                return i;
            }
        }

        // W przypadku, gdyby coś poszło nie tak, zwracamy indeks ostatniego elementu
        return BirthDateGenerator.weights.length - 1;
    }

    private static boolean isLeapYear(int year) {
        return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
    }

}
