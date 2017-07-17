package esi17.hli109;
import battleship.core.*;
import java.util.List;
import java.util.Random;

/*
 * Boaty McBoatFace
 * @author Nick
 */
public class BoatyMcBoatFace extends Ship {
    
    private Random rand = new Random();
    private final Direction[] movement = {Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST};
    
    public BoatyMcBoatFace() {
        this.initializeName("Boaty McBoatFace");
        this.initializeOwner("Nick");
        this.initializeHull(2);
        this.initializeFirepower(4);
        this.initializeSpeed(1);
        this.initializeRange(3);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    public void doTurn(Arena arena) {
        List<Ship> observed = arena.getNearbyEnemies(this);
        double minHealth = Double.POSITIVE_INFINITY;
        Ship target = null;
        
        for (int i = 0; i < observed.size(); i++) {
            if (observed.get(i).getHealth() < minHealth) {
                target = observed.get(i);
                minHealth = target.getHealth();
            }
        }
        
        if (target == null) {
            arena.move(this, movement[rand.nextInt(4)]);
            observed = arena.getNearbyEnemies(this);
            for (int i = 0; i < observed.size(); i++) {
                if (observed.get(i).getHealth() < minHealth) {
                    target = observed.get(i);
                    minHealth = target.getHealth();
                }
            }
        }
        
        if (target != null) {
            while(this.getRemainingShots() > 0) {
                // Coord loc = arena.getShipCoord(this, target);
                Coord loc = arena.getShipCoord(target);
                arena.fire(this, loc.getX(), loc.getY());
            }
        }
    }
    
}

/* Accessible Information
    
    Arena
        // commonly used
        public void move(Ship self, Direction dir)
        
        public void fire(Ship self, int x, int y) 
        
        public List<Ship> getNearbyEnemies(Ship self) 
    
        public boolean isInRange(Ship self, Ship target) 
        
    Ship
        public boolean isSunk()
        
        public Ship getSunkBy()
        public Coord getSunkAt()
        public String getName()
        public String getOwner()
        
        public int getHealth()
        
        public int getHull()
        public int getFirepower()
        public int getSpeed()
        public int getRange()
        
        // Commonly used
        Coord getSelfCoord(Arena arena)
        
        Coord getShipCoord(Arena arena, Ship target)
        
    Direction
        NORTH, SOUTH, WEST, EAST
    
    Coord
        public int getX()
        public int getY()
        public String toString()
        
    Grid
        public boolean isInBounds(int x, int y)
        public int getXSize()
        public int getYSize()
        
*/