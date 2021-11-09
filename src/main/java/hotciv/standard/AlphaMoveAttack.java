package hotciv.standard;

import hotciv.framework.*;

import java.util.Objects;

public class AlphaMoveAttack implements MoveAttackStrat {
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
                // Change position of unit
                unitLoc[to.getRow()][to.getColumn()] = unitLoc[from.getRow()][from.getColumn()];
                unitLoc[from.getRow()][from.getColumn()]= null;
                //Change city ownership
                if (cityLoc[to.getRow()][to.getColumn()] != null) {
                    ((CityImpl)(cityLoc[to.getRow()][to.getColumn()])).setOwner(unitLoc[to.getRow()][to.getColumn()].getOwner());
                }
                return true;
            } else {
                return false;
            }
        }
    }
}
