package org.example.Views;

import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Design extends JFrame {
    private JPanel panel;
    private JMenuBar menuBar;

    // Buttons as instance attributes
    protected JButton button1; // Profile
    protected JButton button2; // Employees
    protected JButton button3; // Agenda
    protected JButton button4; // Patients
    protected JButton button5; // Finance
    protected JButton button6; // Stock
    protected JButton button7; // Parametre
    protected JButton button8; // Deconnexion

    public Design() {
        setTitle("Swing Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 750);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create the menu bar
        menuBar = new JMenuBar();
        menuBar.setBackground(new Color(100, 100, 100)); // Use a valid RGB color
        menuBar.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        setJMenuBar(menuBar);

        // Create the sidebar
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(79, 153, 202));

        // Initialize buttons
        button1 = new JButton("Profile", FontIcon.of(FontAwesomeSolid.USER, 22, Color.black));
        button2 = new JButton("Employees", FontIcon.of(FontAwesomeSolid.USERS, 22, Color.black));
        button3 = new JButton("Agenda", FontIcon.of(FontAwesomeSolid.CALENDAR_CHECK, 22, Color.black));
        button4 = new JButton("Patients", FontIcon.of(FontAwesomeSolid.TOOTH, 22, Color.black));
        button5 = new JButton("Finance", FontIcon.of(FontAwesomeSolid.CHART_LINE, 22, Color.black));
        button6 = new JButton("Stock", FontIcon.of(FontAwesomeSolid.CUBES, 22, Color.black));
        button7 = new JButton("Parametre", FontIcon.of(FontAwesomeSolid.COG, 22, Color.black));
        button8 = new JButton("Deconnexion", FontIcon.of(FontAwesomeSolid.SIGN_OUT_ALT, 22, Color.black));

        // Add buttons to the sidebar with spacing
        int verticalSpace = 10;
        int horizontalSpace = 0;
        addButtonWithSpacing(sidebar, button1, verticalSpace, horizontalSpace);
        addButtonWithSpacing(sidebar, button2, verticalSpace, horizontalSpace);
        addButtonWithSpacing(sidebar, button3, verticalSpace, horizontalSpace);
        addButtonWithSpacing(sidebar, button4, verticalSpace, horizontalSpace);
        addButtonWithSpacing(sidebar, button5, verticalSpace, horizontalSpace);
        addButtonWithSpacing(sidebar, button6, verticalSpace, horizontalSpace);
        addButtonWithSpacing(sidebar, button7, verticalSpace, horizontalSpace);
        addButtonWithSpacing(sidebar, button8, verticalSpace, horizontalSpace);

        // Add the sidebar to the main panel
        panel.add(sidebar, BorderLayout.WEST);

        // Create a main content area
        JPanel mainContent = new JPanel();
        mainContent.setBackground(Color.WHITE);
        mainContent.setLayout(new BorderLayout()); // Flexible layout for future content
        JLabel placeholder = new JLabel("Main Content Area", SwingConstants.CENTER);
        placeholder.setFont(new Font("Arial", Font.BOLD, 24));
        mainContent.add(placeholder, BorderLayout.CENTER);

        // Add the main content area to the main panel
        panel.add(mainContent, BorderLayout.CENTER);

        // Add the main panel to the frame
        add(panel);
        setLocationRelativeTo(null);

        // Add a component listener to adjust the layout on resize
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustMenuBarHeight();
                adjustSidebarWidth(sidebar);
            }
        });
    }

    protected JPanel getMainPanel() {
        return panel;
    }

    private void adjustMenuBarHeight() {
        int newHeight = getHeight() / 20; // Example: 5% of the frame height
        menuBar.setBorder(BorderFactory.createEmptyBorder(newHeight, 0, 10, 0));
        menuBar.revalidate();
    }

    private void adjustSidebarWidth(JPanel sidebar) {
        int newWidth = getWidth() / 7; // Example: Sidebar takes 1/6th of the frame width
        sidebar.setPreferredSize(new Dimension(newWidth, 0));
        sidebar.revalidate();
    }

    public static void addButtonWithSpacing(JPanel panel, JButton button, int verticalSpace, int horizontalSpace) {
        panel.add(Box.createVerticalStrut(verticalSpace));
        panel.add(button);
        panel.add(Box.createHorizontalStrut(horizontalSpace));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            Design design = new Design();
//            design.setVisible(true);
//        });
//    }
}