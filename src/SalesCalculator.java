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

// GUI Component for Item Input and Calculation
class ItemPanel extends JPanel {
    private JTextField priceField;
    private JTextField quantityField;
    private JLabel resultLabel;

    public ItemPanel(String itemName, String priceLabel, String quantityLabel, String resultText) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel itemLabel = new JLabel(itemName);
        JLabel pricePrompt = new JLabel(priceLabel);
        JLabel quantityPrompt = new JLabel(quantityLabel);

        priceField = new JTextField();
        quantityField = new JTextField();
        resultLabel = new JLabel(resultText);

        Dimension fieldSize = new Dimension(120, 20);
        priceField.setPreferredSize(fieldSize);
        quantityField.setPreferredSize(fieldSize);

        add(itemLabel);
        add(pricePrompt);
        add(priceField);
        add(quantityPrompt);
        add(quantityField);
        add(Box.createVerticalStrut(15));
        add(new JLabel("Total:"));
        add(resultLabel);
    }

    public double getPrice() throws NumberFormatException {
        return Double.parseDouble(priceField.getText());
    }

    public int getQuantity() throws NumberFormatException {
        return Integer.parseInt(quantityField.getText());
    }

    public void setResult(double result) {
        resultLabel.setText(String.format("₱%.2f", result));
    }
}

public class SalesCalculator {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sales Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Item panels
        ItemPanel phonePanel = new ItemPanel("PHONE", "Price per Unit:", "Quantity Sold:", "-");
        ItemPanel repairPanel = new ItemPanel("REPAIR", "Price per Hour:", "Hours Worked:", "-");

        // Button to calculate totals
        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Phone calculation
                    Phone phone = new Phone("Phone", phonePanel.getPrice(), phonePanel.getQuantity());
                    phonePanel.setResult(phone.calculateTotal());

                    // Repair calculation
                    Repair repair = new Repair("Repair", repairPanel.getPrice(), repairPanel.getQuantity());
                    repairPanel.setResult(repair.calculateTotal());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Organize panels
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        centerPanel.add(phonePanel);
        centerPanel.add(repairPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(calculateButton);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
