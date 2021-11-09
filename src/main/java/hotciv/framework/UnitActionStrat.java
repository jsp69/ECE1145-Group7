package hotciv.framework;

public interface UnitActionStrat {
    void performUnitActionAt(Position p, Game game);
    City[][] getCitiesArray();
    Tile[][] getTilesArray();
    Unit[][] getUnitsArray();
}

