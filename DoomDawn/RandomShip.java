package esi17.DoomDawn;
import battleship.core.*;
import java.util.List;
import java.util.Random;

/*
 * Random Ship
 * @author The Random Evil
 */
public class RandomShip extends Ship {
    
    public RandomShip() {
        this.initializeName("Random Ship");
        this.initializeOwner("The Random Evil");
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
    //System.out.println("\nShip Random's Current Location Is: ("+shipX+","+shipY+")");
    //Random randomNum = arena.getRandom();
    //int rand1 = randomNum.nextInt(4); 
    Random ran = new Random();
    int rand2 = ran.nextInt(4);
    //System.out.println("The Random Number This Time Is: "+rand2);
    //System.out.println("The Random Number for the correct one This Time Is: "+rand2);
    switch(rand2){
        case 0: arena.move(this, Direction.NORTH);
                break;
        case 1: arena.move(this, Direction.SOUTH);
                break;        
        case 2: arena.move(this, Direction.EAST);
                break;
        case 3: arena.move(this, Direction.WEST);
                break;    
    }
    
    
    List <Ship> ships1 = arena.getNearbyEnemies(this);
    int size = ships1.size();
    for(int q = 0; q<size; q++){
        Ship target = ships1.get(q);
        Coord coord1 = this.getShipCoord(arena, target);
        int shipX1 =coord1.getX();
        int shipY1 =coord1.getY();
        //System.out.println("The nearby ship is: "+target+"\n");
        //System.out.println("The Ship Is Located At: ("+shipX1+","+shipY1+")");
        arena.fire(this, shipX1, shipY1);
        //System.out.println("The nearby ship: "+target+" Was attacked!!!!");
        
    }

    }
    
}