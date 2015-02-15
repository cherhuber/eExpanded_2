

/**
 *
 * @author cheryl
 */
public class Floor {
    //int numWPassengers;
    private int wPassengers;
    private int[] wPassengerDest;
    public int curFloor;
    public Building curBuilding;
 
    public Floor(Building b, int f){
        wPassengers = 0;
        curFloor = f;
        curBuilding = b;
    }
    public void setWPassengers(int n){
        wPassengers = n;
    }
    public int getDestination(int wPassNum){
        return wPassengerDest[wPassNum];
    }
    public int passengersWaiting(){
        return wPassengers;
    }
    public void waitForElevator(){
        wPassengers++;
        int[] temp = new int[wPassengers];
        System.arraycopy(wPassengerDest, 0, temp, 0, wPassengers-1);
        temp[wPassengers]=1;
        wPassengerDest = temp;
    }
    public void waitForElevatorOn1(int dest){
        wPassengers++;
        int[] temp = new int[wPassengers];
        if(wPassengerDest != null)
            System.arraycopy(wPassengerDest, 0, temp, 0, wPassengerDest.length);
        temp[wPassengers-1]=dest;
        wPassengerDest = temp;
    }
}
