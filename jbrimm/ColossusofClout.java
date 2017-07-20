package esi17.jbrimm;
import battleship.core.*;
import java.util.List;

/*
 * Custom Ship
 * @author Your Name
 */
public class ColossusofClout extends Ship {
    
    public ColossusofClout() {
        this.initializeName("ColossusofClout");
        this.initializeOwner("jbrimm");
        this.initializeHull(2);       //HEALTH
        this.initializeFirepower(5); //POWER
        this.initializeSpeed(0);     //SPEED
        this.initializeRange(3);    //RANGE
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    public void doTurn(Arena arena) {
        
        /*
 * Example of how not to shoot your teammates
 */
// Get all nearby ships
List<Ship> nearby = this.getNearbyShips(arena);
// Loop over all the ships
for (int i = 0; i < nearby.size(); i++) {
    Ship ship = nearby.get(i);
    // Call the getTeam() method on any ship to get its team name
    String myTeam = this.getTeam();
    String theirTeam = ship.getTeam();
    // To compare Strings, we have to use the special .equals() method
    // It will return true if the strings are equal and false if they are not
    if (theirTeam.equals(myTeam)) {
        // Don't shoot!
    } else {
        // In the new version of battleship, you can get any ship's coordinate, even if it is out of your range
        // But, snce we used getNearbyShips(), all ships in this loop are in range
        Coord coord = ship.getCoord();
        int x = coord.getX();
        int y = coord.getY();
        // If you run out of firepower on a turn, you can still call fire(), but your ship won't actually fire
        this.fire(arena, x, y);
    }
}
        
        /*
        this.move(arena, Direction.EAST); 
        this. move(arena, Direction.WEST)
        List<Ship> nearby = this.getNearbyShips(arena); 
        if (nearby.size() > 0) {
        Ship first = nearby.get(0);
        Coord coord =this.getShipCoord(arena, arenavf);
        int x = coord.getX();
        int y = coord.getY();
        this.fire(arena, x, y);
        
    // doTurn Example, place in your ship clas
   // doTurn(Arena arena) {
    // your implementation of a ship
    
    if (x > 4) {
        this.move(arena, Direction.WEST);
    }
    else if (x < 6) {
        this.move(arena, Direction.SOUTH);
    }
    else if (y > 4) {
        this.move(arena, Direction.EAST);
    }
    else if (y < 6) {
        this.move(arena, Direction.NORTH);
    }
    else {
        // make a list of all the location, and store it in a variable
        Direction[] possibleMovement = {Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST};
        
        // get a random number and store it in a variable
        Direction randomNumber = arena.getRandom().nextInt(5)
        
        // get a random movement by using the random number to access one possibleMovement 
        this.move(arena, possibleMovement[randomNumber]);
    }
    
    // ship using this instruction will fire at location (x: 0, y: 0) each turn
    this.fire(arena, 0, 0);
}
*/
//leave below
    }
}