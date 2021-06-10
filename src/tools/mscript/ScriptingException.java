package tools.mscript;

import tools.mscript.syntax.Symbols;

/**
 * <b>Class to build and throw exceptions specific to the {@link Script} class</b> <br/>
 * See the {@link Script} documentation for more information on these errors.
 * @implNote {@link #ScriptingException(String, String, int)} is the best exception to throw, seeing as it gives the
 * most insight as to why the code failed.
 */
@SuppressWarnings("unused")
public class ScriptingException {
    private final String message;
    private String possibleFix;
    private int severity = 0;

    /**
     * <b>Builds a {@link ScriptingException}</b>
     * @deprecated - {@link #ScriptingException(String, String, int)} gives better insight to the error.
     * @param message the error cause.
     */
    @Deprecated
    public ScriptingException(String message) {
        this.message = message;
    }

    /**
     * <b>Builds a {@link ScriptingException}</b>
     * @param message the error cause
     * @param severity an {@code int} out of 5 to let the user know the severity of their problem.
     */
    public ScriptingException(String message, int severity) {
        this.message = message;
        this.severity = Math.min(severity, 5);
    }

    /**
     * <b>Builds a {@link ScriptingException}</b>
     * @param message the error cause.
     * @param severity an {@code int} out of 5 to let the user know the severity of their problem.
     * @param possibleFix a string enlisting possible fixes to the user's problem.
     */
    public ScriptingException(String message, String possibleFix, int severity) {
        this.message = message;
        this.possibleFix = possibleFix;
        this.severity = severity;
    }

    /**
     * <b>Builds a {@link ScriptingException}</b> <br/>
     * Default no-argument constructor.
     * @deprecated - {@link #ScriptingException(String, String, int)} gives better insight to the error.
     */
    @Deprecated
    public ScriptingException() {
        this("undefined error", "unknown", -1);
    }

    /**
     * <b>Prints the exception's fields to the {@link System#out}</b> <br/>
     * Similar functionality to {@link Exception#printStackTrace()}
     * @implNote This is the main function to call from {@link ScriptingException}, make sure
     * that if you build one that this is thrown!
     */
    public final void printError() {
        System.out.printf("%nException: %s%n", this.message);
        if (java.util.Objects.nonNull(possibleFix)) {
            System.out.printf("%s Possible Fix: %s%n", getSpaces(1), possibleFix);
            System.out.printf("%s Severity Level: %d/5%n", getSpaces(2), severity);
        } else {
            System.out.printf("%s Severity Level: %d/5%n", getSpaces(1), severity);
        }
        System.exit(-1);
    }

    /**
     * <b>Get the error message associated with this instance of {@link ScriptingException}</b>
     * @return a formatted {@link String} with the cause of the exception.
     */
    public final String getMessage() {
        return message;
    }

    /**
     * <b>Get a message containing the possible fixes associated with this instance of {@link ScriptingException}</b>
     * @return a formatted {@link String} with possible fixes for the exception.
     */
    public final String getPossibleFix() {
        return possibleFix;
    }

    /**
     * <b>Get the severity level of this instance of {@link ScriptingException}</b>
     * @return an {@code int} out of a possible value of 5.
     */
    public final int getSeverity() {
        return severity;
    }

    /**
     * <b>Builds the spaces/characters used to print exception messages.d</b>
     * @param spaces int value that represents how many spaces to be returned.
     * @return the requested spaces value + {@link Symbols#THICK_BOTTOM_BAR_LEFT}
     */
    private String getSpaces(int spaces) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < spaces; i++) s.append(' ');
        return s.append(Symbols.THICK_BOTTOM_BAR_LEFT.getSymbol()).toString();
    }
}
