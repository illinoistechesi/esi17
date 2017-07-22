package esi17.vsandrade99;
import battleship.core.*;
import java.util.List;

/*
 * BlackPearl
 * Vladimir
 */
public class TheBlackPearl extends Ship {
    
    public TheBlackPearl() {
        //max points availible is 11
        this.initializeName("BlackPearl");
        this.initializeOwner("Vladimir");
        this.initializeHull(5);
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
        
        /*arena.move(this, Direction.EAST);
        arena.move(this, Direction.EAST);
        arena.move(this, Direction.EAST);
        arena.fire(this, 7,1);
        arena.move(this, Direction.NORTH);
        arena.fire(this, 7,2);
        arena.move(this, Direction.SOUTH);
        arena.fire(this, 6,2);
        arena.move(this, Direction.SOUTH);
        arena.fire(this, 7,3);
        arena.move(this, Direction.NORTH);
        arena.move(this, Direction.WEST);
        arena.move(this, Direction.WEST);*/
    }
    /*
     * Example of how not to shoot your teammates
    */
    // Get all nearby ships
    /*List<Ship> nearby = this.getNearbyShips(arena);
    // Loop over all the ships
    for (int i = 0; i < nearby.size(); i++) {
        Ship ship = nearby.get(i);
        String myTeam = this.getTeam();
        String theirTeam = ship.getTeam();
        if (theirTeam.equals(myTeam)) {
            // Don't shoot!
        } else {
            Coord coord = ship.getCoord();
            int x = coord.getX();
            int y = coord.getY();
            int myX = this.getCoord().getX();
            int myY = this.getCoord().getY();
        
            if (myX>x) {
                this.move(arena,Direction.WEST);
            } else if (myX<x) {
                this.move(arena,Direction.EAST);
            } else if (myY>y) {
                this.move(arena,Direction.NORTH);
            } else this.move(arena,Direction.SOUTH);
        
            this.fire(arena, x, y);
        }
    }*/
    
    
}