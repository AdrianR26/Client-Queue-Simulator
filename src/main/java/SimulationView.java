import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SimulationView extends JFrame {

    private JTextArea textArea;
    private ArrayList<JTextField> textFields;

    public SimulationView(int numberOfQueues){
        this.textArea = new JTextArea(50, 10);
        this.textFields = new ArrayList<JTextField>();

        JPanel firstPanel  = new JPanel();

        firstPanel.add(this.textArea);

        for (int i = 0; i < numberOfQueues; i++){
            JPanel tmpPanel = new JPanel();
            String queueName = "Queue  " + (i + 1) + ":";

            tmpPanel.add(new JLabel(queueName));

            JTextField tmpTextField = new JTextField(50);
            this.textFields.add(tmpTextField);
            tmpPanel.add(tmpTextField);

            firstPanel.add(tmpPanel);
        }
        firstPanel.setLayout(new GridLayout(numberOfQueues, 1));
        JScrollPane scroller = new JScrollPane(textArea);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel main = new JPanel();
        main.setLayout(new GridLayout(2, 1));
        main.add(firstPanel);
        main.add(scroller);

        this.add(main);

        this.setSize(800, 800);
        this.setTitle("Simulation...");
        this.setVisible(true);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public ArrayList<JTextField> getTextFields() {
        return textFields;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public void addToQueue(int index, String client){
            this.textFields.get(index).setText(this.textFields.get(index).getText() + client);
    }

    public void appendToTextArea(String message){
        this.textArea.append(message + "\n");
    }

}
