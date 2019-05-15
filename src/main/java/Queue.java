import javax.swing.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Queue implements Runnable{
    private BlockingQueue<Client> clients;
    private AtomicInteger waitingPeriod;

    private JTextField textField;
    private JTextArea textArea;

    private float averageWaitingTime;
    private float averageServiceTime;
    private int numberOfClients;

    public Queue(int maxCapacity, JTextField textField, JTextArea textArea){
        this.textField = textField;
        this.textArea = textArea;

        this.averageWaitingTime = 0.0f;
        this.averageServiceTime = 0.0f;
        this.numberOfClients = 0;

        this.clients = new ArrayBlockingQueue<Client>(maxCapacity);
        this.waitingPeriod = new AtomicInteger(0);
        (new Thread(this)).start();
    }

    public synchronized int getWaitingPeriod(){
        return this.waitingPeriod.intValue();
    }

    public synchronized void setWaitingPeriod(int value){
        this.waitingPeriod.set(value);
    }

    public synchronized void addClient(Client c){
        try {
            clients.put(c);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void Wait (int time){
        try {
            wait(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized int getSize(){
        return this.clients.size();
    }

    public void deleteFromTextField(int clientNumber){
        String tmp = this.textField.getText();
        String toDelete = "Client " + clientNumber + "--";

        this.textField.setText(tmp.replaceAll(toDelete, ""));
    }

    public void run() {
        while (true) {
            try {
                Client currentClient = clients.take();

                if (currentClient.isEndSignal() == true){
                    break;
                }

                numberOfClients++;
                averageWaitingTime += currentClient.getFinishTime() - currentClient.getArrivalTime();
                averageServiceTime  += currentClient.getServiceTime();

                Wait(currentClient.getServiceTime());
                deleteFromTextField(currentClient.getClientNumber());
                textArea.append("Client " + currentClient.getClientNumber() + " leaved at time " + currentClient.getFinishTime() + "\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        textArea.append("\nThread " + Thread.currentThread().getId() + " finished: "
                + "average waiting time is " + (averageWaitingTime / numberOfClients)
                + " and average service time is " + (averageServiceTime / numberOfClients) + "\n");

    }

}
