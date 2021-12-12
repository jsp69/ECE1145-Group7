package hotciv.tools;

import hotciv.framework.*;
import hotciv.visual.*;
import minidraw.framework.*;
import minidraw.standard.*;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class CompositionTool
        extends AbstractTool
        implements Tool {

    private Game game;
    private List<Tool> tools = new ArrayList<>();
    private Tool fChild, cachedNullTool;
    protected Figure draggedFigure;

    public CompositionTool(DrawingEditor edit, Game g) {
        super(edit);
        game = g;
        cachedNullTool = new NullTool();
        fChild = cachedNullTool;

        /*tools.add(new EndOfTurnTool(edit, game));
        tools.add(new MoveTool(edit, game));
        tools.add(new FocusTool(edit, game));
        tools.add(new ActionTool(edit, game));
        tools.add(new EndOfTurnTool(edit, game));*/
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y)
    {
        Drawing model = this.editor().drawing();
        model.lock();

        for(Tool tool : tools){
            tool.mouseDown(e, x, y);
        }
        fChild.mouseDown(e, x, y);
    }

    @Override
    public void mouseDrag(MouseEvent e, int x, int y) {
        for(Tool tool : tools){
            tool.mouseDrag(e, x, y);
        }
        fChild.mouseDrag(e, x, y);
    }

    @Override
    public void mouseMove(MouseEvent e, int x, int y) {
        for(Tool tool : tools){
            tool.mouseMove(e, x, y);
        }
        fChild.mouseMove(e, x, y);
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        for(Tool tool : tools){
            tool.mouseUp(e, x, y);
        }
        fChild.mouseUp(e, x, y);
    }

    @Override
    public void keyDown(KeyEvent k, int i) {};
}
