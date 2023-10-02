import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;

public class QueueTicketingApp {

    private Queue<String> waitingQueue = new LinkedList<>();
    private boolean[] countersOnline = {true, true, true, true};
    private String[] currentTickets = {null, null, null, null};

    private JLabel[] counterStatusLabels;
    private JLabel nowServingLabel;
    private JLabel lastNumberLabel;
    private JLabel[] customerCounterStatusLabels;
    private JLabel[] customerCounterStatusLabelLights; // Lights to show counter status

    public QueueTicketingApp() {
        createCustomerView();
        createCounterView();
    }

    private void createCustomerView() {
        JFrame customerFrame = new JFrame("Customer View");
        customerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        customerFrame.setSize(800, 400);

        JPanel customerPanel = new JPanel(new BorderLayout());

        // Create a panel for the customer-related components (top)
        JPanel customerTopPanel = new JPanel(new GridLayout(3, 1));
        nowServingLabel = new JLabel("Now Serving: ");
        nowServingLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center-align text
        lastNumberLabel = new JLabel("Last Number: ");
        lastNumberLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center-align text
        JButton takeTicketButton = new JButton("Take a Number");
        takeTicketButton.setPreferredSize(new Dimension(150, 40)); // Set button size
        takeTicketButton.setMaximumSize(new Dimension(150, 40));

        takeTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newTicket = "T" + (waitingQueue.size() + 1);
                waitingQueue.add(newTicket);
                lastNumberLabel.setText("Last Number: " + newTicket);
            }
        });

        customerTopPanel.add(nowServingLabel);
        customerTopPanel.add(lastNumberLabel);
        customerTopPanel.add(takeTicketButton);

        // Create a panel for the counters (bottom)
        JPanel counterPanel = new JPanel(new GridLayout(1, 4));

        customerCounterStatusLabels = new JLabel[4];
        customerCounterStatusLabelLights = new JLabel[4];

        for (int i = 0; i < 4; i++) {
            JPanel individualCounterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Center-align text
            customerCounterStatusLabels[i] = new JLabel("Counter " + (i + 1) + ": Serving - ");
            customerCounterStatusLabels[i].setHorizontalAlignment(SwingConstants.CENTER); // Center-align text
            customerCounterStatusLabelLights[i] = new JLabel("●"); // Light indicator
            customerCounterStatusLabelLights[i].setForeground(Color.GREEN); // Initially green

            individualCounterPanel.add(customerCounterStatusLabelLights[i]);
            individualCounterPanel.add(customerCounterStatusLabels[i]);
            counterPanel.add(individualCounterPanel);
        }

        // Add the top and bottom panels to the main panel
        customerPanel.add(customerTopPanel, BorderLayout.NORTH);
        customerPanel.add(counterPanel, BorderLayout.CENTER);

        customerFrame.add(customerPanel);
        customerFrame.setVisible(true);
    }




    private void createCounterView() {
        JFrame counterFrame = new JFrame("Counter Management");
        counterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        counterFrame.setSize(700, 150);

        JPanel counterPanel = new JPanel();
        counterPanel.setLayout(new GridLayout(1, 4)); // 1 row, 4 columns for counters
        counterStatusLabels = new JLabel[4];

        for (int i = 0; i < 4; i++) {
            counterStatusLabels[i] = new JLabel("Counter " + (i + 1) + ": Online");
            JPanel counterInnerPanel = new JPanel(); // Create an inner panel for each counter
            counterInnerPanel.setLayout(new BorderLayout()); // Use BorderLayout for the inner panel

            JButton toggleCounterButton = new JButton("Go Offline");
            JButton completeCurrentButton = new JButton("Complete Current");
            JButton callNextButton = new JButton("Call Next");

            int counterIndex = i;

            toggleCounterButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    countersOnline[counterIndex] = !countersOnline[counterIndex];
                    toggleCounterButton.setText(countersOnline[counterIndex] ? "Go Offline" : "Go Online");
                    updateCustomerViewCounterStatus(counterIndex);
                }
            });

            completeCurrentButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentTickets[counterIndex] = null;
                    updateCustomerViewCounterStatus(counterIndex);
                }
            });

            callNextButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!waitingQueue.isEmpty() && countersOnline[counterIndex]) {
                        currentTickets[counterIndex] = waitingQueue.poll();
                        nowServingLabel.setText("Now Serving: " + currentTickets[counterIndex]);
                        updateCustomerViewCounterStatus(counterIndex);
                    } else if (waitingQueue.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No tickets in the waiting queue for Counter " + (counterIndex + 1) + ".");
                    }
                }
            });

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(3, 1)); // 3 rows, 1 column for buttons
            buttonPanel.add(toggleCounterButton);
            buttonPanel.add(completeCurrentButton);
            buttonPanel.add(callNextButton);

            counterInnerPanel.add(counterStatusLabels[i], BorderLayout.NORTH); // Place the counter label at the top
            counterInnerPanel.add(buttonPanel, BorderLayout.SOUTH); // Place the button panel at the bottom
            counterInnerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // Add some spacing
            counterPanel.add(counterInnerPanel); // Add the inner panel (counter + buttons) to the counter panel
        }

        counterFrame.add(counterPanel);
        counterFrame.setVisible(true);
    }



    private void updateCustomerViewCounterStatus(int counterIndex) {
        if (countersOnline[counterIndex]) {
            counterStatusLabels[counterIndex].setText("Counter " + (counterIndex + 1) + ": Online");
            counterStatusLabels[counterIndex].setForeground(Color.GREEN);
            customerCounterStatusLabelLights[counterIndex].setForeground(Color.GREEN);
        } else {
            counterStatusLabels[counterIndex].setText("Counter " + (counterIndex + 1) + ": Offline");
            counterStatusLabels[counterIndex].setForeground(Color.RED);
            customerCounterStatusLabelLights[counterIndex].setForeground(Color.RED);
        }

        if (currentTickets[counterIndex] != null) {
            customerCounterStatusLabels[counterIndex].setText("Counter " + (counterIndex + 1) + ": Serving - " + currentTickets[counterIndex]);
        } else {
            customerCounterStatusLabels[counterIndex].setText("Counter " + (counterIndex + 1) + ": Serving - ");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new QueueTicketingApp();
        });
    }
}
