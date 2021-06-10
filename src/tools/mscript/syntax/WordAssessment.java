package tools.mscript.syntax;

import tools.mscript.ScriptingException;
import java.util.Objects;

/**
 * <b>Non-functional class - Don't use</b>
 * @deprecated - not used, but might be built upon in the future
 */
@Deprecated
public class WordAssessment {
    @Deprecated
    public static Keyword getClass(String word) {
        if (Objects.isNull(word)) new ScriptingException("Null pointer exception", 4).printError();
        if (word.startsWith(Character.toString(TextSpecifications.COMMAND.getSymbol()))) {
            return hashRootCommand(word);
        }
        return null;
    }

    @Deprecated
    private static Keyword hashRootCommand(String word) {
        if (!Keyword.commandExists(word)) new ScriptingException(String.format("Root Command '%s' does not exist", word),
                "Check spelling and read the documentation", 4).printError();
        return Keyword.getKeywordFromName(word);
    }
}
