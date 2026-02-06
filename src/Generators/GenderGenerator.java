package Generators;


public class GenderGenerator extends Generator {

    public static String generateGender() {
        return rand.nextBoolean() ? "M" : "F";
    }

}