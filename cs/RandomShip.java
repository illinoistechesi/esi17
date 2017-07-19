package esi17.cs;
import battleship.core.*;
import java.util.*;

/*
 * Random Ship
 * @author The Evil Fleet
 */
public class RandomShip extends Ship {
    
    public RandomShip() {
        this.initializeName("Random Ship");
        this.initializeOwner("The Evil Fleet");
        this.initializeHull(2);
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
        this.fireAtRandomShip(arena);
        for (int m = 0; m < this.getFirepower(); m++) {
            arena.move(this, getRandomDirection(arena));
            this.fireAtRandomShip(arena);
        }
    }
    
    public static Direction getRandomDirection(Arena arena) {
        Direction[] directions = Direction.values();
        int didx = arena.getRandom().nextInt(directions.length);
        return directions[didx];
    }
    
    public void fireAtRandomShip(Arena arena) {
        List<Ship> ships = arena.getNearbyEnemies(this);
        if (ships.size() > 0) {
            int sidx = arena.getRandom().nextInt(ships.size());
            Ship target = ships.get(sidx);
            Coord coord = this.getShipCoord(arena, target);
            arena.fire(this, coord.getX(), coord.getY());
        }
    }
}