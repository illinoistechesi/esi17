package esi17.caesarsalad64;
import battleship.core.*;
import java.util.List;

/*
 * Cuevas Ship
 * @author Your Name
 */
 
    //https://github.com/illinoistechesi/battleship 
    //Last update: July 26th, 2017 @ 2035
 
public class CuevasShip extends Ship 
{
    public CuevasShip() 
    { 
        this.initializeName("Cuevas Ship");
        this.initializeOwner("Your Name");
        this.initializeHull(4);      //Health --> Previously: 3
        this.initializeFirepower(2); //Power --> Previously: 3
        this.initializeSpeed(0);     //Speed --> Previously: 1
        this.initializeRange(4);     //Range of visibility --> Previously: 3
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    
    @Override
    public void doTurn(Arena arena) 
    {
    // Fill in your strategy here
        
    //My ship's location:
    Coord coord = this.getCoord();
    int myX = coord.getX();
    int myY = coord.getY();
    System.out.println("\n\nCuevas ship is at (" + myX + ", " + myY + ")");        

    //Logic for finding teammates & firing at enemies:
    List<Ship> nearby = this.getNearbyShips(arena);
    for (int i = 0; i < nearby.size(); i++) 
    {
        Ship unknown = nearby.get(i);
        boolean isOnMyTeam = this.isSameTeamAs(unknown);
        if (isOnMyTeam) 
        {
            System.out.println("\nThis ship is FRIENDLY!");
            System.out.println("FRIENDLY ship name: " + unknown.getName());
            //Don't Shoot
            //Go to Starbucks and then Panera
        }
        else
        {
            //Get shots to take down a ship instead of random shots
            System.out.println("\nThis ship is an ENEMY!");
            System.out.println("ENEMY ship name: " + unknown.getName());
            Coord location = unknown.getCoord();
            int x = location.getX();
            int y = location.getY();
        
            for(int j = 0; j < 2; j++)
            {
                this.fire(arena, x, y);
                System.out.println("Fired @ coord: (" + x + ", " + y + ")");
            }
            
            //System.out.println(getNearbyShips(arena));
            }
        }
        
    //My ship's movement:
    this.move(arena, Direction.NORTH);
    
    //Print dashes (-) to seperate code from each turn
    System.out.println("------------------------------------");

    }
}