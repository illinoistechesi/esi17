package esi17.kaminoshimobe;
import battleship.core.*;
import java.util.List;

    // KSsinker
    // @Kamino
    
    
public class KSsinker extends Ship {
    
    public KSsinker() {
        this.initializeName("K.S. Kruiser");
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
        int turn = arena.getTurn();
        if(turn == 0){
         this.move(arena, Direction.EAST);
         this.move(arena, Direction.EAST);
        }
        this.move(arena, Direction.EAST);
       
                
        
        
        //Coord shipLocation = this.getSelfCoord(arena);
        // Additions by Vinesh
        Coord shipLocation = this.getCoord();
        int shipX = shipLocation.getX();

        //Coord enemyShip = this.getNearbyShips(arena);
        
        
        Coord me = this.getCoord();
        int a = me.getX();
        int b = me.getY();
        
        // Coord target this.getShipCoord(Arena arena, Ship ship);
        // int targetX = target.getX();
        // int targetY = target.getY();
        // int shipX = shipLocation.getX();
        // int shipY = shipLocation.getY();
        // int targetRangeX = Math.abs(shipX - targetX);
        // int targetRangeY = Math.abs(shipX - targetY);
        
        
        List<Ship> nearby = this.getNearbyShips(arena);
        
        for (int i = 0; i < nearby.size(); i++) {
            
            Ship ship = nearby.get(i);
            
            String myTeam = this.getTeam();
            String theirTeam = ship.getTeam();
            // for (Ship ship : nearby) {
            // System.out.println("One nearby ship has " + ship.getHealth() + " HP left.");
            
            if (theirTeam.equals(myTeam)) {
                //NOTHING
            }   else {
                
                    Coord enem = ship.getCoord();
                    Coord coord = this.getCoord();
                    int x = enem.getX();
                    int y = enem.getY();
                    int shipX = enem.getX();
                    int HP = ship.getHealth();
                    int SPD = ship.getSpeed();
                    int RAN = ship.getRange();
                    int ATK = ship.getFirepower();
                    
                    // Determine(arena);
                    int sneak = x - 5;
                    
                    
<<<<<<< HEAD
                    if(x > shipX && RAN > shipX){
                    for (int j = 0; j < RAN; j++){
                        this.move(arena, Direction.EAST);
                    }
                }
                    
                
                if(x == sneak){
                    this.move(arena, Direction.EAST);
                    this.move(arena, Direction.EAST);
                    this.fire(arena, x, y);
                    this.move(arena, Direction.WEST);
                    this.move(arena, Direction.WEST);
                }
=======
                //     if(x > shipX && RAN > shipX){
                //     for (int e = 0; e < RAN; e++){
                //         this.move(arena, Direction.EAST);
                //     }
                // }
                    
                
                    this.fire(arena, x, y);
                    this.move(arena, Direction.WEST);
                    this.move(arena, Direction.WEST);
                    this.move(arena, Direction.WEST);
                    this.move(arena, Direction.WEST);
                
>>>>>>> d1a8c250a619258cd6a5f1743c9221ffac8094e7
                    
                    
                    // this.fire(arena, x, y);
                    
                }
            
        // if (nearby.size() == 0){
        //     if(shipY == 0){
        //         arena.move(this, Direction.SOUTH);
        //         arena.move(this, Direction.SOUTH);
        //         arena.move(this, Direction.SOUTH);
        //         arena.move(this, Direction.SOUTH);
        // } else if (shipX == 0)  {
        //         arena.move(this, Direction.EAST);
        //         arena.move(this, Direction.EAST);
        //         arena.move(this, Direction.EAST);
        //         arena.move(this, Direction.EAST);
            
        // } else if (shipX == 0 && shipY == 0)  {
        //         arena.move(this, Direction.EAST);
        //         arena.move(this, Direction.EAST);
        //         arena.move(this, Direction.SOUTH);
        //         arena.move(this, Direction.SOUTH);
            
        // } else if (shipX == 9)  {
        //         arena.move(this, Direction.WEST);
        //         arena.move(this, Direction.WEST);
        //         arena.move(this, Direction.WEST);
        //         arena.move(this, Direction.WEST);
            
        // } else if (shipY == 9)  {
        //         arena.move(this, Direction.NORTH);
        //         arena.move(this, Direction.NORTH);
        //         arena.move(this, Direction.NORTH);
        //         arena.move(this, Direction.NORTH);
            
        // }
        
        //     arena.move(this, Direction.NORTH);
        //     arena.move(this, Direction.EAST);
        //     arena.move(this, Direction.EAST);
        //     arena.move(this, Direction.NORTH);
        
        // }
            
        //     if (targetRangeX == 3 || targetRangeY == 3){
        //         this.fire(Arena arena, targetX, targetY);
        //         if(shipX > targetX && ship > targetY){
        //             arena.move(this, Direction.EAST);
        //             arena.move(this, Direction.EAST);
        //             arena.move(this, Direction.NORTH);
        //             arena.move(this, Direction.NORTH);
        //         }
                
        //         if(shipX > targetX && ship > targetY){
        //             arena.move(this, Direction.WEST);
        //             arena.move(this, Direction.WEST);
        //             arena.move(this, Direction.SOUTH);
        //             arena.move(this, Direction.SOUTH);
        //         }
                
        //     }
        // if(arena.isInRange(first, this) == true)    {
            
        // if(shipX > targetX && ship > targetY){
        //             arena.move(this, Direction.EAST);
        //             arena.move(this, Direction.EAST);
        //             arena.move(this, Direction.NORTH);
        //             arena.move(this, Direction.NORTH);
        //         }
                
        //         if(shipX > targetX && ship > targetY){
        //             arena.move(this, Direction.WEST);
        //             arena.move(this, Direction.WEST);
        //             arena.move(this, Direction.SOUTH);
        //             arena.move(this, Direction.SOUTH);
        //         }
        
        
        // }
    }
    
    // public Determine(Arena arena){
        
    //     List<Ship> nearby = this.getNearbyShips(arena);
        
    //     for (int i = 0; i < nearby.size(); i++) {
            
    //         Ship ship = nearby.get(i);
            
    //         String myTeam = this.getTeam();
    //         String theirTeam = ship.getTeam();
    //         // for (Ship ship : nearby) {
    //         // System.out.println("One nearby ship has " + ship.getHealth() + " HP left.");
            
    //         if (theirTeam.equals(myTeam)) {
    //             //NOTHING
    //         }   else {
                
    //                 Coord coord = ship.getCoord();
    //                 int x = coord.getX();
    //                 int y = coord.getY();
    //                 int HP = ship.getHealth();
    //                 int SPD = ship.getSpeed();
    //                 int RAN = ship.getRange();
    //                 int ATK = ship.getFirepower();
    //                 int shipX = shipLocation.getX();

    //                 int sneak = x - 5;
        
    //             if(x > shipX && RAN > shipX){
    //                 for (int i = 0; i < RAN i++){
    //                     arena.move(this, Direction.EAST);
    //                 }
    //             }
        
    //             if(x == sneak){
    //                 arena.move(this, Direction.EAST);
    //                 arena.move(this, Direction.EAST);
    //                 this.fire(arena, x, y);
    //                 arena.move(this, Direction.WEST);
    //                 arena.move(this, Direction.WEST);
    //             }
                    
    //                 // this.fire(arena, x, y);
                    
    //         }
            
            
        
            }
    
}