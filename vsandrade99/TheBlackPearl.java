package esi17.vsandrade99;
import battleship.core.*;
import java.util.List;

/*
 * BlackPearl
 * Vladimir
 */
public class TheBlackPearl extends Ship {
    
    public TheBlackPearl() {
        //max points availible is 10
        this.initializeName("BlackPearl");
        this.initializeOwner("Vladimir");
        this.initializeHull(2);
        this.initializeFirepower(3);
        this.initializeSpeed(3);
        this.initializeRange(2);
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
        
        this.move(arena, Direction.SOUTH);
        this.move(arena, Direction.SOUTH);
        this.fire(arena, 3,7);
        this.move(arena, Direction.NORTH);
        this.fire(arena, 3,10);
        this.move(arena, Direction.SOUTH);
        this.fire(arena, 4,7);
        this.move(arena, Direction.SOUTH);
        this.fire(arena, 4,8);
        this.move(arena, Direction.NORTH);
        this.move(arena, Direction.WEST);
        this.move(arena, Direction.WEST);
    
    /*
     * Example of how not to shoot your teammates
    */
    // Get all nearby ships
    List<Ship> nearby = this.getNearbyShips(arena);
    // Loop over all the ships
    for (int i = 0; i < nearby.size(); i++) {
        Ship ship = nearby.get(i);
        Coord coord = ship.getCoord();
            int x = coord.getX();
            int y = coord.getY();
            int myX = this.getCoord().getX();
            int myY = this.getCoord().getY();
        String myTeam = this.getTeam();
        String theirTeam = ship.getTeam();
        if (theirTeam.equals(myTeam)) {
            // Don't shoot!
        } else { 
            return;
        }
            if (myX>x) {
                this.move(arena,Direction.WEST);
            } else if (myX<x) {
                this.move(arena,Direction.EAST);
            } else if (myY>y) {
                this.move(arena,Direction.NORTH);
            } else this.move(arena,Direction.SOUTH);
        
            this.fire(arena, x, y);
        }
    }
    
    
}