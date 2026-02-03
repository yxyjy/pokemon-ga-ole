package util;

import model.Player;

//coins utility class
public class Coins {
    private static int winnningRewardCoins = 100;

    public static int getWinnningRewardCoins() {
        return winnningRewardCoins;
    }

    public static void calculateWinningCoins(Player player, int rewardCoins){
        int currentCoins = player.getCoins();
        player.setCoins(currentCoins + rewardCoins);
    }
}
