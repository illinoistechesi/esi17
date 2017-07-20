package esi17.UnknownUser02;
import battleship.core.*;
import java.util.List;

/*
 * Roldan Ship
 * @author Your Name
 */
public class RoldanShip extends Ship {
    
    public RoldanShip() {
        this.initializeName(" RoldanShip");
        this.initializeOwner("Tony");
        this.initializeHull(2);
        this.initializeFirepower(2);
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
        
        /*
    if (x > 1) {
    this.move (Arena arena, Direction. NORTH);
    }
    if (x < 3){
    fire(Arena arena, int 3, int 1);
    }
{    List<Ship> list = this.getNearbyShips(arena);
Ship enemy = list.get(0);
System.out.println("Enemy Ship: " + enemy.getName());
System.out.println("Firepower: " + enemy.getFirepower());

if (enemy.getSpeed() > this.getSpeed()) {
    System.out.println("The enemy ship will move before my ship.");
} else if (enemy.getSpeed() > this.getSpeed()) {
    System.out.println("The enemy ship will move after my ship.");
} else {
    System.out.println("The enemy ship might move before or after my ship.");
}

int hitsTaken = enemy.getHull() - enemy.getHealth();
System.out.println("Enemy has taken " + hitsTaken + " hits.");
 */
    }
}