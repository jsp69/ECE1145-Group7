package hotciv.framework;

import hotciv.standard.UnitImpl;

public interface GammaCiv {

    public City settlerRedAction(Position p);
    public City settlerBlueAction(Position p);
    public UnitImpl redArcherFortify(Position p);
    public UnitImpl blueArcherFortify(Position p);


}
