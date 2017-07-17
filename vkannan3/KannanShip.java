package esi17.vkannan3;
import battleship.core.*;
import java.util.List;

/*
 * KannanShip
 * @author Vinesh Kannan
 */
public class KannanShip extends Ship {
    
    public KannanShip() {
        this.initializeName("Kannan Ship");
        this.initializeOwner("Vinesh Kannan");
        this.initializeHull(3);
        this.initializeFirepower(2);
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
        Coord location = this.getCoord();
        if (location.getX() != 0) {
            this.move(arena, Direction.WEST);
        } else if (location.getY() != 0) {
            this.move(arena, Direction.NORTH);
        }
        List<Ship> ships = this.getNearbyShips(arena);
        try {
            Ship target = ships.get(0);
            Coord coord = this.getShipCoord(arena, target);
            this.fire(arena, coord.getX(), coord.getY());
        } catch (Exception e) {
            //System.out.println("No ships to shoot.");
        }
    }
    
}