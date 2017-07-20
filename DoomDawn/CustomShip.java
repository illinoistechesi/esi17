package esi17.DoomDawn;
import battleship.core.*;
import java.util.List;

/*
 * Custom Ship
 * @author Your Name
 */
public class CustomShip extends Ship {
    
    public CustomShip() {
        this.initializeName("Custom Ship");
        this.initializeOwner("Your Name");
        this.initializeHull(5);
        this.initializeFirepower(1);
        this.initializeSpeed(3);
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
        int goalX = 4;
        int goalY = 4;
        
        Coord shipLocation = this.getShipCoord(arena,this);
        int shipX = shipLocation.getX();
        int shipY = shipLocation.getY();
        //current location Greater than goal's x location
        //if (5<1)
        if(shipX <= goalX){
            this.move(arena, Direction.EAST);
        }
        else if(shipX >= goalX){
            this.move(arena, Direction.WEST);
        }
        
        if(shipY <= goalY){
            this.move(arena, Direction.NORTH);
        }
        else{
            this.move(arena, Direction.SOUTH);
        }
        
    }
    
}