package hotciv.standard;

import hotciv.framework.*;

public class GameLog implements Game {

    Game game;
    boolean logActive=true;

    GameLog(Game g){
        this.game=g;
    }

    @Override
    public Tile getTileAt(Position p) {
        return game.getTileAt(p);
    }

    @Override
    public Unit getUnitAt(Position p) {
        return game.getUnitAt(p);
    }

    @Override
    public City getCityAt(Position p) {
        return game.getCityAt(p);
    }

    @Override
    public Player getPlayerInTurn() {
        return game.getPlayerInTurn();
    }

    @Override
    public Player getWinner() {
        if(game.getWinner()!=null){
            System.out.print("The Winner is "+game.getWinner().toString()+"\n");
        }
        return game.getWinner();
    }

    @Override
    public int getAge() {
        return 0;
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        boolean check=game.moveUnit(from,to);
        if(check){
            System.out.print(game.getPlayerInTurn().toString()+" moved "+getUnitAt(from).getTypeString()+" at "+from.toString()+" to "+to.toString()+"\n");
        }else{
            System.out.print(game.getPlayerInTurn().toString()+" failed to move "+getUnitAt(from).getTypeString()+" at "+from.toString()+" to "+to.toString()+"\n");
        }
        return check;
    }

    @Override
    public void endOfTurn() {
        System.out.print(game.getPlayerInTurn().toString()+" ended turn\n");
        game.endOfTurn();
    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        System.out.print(game.getPlayerInTurn().toString()+" changed Workforce focus in city at "+p.toString()+" to "+balance+"\n");
        game.changeWorkForceFocusInCityAt(p,balance);
    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {
        System.out.print(game.getPlayerInTurn().toString()+" changed production of city at "+p.toString()+" to "+unitType+"\n");
        game.changeProductionInCityAt(p,unitType);
    }

    @Override
    public void performUnitActionAt(Position p) {

        game.performUnitActionAt(p);
    }

    public void toggleLogging(boolean b){logActive=b;}
}
