package esi17.hli109;
import battleship.core.*;
import java.util.List;

/*
 * Boaty McBoatFace
 * @author Nick
 */
public class BoatyMcBoatFace extends Ship {
    
    private final Direction[] movement = {Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST};

    private Coord camp;
    private boolean phase;
    private boolean isHit;
    private int sustainDamage;
    
    public BoatyMcBoatFace() {
        this.initializeName("Boaty McBoatFace");
        this.initializeOwner("Nick");
        this.initializeHull(2);
        this.initializeFirepower(3);
        this.initializeSpeed(2);
        this.initializeRange(3);
        
        phase = 0; 
        isHit = false;
        sustainDamage = 0;
    }
    
    
    private void movementProtocol(Arena arena, Coord target, boolean options) {
        List<Ship> start = this.getNearbyShips(arena);
        double minHealth = Double.POSITIVE_INFINITY;
        Ship target = null;
        int x = Math.abs(this.getCoord().x - target.x);
        int y = Math.abs(this.getCoord().y - target.y);
        
        if (start.size() == 0) {
            if (x > y) {
                this.move(arena, (target.x > this.getCoord().x)? Direction.EAST : Direction.WEST);
            } else {
                this.move(arena, (target.y > this.getCoord().x)? Direction.NORTH : Direction.SOUTH);
            }
            if (options) {
                movementProtocol(arena, target, false);
            }
        }
        if (start.size() > 1) {
            
        }
        
    }
    
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    public void doTurn(Arena arena) {
        if (phase == 0) {
            Coord map = new Coord(arena.getXSize()-1, arena.getYSize()-1);
            Coord self = this.getCoord();
            int x, y;
            
            if (self.x <= map.x/2) {
                x = 1;
            } else {
                x = map.x - 2;
            }
            if (self.y <= map.y/2) {
                y = 1;
            } else {
                y = map.y - 2;
            }
            camp = new Coord(x, y);
            phase++;
        }
        
        if (phase == 1) {
            int x = Math.abs(this.getCoord().x - target.x);
            int y = Math.abs(this.getCoord().y - target.y);
            if (x+y == 0) {
                phase++;
            } else {
                movementProtocol(arena, camp);
            }
        }
        
        if (phase == 2) {
            
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