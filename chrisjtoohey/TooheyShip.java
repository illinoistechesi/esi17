package esi17.chrisjtoohey;
import battleship.core.*;
import java.util.List;

/*
 * Custom Ship
 * @author Your Name
 */
public class TooheyShip extends Ship {
    
    public TooheyShip() {
        this.initializeName("Toohey Ship");
        this.initializeOwner("Your Name");
        this.initializeHull(2);
        this.initializeFirepower(2);
        this.initializeSpeed(0);
        this.initializeRange(6);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    public void doTurn(Arena arena) {
        List<Ship> nearby = this.getNearbyShips(arena);
        Ship lowhealth = null;
        
        for (int i = 0; i < nearby.size(); i++) {
            Ship ship = nearby.get(i);
            String myTeam = this.getTeam();
            String theirTeam = ship.getTeam();
            if (theirTeam.equals(myTeam)) {
            }   else {
                int healthvalue = 10;
                if(lowhealth != null) {
                    healthvalue = lowhealth.getHealth();
                }
                    if (ship.getHealth() < healthvalue) {
                    lowhealth = ship;
                    System.out.println("Found a new lowest ship");
                    }
                
            }
        }
        if (lowhealth != null) {
            Coord coord = lowhealth.getCoord();
            int x = coord.getX();
            int y = coord.getY();
            this.fire(arena, x, y);
        } 
        } 

    }
