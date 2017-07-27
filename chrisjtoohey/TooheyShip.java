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
        /*List<Ship> nearby = this.getNearbyShips(arena);
        Ship lowhealth = null;
        int turn = arena.getTurn();
        if(turn == 1) {
            this.fire(arena, 07, 10);
            this.fire(arena, 07, 10);
            this.fire(arena, 07, 10);
        }
        
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
            */
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
