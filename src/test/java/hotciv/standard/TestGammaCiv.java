package hotciv.standard;

import hotciv.framework.*;
import org.hamcrest.CoreMatchers;
import org.junit.*;

import java.util.Objects;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestGammaCiv {

    private Game game;

    /**
     * Fixture for GammaCiv testing.
     */
    @Before
    public void setUp() {
        game = new GameImpl(new GammaFactory());
    }

    @Test
    public void redSettlerBuildsCity() {
        Position redSettler = new Position(4, 3);
        assertThat(game, is(notNullValue()));
        if (Objects.equals(game.getUnitAt(redSettler).getTypeString(), GameConstants.SETTLER)) {
            game.performUnitActionAt(redSettler);
            //Check if red settler is removed
            assertNull(game.getUnitAt(redSettler));
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
        game.endOfTurn();
        if (Objects.equals(game.getUnitAt(blueSettler).getTypeString(), GameConstants.SETTLER)) {
            game.performUnitActionAt(blueSettler);
            //Check if blue settler is removed
            assertNull(game.getUnitAt(blueSettler));
            //Check if blue owns built city at p
            assertThat(game.getCityAt(blueSettler).getOwner(), is(Player.BLUE));
            //Check if city population is size one
            assertThat(game.getCityAt(blueSettler).getSize(), is(1));
        }
    }

    @Test
    public void redArcherFortifies() {
        Position redArcher = new Position(2, 0);
        assertThat(game, is(notNullValue()));
        if (Objects.equals(game.getUnitAt(redArcher).getTypeString(), GameConstants.ARCHER)) {
            int oldDefStrength = game.getUnitAt(redArcher).getDefensiveStrength();
            game.performUnitActionAt(redArcher);
            //Check if defensive strength doubles
            assertThat(oldDefStrength * 2, is(game.getUnitAt(redArcher).getDefensiveStrength()));
            //Check move count set to 0
            assertThat(game.getUnitAt(redArcher).getMoveCount(), is(0));
        }
    }

    @Test
    public void blueArcherFortifies() {
        Position blueArcher = new Position(0, 2);
        assertThat(game, is(notNullValue()));
        game.endOfTurn();
        if (Objects.equals(game.getUnitAt(blueArcher).getTypeString(), GameConstants.ARCHER)) {
            int oldDefStrength = game.getUnitAt(blueArcher).getDefensiveStrength();
            game.performUnitActionAt(blueArcher);
            //Check if defensive strength doubles
            assertThat(oldDefStrength * 2, is(game.getUnitAt(blueArcher).getDefensiveStrength()));
            //Check move count set to 0
            assertThat(game.getUnitAt(blueArcher).getMoveCount(), is(0));
        }
    }
}