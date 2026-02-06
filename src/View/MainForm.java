package View;

import javax.swing.*;

public class MainForm {
    private JFrame frame;
    private JPanel form;
    private JTextField textFieldN;
    private JComboBox comboBoxFormat;
    private JButton buttonRun;
    private JList listResults;
    private JLabel labelValidationTextFieldN;

    public MainForm() {
        frame = new JFrame();
        frame.setContentPane(form);
        frame.setTitle("Generator danych osobowych");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
    }

    public JFrame getFrame() {
        return frame;
    }

    public JPanel getForm() {
        return form;
    }

    public JTextField getTextFieldN() {
        return textFieldN;
    }

    public JLabel getLabelValidationTextFieldN() {
        return labelValidationTextFieldN;
    }

    public JComboBox getComboBoxFormat() {
        return comboBoxFormat;
    }

    public JButton getButtonRun() {
        return buttonRun;
    }

    public JList getListResults() {
        return listResults;
    }

    private void createUIComponents() {
        listResults = new JList<String>();
        listResults.setModel(new DefaultListModel<String>());
    }
}
