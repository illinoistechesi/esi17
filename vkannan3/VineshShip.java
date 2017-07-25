package esi17.vkannan3;
import battleship.core.*;
import java.util.List;

/*
 * Vinesh
 * @author Your Name
 */
public class VineshShip extends Ship {
    
    public VineshShip() {
        this.initializeName("Vinesh Ship");
        this.initializeOwner("Your Name");
        this.initializeHull(1);
        this.initializeFirepower(1);
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
        // move the ship EAST
        this.move(arena, Direction.EAST);
        // get a list of nearby ships
        List<Ship> nearby = this.getNearbyShips(arena);
        
        if (nearby.size() > 0) { // if there are ships nearby
            Ship first = nearby.get(0); // single out one ship from the list
            // get singled out ship's coordinate
            Coord coord = this.getShipCoord(arena, first); 
            int x = coord.getX();
            int y = coord.getY();
            // fire at the ships coordinate
            this.fire(arena, x, y);
        }
    }
    
}