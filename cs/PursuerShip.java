package esi17.cs;
import battleship.core.*;
import java.util.*;

/*
 * KannanShip
 * @author Vinesh Kannan
 */
public class PursuerShip extends EvilFleetShip {
    
    public PursuerShip() {
        this.initializeName("Pursuer Ship");
        this.initializeOwner("The Evil Fleet");
        this.initializeHull(3);
        this.initializeFirepower(3);
        this.initializeSpeed(2);
        this.initializeRange(2);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    public void doTurn(Arena arena) {
        List<Ship> ships = arena.getNearbyEnemies(this);
        int movesUsed = 0;
        while (ships.size() == 0 && movesUsed < this.getSpeed()) {
            arena.move(this, this.getNextDirection(arena));
            ships = arena.getNearbyEnemies(this);
            movesUsed++;
        }
        if (ships.size() > 0)  {
            ships = EvilFleet.sortShipsByDistanceFrom(this, ships, arena);
            Ship target = ships.get(0);
            Coord coord = this.getShipCoord(arena, target);
            for (int f = 0; f < this.getFirepower(); f++) {
                arena.fire(this, coord.getX(), coord.getY());
            }
        }
    }
    
    protected Direction getNextDirection(Arena arena) {
        boolean preferX = arena.getXSize() >= arena.getYSize();
        Coord loc = this.getSelfCoord(arena);
        boolean westEdge = loc.getX() <= this.getRange();
        boolean eastEdge = arena.getXSize() - loc.getX() <= this.getRange();
        boolean northEdge = loc.getY() <= this.getRange();
        boolean southEdge = arena.getYSize() - loc.getY() <= this.getRange();
        boolean nearEdge = westEdge || eastEdge || northEdge || southEdge;
        if (nearEdge) {
            switch (this.getQuadrant(arena)) {
                case 1: return preferX ? Direction.WEST : Direction.SOUTH;
                case 2: return preferX ? Direction.EAST : Direction.SOUTH;
                case 3: return preferX ? Direction.EAST : Direction.NORTH;
                case 4: return preferX ? Direction.WEST : Direction.NORTH;
                default: return preferX ? Direction.EAST : Direction.SOUTH;
            }
        } else {
            return preferX ? Direction.WEST : Direction.NORTH;
        }
    }
    
}