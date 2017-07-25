package esi17.UnknownUser02;
import battleship.core.*;
import java.util.List;

/*
 * Roldan Ship
 * @author Your Name
 */
public class RoldanShip extends Ship {
    
    public RoldanShip() {
        this.initializeName(" RoldanShip");
        this.initializeOwner("Tony");
        this.initializeHull(2);
        this.initializeFirepower(2);
        this.initializeSpeed(3);
        this.initializeRange(3);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    public void doTurn(Arena arena) {
        // FIll in your strategy here
        
         this.move(arena, Direction.SOUTH);
        List<Ship> nearby = this.getNearbyShips(arena);
        if (nearby.size() > 0) {
            Ship first = nearby.get(0);
            Coord coord = this.getShipCoord(arena, first);
            int x = coord.getX();
            int y = coord.getY();
            this.fire(arena, x, y);
            this.fire(arena, x, y);
            }
        }
    }