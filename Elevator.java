/**
 *
 * @author cheryl
 */
public class Elevator {
    public final int MAX_CAPACITY = 10;
    private final int numFloors;
    private int currentFloor;
    private int curNumPassengers;
    private boolean goingUp;//direction of elevator
    private int[] passengerTargets;//destination floor of passengers
    private boolean[] stops;//Flags if there are passengers waiting
    private Building curBuilding;
    
    public Elevator(Building b){
        curBuilding = b;
        numFloors = Building.FLOORS;
        currentFloor = 0;
        goingUp = true;
        stops = new boolean[numFloors];
        passengerTargets = new int[numFloors];
        curNumPassengers = 0;
        for (int i = 0;i<numFloors;i++){
            stops[i] = true;
            passengerTargets[i]=0;
        }
    }
    public int getCurFloor(){
        return currentFloor;
    }
    public boolean getDirection(){
        return goingUp;
    }
    public int getNumPassengers(){
        int passengers = 0;
        for(int i=0; i<passengerTargets.length;i++){
            if(passengerTargets[i]>0)
                passengers += passengerTargets[i];
        }
        return passengers;
    }
    public int move(){ //returns number of passengers that are
                       //waiting on currentFloor
        if(curNumPassengers>0)//reinitializing passengers on floor
            curNumPassengers = 0;
        if(goingUp)
            currentFloor++;    
        else
            currentFloor--;
        
        if(currentFloor == 0)
            goingUp = true;
        else if (currentFloor == numFloors-1)
            goingUp = false;
        
        if(passengerTargets[currentFloor] != 0 || stops[currentFloor])
            stop();
        
        try{
            Thread.sleep(2000);//2000 milliseconds = 2 seconds
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
        return curNumPassengers;
    }//move
    public void stop() {
        curNumPassengers = passengerTargets[currentFloor];
        passengerTargets[currentFloor] = 0;
        stops[currentFloor] = curNumPassengers > 0;
        Floor thisFloor = curBuilding.floor(currentFloor);
        int wPass = thisFloor.passengersWaiting();
        if(wPass > 0 ){
            for(int i = 0;i < wPass;i++){
                thisFloor.setWPassengers(wPass-(i+1));//decrementing waiting passengers
                try{
                    if(currentFloor > 0) 
                        boardPassenger(1);
                    else
                        boardPassenger(thisFloor.getDestination(i));
                }catch(ElevatorFullException e){
                    thisFloor.waitForElevator();
                }
            }//for
        }//if
            
    }
    public void boardPassenger(int destNum)throws ElevatorFullException{
        destNum--;
        Floor thisFloor = curBuilding.floor(currentFloor);
        try{
            if(getNumPassengers()+1>MAX_CAPACITY)     
                throw new ElevatorFullException("Elevator full.  You must wait.");
            else 
                passengerTargets[destNum]++;   
        }catch(ElevatorFullException e){
            curNumPassengers++;
            if(currentFloor == 0)
                thisFloor.waitForElevatorOn1(destNum+1);
            else
                thisFloor.waitForElevator();
        }
         
    }
    
    @Override public String toString() {
        int passengers = getNumPassengers();  
        return "Current Passengers: "+passengers+"\tCurrent Floor: "+
            (currentFloor+1)+"\tDirection: "+(goingUp?"Up":"Down");
}
}//Elevator Class
