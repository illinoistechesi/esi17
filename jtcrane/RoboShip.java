package esi17.jtcrane;
import battleship.core.*;
import java.util.List;

/*
 * Robo Ship
 * @author Jalen Crane
 */
public class RoboShip extends Ship {
    
    public RoboShip() {
        this.initializeName("Robo Ship");
        this.initializeOwner("Jalen Crane");
        this.initializeHull(1);
        this.initializeFirepower(4);
        this.initializeSpeed(1);
        this.initializeRange(4);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    public void doTurn(Arena arena) {
    /*List<Ship> nearby = nearby.get(0);
    Coord coord = this.getShipCoord()
    int x = coord.getX();
    int y = coord.getY();
    System.out.println("My ship is at (" + x + ", " + y + ").");*/
    
    
    // Get all nearby ships
List<Ship> nearby = this.getNearbyShips(arena);
// Loop over all the ships
for (int i = 0; i < nearby.size(); i++) {
            Ship ship = nearby.get(i);
            // Call the getTeam() method on any ship to get its team name
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
                int HP = ship.getHealth();
                // If you run out of firepower on a turn, you can still call fire(), but your ship won't actually 
                
                if(HP == 1) {
                    this.fire(arena, x, y);
                }
                
                this.fire(arena, x, y);
                
                if(HP == 1) {
                    this.fire(arena, x, y);
                }
                
                this.fire(arena, x, y);
                
                if(HP == 1) {
                    this.fire(arena, x, y);
                }
                
                this.fire(arena, x, y);
                
                if(HP == 1) {
                    this.fire(arena, x, y);
                }
            }
} 
        // Fill in your strategy here
        
        
            
        
    }
    
}