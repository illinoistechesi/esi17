package esi17.npatel6219;
import battleship.core.*;
import java.util.List;

/*
 * Custom Ship
 * @author Your Name
 */
public class NehaShip extends Ship {
    
    public NehaShip() {
        this.initializeName("Neha Ship");
        this.initializeOwner("NehaP");
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
       
       List<Ship> nearby = this.getNearbyShips(arena);
       for (int i = 0; i < nearby.size(); i++) {
         Ship ship = nearby.get(i);
          String myTeam = this.getTeam();
          String theirTeam = ship.getTeam();
          if (theirTeam.equals(myTeam)) {
          } else {
              Coord coord = ship.getCoord();
              int x = coord.getX();
              int y = coord.getY();
              this.fire(arena, x, y);
    
          }
           Coord coord = this.getCoord();
         int x = coord.getX();
         int y = coord.getY();
         
         if (x>8){
             this.move(arena, Direction.WEST);
         }
         else if (x<2) {
             this.move(arena, Direction.EAST);
         }
         else if (y>8){
             this.move(arena, Direction.SOUTH);
         }
         else if (y<2){
             this.move(arena, Direction.NORTH);
         }
         else {
             // make a list of all the location, and store it in a variable
            Direction[] possibleMovement = {Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST};
            
            // get a random number and store it in a variable
            //Direction randomNumber = arena.getRandom().nextInt(4);
            
            // get a random movement by using the random number to access one possibleMovement 
            //this.move(arena, possibleMovement[randomNumber]);
         }
         
         this.fire(arena, 0,0);
       }
       
    
    }
}