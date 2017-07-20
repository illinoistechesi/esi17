package esi17.ageorgescu17;
import battleship.core.*;
import java.util.List;

/*
 * Maverick
 * @author Your Name
 */
public class Maverick extends Ship {
    
    public Maverick() {
        this.initializeName("Maverick");
        this.initializeOwner("Adriana");
        this.initializeHull(4);
        this.initializeFirepower(2);
        this.initializeSpeed(2);
        this.initializeRange(2);
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
        List <Ship> nearby = this.getNearbyShips(arena);
        
        for (int i = 0; i < nearby.size(); i++){
            Ship ship = nearby.get(i);
            String myTeam = this.getTeam();
            String theirTeam = ship.getTeam();
                if (theirTeam.equals(myTeam)){
                } else{
                   Coord coord = ship.getCoord();
            int x = coord.getX();
            int y = coord.getY();
            //fire at ships
            this.fire(arena, x,y); 
            //Moving towards ship coord
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
        Direction[] possibleMovement = {Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST};
        //Direction randomNumber = arena.getRandom().nextInt(4);
        //this.move(arena, possibleMovement[randomNumber]);
        
    }
     // ship using this instruction will fire at location (x: 0, y: 0) each turn
    this.fire(arena, 0, 0);
        }
    
        
        
        }
        
}
    
}