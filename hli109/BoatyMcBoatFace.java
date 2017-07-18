package esi17.hli109;
import battleship.core.*;
import java.util.List;

/*
 * Boaty McBoatFace
 * @author Nick
 */
public class BoatyMcBoatFace extends Ship {
    
    private final Direction[] movement = {Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST};
    
    public BoatyMcBoatFace() {
        this.initializeName("Boaty McBoatFace");
        this.initializeOwner("Nick");
        this.initializeHull(3);
        this.initializeFirepower(1);
        this.initializeSpeed(1);
        this.initializeRange(4);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    public void doTurn(Arena arena) {
        
        List<Ship> observed = this.getNearbyShips(arena);
        double minHealth = Double.POSITIVE_INFINITY;
        Ship target = null;
        
        for (int i = 0; i < observed.size(); i++) {
            if (observed.get(i).getHealth() < minHealth) {
                target = observed.get(i);
                minHealth = target.getHealth();
            }
        }
        
        if (target == null) {
            this.move(arena, movement[arena.getRandom().nextInt(4)]);
            observed = this.getNearbyShips(arena);
            for (int i = 0; i < observed.size(); i++) {
                if (observed.get(i).getHealth() < minHealth) {
                    target = observed.get(i);
                    minHealth = target.getHealth();
                }
            }
        }
        
        if (target != null) {
            while(this.getRemainingShots() > 0) {
                Coord loc = this.getShipCoord(arena, target);
                this.fire(arena, loc.getX(), loc.getY());
            }
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