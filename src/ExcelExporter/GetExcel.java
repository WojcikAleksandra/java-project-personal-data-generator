package ExcelExporter;

import Generators.GenerateEverything;
import Human.Human;

import java.util.List;

public class GetExcel
{

    public static void getPeopleToXLSX(int n) {
        // Podziel liczbę osób na części po maks. 250 000 osób
        int numberOfParts = (n - 1) / 250000 + 1;
        ExcelExporter exporter = new ExcelExporter();

        for (int i = 0; i < numberOfParts; i++) {
            // Oblicz liczbę osób do umieszczenia w tej części
            int peopleInThisPart = Math.min(n - i * 250000, 250000);

            // Utwórz instancję KlasaZKolekcja z odpowiednią liczbą osób
            List<Human> humans = GenerateEverything.generateEverything(peopleInThisPart);

            // Eksportuj do pliku Excel
            String fileName = "humans_part_" + (i + 1) + ".xlsx";
            exporter.exportToXLSX(humans, fileName);
        }
    }


    public static void getPeopleToXLS (int n) {
        // Utwórz instancję KlasaZKolekcja z całą liczbą osób
        List<Human> humans = GenerateEverything.generateEverything(n);

        // Utwórz instancję ExcelExporter
        ExcelExporter exporter = new ExcelExporter();

        // Eksportuj do pliku Excel
        String fileName = "humans.xls";
        exporter.exportToXLS(humans, fileName);
    }


}
