package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestGammaCiv {

    private Game game;
    /** Fixture for GammaCiv testing. */
    @Before
    public void setUp() {
        game = new GameImpl(new GammaCivImpl());
    }

    @Test
    public void settlerRedActionBuildCity() {
        assertThat(game, is(notNullValue()));
        assertThat(game.getPlayerInTurn(), is(Player.RED));
    }
}
