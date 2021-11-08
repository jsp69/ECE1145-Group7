package hotciv.standard;

import hotciv.framework.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestThetaCiv {

    private Game game;
    Position redUFO = new Position(2, 1);

    /** Fixture for ThetaCiv testing. */
    @Before
    public void setUp() { game = new GameImpl(new ThetaFactory()); }

    /*@Test public void ufoProductionCost60() {

    }*/

    @Test public void moveOverMountains() {
        assertThat(game, is(notNullValue()));
        Position m = new Position(2, 2);
        assertTrue(game.moveUnit(redUFO, m));
    }

    @Test public void defenseStrength8() {
        assertThat(game, is(notNullValue()));
        assertEquals(game.getUnitAt(redUFO).getDefensiveStrength(), 8);
    }

    @Test public void attackStrength1() {
        assertThat(game, is(notNullValue()));
        assertEquals(game.getUnitAt(redUFO).getAttackingStrength(), 1);
    }

    @Test public void ufoActionDecreaseCityPopBy1() {
        assertThat(game, is(notNullValue()));
        Position redCity = new Position(1,1);
        int pop1 = game.getCityAt(redCity).getSize();
        game.performUnitActionAt(redUFO);
        int pop2 = game.getCityAt(redCity).getSize();
        assertEquals(pop2, pop1 - 1);
    }

    /*@Test public void produceUFO() {
        assertThat(game, is(notNullValue()));
        Position r = new Position(1, 1);

    }*/

    /*@Test public void flyOverCityWithNoUnits() {
        assertThat(game, is(notNullValue()));

    }*/
}
