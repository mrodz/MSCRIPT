package tools.mscript.syntax;

import tools.mscript.Script;

/**
 * <b>List of available Root Commands and their ids to be called</b>
 * @implNote If you add anything here, you need to add function to it in {@link Script#read()}
 */
public
enum RootCommand implements Keyword {
    START_COMMAND(TextSpecifications.COMMAND.getSymbol() + "start"),
    STOP_COMMAND(TextSpecifications.COMMAND.getSymbol() + "stop");

    String name;
    RootCommand(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}