package esi17.cs;
import battleship.core.*;
import java.util.*;

/*
 * KannanShip
 * @author Vinesh Kannan
 */
public class PursuerShip extends Ship {
    
    public PursuerShip() {
        this.initializeName("Pursuer Ship");
        this.initializeOwner("The Evil Fleet");
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
        Coord loc = this.getSelfCoord(arena);
        PursuerShip self = this;
        List<Ship> ships = arena.getNearbyEnemies(this);
        int movesUsed = 0;
        while (ships.size() == 0 && movesUsed < this.getSpeed()) {
            arena.move(this, Direction.EAST);
            ships = arena.getNearbyEnemies(this);
            movesUsed++;
        }
        if (ships.size() > 0)  {
            Collections.sort(ships, new Comparator<Ship>() {
                @Override
                public int compare(Ship s1, Ship s2) {
                    double d1 = self.getDistance(arena, self, s1);
                    double d2 = self.getDistance(arena, self, s2);
                    double diff = d2 - d1;
                    return (int) diff;
                }
            });
            Ship target = ships.get(0);
            Coord coord = this.getShipCoord(arena, target);
            for (int f = 0; f < this.getFirepower(); f++) {
                arena.fire(this, coord.getX(), coord.getY());
            }
        }
    }
    
    public double getDistance(Arena arena, Ship s1, Ship s2) {
        Coord c1 = this.getShipCoord(arena, s1);
        Coord c2 = this.getShipCoord(arena, s2);
        if (c1 != null && c2 != null) {
            int x1 = c1.getX();
            int y1 = c1.getY();
            int x2 = c2.getX();
            int y2 = c2.getY();
            double xDiff = (double) (x2 - x1);
            double yDiff = (double) (y2 - y1);
            return Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));
        } else {
            return Double.POSITIVE_INFINITY;
        }
    }
    
}