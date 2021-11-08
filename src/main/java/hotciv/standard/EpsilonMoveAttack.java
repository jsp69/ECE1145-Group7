package hotciv.standard;

import hotciv.framework.*;

import java.util.Objects;
import java.util.Random;

public class EpsilonMoveAttack implements MoveAttackStrat {
    DiceRoll diceA, diceD;

    public EpsilonMoveAttack(DiceRoll A, DiceRoll D){diceA=A;diceD=D;}
    public EpsilonMoveAttack(){diceA=new RandomRoll();diceD=new RandomRoll();}

    @Override
    public boolean moveUnit(Position from, Position to, Game game, City[][] cityLoc, Unit[][] unitLoc) {
        //Ensure new position isn't mountains
        if (Objects.equals(game.getTileAt(to).getTypeString(), GameConstants.MOUNTAINS)) {
            return false;
        }
        // Ensure new position isn't oceans
        if (Objects.equals(game.getTileAt(to).getTypeString(), GameConstants.OCEANS)) {
            return false;
        }
        // Ensure it is the correct player's unit
        if (game.getPlayerInTurn() != game.getUnitAt(from).getOwner()) {
            return false;
        } else {
            // Return true if unit was moved
            int disCol = from.getColumn() - to.getColumn();
            int disRow = from.getRow() - to.getRow();
            boolean southNorth = (Math.abs(disCol) == 1);
            boolean eastWest = (Math.abs(disRow) == 1);
            boolean zeros = (disCol == 0) || (disRow == 0);
            // Check that position is only a distance of 1 in any given direction
            if ((southNorth ^ eastWest) && zeros) {
                //Check if the tile is occupied by a unit
                if(unitLoc[to.getRow()][to.getColumn()]!=null){
                    //Check if both units are owned by the same player
                    if(unitLoc[to.getRow()][to.getColumn()].getOwner()==unitLoc[from.getRow()][from.getColumn()].getOwner()){
                        return false;
                    }else{
                        int A=diceA.rollDice()*getTerrain(from,game,cityLoc)*getStrength(unitLoc[from.getRow()][from.getColumn()].getAttackingStrength(),unitLoc,from);
                        int D=diceD.rollDice()*getTerrain(to,game,cityLoc)*getStrength(unitLoc[to.getRow()][to.getColumn()].getDefensiveStrength(),unitLoc,to);
                        if(A>D){
                            unitLoc[to.getRow()][to.getColumn()]=unitLoc[from.getRow()][from.getColumn()];
                            unitLoc[from.getRow()][from.getColumn()]=null;
                            if(cityLoc[to.getRow()][to.getColumn()]!=null){
                                ((CityImpl)(cityLoc[to.getRow()][to.getColumn()])).setOwner(unitLoc[to.getRow()][to.getColumn()].getOwner());
                            }
                        }else{
                            unitLoc[from.getRow()][from.getColumn()]=null;
                        }
                        return true;
                    }
                }else{
                    unitLoc[to.getRow()][to.getColumn()]=unitLoc[from.getRow()][from.getColumn()];
                    unitLoc[from.getRow()][from.getColumn()]=null;
                    if(cityLoc[to.getRow()][to.getColumn()]!=null){
                        ((CityImpl)(cityLoc[to.getRow()][to.getColumn()])).setOwner(unitLoc[to.getRow()][to.getColumn()].getOwner());
                    }
                    return true;
                }
            } else {
                return false;
            }
        }
    }

    private int getStrength(int base, Unit[][] unitLoc, Position p){
        int count=0;
        //Loop through rows around p
        for(int i=-1;i<2;i++){
            //Only look at columns if within map bounds
            if(p.getRow()+i>=0 && p.getRow()+i<=16){
                //Loop through cols around p
                for(int j=-1;j<2;j++){
                    //Only look at positions if within map bounds
                    if(p.getColumn()+j>=0 && p.getColumn()+i<=16){
                        //Don't count current unit
                        if (j != 0 || i != 0) {
                            //Check if a unit is at that location
                            if(unitLoc[p.getRow()+i][p.getColumn()+j]!=null) {
                                //Iterate count if a unit exists at that position with the same owner as the base unit
                                if (unitLoc[p.getRow() + i][p.getColumn() + j].getOwner() == unitLoc[p.getRow()][p.getColumn()].getOwner()) {
                                    count++;
                                }
                            }
                        }
                    }
                }
            }
        }
        return count+base;
    }

    private int getTerrain(Position p, Game game, City[][] cityLoc){
        if(game.getTileAt(p).getTypeString().equals(GameConstants.FOREST) || game.getTileAt(p).getTypeString().equals(GameConstants.HILLS)){
            return 2;
        }
        if(cityLoc[p.getRow()][p.getColumn()]!=null){
            return 3;
        }
        return 1;
    }
}
