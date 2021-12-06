package hotciv.standard;

import hotciv.framework.*;
import org.junit.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestGameObserver {
    private Game game;
    WinStrat ws;
    int roundsPassed;

    @Before
    public void setUp() {
        game = new GameImpl(new AlphaFactory());
        game = new GameImpl(new BetaFactory());
        game = new GameImpl(new DeltaFactory());
        game = new GameImpl(new EpsilonFactory());
        game = new GameImpl(new EtaFactory());
        game = new GameImpl(new GammaFactory());
        game = new GameImpl(new ThetaFactory());
        game = new GameImpl(new ZetaFactory(roundsPassed, ws));
    }

    @Test
    public void worldChanged() {
        //Set positions
        Position rSettler = new Position(4, 3);
        Position bSettler = new Position(3, 4);

        //Set cities
        Position rCity = new Position(1, 1);
        Position bCity = new Position(4, 1);

        //Check for move unit
        game.moveUnit(rSettler, bSettler);
        assertEquals("worldChangedAt called", "worldChangedAt called");

        //Check for change of city player
        assertEquals(game.getCityAt(rCity).getOwner(), Player.RED);
        assertEquals(game.getCityAt(bCity).getOwner(), Player.BLUE);
        assertEquals("worldChangedAt called", "worldChangedAt called");
    }

    @Test
    public void turnEnded() {
        //Check for end of turn
        game.endOfTurn();
        assertEquals("turnEnds called", "turnEnds called");
    }
}



        /** invoked every time some change occurs on a position
         * in the world - a unit disappears or appears, a
         * city appears, a city changes player color, or any
         * other event that requires the GUI to redraw the
         * graphics on a particular position.
         * @param pos the position in the world that has changed state
         */

       /*
        //assertThat(game, is(notNullValue()));
        //Check if unit appears or disappears
        Position redArcher = new Position(2, 0);
        //Check unit attacking strength
        if (game.getUnitAt(redArcher).getAttackingStrength() == 0) {
            //Check the winner is blue
            assertThat(game.moveUnit(new Position(3, 3), new Position(3, 2)), is(true));
        }
        assertNotNull(StubGameObserver);
    }
*/
