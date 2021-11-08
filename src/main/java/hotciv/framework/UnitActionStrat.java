package hotciv.framework;

public interface UnitActionStrat {
    void performUnitActionAt(Position p);
    City[][] getCitiesArray();
    Tile[][] getTilesArray();
    Unit[][] getUnitsArray();
}

