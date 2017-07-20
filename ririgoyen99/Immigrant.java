package esi17.ririgoyen99;
import battleship.core.*;
import java.util.List;

/*
 * Immigrant
 * @ririgoyen99
 */
public class Immigrant extends Ship {
    
    public Immigrant() {
        this.initializeName("Immigrant");
        this.initializeOwner("ririgoyen99");
        this.initializeHull(3);
        this.initializeFirepower(3);
        this.initializeSpeed(1);
        this.initializeRange(3);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    public void doTurn(Arena arena){
        List<Ship> nearby = this.getNearbyShips(arena);
// Loop over all the ships
for (int i = 0; i < nearby.size(); i++) {
    Ship ship = nearby.get(i);
    // Call the getTeam() method on any ship to get its team name
    String myTeam = this.getTeam();
    String theirTeam = ship.getTeam();
    // To compare Strings, we have to use the special .equals() method
    // It will return true if the strings are equal and false if they are not
    if (theirTeam.equals(myTeam)) {
        // Don't shoot!
    } else {
        this.move(arena, Direction.WEST);
        this.move(arena, Direction.WEST);
        this.move(arena, Direction.WEST);
        this.move(arena, Direction.WEST);
        this.move(arena, Direction.SOUTH);
        this.move(arena, Direction.SOUTH);
        this.move(arena, Direction.NORTH);
        this.move(arena, Direction.WEST);
        if (nearby.size() > 0){
         Ship first = nearby.get(0);
         Coord coord = this.getShipCoord(arena, first);
         int X = coord.getX();
         int Y = coord.getY();
         this.fire(arena, X, Y);
        }}
        if (nearby.size() > 0){
         Ship first = nearby.get(1);
         Coord coord = this.getShipCoord(arena, first);
         int X = coord.getX();
         int Y = coord.getY();
         this.fire(arena, X, Y);
    }
    if (nearby.size() > 0){
         Ship first = nearby.get(2);
         Coord coord = this.getShipCoord(arena, first);
         int X = coord.getX();
         int Y = coord.getY();
         this.fire(arena, X, Y);
    }
    }
}}