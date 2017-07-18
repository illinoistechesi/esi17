package esi17.kaminoshimobe;
import core.*;
import java.util.List;

    // KSsinker
    // @Kamino
    
    
public class KSsinker extends Ship {
    
    public KSsinker() {
        this.initializeName("K.S. Sinker");
        this.initializeOwner("Abdul Muhammad");
        this.initializeHull(2);
        this.initializeFirepower(1);
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
        boolean noPlans = true;
        
        
        Coord shipLocation = this.getSelfCoord(arena);
        Coord enemyShip = this.getNearbyEnemies(arena);
        
        
        Coord target this.getShipCoord(Arena arena, Ship ship);
        int targetX = target.getX;
        int targetY = target.getY;
        int shipX = shipLocation.getX();
        int shipY = shipLocation.getY();
        int targetRangeX = Math.abs(shipX - targetX);
        int targetRangeY = Math.abs(shipX - targetY);
        List<Ship> nearby = this.getNearbyShips(arena);
        Ship first = nearby.get(0);
        // for (Ship ship : nearby) {
            System.out.println("One nearby ship has " + ship.getHealth() + " HP left.");
            
        if (nearby.size() == 0){
            if(shipY == 0){
            arena.move(this, Direction.SOUTH);
            arena.move(this, Direction.SOUTH);
            arena.move(this, Direction.SOUTH);
            arena.move(this, Direction.SOUTH);
        } else if (shipX == 0)  {
            arena.move(this, Direction.EAST);
            arena.move(this, Direction.EAST);
            arena.move(this, Direction.EAST);
            arena.move(this, Direction.EAST);
            
        } else if (shipX == 0 && shipY == 0)  {
            arena.move(this, Direction.EAST);
            arena.move(this, Direction.EAST);
            arena.move(this, Direction.SOUTH);
            arena.move(this, Direction.SOUTH);
            
        } else if (shipX == 9)  {
            arena.move(this, Direction.WEST);
            arena.move(this, Direction.WEST);
            arena.move(this, Direction.WEST);
            arena.move(this, Direction.WEST);
            
        } else if (shipY == 9)  {
            arena.move(this, Direction.NORTH);
            arena.move(this, Direction.NORTH);
            arena.move(this, Direction.NORTH);
            arena.move(this, Direction.NORTH);
            
        }
        
            arena.move(this, Direction.NORTH);
            arena.move(this, Direction.EAST);
            arena.move(this, Direction.EAST);
            arena.move(this, Direction.NORTH);
        
        }
            
            if (targetRangeX == 3 || targetRangeY == 3){
                this.fire(Arena arena, targetX, targetY);
                if(shipX > targetX && ship > targetY){
                    arena.move(this, Direction.EAST);
                    arena.move(this, Direction.EAST);
                    arena.move(this, Direction.NORTH);
                    arena.move(this, Direction.NORTH);
                }
                
                if(shipX > targetX && ship > targetY){
                    arena.move(this, Direction.WEST);
                    arena.move(this, Direction.WEST);
                    arena.move(this, Direction.SOUTH);
                    arena.move(this, Direction.SOUTH);
                }
                
            }
        if(arena.isInRange(first, this) == true)    {
            
        if(shipX > targetX && ship > targetY){
                    arena.move(this, Direction.EAST);
                    arena.move(this, Direction.EAST);
                    arena.move(this, Direction.NORTH);
                    arena.move(this, Direction.NORTH);
                }
                
                if(shipX > targetX && ship > targetY){
                    arena.move(this, Direction.WEST);
                    arena.move(this, Direction.WEST);
                    arena.move(this, Direction.SOUTH);
                    arena.move(this, Direction.SOUTH);
                }
        
        
        }
    }
    
}