package hotciv.standard;

import hotciv.framework.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestDeltaCiv {

    private Game game;
    /** Fixture for DeltaCiv testing. */
    @Before
    public void setUp() {
        game = new VarCivImpl( new DeltaCivImpl());
    }

    @Test
    public void TestTile_0_1() {
        assertThat(game, is(notNullValue()));
        //Check tile at (0,1)
        Position t1 = new Position(0,1);
        assertThat(game.getTileAt(t1).getTypeString(), is(GameConstants.HILLS));
    }

    @Test
    public void TestTile_1_0() {
        assertThat(game, is(notNullValue()));
        //Check tile at (1,0)
        Position t1 = new Position(1,0);
        assertThat(game.getTileAt(t1).getTypeString(), is(GameConstants.OCEANS));
    }

    @Test
    public void TestTile_2_2() {
        assertThat(game, is(notNullValue()));
        //Check tile at (0,1)
        Position t1 = new Position(2,2);
        assertThat(game.getTileAt(t1).getTypeString(), is(GameConstants.MOUNTAINS));
    }
}
