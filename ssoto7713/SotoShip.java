package esi17.ssoto7713;
import battleship.core.*;
import java.util.List;

/*
 * SotoShip
 * @author Soto
 */
public class SotoShip extends Ship {
    
    public SotoShip() {
        this.initializeName("SotoShip");
        this.initializeOwner("Soto");
        this.initializeHull(1);
        this.initializeFirepower(3);
        this.initializeSpeed(3);
        this.initializeRange(3);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    public void doTurn(Arena arena) {
        // Fill in your strategy here
 List<Ship> nearby = this.getNearbyShips(arena);
        
        for (int i = 0; i < nearby.size(); i++) {
            
            Ship ship = nearby.get(i);
            
            String myTeam = this.getTeam();
            String theirTeam = ship.getTeam();
            // for (Ship ship : nearby) {
            // System.out.println("One nearby ship has " + ship.getHealth() + " HP left.");
                    Coord enem = ship.getCoord();
                    Coord coord = this.getCoord();
                    int x = enem.getX();
                    int y = enem.getY();            
            if (theirTeam.equals(myTeam)) {
                //NOTHING
            }   else {
                

                    int HP = ship.getHealth();
                    int SPD = ship.getSpeed();
                    int RAN = ship.getRange();
                    int ATK = ship.getFirepower();
                    
                    
                    
                    
              
                    
                
                    //This matches up the nearby ship with the Queen’s direct stats.
                    if(HP == 3 && SPD == 0 && RAN == 3 && ATK == 4){
                        this.fire(arena, x, y);
                        this.fire(arena, x, y);
                        this.fire(arena, x, y);
                    }
                    
                
                    
                    
                    
                    
                }


}
}
}