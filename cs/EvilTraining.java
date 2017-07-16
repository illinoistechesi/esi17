package esi17.cs;
import battleship.core.*;
import battleship.games.*;
import battleship.ships.*;
import java.util.*;

public class EvilTraining {
    
    public static int TRIALS = 20;
    
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
        
        System.out.println("Running Evil Trials.");
        Map<Integer, Integer> killMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < TRIALS; i++) {
            updateLoadingBar(i, TRIALS);
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
            int turns = battle.getArena().getTurn();
            String results = String.format("Seed %d: %d kills, %d turns.", trialSeed, kills, turns);
            EvilFleet.writeEvilLine(results);
            if (!killMap.containsKey(kills)) {
                killMap.put(kills, 0);
            }
            killMap.put(kills, (killMap.get(kills) + 1));
        }
        clearLoadingBar();
        System.out.println(TRIALS + " Evil Trials Completed.");
        for (Map.Entry<Integer, Integer> entry : killMap.entrySet()) {
            int k = entry.getKey();
            int v = entry.getValue();
            System.out.println(String.format("%d Kills: %d", k, v));
        }
        
    }
    
    public static int loadingBarSize = 40;
    
    public static void updateLoadingBar(int i, int total) {
        String loadingBar = "";
        int loadTo = (int) (((double) i / (double) total) * (double) loadingBarSize);
        for (int b = 0; b < loadingBarSize; b++) {
            if (b <= loadTo) {
                loadingBar += "#";
            } else {
                loadingBar += " ";
            }
        }
        String loader = String.format("\r %3d [%s]", i, loadingBar);
        System.out.print(loader);
    }
    
    public static void clearLoadingBar() {
        String fullBar = "";
        for (int f = 0; f < loadingBarSize * 2; f++) {
            fullBar += " ";
        }
        System.out.print("\r" + fullBar + "\r");
    }
    
}