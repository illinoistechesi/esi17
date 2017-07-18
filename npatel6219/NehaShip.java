package esi17.npatel6219;
import battleship.core.*;
import java.util.List;

/*
 * Custom Ship
 * @author Your Name
 */
public class NehaShip extends Ship {
    
    public NehaShip() {
        this.initializeName("Neha Ship");
        this.initializeOwner("Your Name");
        this.initializeHull(3);
        this.initializeFirepower(1);
        this.initializeSpeed(2);
        this.initializeRange(1);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    public void doTurn(Arena arena) {
        // Fill in your strategy here
       this.move(arena, Direction.EAST);
       List <Ship> nearby = this.getNearbyShips(arena);
       if (nearby.size() > 0) {
           Ship first = nearby.get(0);
           
           Coord coord = getShipCoord(arena, first);
           int x= coord.getX();
           int y =coord.getY();
           this.fire(arena, x,  y);
         }
    
    }
}