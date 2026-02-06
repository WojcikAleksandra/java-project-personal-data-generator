package Generators;

import DataProcessing.DataProcessor;

public class NameAndSurnameGenerator extends Generator {

    public static String[] nameAndSurnameForWomen() {
        String firstname = DataProcessor.getFemaleFirstname();
        String surname = DataProcessor.getSurname();
        surname = adjustSurnameForWomen(surname);
        return new String[]{firstname, surname};
    }

    public static String[] nameAndSurnameForMen() {
        String firstname = DataProcessor.getMaleFirstname();
        String surname = DataProcessor.getSurname();
        return new String[]{firstname, surname};
    }

    protected static String adjustSurnameForWomen(String surname) {
        if (surname.length() > 2) {
            if (surname.endsWith("ski") || surname.endsWith("cki") || surname.endsWith("dzki") || surname.endsWith("zki") || surname.endsWith("ny") || surname.endsWith("ty") || surname.endsWith("cy")) {
                return surname.substring(0, surname.length() - 1) + "a";
            } else if (surname.endsWith("iak") || surname.endsWith("ak") || surname.endsWith("ów") || surname.matches(".*[bpdtgkh]$")) {
                return surname + "owa";
            } else {
                String substring = surname.substring(0, surname.length() - 2);
                if (surname.endsWith("ek") || surname.endsWith("ko")) {
                    return substring + "ka";
                } else if (surname.endsWith("go")) {
                    return substring + "ga";
                } else if (surname.endsWith("ło")) {
                    return substring + "ła";
                } else if (surname.endsWith("mo")) {
                    return substring + "ma";
                }
            }
        }
        return surname;
    }
}

