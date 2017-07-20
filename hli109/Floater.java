package esi17.hli109;
import battleship.core.*;
import java.util.List;

/*
 * Floaty
 * @author Nick
 */
public class Floater extends Ship {
    
    public Floater() {
        this.initializeName("Floaty");
        this.initializeOwner("Nick");
        this.initializeHull(1);
        this.initializeFirepower(4);
        this.initializeSpeed(0);
        this.initializeRange(5);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    public void doTurn(Arena arena) {
        List<Ship> nearby = this.getNearbyShips(arena);

        while(this.getRemainingShots() > 0) {
            this.shoot(arena, nearby);
            nearby = this.getNearbyShips(arena);
        }
    }
    
    private void shoot(Arena arena, List<Ship> nearby) {
        double minHealth = Double.POSITIVE_INFINITY;
        Ship target = null;
        for (Ship s : nearby) {
            if (s.getHealth() < minHealth) {
                target = s;
                minHealth = s.getHealth();
            }
        }
        
        for (int i = 0; i <= minHealth; i++) {
            Coord coord = this.getShipCoord(arena, target);
            this.fire(arena, coord.getX(), coord.getY());
        }
    }
    
}


/* Accessible Information

Arena
    
    isInRange(Ship a, Ship b)
    getXSize()
    getYSize()
    countLiveShips()
    getRandom()
    getTurn()

Ship
    doTurn(Arena arena)
    move(Arena arena, Direction direction)
    fire(Arena arena, int x, int y)
    getShipCoord(Arena arena, Ship ship) // for other ships in range
    getNearbyShips(Arena arena)
    
    getCoord() // for the self
    getRemainingMoves()
    getRemainingShots()
    isSunk()
    
    getHealth()
    getName()
    getOwner()
    getHull()
    getFirepower()
    getSpeed()
    getRange()
    
Coord
    getX()
    getY()
    
Direction
    NORTH, SOUTH, WEST, EAST
    
Grid

Helper

*/