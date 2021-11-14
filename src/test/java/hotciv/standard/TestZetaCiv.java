package hotciv.standard;

import hotciv.framework.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


public class TestZetaCiv {

        private Game game;
        WinStrat ws;
        int roundsPassed;

        @Before
        public void setUp(){
            game= new GameImpl(new ZetaFactory(roundsPassed, ws));
        }

        @Test public void roundLessThan20BetaWin() {
            int roundCount = 1;
            WinnerStrategyContext context = new WinnerStrategyContext();
            Position rC = new Position(1,1);
            Position bC = new Position(4,1);
            WinStrat ws = new ZetaWin(new BetaWin(), new EpsilonWin(), roundCount);
            assertThat(game.getWinner(), is(ws.getWinner(game)));
        }

       @Test public void roundGreaterThan20EpsilonWin() {
            int roundCount = 21;
           WinnerStrategyContext context = new WinnerStrategyContext();
           Position rC = new Position(1,1);
           Position bC = new Position(4,1);
           WinStrat ws = new ZetaWin(new BetaWin(), new EpsilonWin(), roundCount);
           assertThat(game.getWinner(), is(ws.getWinner(context,game)));
        }
    }