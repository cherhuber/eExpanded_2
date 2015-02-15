/**
 *
 * @author cheryl
 */
import java.util.Random;
public class Controller {
    public static Building model;
    public static View view;
    public Controller(){
        model = new Building();
        view = new View();
    }
    public static int randInt(int min, int max) {
    Random rand = new Random();

    // nextInt is normally exclusive of the top value,
    // so add 1 to make it inclusive
    int randomNum = rand.nextInt((max - min) + 1) + min;

    return randomNum;
}
    public static void main(String[] args) throws ElevatorFullException{
        Controller myController = new Controller();
        Elevator myElevator = model.elevator();
        int curFloor = myElevator.getCurFloor();
        Floor myCurFloor = model.floor(curFloor);
        int min = 1;
        int max = Building.FLOORS;
        /*for(int i =0;i<12;i++){
            int dest = randInt(min,max); 
            myElevator.boardPassenger(dest);
            System.out.println("Passenger Boards with Destination of " +dest);
        }*/
        myElevator.boardPassenger(6);
        myElevator.boardPassenger(7);
        myElevator.boardPassenger(4);
        myElevator.boardPassenger(2);
        myElevator.boardPassenger(3);
        myElevator.boardPassenger(3);
        myElevator.boardPassenger(6);
        myElevator.boardPassenger(3);
        myElevator.boardPassenger(6);
        myElevator.boardPassenger(3);
        myElevator.boardPassenger(7);
        view.updateAll(curFloor+1, myElevator.getNumPassengers(), true);
        for (int j=0; j<19; j++) {
            
            myElevator.move();
            curFloor = myElevator.getCurFloor();//because elevator moved
            view.updateAll(curFloor+1,myElevator.getNumPassengers(), 
                    myElevator.getDirection());
            
        }//for
        //view.exitElevatorView();
        
      
    }
}
