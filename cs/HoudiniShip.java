package esi17.cs;
import battleship.core.*;
import java.util.List;

/*
 * KannanShip
 * @author Vinesh Kannan
 */
public class HoudiniShip extends EvilFleetShip {
    
    private int lastHealth;
    
    public HoudiniShip() {
        this.initializeName("Houdini Ship");
        this.initializeOwner("The Evil Fleet");
        this.initializeHull(3);
        this.initializeFirepower(2);
        this.initializeSpeed(1);
        this.initializeRange(4);
        this.lastHealth = this.getHealth();
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    public void doTurn(Arena arena) {
        
        if (this.wasHit()) {
            List<Ship> ships = this.getNearbyShips(arena);
            
        } else {
            
        }
        
        Coord location = this.getCoord();
        if (location.getX() != 0) {
            this.move(arena, Direction.WEST);
        } else if (location.getY() != 0) {
            this.move(arena, Direction.NORTH);
        }
        List<Ship> ships = this.getNearbyShips(arena);
        Ship target = ships.get(0);
        Coord coord = this.getShipCoord(arena, target);
        this.fire(arena, coord.getX(), coord.getY());
        
        
        
        this.lastHealth = this.getHealth();
    }
    
    private boolean wasHit() {
        return this.lastHealth != this.getHealth();
    }
    
}