package hotciv.visual;

import hotciv.standard.AlphaFactory;
import hotciv.standard.GameImpl;
import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import hotciv.framework.*;
import hotciv.view.*;
import hotciv.stub.*;

/** Template code for exercise FRS 36.40.

   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Computer Science Department
     Aarhus University
   
   This source code is provided WITHOUT ANY WARRANTY either 
   expressed or implied. You may study, use, modify, and 
   distribute it for non-commercial purposes. For any 
   commercial use, see http://www.baerbak.com/
 */
public class ShowSetFocus {
  
  public static void main(String[] args) {
    //Game game = new StubGame2();
    Game game = new GameImpl(new AlphaFactory());

    DrawingEditor editor = 
      new MiniDrawApplication( "Click any tile to set focus",  
                               new HotCivFactory4(game) );
    editor.open();
    editor.showStatus("Click a tile to see Game's setFocus method being called.");

    editor.setTool( new FocusTool(game,editor));
  }
}

class FocusTool extends NullTool{
  Game game;
  DrawingEditor editor;

  public FocusTool(Game game, DrawingEditor editor){
    this.game=game;
    this.editor=editor;
  }

  @Override
  public void mouseDown(MouseEvent e, int x, int y) {
    //Getting offset coordinates for click
    int newX=x-20;
    int newY=y-16;

    //Getting grid position from 0-15
    int posX= (int) Math.floor(newX/30);
    int posY= (int) Math.floor(newY/30);

    game.setTileFocus(new Position(posY,posX));
    editor.showStatus(String.valueOf(posY)+" - "+String.valueOf(posX));
  }
}