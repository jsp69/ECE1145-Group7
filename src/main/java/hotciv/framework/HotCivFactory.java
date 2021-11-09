package hotciv.framework;

public interface HotCivFactory {
    WinStrat createWinStrat();
    AgingStrat createAgingStrat();
    UnitActionStrat createUnitActionStrat();
    MoveAttackStrat createMoveAttackStrat();
    CityStrat createCityStrat();
    WorldStrat createWorldStrat();
}
