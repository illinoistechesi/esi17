package esi17.caesarsalad64;
import battleship.core.*;
import java.util.List;

/*
 * Cuevas Ship
 * @author Your Name
 */
public class CuevasShip extends Ship 
{
    
    public CuevasShip() 
    { 
    
    //Plan A --Move around strategically [OFFENSE]
    
        this.initializeName("Cuevas Ship");
        this.initializeOwner("Your Name");
        this.initializeHull(4);      //Health
        this.initializeFirepower(3); //Power
        this.initializeSpeed(1);     //Speed
        this.initializeRange(2);     //Range of visibility

    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    
    @Override
    public void doTurn(Arena arena) {
    // Fill in your strategy here
    
        Coord myShip = this.getCoord();
        int a = myShip.getX();
        
        if (a > 2) 
        {
            this.move(arena, Direction.WEST);
        }
        else
        {
            this.move(arena, Direction.EAST);
        }
    
    List<Ship> nearby = this.getNearbyShips(arena);
    // Loop over all the ships
    for (Ship ship : nearby) 
    {
    System.out.println("Most near ship coords are: " + ship.getCoord() + " .");
    }
    
    for (int i = 0; i < nearby.size(); i++)
    {
        Ship ship = nearby.get(i);
        // Call the getTeam() method on any ship to get its team name
        String myTeam = this.getTeam();
        String theirTeam = ship.getTeam();
        // To compare Strings, we have to use the special .equals() method
        // It will return true if the strings are equal and false if they are not
        if (theirTeam.equals(myTeam)) 
        {
        // Don't shoot!
        } 
        else 
        {
        // In the new version of battleship, you can get any ship's coordinate, even if it is out of your range
        // But, since we used getNearbyShips(), all ships in this loop are in range
       
        Coord coord = ship.getCoord();
        int x = coord.getX();
        int y = coord.getY();
        this.move(arena, Direction.SOUTH);
    }
    else {
        // make a list of all the location, and store it in a variable
        Direction[] possibleMovement = {Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST};
        
        // get a random number and store it in a variable
        Direction randomNumber = arena.getRandom().nextInt(4); //Forgot this semicolon
        
        // If you run out of firepower on a turn, you can still call fire(), but your ship won't actually fire
        this.fire(arena, x, y);
            }
        }
    }
}