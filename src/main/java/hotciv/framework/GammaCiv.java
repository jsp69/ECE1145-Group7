package hotciv.framework;

import hotciv.standard.UnitImpl;

public interface GammaCiv {

    public City redSettlerAction(Position p);
    public City blueSettlerAction(Position p);
    public UnitImpl redArcherFortify(Position p);
    public UnitImpl blueArcherFortify(Position p);


}
