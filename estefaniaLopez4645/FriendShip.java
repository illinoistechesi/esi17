package esi17.estefaniaLopez4645;
import battleship.core.*;
import java.util.List;

/*
 * Custom Ship
 * @author Your Name
 */
public class FriendShip extends Ship {
    
    public FriendShip() {
        this.initializeName("Custom Ship");
        this.initializeOwner("Your Name");
        this.initializeHull(2);
        this.initializeFirepower(3);
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
        
       /* Coord my= this.getCoord();
          int myx = my.getX();
          
          if (myx==0){
            for(int i=0; i<7; i++){
            this.move(arena, Direction.EAST);
            }
          }
        if (myx==9){
            for(int i=0; i<7; i++){
            this.move(arena, Direction.WEST);
            }
          }
        
       */ 
        
        
        List<Ship> alive= arena.getAllShips();
    for (int i = 0; i < alive.size(); i++) {
    Ship ship = alive.get(i);
    // Call the getTeam() method on any ship to get its team name
    String myTeam = this.getTeam();
    String theirTeam =ship.getTeam();
    // To compare Strings, we have to use the special .equals() method
    // It will return true if the strings are equal and false if they are not
         if (theirTeam.equals(myTeam)) {
        // Don't shoot!
    } else {
        
        Coord coord = ship.getCoord();
        int x = coord.getX();
        int y = coord.getY();
         this.fire(arena, x, y);
       
        if(!arena.isInRange(this, ship ))
        {
          Coord my= this.getCoord();
          int myx = my.getX();
          int myy = my.getY();
          
          
          if(myy<y)
            this.move(arena, Direction.SOUTH);
          if(myy>y)
             this.move(arena, Direction.NORTH);
           if(myx<x)
           this.move(arena, Direction.EAST);
          if(myx>x)
             this.move(arena, Direction.WEST);
             
              if(arena.isInRange(this, ship )){
             
        // If you run out of firepower on a turn, you can still call fire(), but your ship won't actually fire
        
       
        }
        }
        // In the new version of battleship, you can get any ship's coordinate, even if it is out of your range
        // But, snce we used getNearbyShips(), all ships in this loop are in range
        
       
        
        
    }

        
    
    // ship using this instruction will fire at location (x: 0, y: 0) each turn
    //this.fire(arena, 7,2 );
   // this.fire(arena, 12,2 );
        
    }
    }
}
