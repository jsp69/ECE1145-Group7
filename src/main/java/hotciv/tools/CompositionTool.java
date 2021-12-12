package hotciv.tools;

import hotciv.framework.*;
import hotciv.view.GfxConstants;
import hotciv.visual.*;
import minidraw.framework.*;
import minidraw.standard.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class CompositionTool extends AbstractTool {

    private Game game;
    private DrawingEditor editor;
    private NullTool tool;

    public CompositionTool(DrawingEditor edit, Game g) {
        super(edit);
        game = g;
        editor = edit;
    }

    /* Definitions of the different tools:
       UnitMoveTool = MOUSE_PRESSED + MOUSE_DRAGGED
       UnitActionTool = MOUSE_CLICKED + isShiftDown()
       SetFocusTool = MOUSE_CLICKED
       EndOfTurnTool = MOUSE_CLICKED + in the turn shield rectangle
     */

    public void mouseDown(MouseEvent e, int x, int y) {
        // f is the figure below the mouse click
        Figure f = editor.drawing().findFigure(x,y);
        if (f != null) {
            // Get origin points of the figure
            int fx = f.displayBox().getLocation().x;
            int fy = f.displayBox().getLocation().y;

            // Determine correct tool
            if (e.isShiftDown()) {
                tool = new UnitActionTool(editor, game);
            } else if ((fx == GfxConstants.TURN_SHIELD_X) && (fy == GfxConstants.TURN_SHIELD_Y)) {
                tool = new EndOfTurnTool(editor, game);
            } else if (true) {
                tool = new UnitMoveTool(editor, game);
            } else {
                tool = new SetFocusTool(editor, game);
            }
        }

        // Complete requested action
        tool.mouseDown(e,x,y);
    }

    public void mouseDrag(MouseEvent e, int x, int y) {
        // UnitMoveTool mouseDrag() event is called
        tool.mouseDrag(e,x,y);
    }

    public void mouseUp(MouseEvent e, int x, int y) {
        // UnitMoveTool mouseUp() event is called
        tool.mouseUp(e,x,y);
    }
}
