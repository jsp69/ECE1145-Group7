package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.WinStrat;

public class EpsilonWin implements WinStrat {
    int redAttack=0;
    int blueAttack=0;
    int greenAttack=0;
    int yellowAttack=0;

    @Override
    public Player getWinner(Game game) {
        if(redAttack>=3){
            return Player.RED;
        }else if(blueAttack>=3){
            return Player.BLUE;
        }else if(greenAttack>=3){
            return Player.GREEN;
        }else if(yellowAttack>=3){
            return Player.YELLOW;
        }else{
            return null;
        }
    }

    @Override
    public void increaseAttack(Player p) {
        if(p==Player.RED){
            redAttack++;
        }else if(p==Player.BLUE){
            blueAttack++;
        }else if(p==Player.GREEN){
            greenAttack++;
        }else if(p==Player.YELLOW){
            yellowAttack++;
        }
    }
}
