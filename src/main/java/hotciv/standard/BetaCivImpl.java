package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.Position;

public class BetaCivImpl extends GameImpl{
    //Variant Win Condition for BetaCiv
    @Override
    public Player getWinner(){
        Position p=new Position(0,0);
        int blueCount=0;
        int redCount=0;

        //Count number of cities owned by RED and BLUE
        for(int i=0;i<16;i++){
            p.setColumn(i);
            for(int j=0;j<16;j++) {
                p.setRow(j);
                if (getCityAt(p).getOwner() == Player.RED) {
                    redCount++;
                } else if (getCityAt(p).getOwner() == Player.BLUE) {
                    blueCount++;
                }
            }
        }
        if(redCount>0 && blueCount==0){
            return Player.RED;
        }else if(blueCount>0 && redCount==0){
            return Player.BLUE;
        }else{
            return null;
        }
    }

    //Variant World Aging for BetaCiv
    @Override
    public int getAge(){
        if(this.turn==0){
            endOfTurn();
        }else {
            if (this.age >= -4000 && this.age < -100) {
                this.age += 100;
            }else if(this.age==-100){
                this.age=-1;
            }else if(this.age==-1){
                this.age=1;
            }else if(this.age==1){
                this.age=50;
            }else if(this.age>=50 && this.age<1750){
                this.age+=50;
            }else if(this.age>=1750 && this.age<1900){
                this.age+=25;
            }else if(this.age>=1900 && this.age<1970){
                this.age+=5;
            }else if(this.age>=1970){
                this.age++;
            }else{
                this.age=-4000;
            }
        }
        return this.age;
    }
}
