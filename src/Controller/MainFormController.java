package Controller;

import ExcelExporter.GetExcel;
import View.MainForm;

import javax.swing.*;

public class MainFormController {
    private MainForm mainForm;

    public void control() {
        mainForm = new MainForm();
        mainForm.getFrame().setVisible(true);

        mainForm.getButtonRun().addActionListener(e -> onClickButtonRun());
    }

    private void onClickButtonRun() {
        clearValidation();
        long startTime = System.currentTimeMillis();
        String fileFormat = String.valueOf(mainForm.getComboBoxFormat().getSelectedItem());
        int n = Integer.parseInt(mainForm.getTextFieldN().getText());
        if (!validate(n, fileFormat)) {
            mainForm.getLabelValidationTextFieldN().setText("Błąd walidacji!");
            return;
        }
        if ("XLSX".equals(fileFormat))
            GetExcel.getPeopleToXLSX(n);
        else if ("XLS".equals(fileFormat))
            GetExcel.getPeopleToXLS(n);
        else
            throw new IllegalStateException("Provided file format is not valid!");
        ((DefaultListModel<String>) mainForm.getListResults().getModel()).addElement("format: " + fileFormat + ", n: " + n + ", time: " + (System.currentTimeMillis() - startTime) + " ms");
    }

    private boolean validate(int n, String fileFormat) {
        if ("XLSX".equals(fileFormat))
            return n >= 100 && n <= 1_000_000;
        else if ("XLS".equals(fileFormat))
            return n >= 100 && n <= 64_000;
        else
            throw new IllegalStateException("Provided file format is not valid!");
    }

    private void clearValidation() {
        mainForm.getLabelValidationTextFieldN().setText("");
    }
}
