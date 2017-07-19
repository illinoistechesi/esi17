package esi17.cmercado995;
import battleship.core.*;
import java.util.List;

/*
 * Mercado Ship
 * @author Christian Mercado
 */
public class CannonFodder extends Ship {
    
    public CannonFodder() {
        this.initializeName("Cannon Fodder");
        this.initializeOwner("Christian Mercado");
        this.initializeHull(3);
        this.initializeFirepower(1);
        this.initializeSpeed(5);
        this.initializeRange(5);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    public void doTurn(Arena arena){
        // Fill in your strategy here
         // gets the current location of the ship
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
                // make an array of all the location, and store it in a variable
                Direction[] possibleMovement = {Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST};
        
                // get a random number and store it in a variable
                int randomNumber = arena.getRandom().nextInt(4);
        
                // get a random movement by using the random number to access one possibleMovement 
                this.move(arena, possibleMovement[randomNumber]);
            }
        }
}