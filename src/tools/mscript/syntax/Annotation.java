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

    private final String ID;
    private final String represents;
    Annotation(String ID, String represents) {
        this.ID = ID;
        this.represents = represents;
    }

    public String getID() {
        return ID;
    }

    public String getActualValue() {
        return represents;
    }
}
