import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DataInput extends JFrame {

    private JLabel arrivingTimeLabel = new JLabel("Arriving time: ");
    private JLabel serviceTimeLabel = new JLabel("Service time: ");
    private JLabel numberOfQueuesLabel = new JLabel("Number of queues:");
    private JLabel simulationIntervalLabel = new JLabel("Simulation interval (starts from 0): ");
    private JLabel simultanyousArrivedClients = new JLabel("Number of clients at the same time: ");

    private JPanel arrivingTimePanel = new JPanel();
    private JPanel serviceTimePanel = new JPanel();
    private JPanel numberOfQueuesPanel = new JPanel();
    private JPanel simulationIntervalPanel = new JPanel();
    private JPanel sArrivedClientsPanel = new JPanel();

    private JTextField minArrivingTime = new JTextField();
    private JTextField maxArrivingTime = new JTextField();

    private JTextField minServiceTime = new JTextField();
    private JTextField maxServiceTime = new JTextField();

    private JTextField numberOfQueues = new JTextField();
    private JTextField simulationInterval = new JTextField();
    private JTextField sArrivedClients = new JTextField();

    private JButton startButton = new JButton("Start");

    public DataInput(){
        arrivingTimePanel.setLayout(new GridLayout(1, 3));
        arrivingTimePanel.add(arrivingTimeLabel);
        arrivingTimePanel.add(minArrivingTime);
        arrivingTimePanel.add(maxArrivingTime);

        serviceTimePanel.setLayout(new GridLayout(1 ,3));
        serviceTimePanel.add(serviceTimeLabel);
        serviceTimePanel.add(minServiceTime);
        serviceTimePanel.add(maxServiceTime);

        numberOfQueuesPanel.setLayout(new GridLayout(1, 2));
        numberOfQueuesPanel.add(numberOfQueuesLabel);
        numberOfQueuesPanel.add(numberOfQueues);

        simulationIntervalPanel.setLayout(new GridLayout(1, 2));
        simulationIntervalPanel.add(simulationIntervalLabel);
        simulationIntervalPanel.add(simulationInterval);


        sArrivedClientsPanel.setLayout(new GridLayout(1, 2));
        sArrivedClientsPanel.add(simultanyousArrivedClients);
        sArrivedClientsPanel.add(sArrivedClients);

        JPanel main = new JPanel();
        GridLayout gridLayout = new GridLayout(6, 1);
        gridLayout.setVgap(50);
        main.setLayout(gridLayout);

        main.add(arrivingTimePanel);
        main.add(serviceTimePanel);
        main.add(numberOfQueuesPanel);
        main.add(simulationIntervalPanel);
        main.add(sArrivedClientsPanel);
        main.add(startButton);

        this.add(main);

        this.setTitle("Client Queues Simulator");
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setSize(500, 500);
    }

    public int getMinArrivingTime(){
        return Integer.parseInt(this.minArrivingTime.getText());
    }

    public int getMaxArrivingTime(){
        return Integer.parseInt(this.maxArrivingTime.getText());
    }

    public int getMinServiceTime(){
        return Integer.parseInt(this.minServiceTime.getText());
    }

    public int getMaxServiceTime(){
        return Integer.parseInt(this.maxServiceTime.getText());
    }

    public int getNumberOfQueues(){
        return Integer.parseInt(this.numberOfQueues.getText());
    }

    public int getSimulationInterval(){
        return Integer.parseInt(this.simulationInterval.getText());
    }

    public int getSymArrivedClients(){
        return Integer.parseInt(this.sArrivedClients.getText());
    }

    public void addStartButtonListener(ActionListener a){
        this.startButton.addActionListener(a);
    }

}
