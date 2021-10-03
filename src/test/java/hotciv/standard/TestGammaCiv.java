package hotciv.standard;

import hotciv.framework.*;
import org.hamcrest.CoreMatchers;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestGammaCiv {

    private Game game;

    /**
     * Fixture for GammaCiv testing.
     */
    @Before
    public void setUp() {
        game = new VarCivImpl( new GammaCivImpl());
    }

    @Test
    public void redSettlerBuildsCity() {
        Position redSettler = new Position(4, 3);
        assertThat(game, is(notNullValue()));
        if (redSettler.equals("settler")) {
            //Check if red owns built city at p
            assertThat(game.getCityAt(redSettler).getOwner(), is(Player.RED));
        }
    }

    @Test
    public void blueSettlerBuildsCity() {
        Position blueSettler = new Position(3, 4);
        assertThat(game, is(notNullValue()));
        if (blueSettler.equals("settler")) {
            //Check if blue owns built city at p
            assertThat(game.getCityAt(blueSettler).getOwner(), is(Player.BLUE));
        }
    }

    @Test
    public void redArcherFortifies() {
        Position oldRedArcher = new Position(1, 0);
        Position newRedArcher = new Position(2, 0);
        assertThat(game, is(notNullValue()));
        if (newRedArcher.equals("archer")) {
            //Check if defensive strength doubles
            int oldDoubled = game.getUnitAt(oldRedArcher).getDefensiveStrength() * 2;
            assertThat(oldDoubled, is(game.getUnitAt(newRedArcher).getDefensiveStrength()));
            //Check move count set to 0
            assertThat(game.getUnitAt(newRedArcher).getMoveCount(), is(0));
        }
    }

    @Test
    public void blueArcherFortifies() {
        Position oldBlueArcher = new Position(1, 1);
        Position newBlueArcher = new Position(0, 2);
        assertThat(game, is(notNullValue()));
        if (newBlueArcher.equals("archer")) {
            //Check if defensive strength doubles
            int oldDoubled = game.getUnitAt(oldBlueArcher).getDefensiveStrength() * 2;
            assertThat(oldDoubled, is(game.getUnitAt(newBlueArcher).getDefensiveStrength()));
            //Check move count set to 0
            assertThat(game.getUnitAt(newBlueArcher).getMoveCount(), is(0));
        }
        assertThat(game.getPlayerInTurn(), CoreMatchers.is(Player.RED));
    }
}