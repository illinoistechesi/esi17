package esi17.estefaniaLopez4645;
import battleship.core.*;
import java.util.List;

/*
 * Custom Ship
 * @author Your Name
 */
public class FriendShip extends Ship {
    
    public FriendShip() {
        this.initializeName("Custom Ship");
        this.initializeOwner("Your Name");
        this.initializeHull(2);
        this.initializeFirepower(3);
        this.initializeSpeed(4);
        this.initializeRange(1);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    public void doTurn(Arena arena) {
        List<Ship> nearb= this.getNearbyShips(arena);
       Coord coord = this.getCoord();
    int x = coord.getX();
    int y = coord.getY();
    
    if (x > 8) {
        this.move(arena, Direction.WEST);
    }
    else if (x < 2) {
        this.move(arena, Direction.EAST);
    }
    else if (y > 8) {
        this.move(arena, Direction.NORTH);
    }
    else if (y < 2) {
        this.move(arena, Direction.SOUTH);
    }
    else {
        // make a list of all the location, and store it in a variable
        Direction[] possibleMovement = {Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST};
        
        // get a random number and store it in a variable
        int randomNumber = arena.getRandom().nextInt(4);
        
        // get a random movement by using the random number to access one possibleMovement 
        this.move(arena, possibleMovement[randomNumber]);
    }
    
    // ship using this instruction will fire at location (x: 0, y: 0) each turn
    this.fire(arena, 7,2 );
    this.fire(arena, 12,2 );
        
    }
    
}