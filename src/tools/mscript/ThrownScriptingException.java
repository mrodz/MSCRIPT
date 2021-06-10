package tools.mscript;

/**
 * <b>Builds an exception and throws it on initialization</b><br/>
 * For information on how to use, review the {@link Script Documentation}
 * @see tools.mscript.ScriptingException information on how the class works
 */
@SuppressWarnings("unused")
public class ThrownScriptingException extends ScriptingException {
    /**
     * @see ScriptingException#ScriptingException(String)
     */
    @Deprecated
    public ThrownScriptingException(String message) {
        super(message);
        printError();
    }

    /**
     * @see ScriptingException#ScriptingException(String, int)
     */
    public ThrownScriptingException(String message, int severity) {
        super(message, severity);
        printError();
    }

    /**
     * @see ScriptingException#ScriptingException(String, String, int)
     */
    public ThrownScriptingException(String message, String possibleFix, int severity) {
        super(message, possibleFix, severity);
        printError();
    }
}
