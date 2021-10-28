package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.WinStrat;

public class BetaWin implements WinStrat {
    @Override
    public Player getWinner(Game game) {
        Position p=new Position(0,0);
        int blueCount=0;
        int redCount=0;

        //Count number of cities owned by RED and BLUE
        for(int i=0;i<16;i++){
            p.setColumn(i);
            for(int j=0;j<16;j++) {
                p.setRow(j);
                if(game.getCityAt(p)!=null) {
                    if (game.getCityAt(p).getOwner() == Player.RED) {
                        redCount++;
                    } else if (game.getCityAt(p).getOwner() == Player.BLUE) {
                        blueCount++;
                    }
                }
            }
        }
        if(redCount>0 && blueCount==0){
            return Player.RED;
        }else if(blueCount>0 && redCount==0){
            return Player.BLUE;
        }else{
            return null;
        }
    }
}
