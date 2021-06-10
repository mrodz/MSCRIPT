package tools.mscript.syntax;

import tools.mscript.Script;

/**
 * <b>List of annotations.</b> <br/>
 * Some have a return value, ie. the ones that call a Character reserved for {@link TextSpecifications}.
 * @implNote Useful for referencing. If you add any value to this list, you need to implement it in {@link Script#read()}
 */
public enum Annotation {
    TEXT_ROOT_SYMBOL("[@]", "@"),
    TEXT_COMMENT_SYMBOL("[#]", "#"),
    TEXT_ANNOTATION_SYMBOL("[*]", "*"),
    WHITESPACE("[_]", " "),
    STORE_AS_LINE("[^+]", null),
    GROUP_ENTRIES_OPEN("[{{]", null),
    GROUP_ENTRIES_CLOSE("[}}]", null);

    /**
     * The ID of an {@link Annotation} is its unique call sign that is searched for while compiling.<br/>
     * This <u>does not</u> include the symbol associated with {@link TextSpecifications#ANNOTATION}
     * */
    private final String ID;

    /** This field is used with any {@link Annotation} that represents a predefined Character/String */
    private final String represents;

    Annotation(String ID, String represents) {
        this.ID = ID;
        this.represents = represents;
    }

    /**
     * <b>Get the ID (Call sign) of an {@link Annotation}</b>
     * @return the ID field bound to the Annotation
     * @see #ID
     */
    public String getID() {
        return ID;
    }

    public String getActualValue() {
        return represents;
    }
}
