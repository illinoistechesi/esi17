package esi17.cs;
import battleship.core.*;
import java.util.List;

/*
 * KannanShip
 * @author Vinesh Kannan
 */
public class HoudiniShip extends Ship {
    
    public HoudiniShip() {
        this.initializeName("Houdini Ship");
        this.initializeOwner("The Evil Fleet");
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
        Coord location = this.getSelfCoord(arena);
        if (location.getX() != 0) {
            arena.move(this, Direction.WEST);
        } else if (location.getY() != 0) {
            arena.move(this, Direction.NORTH);
        }
        List<Ship> ships = arena.getNearbyEnemies(this);
        Ship target = ships.get(0);
        Coord coord = this.getShipCoord(arena, target);
        arena.fire(this, coord.getX(), coord.getY());
    }
    
}