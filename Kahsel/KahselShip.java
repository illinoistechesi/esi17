package esi17.Kahsel;
import battleship.core.*;
import java.util.List;

/*
 * Kahsel Ship
 * @author Ka Hsel Nay
 */
public class KahselShip extends Ship {
    
    public KahselShip() {
        this.initializeName("Kahsel Ship");
        this.initializeOwner("Ka Hsel Nay");
        this.initializeHull(3);
        this.initializeFirepower(2);
        this.initializeSpeed(3);
        this.initializeRange(2);
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
        this.move(arena, Direction.EAST);
        this.move(arena, Direction.EAST);
        
    /*    this.move(arena, Direction.EAST);
        this.move(arena, Direction.EAST);
        this.move(arena, Direction.EAST);
        */
        
        
        
        List<Ship> nearby = this.getNearbyShips(arena);
        nearby.get(3);
     
     
        Coord coord = this.getCoord();
        int x = coord.getX();
        int y = coord.getY();
        this.fire(arena, x, y);
    }
    
}