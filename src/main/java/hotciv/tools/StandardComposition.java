package hotciv.tools;

import hotciv.framework.*;
import minidraw.framework.Tool;

import java.util.ArrayList;
import java.util.List;

public class StandardComposition implements CompositionToolHandler {
    protected List<Tool> tools;

    public StandardComposition() { tools = new ArrayList<>(); }

    @Override
    public List<Tool> tools() { return tools; }


}
