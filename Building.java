/**
 *
 * @author cheryl
 */
public class Building {
    public static final int FLOORS = 7;
    private final Elevator e;
    private final Floor[] f;
    public Building(){
        e = new Elevator(this);
        f = new Floor[FLOORS];
        for (int i=0;i<FLOORS;i++){
            f[i] = new Floor(this,i);
        }
    }
    public Elevator elevator(){
        return e;
    }
    public Floor floor(int floorNumber){
        return f[floorNumber];
    }
   
}
