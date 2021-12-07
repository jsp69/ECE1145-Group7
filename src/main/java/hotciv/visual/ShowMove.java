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

/** Template code for exercise FRS 36.39.

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
public class ShowMove {
  
  public static void main(String[] args) {
    Game game=new GameImpl(new AlphaFactory());

    DrawingEditor editor = 
      new MiniDrawApplication( "Move any unit using the mouse",  
                               new HotCivFactory4(game) );
    editor.open();
    editor.showStatus("Move units to see Game's moveUnit method being called.");

    // TODO: Replace the setting of the tool with your UnitMoveTool implementation.
    //editor.setTool( new SelectionTool(editor) );
    editor.setTool(new MoveTool(editor,game));
  }
}

//Game board starts at 20,16
//Game board ends at 500,492
class MoveTool extends NullTool{
  private Game game;
  private DrawingEditor editor;
  private Position from;

  public MoveTool(DrawingEditor e, Game g){
    this.editor=e;
    this.game=g;
  }

  @Override
  public void mouseDown(MouseEvent e, int x, int y){
    //Getting offset coordinates for click
    int newX=x-20;
    int newY=y-16;

    //Getting grid position from 0-15
    int posX= (int) Math.floor(newX/30);
    int posY= (int) Math.floor(newY/30);

    this.from=new Position(posY,posX);
    editor.showStatus(String.valueOf(from.getRow()));
  }

  @Override
  public void mouseUp(MouseEvent e, int x, int y) {
    //Getting offset coordinates for click
    int newX=x-20;
    int newY=y-16;

    //Getting grid position from 0-15
    int posX= (int) Math.floor(newX/30);
    int posY= (int) Math.floor(newY/30);

    if(!(from.getRow()==posY && from.getColumn()==posX)) {
      game.moveUnit(this.from, new Position(posY, posX));
    }
  }
}