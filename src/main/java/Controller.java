import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

    private DataInput dataInputView;

    public Controller(DataInput dataInputView){
        this.dataInputView = dataInputView;

        this.dataInputView.addStartButtonListener(new startButtonListener());
    }

    class startButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            int minArrivingTime = dataInputView.getMinArrivingTime();
            int maxArrivingTime = dataInputView.getMaxArrivingTime();

            int minServiceTime = dataInputView.getMinServiceTime();
            int maxServiceTime = dataInputView.getMaxServiceTime();

            int numberOfQueues = dataInputView.getNumberOfQueues();
            int simulationInterval = dataInputView.getSimulationInterval();
            int symArrivedClients = dataInputView.getSymArrivedClients();

            dataInputView.setVisible(false);

            Scheduler scheduler = new Scheduler(numberOfQueues, minArrivingTime, maxArrivingTime, minServiceTime, maxServiceTime, simulationInterval, symArrivedClients);
            (new Thread(scheduler)).start();
        }
    }

}
