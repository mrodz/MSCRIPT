package tools.mscript.syntax;

import tools.mscript.Script;

/**
 * <b>List of available Root Commands and their unique IDs to be called</b>
 * @implNote If you add anything here, you need to add function to it in {@link Script#read()}
 */
public enum RootCommand implements Keyword {
    START_COMMAND(TextSpecifications.COMMAND.getSymbol() + "start"),
    STOP_COMMAND(TextSpecifications.COMMAND.getSymbol() + "stop");

    /**
     * This field defines the ID of a {@link RootCommand}, which <u>must</u> begin
     * with the symbol associated with the value {@link TextSpecifications#COMMAND}
     */
    String name;

    RootCommand(String name) {
        this.name = name;
    }

    /**
     * <b>Gets the full calling of a {@link RootCommand} (ie. `@start`)</b>
     * @return The {@link #name} field
     */
    public String getName() {
        return name;
    }
}