package org.example.Views;

import org.example.Controllers.PatientController;
import org.example.MODELS.Patient;
import org.example.MODELS.enums.Assurence;
import org.example.Repositories.Implementation.PatientDAO;
import org.example.Services.Implementation.PatientService;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;



public class PatientManagementUI extends Design {

    PatientController ahmed = new PatientController(new PatientService(new PatientDAO()));
    private JTable patientTable;
    private JTextField searchField;
    private JTextField nomField;
    private JTextField prenomField;
    private JTextField telField;
    private JTextField emailField;
    private JTextField adresseField;
    private JTextField cinField;
    private JComboBox<String> sexeCombo;
    JComboBox<Assurence> assuranceCombo;
    private JComboBox<String> risqueCombo;
    private JSpinner ageSpinner;
    private JSpinner dateSpinner;

    public PatientManagementUI() {
        super();
        setLocationRelativeTo(null);

        // Build the main panel
        JPanel mainPanel = createMainPanel();

        // Add the main panel to the parent container (Design class)
        getMainPanel().add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Creates and returns the main panel containing all UI components.
     */
    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Top panel: Title + Search
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(230, 230, 250));
        JLabel titleLabel = new JLabel("Liste des patients");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(new Color(102, 51, 153));
        topPanel.add(titleLabel, BorderLayout.WEST);

        // Search Field
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchField = new JTextField(15);
        searchPanel.add(new JLabel("Rechercher : "));
        searchPanel.add(searchField);

        topPanel.add(searchPanel, BorderLayout.EAST);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Center panel: Table
        JPanel centerPanel = new JPanel(new BorderLayout());
        String[] columnNames = {
            "Nom Complet", "Âge", "Sexe", "Email", "Adresse",
            "Date d'Ajout", "Action"
        };
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        // ? Fill with mock data
//        tableModel.addRow(new Object[]{ahmed.getPatientById(1)});
        tableModel.addRow(new Object[]{
            "Tanae El Ghali", "28", "F", "tanae.elghali@gmail.com",
            "Avenue Med VI Villa tanae souissi", "10/10/2019", "Dossier / Edit / Suppr"
        });



        patientTable = new JTable(tableModel);
        patientTable.setRowHeight(30);
        // Center text in table cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < patientTable.getColumnCount(); i++) {
            patientTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane tableScroll = new JScrollPane(patientTable);
        centerPanel.add(tableScroll, BorderLayout.CENTER);

        JPanel tableFooter = new JPanel(new FlowLayout(FlowLayout.LEFT));
        centerPanel.add(tableFooter, BorderLayout.SOUTH);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        // Patient form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(102, 51, 153)),
                "Formulaire patient",
                0, 0, new Font("SansSerif", Font.BOLD, 16),
                new Color(102, 51, 153)
            )
        );
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Row 1
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Nom :"), gbc);
        nomField = new JTextField(15);
        gbc.gridx = 1;
        formPanel.add(nomField, gbc);

        gbc.gridx = 2;
        formPanel.add(new JLabel("Prénom :"), gbc);
        prenomField = new JTextField(15);
        gbc.gridx = 3;
        formPanel.add(prenomField, gbc);

        // Row 2
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Âge :"), gbc);
        ageSpinner = new JSpinner(new SpinnerNumberModel(20, 0, 120, 1));
        gbc.gridx = 1;
        formPanel.add(ageSpinner, gbc);

        gbc.gridx = 2;
        formPanel.add(new JLabel("Sexe :"), gbc);
        sexeCombo = new JComboBox<>(new String[]{"Homme", "Femme"});
        gbc.gridx = 3;
        formPanel.add(sexeCombo, gbc);
        // Row 3
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Téléphone :"), gbc);
        telField = new JTextField(15);
        gbc.gridx = 1;
        formPanel.add(telField, gbc);

        gbc.gridx = 2;
        formPanel.add(new JLabel("Email :"), gbc);
        emailField = new JTextField(15);
        gbc.gridx = 3;
        formPanel.add(emailField, gbc);
        // Row 4
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Adresse :"), gbc);
        adresseField = new JTextField(15);
        gbc.gridx = 1;
        formPanel.add(adresseField, gbc);

        gbc.gridx = 2;
        formPanel.add(new JLabel("CIN :"), gbc);
        cinField = new JTextField(15);
        gbc.gridx = 3;
        formPanel.add(cinField, gbc);
        // Row 5
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Assurance :"), gbc);
        assuranceCombo = new JComboBox<>(Assurence.values());
        assuranceCombo.setSelectedIndex(0);
        System.out.println("Assurance Combo Initialized: " + (assuranceCombo != null));
        gbc.gridx = 1;
        assert assuranceCombo != null;
        formPanel.add(assuranceCombo, gbc);

        gbc.gridx = 2;
        formPanel.add(new JLabel("Risques du patient :"), gbc);
        risqueCombo = new JComboBox<>(new String[]{"Maladies Chroniques", "Aucun"});
        gbc.gridx = 3;
        formPanel.add(risqueCombo, gbc);
        // Row 6
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(new JLabel("Date de naissance :"), gbc);
        dateSpinner = new JSpinner(new SpinnerDateModel());
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy"));
        gbc.gridx = 1;
        formPanel.add(dateSpinner, gbc);
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton effacerBtn = new JButton("Effacer");
        JButton validerBtn = new JButton("Valider");
        buttonPanel.add(effacerBtn);
        buttonPanel.add(validerBtn);

        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);
        // center panel: image link hna
        JLabel bottomPanel = toothImage("src/main/resources/images/c.jpg");
        // Put formPanel + bottomPanel together
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());
        southPanel.add(formPanel, BorderLayout.SOUTH);
        southPanel.add(bottomPanel);

        mainPanel.add(southPanel, BorderLayout.SOUTH);
        System.out.println(java.util.Arrays.toString(Assurence.values()));
        System.out.println("ComboBox item count: " + assuranceCombo.getItemCount());
        System.out.println("Selected item: " + assuranceCombo.getSelectedItem());
        // Example button actions
        effacerBtn.addActionListener(e -> clearFormFields());
        validerBtn.addActionListener(e -> {
            addPatient();
            JOptionPane.showMessageDialog(
                PatientManagementUI.this,
                "Données du patient validées!"
            );
            clearFormFields();
        });
        return mainPanel;
    }


    private void clearFormFields() {
        nomField.setText("John");
        prenomField.setText("Doe");
        telField.setText("06523732");
        emailField.setText("yassine@gmail.com");
        adresseField.setText("avenue rabat");
        cinField.setText("M237823");
        sexeCombo.setSelectedIndex(0);
        risqueCombo.setSelectedIndex(0);
        assuranceCombo.setSelectedIndex(0);
        ageSpinner.setValue(20);
        dateSpinner.setValue(new java.util.Date());
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

    }
    private JLabel toothImage(String placeofimage) {
        ImageIcon imageIcon = new ImageIcon(placeofimage); // Replace with your image path
        Image image = imageIcon.getImage(); // Get the Image from ImageIcon
        Image scaledImage = image.getScaledInstance(getWidth()-30, 200, Image.SCALE_SMOOTH); // Scale to desired size
        imageIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(imageIcon);
        return imageLabel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PatientManagementUI frame = new PatientManagementUI();
            frame.setVisible(true);

        });
    }
}