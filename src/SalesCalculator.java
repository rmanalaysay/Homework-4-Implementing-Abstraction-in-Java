import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Abstract class representing a generic item
abstract class Item {
    protected String itemName;
    protected double price;

    public Item(String itemName, double price) {
        this.itemName = itemName;
        this.price = price;
    }

    public abstract double calculateTotal();

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }
}

// Concrete class for Phones
class Phone extends Item {
    private int quantitySold;

    public Phone(String itemName, double price, int quantitySold) {
        super(itemName, price);
        this.quantitySold = quantitySold;
    }

    @Override
    public double calculateTotal() {
        return price * quantitySold;
    }
}

// Concrete class for Repair services
class Repair extends Item {
    private int hours;

    public Repair(String itemName, double pricePerHour, int hours) {
        super(itemName, pricePerHour);
        this.hours = hours;
    }

    @Override
    public double calculateTotal() {
        return price * hours;
    }
}

public class SalesCalculator {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sales Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 250);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10)); // Margins
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // Padding around the panel

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS)); // Vertical alignment

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS)); // Vertical alignment

        // Adjust size of text fields
        Dimension textFieldSize = new Dimension(150, 30);

        // Phone details input
        JLabel phoneLabel = new JLabel("Phone Price:");
        JTextField phonePriceField = new JTextField();
        phonePriceField.setPreferredSize(textFieldSize);
        phonePriceField.setMaximumSize(textFieldSize);

        JLabel phoneQuantityLabel = new JLabel("Quantity Sold:");
        JTextField phoneQuantityField = new JTextField();
        phoneQuantityField.setPreferredSize(textFieldSize);
        phoneQuantityField.setMaximumSize(textFieldSize);

        // Phone total sales result
        JLabel phoneTotalLabel = new JLabel("Phone Total Sales:");
        JLabel phoneTotalResult = new JLabel("-");

        // Add components to the left panel
        leftPanel.add(phoneLabel);
        leftPanel.add(phonePriceField);
        leftPanel.add(phoneQuantityLabel);
        leftPanel.add(phoneQuantityField);
        leftPanel.add(Box.createVerticalStrut(15)); // Break
        leftPanel.add(phoneTotalLabel);
        leftPanel.add(phoneTotalResult);

        // Repair details input
        JLabel repairLabel = new JLabel("Repair Price per Hour:");
        JTextField repairPriceField = new JTextField();
        repairPriceField.setPreferredSize(textFieldSize);
        repairPriceField.setMaximumSize(textFieldSize);

        JLabel repairHoursLabel = new JLabel("Number of Hours:");
        JTextField repairHoursField = new JTextField();
        repairHoursField.setPreferredSize(textFieldSize);
        repairHoursField.setMaximumSize(textFieldSize);

        // Repair total sales result
        JLabel repairTotalLabel = new JLabel("Repair Total Sales:");
        JLabel repairTotalResult = new JLabel("-");

        // Add components to the right panel
        rightPanel.add(repairLabel);
        rightPanel.add(repairPriceField);
        rightPanel.add(repairHoursLabel);
        rightPanel.add(repairHoursField);
        rightPanel.add(Box.createVerticalStrut(15)); // Break
        rightPanel.add(repairTotalLabel);
        rightPanel.add(repairTotalResult);

        // Calculate button
        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Read inputs for Phone
                    double phonePrice = Double.parseDouble(phonePriceField.getText());
                    int phoneQuantity = Integer.parseInt(phoneQuantityField.getText());

                    // Read inputs for Repair
                    double repairPricePerHour = Double.parseDouble(repairPriceField.getText());
                    int repairHours = Integer.parseInt(repairHoursField.getText());

                    // Create objects
                    Phone phone = new Phone("Phone", phonePrice, phoneQuantity);
                    Repair repair = new Repair("Repair", repairPricePerHour, repairHours);

                    // Calculate totals
                    phoneTotalResult.setText(String.format("₱%.2f", phone.calculateTotal()));
                    repairTotalResult.setText(String.format("₱%.2f", repair.calculateTotal()));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add panels to main panel
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        centerPanel.add(leftPanel);
        centerPanel.add(rightPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(calculateButton);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
