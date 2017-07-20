package esi17.joshuatgonzalez;
import battleship.core.*;
import java.util.List;

/*
 * TheShip
 * @Joshua Gonzalez
 */
public class TheShip extends Ship {
    
    public TheShip() {
        this.initializeName("TheShip");
        this.initializeOwner("Joshua Gonzalez");
        this.initializeHull(2);
        this.initializeFirepower(3);
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
       /* getAllShips()
        getTurn()
        Direction[] possibleMovement = {Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST};
        this.move(arena, Direction.WEST);
        List<Ship> nearby = this.getNearbyShips(arena);
        if (nearby.size() > 0) {
        Ship first = nearby.get(0);
        Coord coord = this.getCoord(arena, first);
        int x = coord.getx();
        int y = coord.getY();
        this.fire(arena, x, y);
        fire(Arena arena, int x, int y)
        // Fill in your strategy here
  */
    }
    
}