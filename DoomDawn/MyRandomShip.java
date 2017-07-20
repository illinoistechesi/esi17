package esi17.DoomDawn;
import battleship.core.*;
import java.util.List;
import java.util.Random;

/*
 * Random Ship
 * @author The Random Evil
 */
public class MyRandomShip extends Ship {
    
    public MyRandomShip() {
        this.initializeName("My Random Ship");
        this.initializeOwner("Doom Random");
        this.initializeHull(1);
        this.initializeFirepower(2);
        this.initializeSpeed(4);
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
        
    Coord shipLocation = this.getShipCoord(arena,this);
    int shipX = shipLocation.getX();
    int shipY = shipLocation.getY();
    
    System.out.println("\nShip Random's Current Location Is: ("+shipX+","+shipY+")");
    
    //Random randomNum = arena.getRandom();
    Random ran = new Random();
    int rand2 = ran.nextInt(4);
    //int rand1 = randomNum.nextInt(4);  
    //System.out.println("The Random Number This Time Is: "+rand2);
    //System.out.println("The Random Number for the correct one This Time Is: "+rand2);
    switch(rand2){
        case 0: this.move(arena, Direction.NORTH);
                //System.out.println("R # was 0 So You Successfully Moved North\n");
                //System.out.println("Ship Random's New Location Is: ("+shipLocation.getX()+","+shipLocation.getY()+")\n");
                break;
        case 1: this.move(arena, Direction.SOUTH);
                //System.out.println("R # was 1 So You Successfully Moved South\n");
                //System.out.println("Ship Random's New Location Is: ("+shipLocation.getX()+","+shipLocation.getY()+")\n");
                break;        
        case 2: this.move(arena, Direction.EAST);
                //System.out.println("R # was 2 So You Successfully Moved East\n");
                //System.out.println("Ship Random's New Location Is: ("+shipX+","+shipY+")\n");
                break;
        case 3: this.move(arena, Direction.WEST);
                //System.out.println("R # was 3 So You Successfully Moved West\n");
                //System.out.println("Ship Random's New Location Is: ("+shipX+","+shipY+")\n");
                break;    
    }
    
    
    List <Ship> ships1 = this.getNearbyShips(arena);
    int size = ships1.size();
    //Ship target = ships.get(0);
    int arrl = 0;
    //System.out.println("The Nearby Ships Are: "+ships1);
    for(int q = 0; q<size; q++){
        arrl++;
        //Coord coord1 = this.getShipCoord(arena, target);
        Ship target = ships1.get(q);
        Coord coord1 = this.getShipCoord(arena, target);
        int shipX1 =coord1.getX();
        int shipY1 =coord1.getY();
        
        System.out.println("The nearby ship is: "+target+"\n");
        System.out.println("The Ship Is Located At: ("+shipX1+","+shipY1+")");
        this.fire(arena, shipX1, shipY1);
        System.out.println("The nearby ship: "+target+" Was attacked!!!!");
        
    }

    }
    
}