package Generators;
import DataProcessing.DataProcessor;


public class AddressesGenerator extends Generator {

    public static String[] addressGenerator() {

        String[] cityAndStreet = {"",""};
        boolean isVillage = false;
        double random1 = rand.nextDouble();
        double random2 = rand.nextDouble();
        double random3 = rand.nextDouble();
        double random4 = rand.nextDouble();

        if (random1 < 0.35)
            isVillage = true;


        if (isVillage) {
            cityAndStreet[0] = DataProcessor.getRandomVillage();
            int streetNumber = 1 + (int) ((random2 * random2) * 35);

            String street = DataProcessor.getRandomStreet() + " " + streetNumber;

            if (random3 < 0.3) {
                int houseNumber = 1 + (int) ((random4 * random4) * 20);
                street = street + "/" + houseNumber;
            }

            cityAndStreet[1] = street;

        } else {
            cityAndStreet[0] = DataProcessor.getRandomCityWithProbabilityProportionalToPopulation();

            int streetNumber = 1 + (int) ((random2 * random2) * 100);

            String street = DataProcessor.getRandomStreet() + " " + streetNumber;

            if (random3 < 0.8) {
                int houseNumber = 1 + (int) ((random4 * random4) * 60);
                street = street + "/" + houseNumber;
            }

            cityAndStreet[1] = street;

        }
        return cityAndStreet;
    }
}











