package esi17.mruiz9;
import battleship.core.*;
import java.util.List;

/*
 * mruiz9
 * @author Your Name
 */
public class guppy extends Ship {
    
    public guppy() {
        this.initializeName("guppy");
        this.initializeOwner("guppy");
        this.initializeHull(3);
        this.initializeFirepower(3);
        this.initializeSpeed(1);
        this.initializeRange(3);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    public void doTurn(Arena arena) {
        // Fill in your strategy here
        this.move(arena, Direction.WEST);
        this.move(arena, Direction.NORTH);
        this.move(arena, Direction.WEST);
        this.move(arena, Direction.NORTH);
        List<Ship> nearby = this.getNearbyShips(arena);
            
            
    int minHealth = 10;
     Ship target = null;        
// Loop over all the ships
for (int i = 0; i < nearby.size(); i++) {
   //find min health
    Ship ship1 = nearby.get(i);

    // Call the getTeam() method on any ship to get its team name
    String myTeam = this.getTeam();
    String theirTeam = ship1.getTeam();
    // To compare Strings, we have to use the special .equals() method
    // It will return true if the strings are equal and false if they are not
    if (theirTeam.equals(myTeam)) {
        // Don't shoot!
    } else {
        // In the new version of battleship, you can get any ship's coordinate, even if it is out of your range
        // But, snce we used getNearbyShips(), all ships in this loop are in range
           if (minHealth > ship1.getHealth()) {
        target = ship1;
    minHealth = target.getHealth();
        
    }
    }
}   if (target  == (null)) {
}
 else {
Coord coord = target.getCoord();
        int x = coord.getX();
        int y = coord.getY();
        // If you run out of firepower on a turn, you can still call fire(), but your ship won't actually fire
        this.fire(arena, x, y);
 
        System.out.println(x+" "+y);
 }
        } 
    }
    