package esi17.cs;
import esi17.cs.EvilFleet;
import battleship.core.*;
import java.util.*;

/*
 * Evil Fleet Ship
 * @author The Evil Fleet
 */
abstract class EvilFleetShip extends Ship {
    
    EvilFleetShip() {
        EvilFleet.joinFleet(this);
        //String note = EvilFleet.getFleet().size() + " ships in the Evil Fleet.";
        //EvilFleet.writeEvilLine(note);
    }
    
    abstract public void doTurn(Arena arena);
    
    Coord getShipCoordEvilly(Arena arena, Ship ship) {
        return this.getShipCoord(arena, ship);
    }
    
    int getQuadrant(Arena arena) {
        Coord loc = this.getSelfCoord(arena);
        int xMid = (int) (((double) arena.getXSize()) / 2.0);
        int yMid = (int) (((double) arena.getYSize()) / 2.0);
        int quadrant = 1;
        if (loc.getX() >= xMid) {
            if (loc.getY() >= yMid) {
                quadrant = 1;
            } else {
                quadrant = 4;
            }
        } else {
            if (loc.getY() >= yMid) {
                quadrant = 2;
            } else {
                quadrant = 3;
            }
        }
        return quadrant;
    }
    
}