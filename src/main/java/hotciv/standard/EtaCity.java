package hotciv.standard;

import hotciv.framework.*;

import java.util.Arrays;

public class EtaCity implements CityStrat {
    @Override
    public void cityGrow(Game game) {
        Position p = new Position(0,0);
        for(int i=0;i<16;i++){
            p.setRow(i);
            for(int j=0;j<16;j++){
                p.setColumn(j);
                if (game.getCityAt(p) != null){
                    if ( ( ((CityImpl)(game.getCityAt(p))).getFood() >= ( 3 * game.getCityAt(p).getSize() + 5 ) )
                            && ( game.getCityAt(p).getSize() <= 9 ) ) {
                        ((CityImpl)(game.getCityAt(p))).setFood(0);
                        ((CityImpl)(game.getCityAt(p))).setSize(game.getCityAt(p).getSize() + 1);
                    }
                }
            }
        }
    }

    @Override
    public void cityProduce(Game game) {
        Position p = new Position(0,0);
        int[] foodTiles;
        int[] prodTiles;
        int foodOut;
        int prodOut;

        for(int i=0;i<16;i++) {
            p.setRow(i);
            for (int j = 0; j < 16; j++) {
                p.setColumn(j);
                foodOut = 1;
                prodOut = 1;
                if (game.getCityAt(p) != null) {
                    prodTiles = surroundProd(p,game);
                    foodTiles = surroundFood(p,game);

                    Arrays.sort(prodTiles);
                    Arrays.sort(foodTiles);

                    for (int k=7;k>7-game.getCityAt(p).getSize()+1;k--){
                        foodOut+=foodTiles[k];
                        prodOut+=prodTiles[k];
                    }

                    if (game.getCityAt(p).getWorkforceFocus().equals(GameConstants.productionFocus)){
                        ((CityImpl)(game.getCityAt(p))).setTreasury(game.getCityAt(p).getTreasury() + prodOut);
                        ((CityImpl)(game.getCityAt(p))).setFood(((CityImpl)(game.getCityAt(p))).getFood() + 1);
                    } else{
                        ((CityImpl)(game.getCityAt(p))).setTreasury(game.getCityAt(p).getTreasury() + 1);
                        ((CityImpl)(game.getCityAt(p))).setFood(((CityImpl)(game.getCityAt(p))).getFood() + foodOut);
                    }
                }
            }
        }
    }

    private int[] surroundProd(Position p, Game game){
        int [] out=new int[] {0,0,0,0,0,0,0,0};
        Position check=new Position(p);
        int k=0;

        for(int i=-1;i<2;i++){
            check.setRow(p.getRow()+i);
            if(check.getRow()>=0 && check.getRow()<16) {
                for (int j = -1; j < 2; j++) {
                    check.setColumn(p.getColumn()+j);
                    if(check.getColumn()>=0 && check.getColumn()<16) {
                        if(game.getTileAt(check)!=null){
                            if(game.getTileAt(check).getTypeString().equals(GameConstants.FOREST)){
                                out[k]=3;
                                k++;
                            }else if(game.getTileAt(check).getTypeString().equals(GameConstants.HILLS)){
                                out[k]=2;
                                k++;
                            }else if(game.getTileAt(check).getTypeString().equals(GameConstants.MOUNTAINS)){
                                out[k]=1;
                                k++;
                            }
                        }
                    }
                }
            }
        }
        return out;
    }

    private int[] surroundFood(Position p, Game game){
        int [] out=new int[] {0,0,0,0,0,0,0,0};
        Position check=new Position(p);
        int k=0;
        int count=0;

        for(int i=-1;i<2;i++){
            check.setRow(p.getRow()+i);
            if(check.getRow()>=0 && check.getRow()<16) {
                for (int j = -1; j < 2; j++) {
                    check.setColumn(p.getColumn()+j);
                    if(check.getColumn()>=0 && check.getColumn()<16) {
                        if(game.getTileAt(check)!=null && !(i==0 && j==0)){
                            if(game.getTileAt(check).getTypeString().equals(GameConstants.PLAINS)){
                                out[k]=3;
                                k++;
                            }else if(game.getTileAt(check).getTypeString().equals(GameConstants.OCEANS)){
                                out[k]=2;
                                k++;
                            }
                        }
                    }
                }
            }
        }
        return out;
    }
}
