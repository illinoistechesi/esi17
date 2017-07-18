package esi17.caesarsalad64;
import battleship.core.*;
import java.util.List;

/*
 * Cuevas Ship
 * @author Your Name
 */
public class CuevasShip extends Ship {
    
    public CuevasShip() {
        this.initializeName("Cuevas Ship");
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
    
    List<Ship> nearby = this.getNearbyShips(arena);
    if(nearby.size() > 0)
    {
        Ship first = nearby.get(0);
        Coord coord = this.getShipCoord(arena, first); // gets the current location of the ship
        int x = coord.getX();
        int y = coord.getY();
        this.fire(arena, x, y);
    }

    if (x > 8) 
    {
        this.move(arena, Direction.WEST);
    }
    else if (x < 2) 
    {
        this.move(arena, Direction.EAST);
    }
    else if (y > 8) 
    {
        this.move(arena, Direction.NORTH);
    }
    else if (y < 2) 
    {
        this.move(arena, Direction.SOUTH);
    }
    else {
        // make a list of all the location, and store it in a variable
        Direction[] possibleMovement = {Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST};
        
        // get a random number and store it in a variable
        Direction randomNumber = arena.getRandom().nextInt(4); //Forgot this semicolon
        
        // get a random movement by using the random number to access one possibleMovement 
        this.move(arena, possibleMovement[randomNumber]);
    }
    
    // ship using this instruction will fire at location (x: 0, y: 0) each turn
    this.fire(arena, 0, 0);
    }
    
}