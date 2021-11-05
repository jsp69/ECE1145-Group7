package hotciv.standard;

import hotciv.framework.*;
import org.hamcrest.CoreMatchers;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestEtaCiv {
    Game game;
    @Before
    public void setUp() {
        game = new GameImpl(new EtaFactory());
    }

    @Test
    public void ChangeCityProduction(){
        assertNotNull(game);
        Position p=new Position(1,1);
        assertNotNull(game.getCityAt(p));

        game.changeProductionInCityAt(p,GameConstants.ARCHER);
        assertThat(game.getCityAt(p).getProduction(),is(GameConstants.ARCHER));

        game.changeProductionInCityAt(p,GameConstants.LEGION);
        assertThat(game.getCityAt(p).getProduction(),is(GameConstants.LEGION));

        game.changeProductionInCityAt(p,GameConstants.SETTLER);
        assertThat(game.getCityAt(p).getProduction(),is(GameConstants.SETTLER));
    }

    @Test
    public void ChangeCityFocus(){
        assertNotNull(game);
        Position p=new Position(1,1);
        assertNotNull(game.getCityAt(p));

        assertThat(game.getCityAt(p).getWorkforceFocus(),is(GameConstants.foodFocus));

        game.changeWorkForceFocusInCityAt(p,GameConstants.productionFocus);
        assertThat(game.getCityAt(p).getWorkforceFocus(),is(GameConstants.productionFocus));
    }

    //City at 1,1 has 1 Oceans, 1 Hills, 1 Mountains, and 5 Plains around it
    //City at 1,1 has 16 Food and 3 Production surrounding it
    @Test
    public void CityGrow(){
        assertNotNull(game);
        Position p=new Position(1,1);
        assertNotNull(game.getCityAt(p));

        assertThat(game.getCityAt(p).getSize(),is(1));

        //Grow City while ensuring it doesn't increase in size before it has enough food
        for(int i=0;i<16;i++){
            game.endOfTurn();
            assertThat(game.getCityAt(p).getSize(),is(1));
        }

        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getCityAt(p).getSize(),is(2));

        for(int i=0;i<3;i++){
            game.endOfTurn();
            game.endOfTurn();
        }
        assertThat(game.getCityAt(p).getSize(),is(3));

        for(int i=0;i<2;i++){
            game.endOfTurn();
            game.endOfTurn();
        }
        assertThat(game.getCityAt(p).getSize(),is(4));
    }

    //City at 1,1 has 1 Oceans, 1 Hills, 1 Mountains, and 5 Plains around it
    //City at 1,1 has 16 Food and 3 Production surrounding it
    @Test
    public void CityProductionFocus(){
        assertNotNull(game);
        Position p=new Position(1,1);
        assertNotNull(game.getCityAt(p));

        assertThat(game.getCityAt(p).getTreasury(),is(0));
        assertThat(game.getCityAt(p).getWorkforceFocus(),is(GameConstants.foodFocus));
        game.changeWorkForceFocusInCityAt(p,GameConstants.productionFocus);
        assertThat(game.getCityAt(p).getWorkforceFocus(),is(GameConstants.productionFocus));

        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getCityAt(p).getTreasury(),is(1));

        for(int i=0;i<14;i++){
            game.endOfTurn();
            assertThat(game.getCityAt(p).getSize(),is(1));
        }
        assertThat(game.getCityAt(p).getTreasury(),is(8));

        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getCityAt(p).getSize(),is(2));
        assertThat(game.getCityAt(p).getTreasury(),is(11));

        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getCityAt(p).getTreasury(),is(14));
    }
}
