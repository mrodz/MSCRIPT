package tools.mscript.syntax;

import tools.mscript.ScriptingException;

/**
 * <b>This interface is used to perform operations on the different keywords in MSCRIPT</b>
 */
public interface Keyword {
    /**<b>Checks for the existence of a keyword.</b>
     * @param name Takes the full name of the keyword, including any symbols. (`@start` is a full keyword.)
     * @return {@code boolean} of whether or not this value exists.
     * @implNote can be useful for throwing exceptions.
     */
    static boolean commandExists(String name) {
        for (RootCommand a : RootCommand.values()) {
            if (a.getName().equals(name)) return true;
        }
        return false;
    }
    static boolean annotationExists(String name) {
        for (Annotation a : Annotation.values()) {
            if (a.getID().equals(name)) return true;
        }
        return false;
    }

    /**
     * <b>Pulls a {@link Keyword} from its name.</b>
     * @param name takes the full name of the root command, ie. @start, and checks whether or not is exists.
     * @return The keyword value if found, if not {@code null}
     */
    static RootCommand getKeywordFromName(String name) {
        for (RootCommand a : RootCommand.values()) {
            if (a.getName().equals(name)) return a;
        }
        new ScriptingException(String.format("Non-existent Root Command '%s'", name),
                "Check the documentation for available Root Commands.",
                4
        ).printError();
        return null;
    }

    /**
     * <b>Pulls an {@link Annotation} from its name.</b>
     * @param name takes the full tools.TestFile.syntax, ie. [something] <u>excluding</u> the Annotation declaration.
     * @return The correct annotation if it exists; if not, saves it as a line.
     */
    static Annotation getAnnotationFromID(String name) {
        for (Annotation a : Annotation.values()) {
            if (a.getID().equals(name)) {
                return a;
            }
        }
        return Annotation.STORE_AS_LINE;
    }
}