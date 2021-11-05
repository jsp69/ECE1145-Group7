package hotciv.standard;

import hotciv.framework.CityStrat;
import hotciv.framework.Game;
import hotciv.framework.Position;

public class AlphaCity implements CityStrat {
    @Override
    public void cityGrow(Game game) {

    }

    @Override
    public void cityProduce(Game game) {
        Position p=new Position(0,0);
        for(int i=0;i<16;i++) {
            p.setRow(i);
            for (int j = 0; j < 16; j++) {
                p.setColumn(j);
                if (game.getCityAt(p) != null) {
                    ((CityImpl)(game.getCityAt(p))).setTreasury(game.getCityAt(p).getTreasury()+6);
                }
            }
        }
    }
}
