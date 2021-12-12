package hotciv.tools;

import hotciv.framework.*;
import hotciv.view.GfxConstants;
import hotciv.visual.*;
import minidraw.framework.*;
import minidraw.standard.*;

import java.util.ArrayList;
import java.util.List;

import java.awt.event.MouseEvent;

public class CompositionTool extends AbstractTool {

    private Game game;
    private DrawingEditor editor;
    private List<NullTool> tools;

    public CompositionTool(DrawingEditor edit, Game g) {
        super(edit);
        game = g;
        editor = edit;

        tools = new ArrayList<>();
        tools.add(new SetFocusTool(editor, game));
        tools.add(new EndOfTurnTool(editor, game));
        tools.add(new UnitActionTool(editor, game));
        tools.add(new UnitMoveTool(editor, game));
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
                tools.get(2).mouseDown(e,x,y);
            } else if ((fx == GfxConstants.TURN_SHIELD_X) && (fy == GfxConstants.TURN_SHIELD_Y)) {
                tools.get(1).mouseDown(e,x,y);
            } else {
                tools.get(0).mouseDown(e,x,y);
                tools.get(3).mouseDown(e,x,y);
            }
        }
    }

    public void mouseDrag(MouseEvent e, int x, int y) {
        // UnitMoveTool mouseDrag() event is called
        tools.get(3).mouseDrag(e,x,y);
    }

    public void mouseUp(MouseEvent e, int x, int y) {
        // UnitMoveTool mouseUp() event is called
        tools.get(3).mouseUp(e,x,y);
    }
}
