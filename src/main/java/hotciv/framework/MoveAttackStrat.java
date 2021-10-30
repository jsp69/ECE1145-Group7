package hotciv.framework;

public interface MoveAttackStrat {
    boolean moveUnit( Position from, Position to, Game game, City[][] cityLoc, Unit[][] unitLoc );
}
