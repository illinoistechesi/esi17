package esi17.Nickthegreat;
import battleship.core.*;
import java.util.List;

/*
 * Custom Ship
 * @author Your Name
 */
public class MoistNoodle extends Ship {
    
    public MoistNoodle() {
        this.initializeName("MoistNoodle");
        this.initializeOwner("Nick");
        this.initializeHull(1);
        this.initializeFirepower(2);
        this.initializeSpeed(1);
        this.initializeRange(6);
    }
 
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    public void doTurn(Arena arena) {
        
        Coord me = this.getCoord();
        int a = me.getX();
        int b = me.getY();
            
        
         
         
        
        //if (a < 7) {
        //this.move(arena, Direction.EAST);
        //this.move(arena, Direction.EAST);
        //}
    
        //gets neaby enemies
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
        Coord enemy = ship.getCoord();
        int x = enemy.getX();
        int y = enemy.getY();
        this.fire(arena, x, y);
        this.fire(arena, x, y);
       // for (int firecount=0;firecount<6;firecount++){
    //   if ship.getHealth()>0;
      //  this.fire(arena, x, y);
        //else
        //for (int i = 0; i < nearby.size(); i++) {
    this.move(arena, Direction.SOUTH);
        
        }
        // If you run out of firepower on a turn, you can still call fire(), but your ship won't actually fire

    }
}
        // Fill in your strategy here
    
    
}