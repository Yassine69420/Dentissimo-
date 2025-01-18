package org.example.Views;

import org.example.Controllers.PatientController;
import org.example.Exceptions.ControllerException;
import org.example.MODELS.Patient;
import org.example.MODELS.enums.Assurence;
import org.example.Repositories.Implementation.PatientDAO;
import org.example.Services.Implementation.PatientService;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.Date;

public class PatientManagementUI extends Design {

    PatientController ahmed = new PatientController(new PatientService(new PatientDAO()));

    private JTable patientTable;
    private JTextField searchField, nomField, prenomField, telField, emailField, adresseField, cinField;
    private JComboBox<String> sexeCombo, risqueCombo;
    private JComboBox<Assurence> assuranceCombo;
    private JSpinner ageSpinner, dateSpinner;

    public PatientManagementUI() {
        super();
        setLocationRelativeTo(null);
        getMainPanel().add(createMainPanel(), BorderLayout.CENTER);
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        // Top Panel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(239, 255, 251));
        topPanel.add(createTitleLabel(), BorderLayout.WEST);
        topPanel.add(createSearchPanel(), BorderLayout.EAST);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        // Center Panel (Table)
        mainPanel.add(createTablePanel(), BorderLayout.CENTER);
        // Bottom Panel (Form + Image)
        mainPanel.add(createFormPanel(), BorderLayout.SOUTH);
        return mainPanel;
    }

