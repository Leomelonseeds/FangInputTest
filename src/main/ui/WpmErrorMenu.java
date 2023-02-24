package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Error menu if input does not match paragraph length
public class WpmErrorMenu extends JFrame {

    public WpmErrorMenu() {
        super("Error");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 85);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        JLabel errorLabel = new JLabel("Error: Input and paragraph do not "
                + "have the same amount of words! Please try again.");
        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(errorLabel);

        JButton okButton = new JButton("Ok");
        okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        okButton.addActionListener(e -> {
            dispose();
            new WpmMenu();
        });
        contentPane.add(okButton);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
