import java.util.ArrayList;
import java.util.Random;

public class Scheduler implements Runnable{

    private ArrayList<Queue> queues;
    private ArrayList<Client> clientsToBeServer;

    private int minArrivingTime;
    private int maxArrivingTime;

    private int minSeviceTime;
    private int maxServiceTime;

    private int simulationInterval;
    private int maxSimultaneousArrivedClients;

    private int peakHour;
    private int numberOfClients;

    private SimulationView simView;

    public Scheduler(int queuesNr, int minArrivingTime, int maxArrivingTime, int minSeviceTime, int maxServiceTime, int simulationInterval, int maxSimultaneousArrivedClients){
        this.minArrivingTime = minArrivingTime;
        this.maxArrivingTime = maxArrivingTime;
        this.minSeviceTime = minSeviceTime;
        this.maxServiceTime = maxServiceTime;
        this.simulationInterval = simulationInterval;
        this.maxSimultaneousArrivedClients = maxSimultaneousArrivedClients;

        this.peakHour = 0;
        this.numberOfClients = 0;

        this.queues = new ArrayList<Queue>();

        this.simView = new SimulationView(queuesNr);

        for (int i = 0; i < queuesNr; i++){
            queues.add(new Queue(100, simView.getTextFields().get(i), simView.getTextArea()));
        }

    }

    private void generateClients(){
        clientsToBeServer = new ArrayList<Client>();
        Random randomNumber = new Random();
        int clientNumber = 1;

        int i = 0;
        while (i < simulationInterval){
            int arrivedClients = randomNumber.nextInt(maxSimultaneousArrivedClients);

            for (int j = 0; j < arrivedClients; j++){
                int arrivalTime = i;
                int serviceTime = randomNumber.nextInt(maxServiceTime - minSeviceTime + 1) + minSeviceTime;
                clientsToBeServer.add(new Client(arrivalTime, serviceTime, clientNumber));
                clientNumber++;
            }

            i += randomNumber.nextInt(maxArrivingTime - minArrivingTime + 1) + minArrivingTime;
        }

    }

    private int getQueueIndex(){
        int index = 0;
        int minWaitingPetriod = Integer.MAX_VALUE;

        for (Queue q : queues){
            if (q.getWaitingPeriod() < minWaitingPetriod){
                index = queues.indexOf(q);
                minWaitingPetriod = q.getWaitingPeriod();
            }
        }

        return index;
    }

    private void updatePeakHour(int timer){
        int total = 0;
        for (Queue q : queues){
            total += q.getSize();
        }
        if (total > this.numberOfClients){
            peakHour = timer;
            this.numberOfClients = total;
        }
    }

    public void run() {
        synchronized (this){
            this.generateClients();
            int timer = 1;
            while (timer < simulationInterval){
                try {
                    for (Client c : clientsToBeServer){
                        if (c.getArrivalTime() == timer){
                            simView.appendToTextArea("Client " + c.getClientNumber() + " arrived at time " + c.getArrivalTime() + " (Service Time = " + c.getServiceTime() + ")");
                            int index = this.getQueueIndex();
                            queues.get(index).addClient(c);
                            c.setFinishTime(c.getArrivalTime() + queues.get(index).getWaitingPeriod() + c.getServiceTime());
                            queues.get(index).setWaitingPeriod(queues.get(index).getWaitingPeriod() + c.getServiceTime());
                            simView.appendToTextArea("Client " + c.getClientNumber() + " was sent to queue " + index);
                            simView.addToQueue(index, "Client " + c.getClientNumber() + "--");
                            updatePeakHour(timer);
                        }
                    }
                    wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timer++;

                for (Queue q : queues){
                    if (q.getWaitingPeriod() != 0){
                        q.setWaitingPeriod(q.getWaitingPeriod() - 1);
                    }
                }
            }

            Client endSignal = new Client();
            for (Queue q : queues){
              q.addClient(endSignal);
            }

            simView.appendToTextArea("\nPeak-hour: " + peakHour + " with " + numberOfClients + " clients!");
        }
   }

}