    private JLabel createTitleLabel() {
        JLabel titleLabel = new JLabel("Liste des patients");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));

        return titleLabel;
    }
    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchField = new JTextField(15);
        searchPanel.add(new JLabel("Rechercher : "));
        searchPanel.setBackground(new Color(239, 255, 251));
        searchPanel.add(searchField);
        return searchPanel;
    }

    private JPanel createTablePanel() {
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(239, 255, 251));

        String[] columnNames = {"Nom Complet", "Âge", "Sexe", "Email", "Adresse", "Date d'Ajout"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        patientTable = new JTable(tableModel);

        styleTable(patientTable);

        refreshTableData(tableModel);

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(createDetailButton(centerPanel));

        JButton editButton = getEditButton(centerPanel);
        buttonPanel.add(editButton);
        buttonPanel.add(createDeleteButton(tableModel, centerPanel));
        JScrollPane tableScroll = new JScrollPane(patientTable);
        centerPanel.add(tableScroll, BorderLayout.CENTER);
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);

        return centerPanel;
    }

    private void showEditPatientFrame(Patient patient) {
        JFrame editFrame = new JFrame("Modifier les Détails du Patient");
        editFrame.setSize(600, 500); // Adjusted size for vertical layout
        editFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editFrame.setLocationRelativeTo(null); // Center the frame

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(239, 255, 251));
        formPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(79, 152, 202)),
                "Modifier le Patient",
                0, 0, new Font("SansSerif", Font.BOLD, 16),
                new Color(79, 152, 202))
        );

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding
        gbc.fill = GridBagConstraints.HORIZONTAL; // Stretch components horizontally
        gbc.weightx = 1.0; // Allow horizontal resizing
        gbc.gridx = 0; // Always add components in the first column

        // Adding labeled fields vertically
        JTextField nomField = addLabeledFieldWithSize(formPanel, "Nom :", 0, patient.getNom(), gbc, 25);
        JTextField prenomField = addLabeledFieldWithSize(formPanel, "Prénom :", 1, patient.getPrenom(), gbc, 25);
        JTextField cinField = addLabeledFieldWithSize(formPanel, "CIN :", 2, patient.getCIN(), gbc, 25);
        JTextField ageField = addLabeledFieldWithSize(formPanel, "Âge :", 3, String.valueOf(patient.getAge()), gbc, 25);
        JTextField sexeField = addLabeledFieldWithSize(formPanel, "Sexe :", 4, patient.getSexe(), gbc, 25);
        JTextField emailField = addLabeledFieldWithSize(formPanel, "Email :", 5, patient.getEmail(), gbc, 25);
        JTextField adresseField = addLabeledFieldWithSize(formPanel, "Adresse :", 6, patient.getAdresse(), gbc, 25);
        JTextField telephoneField = addLabeledFieldWithSize(formPanel, "Téléphone :", 7, patient.getTelephone(), gbc, 25);
        JTextField risqueField = addLabeledFieldWithSize(formPanel, "Risque :", 8, patient.getRisque(), gbc, 25);
        JTextField factureField = addLabeledFieldWithSize(formPanel, "Facture Totale :", 9, String.valueOf(patient.getFacture_total()), gbc, 25);

        // Save Button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(239, 255, 251));

        JButton saveButton = new JButton("Enregistrer");
        styleButton(saveButton, new Color(79, 152, 202));
        saveButton.addActionListener(e -> {
            try {
                // Updating patient details
                patient.setNom(nomField.getText());
                patient.setPrenom(prenomField.getText());
                patient.setCIN(cinField.getText());
                patient.setAge(Integer.parseInt(ageField.getText()));
                patient.setSexe(sexeField.getText());
                patient.setEmail(emailField.getText());
                patient.setAdresse(adresseField.getText());
                patient.setTelephone(telephoneField.getText());
                patient.setRisque(risqueField.getText());
                patient.setFacture_total(Double.parseDouble(factureField.getText()));

                // Saving updated details
                ahmed.updatePatient(patient);
                JOptionPane.showMessageDialog(editFrame, "Patient modifié avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                editFrame.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(editFrame, "Veuillez vérifier les champs numériques.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (ControllerException ex) {
                JOptionPane.showMessageDialog(editFrame, "Erreur lors de la mise à jour: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            refreshTableData((DefaultTableModel) patientTable.getModel());
        });

        buttonPanel.add(saveButton);

        // Add button panel to the bottom of the form
        gbc.gridy = 10; // Place the button panel after all fields
        gbc.gridwidth = 2; // Span the button across the width of the panel
        gbc.anchor = GridBagConstraints.CENTER; // Center the button
        formPanel.add(buttonPanel, gbc);

        editFrame.add(formPanel);
        editFrame.setVisible(true);
    }
    private JTextField addLabeledFieldWithSize(JPanel panel, String labelText, int gridy, String value, GridBagConstraints gbc, int columns) {
        JLabel label = new JLabel(labelText);
        gbc.gridy = gridy; // Position by row
        gbc.gridx = 0; // Label is always in the first column
        gbc.gridwidth = 1; // Reset gridwidth
        panel.add(label, gbc);

        JTextField textField = new JTextField(value, columns);
        gbc.gridx = 1; // Field is in the second column
        panel.add(textField, gbc);

        return textField;
    }

    private JButton getEditButton(JPanel centerPanel) {
        JButton editButton = new JButton("Modifier", FontIcon.of(FontAwesomeSolid.EDIT, 22, Color.white));
        editButton.setBackground(new Color(79, 152, 202)); // Button background color
        editButton.setForeground(Color.WHITE); // Button text color
        editButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        editButton.setFocusPainted(false);
        editButton.addActionListener(e -> {
            int selectedRow = patientTable.getSelectedRow();
            if (selectedRow != -1) {
                Patient selectedPatient = ahmed.getAllPatients().get(selectedRow);
                showEditPatientFrame(selectedPatient); // Open edit frame
            } else {
                JOptionPane.showMessageDialog(centerPanel, "Veuillez sélectionner un patient à modifier.", "Avertissement", JOptionPane.WARNING_MESSAGE);
            }
        });
        return editButton;
    }

    private JButton createDeleteButton(DefaultTableModel tableModel, JPanel centerPanel) {
        JButton deleteButton = new JButton("Supprimer",FontIcon.of(FontAwesomeSolid.REMOVE_FORMAT, 22, Color.white));
        styleButton(deleteButton, new Color(255, 102, 102));
        deleteButton.addActionListener(e -> {
            int selectedRow = patientTable.getSelectedRow();
            if (selectedRow != -1) {
                int response = JOptionPane.showConfirmDialog(centerPanel, "Êtes-vous sûr de vouloir supprimer ce patient?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    try {
                        int patientId = (int) ahmed.getAllPatients().get(selectedRow).getPatient_id();
                        ahmed.deletePatient(patientId);
                        refreshTableData(tableModel);
                        JOptionPane.showMessageDialog(centerPanel, "Patient supprimé avec succès!");
                    } catch (ControllerException ex) {
                        JOptionPane.showMessageDialog(centerPanel, "Erreur lors de la suppression du patient: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(centerPanel, "Veuillez sélectionner un patient à supprimer.", "Avertissement", JOptionPane.WARNING_MESSAGE);
            }
        });
        return deleteButton;
    }

    private JButton createDetailButton(JPanel centerPanel) {
        JButton detailButton = new JButton("Details", FontIcon.of(FontAwesomeSolid.INFO_CIRCLE, 22, Color.white));
        styleButton(detailButton, new Color(79, 152, 202));
        detailButton.addActionListener(e -> {
            int selectedRow = patientTable.getSelectedRow();
            if (selectedRow != -1) {
                Patient selectedPatient = ahmed.getAllPatients().get(selectedRow);
                showPatientDetails(selectedPatient);
            } else {
                JOptionPane.showMessageDialog(centerPanel, "Veuillez sélectionner un patient pour afficher les détails.", "Avertissement", JOptionPane.WARNING_MESSAGE);
            }
        });
        return detailButton;
    }

    private void showPatientDetails(Patient patient) {
        JFrame detailFrame = new JFrame("Détails du Patient");
        detailFrame.setSize(500, 450);
        detailFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        detailFrame.setLocationRelativeTo(null);
        JPanel detailPanel = new JPanel(new BorderLayout());
        detailPanel.setBackground(new Color(239, 255, 251));

        JTable detailsTable = getPatientTable(patient);
        styleTable(detailsTable);

        JScrollPane tableScroll = new JScrollPane(detailsTable);
        detailPanel.add(tableScroll, BorderLayout.CENTER);

        JButton closeButton = new JButton("Fermer");
        styleButton(closeButton, new Color(79, 152, 202));
        closeButton.addActionListener(e -> detailFrame.dispose());
        detailPanel.add(closeButton, BorderLayout.SOUTH);

        detailFrame.add(detailPanel);
        detailFrame.setVisible(true);
    }

    private static JTable getPatientTable(Patient patient) {
        String[] columnNames = {"Attribut", "Valeur"};
        Object[][] data = {
                        {"id", patient.getPatient_id()},
                        {"Nom", patient.getNom()},
                        {"Prénom", patient.getPrenom()},
                        {"CIN", patient.getCIN()},
                        {"Âge", String.valueOf(patient.getAge())},
                        {"Sexe", patient.getSexe()},
                        {"Email", patient.getEmail()},
                        {"Adresse", patient.getAdresse()},
                        {"Téléphone", patient.getTelephone()},
                        {"Risque", patient.getRisque()},
                        {"Facture Totale", String.valueOf(patient.getFacture_total())},
                        {"Date d'Ajout", patient.getDate_ajout().toString()}
        };
JTable detailsTable = new JTable(data, columnNames);
        return detailsTable;
    }

    private void styleTable(JTable table) {
        table.setRowHeight(30);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setShowGrid(true);
        table.setIntercellSpacing(new Dimension(10, 10));
        table.setFillsViewportHeight(true);
        table.setBorder(BorderFactory.createEmptyBorder());

        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
        table.getTableHeader().setBackground(new Color(79, 152, 202));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setPreferredSize(new Dimension(0, 40));
        table.getTableHeader().setReorderingAllowed(false);
    }

    private void styleButton(JButton button, Color backgroundColor) {
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 12));
        button.setFocusPainted(false);
    }
    // Method to refresh table data
    private void refreshTableData(DefaultTableModel tableModel) {
        tableModel.setRowCount(0); // Clear all rows in the table model
        try {
            for (Patient patient : ahmed.getAllPatients()) {
                Object[] rowData = {
                        patient.getNom() + " " + patient.getPrenom(),
                        patient.getAge(),
                        patient.getSexe(),
                        patient.getEmail(),
                        patient.getAdresse(),
                        patient.getDate_ajout()
                };
                tableModel.addRow(rowData);
            }
        } catch (ControllerException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de la récupération des patients: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(239, 255, 251));
        formPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(79, 152, 202)),
                "Formulaire patient",
                0, 0, new Font("SansSerif", Font.BOLD, 16),
                new Color(79, 152, 202))
        );

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        nomField = addLabeledField(formPanel, "Nom :", 0, 0, gbc);
        prenomField = addLabeledField(formPanel, "Prénom :", 2, 0, gbc);
        ageSpinner = addLabeledSpinner(formPanel, "Âge :", 0, 1, 20, gbc);
        sexeCombo = addLabeledComboBox(formPanel, "Sexe :", 2, 1, new String[]{"Homme", "Femme"}, gbc);
        telField = addLabeledField(formPanel, "Téléphone :", 0, 2, gbc);
        emailField = addLabeledField(formPanel, "Email :", 2, 2, gbc);
        adresseField = addLabeledField(formPanel, "Adresse :", 0, 3, gbc);
        cinField = addLabeledField(formPanel, "CIN :", 2, 3, gbc);
        assuranceCombo = addLabeledComboBox(formPanel, "Assurance :", 0, 4, Assurence.values(), gbc);
        risqueCombo = addLabeledComboBox(formPanel, "Risques :", 2, 4, new String[]{"Maladies Chroniques", "Aucun"}, gbc);
        dateSpinner = addLabeledSpinner(formPanel, "Date de naissance :", 0, 5, new Date(), gbc);

        JPanel buttonPanel = createButtonPanel();
        buttonPanel.setBackground(new Color(239, 255, 251));
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);

        return formPanel;
    }
    private JTextField addLabeledField(JPanel panel, String label, int x, int y, GridBagConstraints gbc) {
        gbc.gridx = x;
        gbc.gridy = y;
        JLabel fieldLabel = new JLabel(label);
        fieldLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        panel.add(fieldLabel, gbc);

        JTextField field = new JTextField(15);
        field.setFont(new Font("SansSerif", Font.PLAIN, 14));

        gbc.gridx = x + 1;
        panel.add(field, gbc);

        return field;
    }
    private JComboBox addLabeledComboBox(JPanel panel, String label, int x, int y, Object[] items, GridBagConstraints gbc) {
        gbc.gridx = x;
        gbc.gridy = y;
        JLabel comboLabel = new JLabel(label);
        comboLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        panel.add(comboLabel, gbc);

        JComboBox comboBox = new JComboBox(items);
        comboBox.setFont(new Font("SansSerif", Font.PLAIN, 14));

        gbc.gridx = x + 1;
        panel.add(comboBox, gbc);

        return comboBox;
    }
    private JSpinner addLabeledSpinner(JPanel panel, String label, int x, int y, Object value, GridBagConstraints gbc) {
        gbc.gridx = x;
        gbc.gridy = y;
        JLabel spinnerLabel = new JLabel(label);
        spinnerLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        panel.add(spinnerLabel, gbc);

        JSpinner spinner = new JSpinner();
        spinner.setFont(new Font("SansSerif", Font.PLAIN, 14));
        if (value instanceof Integer) {
            spinner.setModel(new SpinnerNumberModel((int) value, 0, 150, 1));
        } else {
            spinner.setModel(new SpinnerDateModel());
            spinner.setEditor(new JSpinner.DateEditor(spinner, "dd/MM/yyyy"));
        }
        gbc.gridx = x + 1;
        panel.add(spinner, gbc);

        return spinner;
    }
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton clearButton = new JButton("Effacer");
        clearButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        clearButton.setBackground(new Color(190, 49, 68));
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);
        clearButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JButton validateButton = new JButton("Valider");
        validateButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        validateButton.setBackground(new Color(80, 216, 144));
        validateButton.setForeground(Color.WHITE);
        validateButton.setFocusPainted(false);
        validateButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        clearButton.addActionListener(e -> clearFormFields());
        validateButton.addActionListener(e -> {
            addPatient();
            JOptionPane.showMessageDialog(this, "Patient ajouté avec succès!");
            clearFormFields();
        });

        buttonPanel.add(clearButton);
        buttonPanel.add(validateButton);
        return buttonPanel;
    }

    private void clearFormFields() {
        nomField.setText("");
        prenomField.setText("");
        telField.setText("");
        emailField.setText("");
        adresseField.setText("");
        cinField.setText("");
        sexeCombo.setSelectedIndex(0);
        risqueCombo.setSelectedIndex(0);
        assuranceCombo.setSelectedIndex(0);
        ageSpinner.setValue(20);
        dateSpinner.setValue(new Date());
    }
    private void addPatient() {

            String nom = nomField.getText();
            String CIN = cinField.getText();
            String prenom = prenomField.getText();
            String telephone = telField.getText();
            String email = emailField.getText();
            String adress = adresseField.getText();
            String sexe = (String) sexeCombo.getSelectedItem();
            Assurence assurance = (Assurence) assuranceCombo.getSelectedItem();
            String risque = (String) risqueCombo.getSelectedItem();
            int age = (int) ageSpinner.getValue();
            LocalDate date_naissance = ((java.util.Date) dateSpinner.getValue())
                    .toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDate();

            ahmed.addPatient(new Patient(
                    CIN, nom, prenom, date_naissance, adress, telephone, email, sexe, age, assurance, risque
            ));

        refreshTableData((DefaultTableModel) patientTable.getModel());
    }

    public static void main(String[] args) {
        PatientController patientController = new PatientController(new PatientService(new PatientDAO()));
        // Create the UI frame
        SwingUtilities.invokeLater(() -> {
            PatientManagementUI patientManagementUI = new PatientManagementUI();
            patientManagementUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            patientManagementUI.setSize(800, 600);
            patientManagementUI.setVisible(true);
        });
    }
}
