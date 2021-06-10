package tools.mscript.syntax;

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
