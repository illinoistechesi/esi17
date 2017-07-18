package esi17.cguy1;
import battleship.core.*;
import java.util.List;

/*
 * Custom Ship
 * @author Your Name
 */
public class Pirateship extends Ship {
    
    public Pirateship ()  {
        this.initializeName("Pirateship");
        this.initializeOwner("cguy1");
        this.initializeHull(3);
        this.initializeFirepower(2);
        this.initializeSpeed(2);
        this.initializeRange(3);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    public void doTurn(Arena arena) {
        this.move(arena, Direction.EAST);
        List<Ship> nearby = this.getNearbyShips(arena);
        if (nearby.size() > 0) {
           
        Ship first = nearby.get(0);
        Coord coord = this.getShipCoord(arena, first);
        int x = coord.getX();
        int y = coord.getY();
        this.fire(arena, x, y);
            
        }
        
    }
    
}