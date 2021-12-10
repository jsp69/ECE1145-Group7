package hotciv.visual;

import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import hotciv.framework.*;
import hotciv.view.*;
import hotciv.stub.*;

/** Template code for exercise FRS 36.43.

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
public class ShowAction {
  
  public static void main(String[] args) {
    Game game = new StubGame2();

    DrawingEditor editor = 
      new MiniDrawApplication( "Shift-Click unit to invoke its action",  
                               new HotCivFactory4(game) );
    editor.open();
    editor.showStatus("Shift-Click on unit to see Game's performAction method being called.");

    // TODO: Replace the setting of the tool with your ActionTool implementation.
    editor.setTool( new ActionTool(editor, game) );
  }
}

class ActionTool extends NullTool {
    private DrawingEditor editor;
    private Game game;
    private Position p;

  public ActionTool(DrawingEditor e, Game g) {
    this.editor = e;
    this.game = g;
  }

  @Override
  public void mouseDown(MouseEvent e, int x, int y) {
      if(e.isShiftDown()) {
          game.performUnitActionAt(p);
      }
  }
  @Override
  public void mouseUp(MouseEvent e, int x, int y) {
      if(e.isShiftDown()) {
          game.performUnitActionAt(p);
      }
  }
}
