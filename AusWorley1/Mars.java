package esi17.AusWorley1;
import battleship.core.*;
import java.util.List;


//   Mars
//   @AusWorley1
 
public class Mars extends Ship {
    
    public Mars() {
        this.initializeName("Mars");
        this.initializeOwner("Austin Worley");
        this.initializeHull(4);
        this.initializeFirepower(3);
        this.initializeSpeed(1);
        this.initializeRange(2);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    public void doTurn(Arena arena) {
       // Get all nearby ships
    List<Ship> nearby = this.getNearbyShips(arena);
// Loop over all the ships
    for (int i = 0; i < nearby.size(); i++) {
    Ship ship = nearby.get(i);
    // Call the getTeam() method on any ship to get its team name    String myTeam = this.getTeam();
    String myTeam = this.getTeam();
    String theirTeam = ship.getTeam();
    // To compare Strings, we have to use the special .equals() method
    // It will return true if the strings are equal and false if they are not
    if (theirTeam.equals(myTeam)) {
        // Don't shoot!

    } else {
        // In the new version of battleship, you can get any ship's coordinate, even if it is out of your range
        // But, snce we used getNearbyShips(), all ships in this loop are in range
        Coord coord = ship.getCoord();        
        int x = coord.getX();
        int y = coord.getY();
        // If you run out of firepower on a turn, you can still call fire(), but your ship won't actually fire
  
             if (nearby.size()>0) {
            Ship a = nearby.get(0);
           Coord coord2 = this.getShipCoord(arena, a);
           int shipX = coord2.getX();
           int shipY = coord2.getY();
           this.move(arena, Direction.EAST);
           this.fire(arena, x, y);
           this.fire(arena, x, y);
           this.fire(arena, x, y);
           
    }
       
       
       
       
       
       
       
       
       
       
       
       
       
       
        // Fill in your strategy here
       //  List<Ship> nearby = this.getNearbyShips(arena);
       // Ship a;
        //System.out.println(a.getHealth());
        //int size = nearby.size();
       // if (size>0) {
       //     a = nearby.get(0);
        //    Coord coord = this.getShipCoord(arena, a);
        //    int shipX = coord.getX();
        //    int shipY = coord.getY();
        //    this.fire(arena, shipX, shipY);
        //    this.fire(arena, shipX, shipY);
        //    this.fire(arena, shipX, shipY);
        //    this.move(arena, Direction.EAST);
       // }
       // else if (size == 0){
        //    Coord coord = this.getCoord();
        //    int x = coord.getX();
        //    int maxX = arena.getXSize();
        //    if (x == maxX){
        //        this.move(arena, Direction.WEST);
        //        
        //    }else if (x < maxX){
        //        this.move(arena, Direction.EAST);
        //        
        //    }
        //    
       // }
        
    }
    }
        
    //     Coord coord = this.getCoord();
    //     int x = coord.getX();
    //  if (x < 5) {
    //     this.move(arena, Direction.EAST);
    //     List<Ship> nearby = this.getNearbyShips(arena);
    //     Ship a = nearby.get(0);
    //     System.out.println(a.getHealth());
    //  }
    }
}
/*
If enemy is in sight 
    Shoot at ship with the least hull points
    Move to the right
When no enemy is in sight
    Scan for enemy ships
If at corner
    Head one spots away from the corner
If not at corner
    Move towards corner
*/