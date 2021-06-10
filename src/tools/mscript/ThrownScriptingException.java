package tools.mscript;

/**
 * <b>Builds an exception and throws it on its creation</b>
 * @see tools.mscript.ScriptingException
 */
@SuppressWarnings("unused")
public class ThrownScriptingException extends ScriptingException {
    @Deprecated
    public ThrownScriptingException(String message) {
        super(message);
        printError();
    }

    public ThrownScriptingException(String message, int severity) {
        super(message, severity);
        printError();
    }

    public ThrownScriptingException(String message, String possibleFix, int severity) {
        super(message, possibleFix, severity);
        printError();
    }

    @Deprecated
    public ThrownScriptingException() {
        printError();
    }
}
