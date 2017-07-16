package esi17.cs;
import battleship.core.*;
import battleship.games.*;
import battleship.ships.*;
import java.util.*;

public class EvilTraining {
    
    public static int TRIALS = 100;
    
    public static void main(String[] args) {
        
        int seed = 42;
        Battle.Mode mode = Battle.Mode.ZONE_SPAWN;
        if (args.length >= 1) {
            switch (args[0]) {
                case "random":
                    mode = Battle.Mode.RANDOM_SPAWN;
                    break;
                case "zone":
                    mode = Battle.Mode.ZONE_SPAWN;
                    break;
            }
        }
        if (args.length >= 2) {
            seed = Integer.parseInt(args[1]);
        }
        
        List<Class<? extends Ship>> c = new ArrayList<Class<? extends Ship>>();
        
        c.add(esi17.cs.PursuerShip.class);
        for (int s = 0; s < 4; s++) {
            c.add(battleship.ships.DummyShip.class);
        }
        
        for (int i = 0; i < TRIALS; i++) {
            System.out.print("\rRunning Evil Trial " + i);
            int trialSeed = i;
            Battle battle = new Battle(c, trialSeed, mode);
            battle.setVerbose(false);
            battle.setMaxTurns(100);
            battle.setArenaFile("files/battle-arena.txt");
            battle.setTurnFile("files/battle-turns.txt");
            battle.run();
            int kills = 0;
            List<Action> actions = battle.getActions(battle.getArena());
            for (Action act : actions) {
                if (act.getType().equals("SINK")) {
                    String attacker = act.getAttacker();
                    if (attacker.indexOf("esi17.cs.PursuerShip") > -1) {
                        kills++;
                    }
                }
            }
            String results = String.format("Seed %d: %d kills.", trialSeed, kills);
            EvilFleet.writeEvilLine(results);
        }
        System.out.println("\r" + TRIALS + " Evil Trials Completed.");
        
    }
    
}