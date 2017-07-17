package esi17.DoomDawn;
import battleship.core.*;
import java.util.List;

/*
 * Doom Ship
 * @author Freddy Herrera
 */
public class DoomShip extends Ship {
    
    public DoomShip() {
        this.initializeName("Doom Ship");
        this.initializeOwner("Freddy Herrera");
        this.initializeHull(7);
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
    
        int goalX = 2;
        int goalY = 2;
        
        Coord shipLocation = this.getShipCoord(arena,this);
        int shipX = shipLocation.getX();
        int shipY = shipLocation.getY();
        //current location Greater than goal's x location
        //if (5<1)
        if(shipX <= goalX){
            arena.move(this, Direction.EAST);
        }
        else if(shipX >= goalX){
            arena.move(this, Direction.WEST);
        }
        
        if(shipY <= goalY){
            arena.move(this, Direction.NORTH);
        }
        else{
            arena.move(this, Direction.SOUTH);
        }
        
        //System.out.println("\nShip Doom Location is: ("+shipX+","+shipY+")\n");
        
        //arena.move(this, Direction.NORTH);
        //arena.move(this, Direction.SOUTH);
        //arena.move(this, Direction.EAST);
        //arena.move(this, Direction.WEST);
        
    }
    
}