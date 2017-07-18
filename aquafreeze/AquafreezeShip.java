package esi17.aquafreeze;
import battleship.core.*;
import java.util.List;

/*
 * DestroyerShip
 * aquafreeze
 */
public class AquafreezeShip extends Ship {
    
    public AquafreezeShip() {
        this.initializeName("AquafreezeShip");
        this.initializeOwner("aquafreeze");
        this.initializeHull(3);
        this.initializeFirepower(2);
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
        // Fill in your strategy here
        
       
       this.move(arena, Direction.EAST);
     this.move(arena, Direction.NORTH);
     this.move(arena, Direction.WEST);
     this.move(arena, Direction.SOUTH);
     this.move(arena, Direction.EAST);
 
 
    }
    
}