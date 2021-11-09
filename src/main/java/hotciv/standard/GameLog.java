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
            if(logActive) System.out.print("The Winner is " + game.getWinner().toString() + "\n");
        }
        return game.getWinner();
    }

    @Override
    public int getAge() {
        return game.getAge();
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        Unit def=getUnitAt(to);
        Unit att=getUnitAt(from);
        Player cap=null;
        if(getCityAt(to)!=null) cap=getCityAt(to).getOwner();
        boolean check=game.moveUnit(from,to);

        //Log movement
        if(check){
            if(logActive) System.out.print(getPlayerInTurn().toString()+" moved "+att.getTypeString()+" at "+from.toString()+" to "+to.toString()+"\n");
            if(def!=null) {
                //Log attack
                if (def.getOwner() != getUnitAt(to).getOwner()) {
                    if (logActive) System.out.print(getPlayerInTurn().toString() + " successfully attacked " + def.getOwner().toString() + " " + def.getTypeString() + " at " + to.toString() + "\n");
                } else {
                    if (logActive) System.out.print(getPlayerInTurn().toString() + " failed to attack " + def.getOwner().toString() + " " + def.getTypeString() + " at " + to.toString() + "\n");
                }
            }
            //Log capture
            if(cap!=null) {
                if (cap != getCityAt(to).getOwner()) {
                    if (logActive) System.out.print(getPlayerInTurn().toString() + " successfully captured " + cap + " city at " + to.toString() + "\n");
                }else{
                    if (logActive) System.out.print(getPlayerInTurn().toString() + " did not capture " + cap + " city at " + to.toString() + "\n");
                }
            }
        }else{
            if(logActive) System.out.print(getPlayerInTurn().toString()+" failed to move "+getUnitAt(from).getTypeString()+" at "+from.toString()+" to "+to.toString()+"\n");
        }

        return check;
    }

    @Override
    public void endOfTurn() {
        if(logActive) System.out.print(getPlayerInTurn().toString()+" ended turn\n");
        game.endOfTurn();
    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        if(logActive) System.out.print(getPlayerInTurn().toString()+" changed Workforce focus in city at "+p.toString()+" to "+balance+"\n");
        game.changeWorkForceFocusInCityAt(p,balance);
    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {
        if(logActive) System.out.print(getPlayerInTurn().toString()+" changed production of city at "+p.toString()+" to "+unitType+"\n");
        game.changeProductionInCityAt(p,unitType);
    }

    @Override
    public void performUnitActionAt(Position p) {
        if(getUnitAt(p)!=null) {
            if(getUnitAt(p).getTypeString().equals(GameConstants.ARCHER)){
                if(logActive) System.out.print(getPlayerInTurn().toString()+" fortified ARCHER at "+p.toString()+"\n");
            }else if(getUnitAt(p).getTypeString().equals(GameConstants.LEGION)){
                if(logActive) System.out.print(getPlayerInTurn().toString()+" attempted to trigger LEGION at "+p.toString()+"\n");
            }else if(getUnitAt(p).getTypeString().equals(GameConstants.SETTLER)){
                if(logActive) System.out.print(getPlayerInTurn().toString()+" SETTLER at "+p.toString()+" created a city "+"\n");
            }else{
                if(logActive) System.out.print(getPlayerInTurn().toString()+" triggered UNIT at "+p.toString()+"\n");
            }
            game.performUnitActionAt(p);
        }
    }

    public void toggleLogging(){logActive=!logActive;}
}
