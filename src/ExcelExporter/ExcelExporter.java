package ExcelExporter;
import Human.Human;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.io.FileOutputStream;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public class ExcelExporter {

    protected void exportToXLS(List<Human> humans, String outputFile) {
        try (HSSFWorkbook workbook = new HSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Humans");
            CreationHelper createHelper = workbook.getCreationHelper();
            CellStyle dateStyle = workbook.createCellStyle();
            dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

            // Nagłówki
            String[] headers = {"First Name", "Last Name", "Birth Date", "PESEL", "City", "Street", "Father Index", "Mother Index", "Children Indices", "Sisters Indices", "Brothers Indices", "Spouse Index", "Phone Number"};
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                headerRow.createCell(i).setCellValue(headers[i]);
            }

            // Dane
            for (int i = 0; i < humans.size(); i++) {
                Human human = humans.get(i);
                Row row = sheet.createRow(i + 1);

                row.createCell(0).setCellValue(human.getFirstName());
                row.createCell(1).setCellValue(human.getLastName());
                Cell birthDateCell = row.createCell(2);
                birthDateCell.setCellValue(human.getBirthDate());
                birthDateCell.setCellStyle(dateStyle);
                row.createCell(3).setCellValue(human.getPesel());
                row.createCell(4).setCellValue(human.getCity());
                row.createCell(5).setCellValue(human.getAddress());

                setIndexCell(row, 6, human.getFather(), humans);
                setIndexCell(row, 7, human.getMother(), humans);
                setIndicesCell(row, 8, human.getChildren(), humans);
                setIndicesCell(row, 9, human.getSisters(), humans);
                setIndicesCell(row, 10, human.getBrothers(), humans);
                setIndexCell(row, 11, human.getSpouse(), humans);

                row.createCell(12).setCellValue(human.getPhoneNumber());
            }

            // Zapis do pliku
            try (FileOutputStream fileOut = new FileOutputStream(outputFile)) {
                workbook.write(fileOut);
            }
        } catch (Exception e) {
            e.printStackTrace(); // tu jest warning ale ten program nigdy tu nie wejdzie
        }
    }

    // Metoda do eksportowania danych do formatu XLSX
    protected void exportToXLSX(List<Human> humans, String outputFile) {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Humans");
            CreationHelper createHelper = workbook.getCreationHelper();
            CellStyle dateStyle = workbook.createCellStyle();
            dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

            // Nagłówki
            String[] headers = {"First Name", "Last Name", "Birth Date", "PESEL", "City", "Street", "Father Index", "Mother Index", "Children Indices", "Sisters Indices", "Brothers Indices", "Spouse Index", "Phone Number"};
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                headerRow.createCell(i).setCellValue(headers[i]);
            }

            // Wstawianie danych
            for (int i = 0; i < humans.size(); i++) {
                Human human = humans.get(i);
                Row row = sheet.createRow(i + 1);

                row.createCell(0).setCellValue(human.getFirstName());
                row.createCell(1).setCellValue(human.getLastName());
                Cell birthDateCell = row.createCell(2);
                if (human.getBirthDate() != null) {
                    birthDateCell.setCellValue(Date.from(human.getBirthDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    birthDateCell.setCellStyle(dateStyle);
                } else {
                    birthDateCell.setCellValue("NULL");
                }
                row.createCell(3).setCellValue(human.getPesel());
                row.createCell(4).setCellValue(human.getCity());
                row.createCell(5).setCellValue(human.getAddress());

                // Numer wiersza zamiast hiperłącza
                setIndexCell(row, 6, human.getFather(), humans);
                setIndexCell(row, 7, human.getMother(), humans);
                setIndicesCell(row, 8, human.getChildren(), humans);
                setIndicesCell(row, 9, human.getSisters(), humans);
                setIndicesCell(row, 10, human.getBrothers(), humans);
                setIndexCell(row, 11, human.getSpouse(), humans);

                row.createCell(12).setCellValue(human.getPhoneNumber());
            }

            // Zapisywanie pliku
            try (FileOutputStream fileOut = new FileOutputStream(outputFile)) {
                workbook.write(fileOut);
            }
        } catch (Exception e) {
            e.printStackTrace(); // // tu jest warning ale ten program nigdy tu nie wejdzie
        }
    }

    private void setIndexCell(Row row, int cellIndex, Human relative, List<Human> humans) {
        Cell cell = row.createCell(cellIndex);
        if (relative != null) {
            int index = humans.indexOf(relative) + 2; // +2 ponieważ Excel rozpoczyna numerację wierszy od 1, a jest nagłówek
            cell.setCellValue(index);
        } else {
            cell.setCellValue("NULL");
        }
    }

    private void setIndicesCell(Row row, int cellIndex, List<Human> relatives, List<Human> humans) {
        Cell cell = row.createCell(cellIndex);
        if (relatives == null || relatives.isEmpty()) {
            cell.setCellValue("NULL");
        } else {
            String indices = relatives.stream()
                    .map(human -> Integer.toString(humans.indexOf(human) + 2)) // +2 z tego samego powodu
                    .collect(Collectors.joining(", "));
            cell.setCellValue(indices);
        }
    }
}
