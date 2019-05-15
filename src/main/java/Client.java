public class Client {

    private int arrivalTime;
    private int serviceTime;
    private int finishTime;

    private int clientNumber;

    boolean endSignal;

    public Client(int arrivalTime, int serviceTime, int clientNumber){
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.clientNumber = clientNumber;
        this.endSignal = false;
    }

    //Constructor for endSignal-Client
    public Client(){
        this.endSignal = true;
    }

    public boolean isEndSignal() {
        return endSignal;
    }

    public int getClientNumber() {
        return clientNumber;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }


}
