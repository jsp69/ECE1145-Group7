package hotciv.framework;

public interface CivFactory {
    WinStrat createWinStrat();
    AgingStrat createAgingStrat();
    UnitActionStrat createUnitActionStrat();
}
