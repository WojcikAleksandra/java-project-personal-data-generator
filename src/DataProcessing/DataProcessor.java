package DataProcessing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class DataProcessor {

    private static final Random rand = new Random();
    private static final String pathVillages = "src/DataProcessing/Data/villages.csv";
    private static final String pathFemaleFirstnames = "src/DataProcessing/Data/polish_female_firstnames.txt";
    private static final String pathMaleFirstnames = "src/DataProcessing/Data/polish_male_firstnames.txt";
    private static final String pathSurnames = "src/DataProcessing/Data/polish_surnames.txt";
    private static final String pathUlic =  "src/DataProcessing/Data/streets.txt";
    private static final String pathCitiesData = "src/DataProcessing/Data/cities_population.txt";

    private static final ArrayList<String> femaleFirstnames = readFile(pathFemaleFirstnames);
    private static final ArrayList<String> maleFirstnames = readFile(pathMaleFirstnames);
    private static final ArrayList<String> surnames = getSingleWordSurnames(readFile(pathSurnames));
    private static final ArrayList<String> streets = readFile(pathUlic);
    private static final ArrayList<String[]> cityAndPopulation = readCitiesPopulation();
    private static final ArrayList<String> villages = readDistinctVillages();

    private static final int totalPopulationOfCities = cityAndPopulation.stream()
            .mapToInt(cityInfo -> Integer.parseInt(cityInfo[1]))
            .sum();

    public static String getFemaleFirstname() {
        return femaleFirstnames.get(rand.nextInt(femaleFirstnames.size()));
    }

    public static String getMaleFirstname() {
        return maleFirstnames.get(rand.nextInt(maleFirstnames.size()));
    }

    public static String getSurname() {
        return surnames.get(rand.nextInt(surnames.size()));
    }

    public static String getRandomStreet() {
        return streets.get(rand.nextInt(streets.size()));
    }

    public static String getRandomCityWithProbabilityProportionalToPopulation() {
        int randomNum = rand.nextInt(totalPopulationOfCities);
        int cumulativePopulation = 0;

        for (String[] cityInfo : cityAndPopulation) {
            cumulativePopulation += Integer.parseInt(cityInfo[1]);
            if (randomNum < cumulativePopulation) {
                return cityInfo[0].substring(1, cityInfo[0].length() - 1);
            }
        }

        return "Unknown";
    }

    public static String getRandomVillage() {
        return villages.get(rand.nextInt(villages.size()));
    }

    private static ArrayList<String> readFile(String path) {
        ArrayList<String> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace(); // tu jest warning ale ten program nigdy tu nie wejdzie
        }
        return data;
    }

    private static ArrayList<String[]> readCitiesPopulation() {
        ArrayList<String[]> citiesPopulation = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(DataProcessor.pathCitiesData))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] cityInfo = line.split(", ");
                if (cityInfo.length == 2) {
                    citiesPopulation.add(cityInfo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // tu jest warning ale ten program nigdy tu nie wejdzie
        }
        return citiesPopulation;
    }

    private static ArrayList<String> readDistinctVillages() {
        ArrayList<String> allVillages = readFile(DataProcessor.pathVillages);
        return allVillages.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
    }

    private static ArrayList<String> getSingleWordSurnames(ArrayList<String> surnames) {
        return surnames.stream()
                .filter(surname -> !surname.contains("-")).collect(Collectors.toCollection(ArrayList::new));
    }
}
