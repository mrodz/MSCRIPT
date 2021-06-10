package tools.mscript.syntax;

/**
 * <b>Sets the prefix to the {@link Keyword} and {@link Annotation} class, and sets the symbol for comments. </b>
 * @implNote Do not change unless you want to go through a bunch of code to update occurrences!
 */
public enum TextSpecifications {
    COMMAND('@'),
    ANNOTATION('*'),
    COMMENT('#');

    private final char symbol;
    TextSpecifications(char symbol) {
        this.symbol = symbol;
    }
    public char getSymbol() {
        return symbol;
    }
}
