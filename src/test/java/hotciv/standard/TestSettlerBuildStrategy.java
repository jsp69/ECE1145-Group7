package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestSettlerBuildStrategy {

    private Game game;
    /** Fixture for GammaCiv Strategy testing. */
    @Before
    public void setUp() {
        game = new GameImpl(new GammaFactory());
    }

    @Test
    public void redSettlerBuildsCity() {
        Position redSettler = new Position(4, 3);
        assertThat(game, is(notNullValue()));
        if (game.getUnitAt(redSettler).getTypeString().equals(GameConstants.SETTLER)) {
            game.performUnitActionAt(redSettler);
            //Check if red settler is removed
            assertNull(GameImpl.unitLoc[4][3]);
            //Check if red owns built city at p
            assertThat(game.getCityAt(redSettler).getOwner(), is(Player.RED));
            //Check if city population is size one
            assertThat(game.getCityAt(redSettler).getSize(), is(1));
        }
    }

    @Test
    public void blueSettlerBuildsCity() {
        Position blueSettler = new Position(3, 4);
        assertThat(game, is(notNullValue()));
        if (game.getUnitAt(blueSettler).getTypeString().equals(GameConstants.SETTLER)) {
            game.performUnitActionAt(blueSettler);
            //Check if blue settler is removed
            assertNull(GameImpl.unitLoc[3][4]);
            //Check if blue owns built city at p
            assertThat(game.getCityAt(blueSettler).getOwner(), is(Player.BLUE));
            //Check if city population is size one
            assertThat(game.getCityAt(blueSettler).getSize(), is(1));
        }
    }
}
