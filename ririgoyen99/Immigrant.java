package esi17.ririgoyen99;
import battleship.core.*;
import java.util.List;

/*
 * Immigrant
 * @ririgoyen99
 */
public class Immigrant extends Ship {
    
    public Immigrant() {
        this.initializeName("Immigrant");
        this.initializeOwner("ririgoyen99");
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
        this.move(arena, Direction.EAST);
        
        List<Ship> nearby = this.getNearbyShips(arena);
        
        if (nearby.size() > 0){
         Ship first = nearby.get(0);
         Coord coord = this.getShipCoord(arena, first);
         int X = coord.getX();
         int Y = coord.getY();
         this.fire(arena, X, Y);
        }
        
    }
}
        