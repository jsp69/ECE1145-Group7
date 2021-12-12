package hotciv.framework;

import minidraw.framework.Tool;

import java.util.List;

public interface CompositionToolHandler {
    /* Get an iterator over all different tools available
       Returns: a list of all tools
    */
    public List<Tool> tools();
}