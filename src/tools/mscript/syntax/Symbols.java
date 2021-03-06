package tools.mscript.syntax;

/**
 * <b>Miscellaneous symbols used throughout the program.</b><br/>
 * This {@code enum} provides quick access to special characters that
 * make scripting elements unique.
 * <p>
 *     Implementations: {@link tools.mscript.Script Script}, {@link tools.mscript.ScriptingException ScriptingException}
 * </p>
 */
@SuppressWarnings("unused")
public enum Symbols {
    THICK_TOP_BAR_LEFT('╔'),
    THICK_BOTTOM_BAR_LEFT('╚'),
    THICK_TOP_BAR_RIGHT('╗'),
    THICK_BOTTOM_BAR_RIGHT('╝'),
    THICK_MIDDLE_BAR_VERTICAL('║'),
    THICK_MIDDLE_BAR_HORIZONTAL('═'),
    THICK_MIDDLE_BAR_WITH_PIPE('╠'),
    THICK_CROSSED_BARS('╬'),

    TOP_BAR_LEFT('┌'),
    BOTTOM_BAR_LEFT('└'),
    TOP_BAR_RIGHT('┐'),
    BOTTOM_BAR_RIGHT('┘'),
    MIDDLE_BAR_VERTICAL('│'),
    MIDDLE_BAR_HORIZONTAL('─'),
    MIDDLE_BAR_WITH_PIPE('├'),
    CROSSED_BARS('┼');

    /** The UNICODE character associated with each class entry */
    char symbol;

    Symbols(char symbol) {
        this.symbol = symbol;
    }

    /**
     * <b>Gets the symbol associated with the entry</b>
     * @return the symbol bound to the entry object.
     */
    public char getSymbol() {
        return symbol;
    }
}
