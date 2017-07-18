package esi17.ageorgescu17;
import battleship.core.*;
import java.util.List;

/*
 * Maverick
 * @author Your Name
 */
public class Maverick extends Ship {
    
    public Maverick() {
        this.initializeName("Maverick");
        this.initializeOwner("Your Name");
        this.initializeHull(1);
        this.initializeFirepower(1);
        this.initializeSpeed(1);
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
        if (nearby.size() > 0 ){
            Ship first = nearby.get(0);
            Coord coord = getShipCoord(arena, first);
            int x = coord.getX();
            int y = coord.getY();
            //fire at ships
            this.fire(arena, x,y);
        }
    }
    
}