package hotciv.visual;

import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import hotciv.framework.*;
import hotciv.view.*;
import hotciv.stub.*;

/** Template code for exercise FRS 36.44.

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
public class ShowComposition {
  
  public static void main(String[] args) {
    Game game = new StubGame2();

    DrawingEditor editor = 
      new MiniDrawApplication( "Click and/or drag any item to see all game actions",  
                               new HotCivFactory4(game) );
    editor.open();
    editor.showStatus("Click and drag any item to see Game's proper response.");

    // TODO: Replace the setting of the tool with your CompositionTool implementation.
    editor.setTool( new CompositionTool(editor, game) );
  }

}

class CompositionTool extends NullTool {
  private final DrawingEditor editor;
  private final Game game;
  private CivFigure figure;
  private Tool state;

  public CompositionTool(DrawingEditor editor, Game game) {
    state = null;
    this.editor = editor;
    this.game = game;
  }

  @Override
  public void mouseDown(MouseEvent e, int x, int y) {
    // Find the figure (if any) just below the mouse click position
    figure = (CivFigure) editor.drawing().findFigure(x, y);
    // Determine correct tool to use
    if (figure == null) {
      System.out.println("No figure below click point.");
      System.out.println(" *** IMPLEMENTATION PENDING ***");
      state = new NullTool();
    } else {
      if (figure.getType().equals(GfxConstants.TEXT_TYPE_STRING)) {
        state = new EndOfTurnTool(editor, game);
      } else {
        // TODO: handle all the cases - action tool, unit move tool, etc
        switch (figure.getType()) {
          case GfxConstants.UNIT_TYPE_STRING:
            state = new MoveTool(editor, game);
            break;
          case GfxConstants.CITY_TYPE_STRING:
            state = new ChangeCityTool((CityFigure) figure);
            break;
          case GfxConstants.TURN_SHIELD_TYPE_STRING:
            state = new EndOfTurnTool(editor, game);
            break;
          case GfxConstants.UNIT_SHIELD_TYPE_STRING:
            state = new ActionTool(editor, game);
            break;
          default:
            state = new NullTool();
            break;
        }
      }
    }
    // Finally, delegate to the selected state
    state.mouseDown(e, x, y);
  }

}
