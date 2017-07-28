package esi17.DoomDawn;
import battleship.core.*;
import java.util.List;

/*
 * Doom Ship
 * @author Freddy Herrera
 */
public class DoomShip extends Ship {
    
    public DoomShip() {
        this.initializeName("Doom Ship");
        this.initializeOwner("Freddy Herrera");
        this.initializeHull(3);
        this.initializeFirepower(2);
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
        // Fill in your strategy here
        int ASizeX = arena.getXSize();
        int ASizeY = arena.getYSize();
        //System.out.println("\nArena X: "+ASizeX+" Arena Y: "+ASizeY);
        Coord coordMy = this.getCoord();
        int x1 = coordMy.getX();
        int y1 = coordMy.getY();
        
        int myhealth = this.getHealth();
        int myhull = this.getHull();
        int myfire = this.getFirepower();
        int myspeed = this.getSpeed();
        int myrange = this.getRange();

       // Coord shipLocation = this.getShipCoord(arena, this);
        //int shipX = shipLocation.getX();
        //int shipY = shipLocation.getY();
        //System.out.println("\nDoomShip's Location is: ("+shipX+","+shipY+")\n");
        //int h1 = this.getHealth();
        //System.out.println("DoomShip's Health is: "+h1);
        //arena.move(this, Direction.NORTH);
        //arena.move(this, Direction.SOUTH);
        //arena.move(this, Direction.EAST);
        //arena.move(this, Direction.WEST);

        List<Ship> allships = arena.getAllShips();
        for (int i = 0; i < allships.size(); i++) {
            Ship ship = allships.get(i);
            boolean isOnMyTeam = this.isSameTeamAs(ship);
            if (isOnMyTeam) {

            }            
             else {
                Coord coord = ship.getCoord();
                int x = coord.getX();
                int y = coord.getY();
                
                String shipname = ship.getName();
                
                int healthship = ship.getHealth();
                int hullship = ship.getHull();
                int fireship = ship.getFirepower();
                int speedship = ship.getSpeed();
                int rangeship = ship.getRange();
                //int healthship = this.getHealth(ship);
                /*System.out.println("The Enemy Ship Found is: "+shipname);
                //System.out.println("Enemy Health: "+healthship);
                //System.out.println("Enemy Hull: "+hullship);
                //System.out.println("Enemy Firepower: "+fireship);
                //System.out.println("Enemy Speed: "+speedship);
                //System.out.println("Enemy Range: "+rangeship);
                if (healthship > myhealth){
                    System.out.println("Enemy Ship Health is higher");
                }
                else {
                    System.out.println("Enemy Ship Health is lower");
                }

                if (hullship > myhull){
                    System.out.println("Enemy Ship Hull is higher");
                }
                else {
                    System.out.println("Enemy Ship Hull is lower");
                }                

                if (fireship > myfire){
                    System.out.println("Enemy Ship Fire is higher");
                }
                else {
                    System.out.println("Enemy Ship Fire is lower");
                }                

                if (speedship > myspeed){
                    System.out.println("Enemy Ship Speed is higher");
                }
                else {
                    System.out.println("Enemy Ship Speed is lower");
                }
                
                if (rangeship > myrange){
                    System.out.println("Enemy Ship Range is higher");
                }
                else {
                    System.out.println("Enemy Ship Range is lower");
                }
                */
                if (fireship > 2){
                    if (x1 < x){
                        this.move(arena, Direction.EAST);
                    
                        if (y1 < y){
                            this.move(arena, Direction.SOUTH);
                        }
                        else{
                            this.move(arena, Direction.NORTH);
                        }
                        this.fire(arena, x ,y);
                    }
                    else{
                        this.move(arena, Direction.WEST);
                    
                        if (y1 < y){
                            this.move(arena, Direction.SOUTH);
                        }
                        else{
                            this.move(arena, Direction.NORTH);
                        }
                        this.fire(arena, x ,y);
                    }
                }
                /*if (x1 < x){
                    this.move(arena, Direction.EAST);
                    
                    if (y1 < y){
                        this.move(arena, Direction.SOUTH);
                    }
                    else{
                        this.move(arena, Direction.NORTH);
                    }
                }
                else{
                    this.move(arena, Direction.WEST);
                    
                    if (y1 < y){
                        this.move(arena, Direction.SOUTH);
                    }
                    else{
                        this.move(arena, Direction.NORTH);
                    }
                }*/
                this.fire(arena, x, y);
             }
        }
        
        //this.move(arena, Direction.WEST);
        
    }
    
}