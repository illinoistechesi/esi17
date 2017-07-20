package esi17.vsandrade99;
import battleship.core.*;
import java.util.List;

/*
 * TheBlackPearl
 * @Vladimir
 */
public class TheBlackPearl extends Ship {
    
    public TheBlackPearl() {
        //max points availible is 10
        this.initializeName("TheBlackPearl");
        this.initializeOwner("Vladimir");
        this.initializeHull(3);
        this.initializeFirepower(3);
        this.initializeSpeed(1);
        this.initializeRange(3);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    public void doTurn(Arena arena) {
        //Fill in your strategy here
        //int goalX=0;
        //int goalY=0;
        
        this.move(arena, Direction.WEST);
        this.move(arena, Direction.WEST);
        this.move(arena, Direction.NORTH);
        this.move(arena, Direction.NORTH);
        this.fire(arena, 3,1);
        this.move(arena, Direction.EAST);
        this.fire(arena, 4,0);
        this.move(arena, Direction.EAST);
        this.fire(arena, 5,0);
        this.move(arena, Direction.EAST);
        this.fire(arena, 6,1);
        this.move(arena, Direction.SOUTH);
        this.move(arena, Direction.SOUTH);
        this.move(arena, Direction.WEST);
    }
    
}