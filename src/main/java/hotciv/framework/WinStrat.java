package hotciv.framework;

public interface WinStrat {
    int redAttack=0;
    int blueAttack=0;
    int greenAttack=0;
    int yellowAttack=0;

    //Determine winner from conditions
    Player getWinner(Game game);

    //Increase attack count for player
    void increaseAttack(Player p);
}
