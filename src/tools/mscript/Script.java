package tools.mscript;

import tools.mscript.syntax.Keyword;
import tools.mscript.syntax.Comment;
import tools.mscript.syntax.Annotation;
import tools.mscript.syntax.RootCommand;
import tools.mscript.syntax.TextSpecifications;
import tools.mscript.syntax.Symbols;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * <h2>Main</h2>
 * This tool can be used in a variety of ways, but it is most helpful in running tests on programs.
 * This build implements {@link java.util.Scanner}, {@link java.io.FileReader}, and {@link java.io.BufferedReader}
 * to access raw-text data on a <b>.txt</b> file.
 * <p>
 *     The method {@link #read()} is the main calling method for this class, and will read/parse the
 *     entirety of the text file.
 * </p>
 * <br/>
 * <h2>Documentation:</h2>
 * <ul>
 *     <li>
 *         '<span style="color:#a2b0a6">#</span>' comments a line
 *         <!-- '#' Is the comment symbol -->
 *     </li>
 *     <li>
 *         '<span style="color:#a2b0a6">##!</span>' opens a bulk comment
 *         <!-- '##!' Is the bulk comment opening symbol -->
 *     </li>
 *     <li>
 *          '<span style="color:#a2b0a6">!##</span>' closes a bulk comment
 *          <!-- '!##' Is the bulk comment closure symbol -->
 *      </li>
 *     <li>
 *         '<span style="color:#f5f242">@</span>' declares a {@link RootCommand}
 *         <!-- '@' Is the root command symbol -->
 *     </li>
 *     <li>
 *         '<span style="color:#1a7320">*</span>' declares an {@link Annotation}
 *         <!-- '*' Is the annotation symbol -->
 *     </li>
 * </ul>
 *
 * <p>
 *     To declare a file as an <b>MSCRIPT</b>, include: <span style="color:#2c9ba3">```mscript</span> as the first
 *     line entry.
 *     <!-- ```mscript declares an MSCRIPT file. -->
 * </p>
 *
 * <p>
 *     For the file to be parsed, it is required for it to contain the `<span style="color:#f5f242">@start</span>`
 *     parameter on its own line. From there, this tool will read and return each entry while breaking at whitespace until it either
 *     reaches the end of the file or the `<span style="color:#f5f242">@stop</span>` parameter.
 *     <!-- @start is required; @stop returns previously collected entries -->
 * </p>
 *
 * <p>
 *     Annotations are used to perform specific actions on data entries. They must be present at the beginning of an
 *     entry in order to be parsed.
 *     <ul>
 *         List of annotations:
 *         <li>*[@] is used to start an entry with '@'</li>
 *         <li>*[#] is used to start an entry with '#'</li>
 *         <li>*[*] is used to start an entry with '*'</li>
 *         <li>*[_] is used to signal a blank entry (whitespace)</li>
 *         <li>*[^+] is used to store a <u>Doubled String</u> (The entire line as a one entry)</li>
 *         <li>*[{{] is used to open a group data entry</li>
 *         <li>*[}}] is used to close a group data entry</li>
 *     </ul>
 * </p>
 * <p>
 *     Dangling text is not allowed. Any non-commented text above or below the reading parameters
 *     will throw an exception.
 * </p>
 * <br/>
 * <h2>Exceptions:</h2>
 * <p>
 *     Follow the format...
 *     <tt>
 *         <pre>
 *             Exception: {reason}
 *              ╚ Possible Fix: {supplied information}
 *               ╚ Severity Level: #/5
 *         </pre>
 *     </tt>
 *     <ul>
 *         Common exceptions are:
 *         <li>No `@start` Parameter Found Exception</li>
 *         <li>Dangling Code Exception</li>
 *         <li>Non-Existent Root Command Exception</li>
 *         <li>Non-Existent Annotation Exception</li>
 *         <li>Dangling Data Group Exception</li>
 *     </ul>
 *
 *     Although this should never be thrown, you might come across a...
 *     <tt>
 *         <pre>
 *             Exception: Native Error ({reason})
 *              ╚ Severity Level: 5/5
 *         </pre>
 *     </tt>
 *     <p>
 *         If this is ever thrown, it means something went wrong with the native language! Please report
 *         any occurrences of this happening.
 *     </p>
 *     <ol>
 *         The `Severity Level` entry refers to a value out of five:
 *         <li>Minor error, and can easily be fixed.</li>
 *         <li>Slight syntax error, can be fixed</li>
 *         <li>More complex syntax errror</li>
 *         <li>The script encounters a user error while parsing the text</li>
 *         <li>The script cannot compile or run the file</li>
 *     </ol>
 * </p>
 * <br/> <br/>
 * <h2>Sample document (The colors are cosmetic, and are only there to clarify what each element is doing):</h2>
 * <pre>
 *     <span style="color:#2c9ba3">```mscript</span>
 *
 *     <span style="color:#a2b0a6"># This is a comment!</span>
 *     <span style="color:#a2b0a6"># This is another comment :)</span>
 *
 *     <span style="color:#f5f242">@start</span>
 *     <span style="color:#d99338">entry1
 *     entry2
 *     entry3 entry4 entry5
 *     </span>
 *
 *     <span style="color:#a2b0a6"># entry6</span>
 *
 *     <span style="color:#1a7320">*[^+]</span><span style="color:#ebe9e6"> Wow! This is great! This is all saved as entry6! </span>
 *     <span style="color:#1a7320">*[@]</span> <span style="color:#a2b0a6"># Saves an entry starting with '@'</span>
 *     <span style="color:#1a7320">*[_]</span> <span style="color:#a2b0a6"># Saves a blank entry</span>
 *
 *     <span style="color:#1a7320">*[{{]</span> <span style="color:#ebe9e6">This is all being
 *     stored
 *     as
 *     a grouped data entry!
 *     :) </span><span style="color:#1a7320">*[}}]</span>
 *
 *     <span style="color:#d99338">a b c</span>
 *
 *     <span style="color:#f5f242">@stop</span> <span style="color:#a2b0a6"># Stops the scanning of the document.</span>
 *
 *     <span style="color:#a2b0a6"># Use comments to avoid dangling text!</span>
 * </pre>
 */
@SuppressWarnings({"SpellCheckingInspection", "unused"})
public class Script {
    private final File file;
    private int startLine = 0;
    private final boolean initialized;
    private boolean log = false;
    private final ArrayList<String> returnValue = new ArrayList<>();

    public Script(File file) {
        if (!file.getPath().endsWith(".txt")) new ScriptingException(String.format("Wrong file type. " +
                "Required: '.txt', found '%s'", file.getName().contains(".")
                ? file.getName().substring(file.getName().lastIndexOf('.'))
                : " "
        ),
                "Check the file type",
                5
        ).printError();
        if (!file.exists()) new ScriptingException(String.format("Could not find the file '%s'", file.getPath()),
                "Check the file declaration",
                5
        ).printError();
        if (!file.canRead()) new ThrownScriptingException("This file is private and cannot be read by this compiler",
                "Change the access permissions linked to " + file.getName(), 5);

        this.file = file;
        this.initialized = true;

        try (Scanner scanner = new Scanner(this.file)) {
            //noinspection SpellCheckingInspection
            if (!scanner.hasNext() || !scanner.nextLine().equals("```mscript"))
                new ScriptingException("This file is not compatible " +
                        "with the TestFile Scanner!",
                        "Make sure to declare the file as an MSCRIPT by using the tools.TestFile.syntax given in the documentation",
                        4
                ).printError();
        } catch (Exception exception) {
            new ScriptingException(String.format("Native Error (%s)", exception.getMessage()), 5).printError();
        }
    }

    public Script(String filePath) {
        this(new File(filePath));
    }

    public Script(File file, boolean logSuccessfulOutputs) {
        this(file);
        if (logSuccessfulOutputs && !file.canWrite()) new ThrownScriptingException("This file cannot be edited",
                "Modify the file accessibility or set <logSuccessfulOutputs> to 'false'", 5);
        log = logSuccessfulOutputs;
    }

    public Script(String filePath, boolean logSuccessfulOutputs) {
        this(new File(filePath), logSuccessfulOutputs);
    }

    /**
     * <b>The method to start scanning the file</b>
     *
     * @return An String[] array consisting of every item read from the file.
     * @see Script Scripting Documentation
     */
    public String[] read() {
        try (Scanner scanner = new Scanner(this.file);
             BufferedReader reader = new BufferedReader(new FileReader(this.file));
             FileWriter fileWriter = new FileWriter(this.file, true)
        ) {
            String check = reader.readLine();
            if (Objects.isNull(check)) {
                new ScriptingException("Empty file", 2).printError();
            }

            if (hasStart()) {
                for (int i = 0; i <= startLine; i++) {
                    scanner.nextLine();
                    reader.readLine();
                }
                int maxInputLength = 0;
                String input;
                for (int i = startLine; scanner.hasNext(); i++) {
                    input = scanner.next();
                    if (input.isEmpty()) { //Blank? edit: I don't this this is ever activated.
                        scanner.nextLine();
                    } else if (input.startsWith(Character.toString(TextSpecifications.COMMENT.getSymbol()))) { //Comment?
                        if (input.startsWith(Comment.BULK_OPEN.getSignature())) { //multi-line comment
                            if (!input.equals(Comment.BULK_OPEN.getSignature() + Comment.BULK_CLOSE.getSignature()))
                                while (true) {
                                    String tempVar;
                                    if (scanner.hasNext()) {
                                        tempVar = scanner.next();
                                        if (tempVar != null && (tempVar.contains(Comment.BULK_CLOSE.getSignature()))) {
                                            if (!tempVar.endsWith(Comment.BULK_CLOSE.getSignature())) {
                                                returnValue.add(tempVar.replaceAll(".*!##", ""));
                                            }
                                            break;
                                        }
                                    } else {
                                        break;
                                    }
                                }
                        } else if (input.startsWith(Comment.BULK_CLOSE.getSignature())) {
                            new ScriptingException("Dangling bulk comment",
                                    "Look over written bulk comment declarations",
                                    1
                            ).printError();
                        } else if (input.startsWith(Comment.SINGLE.getSignature())) {
                            scanner.nextLine();
                        }
                    } else if (input.startsWith(Character.toString(TextSpecifications.COMMAND.getSymbol()))) {//Root Command?
                        switch (Objects.requireNonNull(Keyword.getKeywordFromName(input))) {
                            case START_COMMAND:
                                new ScriptingException(String
                                        .format("Found duplicate `@start` parameter on line %d", i),
                                        "Remove the duplicate `@start` parameter",
                                        1
                                ).printError();
                            case STOP_COMMAND:
                                int c = startLine;
                                do {
                                    if (input.equals(check)) {
                                        c++;
                                        break;
                                    }
                                    c++;
                                    check = reader.readLine();
                                } while (check != null);

                                if (scanner.hasNext()) {
                                    while (scanner.hasNext()) {
                                        input = scanner.next();
                                        if ((input.length() > 0 && input.replaceAll("\\s++", "").charAt(0) == TextSpecifications.COMMENT.getSymbol()) && !input.matches("\\s++")) {
                                            if (input.startsWith(Comment.BULK_OPEN.getSignature())) { //multi-line comment
                                                if (!input.equals(Comment.BULK_OPEN.getSignature() + Comment.BULK_CLOSE.getSignature()))
                                                    while (true) {
                                                        String tempVar;
                                                        if (scanner.hasNext()) {
                                                            tempVar = scanner.next();
                                                            if (tempVar != null && (tempVar.contains(Comment.BULK_CLOSE.getSignature()))) {
                                                                if (!tempVar.endsWith(Comment.BULK_CLOSE.getSignature())) {
                                                                    returnValue.add(tempVar.replaceAll(".*!##", ""));
                                                                }
                                                                break;
                                                            }
                                                        } else {
                                                            break;
                                                        }
                                                    }
                                            } else if (input.startsWith(Comment.BULK_CLOSE.getSignature())) {
                                                new ScriptingException("Dangling bulk comment",
                                                        "Look over written bulk comment declarations",
                                                        1
                                                ).printError();
                                            } else if (input.replaceAll("\\s++", "").startsWith(Comment
                                                    .SINGLE.getSignature())) {
                                                scanner.nextLine();
                                            } else {
                                                new ThrownScriptingException(
                                                        String.format("Dangling code on line %d (Found: '%s')", c, input),
                                                        "Remove dangling code",
                                                        2
                                                );
                                            }
                                        } else {
                                            new ThrownScriptingException(
                                                String.format("Dangling code on line %d (Found: '%s')", c, input),
                                                "Remove dangling code",
                                                2
                                            );
                                        }
                                    }
                                }
                                printDataStack(returnValue);
                                System.out.printf("%c Found `@stop` parameter on line %d, terminating and returning any data " +
                                                "that has been stored.%n%n",
                                        Symbols.BOTTOM_BAR_LEFT.getSymbol(), c);
                                return returnValue.toArray(new String[0]);
                        }
                    } else if (input.startsWith(Character.toString(TextSpecifications.ANNOTATION.getSymbol()))) {
                        StringBuilder str = new StringBuilder();
                        String annotation = input.replaceAll("\\*\\[|].*", "");
                        String str1;
                        if (Keyword.annotationExists('[' + annotation + ']') && input.length() != 1) {
                            str1 = input.substring(3 + annotation.length());
                            switch (Keyword.getAnnotationFromID("[" + annotation + "]")) {
                                case TEXT_COMMENT_SYMBOL: // *[#] -> `#`
                                    str.append(Annotation.TEXT_COMMENT_SYMBOL
                                            .getActualValue())
                                            .append(str1);
                                    returnValue.add(str.toString());
                                    break;
                                case TEXT_ROOT_SYMBOL: // *[@] -> `@`
                                    str.append(Annotation.TEXT_ROOT_SYMBOL
                                            .getActualValue())
                                            .append(str1);
                                    returnValue.add(str.toString());
                                    break;
                                case TEXT_ANNOTATION_SYMBOL: // *[*] -> `*`
                                    str.append(Annotation.TEXT_ANNOTATION_SYMBOL
                                            .getActualValue())
                                            .append(str1);
                                    returnValue.add(str.toString());
                                    break;
                                case WHITESPACE: // *[_] -> ` `
                                    str.append(Annotation.WHITESPACE
                                            .getActualValue())
                                            .append(str1);
                                    returnValue.add(str.toString());
                                    break;
                                case STORE_AS_LINE: // *[^+] -> `Saves the entire line`
                                    boolean tagContainsText = Annotation.STORE_AS_LINE.getID().length() + 1 < input.length();
                                    String s = scanner.nextLine();
                                    str.append(tagContainsText
                                            ? str1.concat(" ")
                                            : str1)
                                            .append(s.charAt(0) == 32 ? s.substring(1) : s);
                                    returnValue.add(str.toString());
                                    break;
                                case GROUP_ENTRIES_OPEN: // *[{{] -> Opens a group entry
                                    StringBuilder groupEntryBuilder = new StringBuilder().append('{');
                                    if (str1.length() > 0) groupEntryBuilder.append(str1).append(", ");
                                    while (true) {
                                        String tempVar;
                                        if (scanner.hasNext()) {
                                            tempVar = scanner.next();
                                            if (tempVar != null) {
                                                if (tempVar.equals(TextSpecifications.ANNOTATION
                                                        .getSymbol() + Annotation.GROUP_ENTRIES_CLOSE.getID())) {
                                                    break;
                                                } else {
                                                    groupEntryBuilder.append(tempVar).append(", ");
                                                }
                                            }
                                        } else {
                                            break;
                                        }
                                    }
                                    returnValue.add(groupEntryBuilder.substring(0, groupEntryBuilder.length() - 2 > 0
                                            ? groupEntryBuilder.length() - 2
                                            : groupEntryBuilder.length() - 1 > 0
                                            ? groupEntryBuilder.length() - 1
                                            : groupEntryBuilder.length()).concat("}"));
                                    break;
                                case GROUP_ENTRIES_CLOSE: // *[}}] -> Closes a group entry; This is already handled on open!
                                    new ScriptingException("Dangling data group",
                                            "Look over written data group annotations",
                                            1
                                    ).printError();
                            }
                        } else {
                            new ScriptingException(String
                                    .format("Unknown annotation: %s", input),
                                    "Look at the list of annotations present on GitHub",
                                    4
                            ).printError();
                        }
                    } else {//Raw Text
                        returnValue.add(input);
                        if (input.length() > maxInputLength) maxInputLength = input.length();
                    }
                }

                printDataStack(returnValue);
                System.out.printf("%c End of file%n%n", Symbols.BOTTOM_BAR_LEFT.getSymbol());
                if (log) {
                    LocalDateTime now = LocalDateTime.now();
                    fileWriter.append(String.format("%n%n# This file was read at %s on %s - SUCCESS",
                            DateTimeFormatter.ofPattern("MM/dd/yyyy").format(now),
                            DateTimeFormatter.ofPattern("HH:mm:ss").format(now)));
                    fileWriter.flush();
                }
            }
        } catch (Exception exception) {
            new ScriptingException(String.format("Native Error (%s)", exception.getMessage()), 5).printError();
        }
        return returnValue.toArray(new String[0]);
    }

    /**
     * <b>Parses every line of a file until it reaches `@start`</b>
     *
     * @return {@code true} if the file contains the `@start` parameter; {@code false} if not.
     * If possible, this method will also set {@link #startLine} to the line found.
     * @implNote Can throw planned exceptions! This is important to keep in mind.
     */
    private boolean hasStart() {
        try (Scanner scanner = new Scanner(this.file);
             BufferedReader reader = new BufferedReader(new FileReader(this.file))
        ) {
            String check = reader.readLine();
            scanner.nextLine();
            if (Objects.isNull(check)) {
                new ScriptingException("Empty file", 2).printError();
            }
            String input;
            for (int i = 1; i <= 5000 && scanner.hasNext(); i++) {
                input = scanner.next();
                if (input != null && input.startsWith(Character.toString(TextSpecifications.COMMENT.getSymbol()))) {
                    if (input.startsWith(Comment.BULK_OPEN.getSignature())) { //multi-line comment
                        if (!input.equals(Comment.BULK_OPEN.getSignature() + Comment.BULK_CLOSE.getSignature()))
                            while (true) {
                                String tempVar;
                                if (scanner.hasNext()) {
                                    tempVar = scanner.next();
                                    if (tempVar != null && (tempVar.contains(Comment.BULK_CLOSE.getSignature()))) {
                                        if (!tempVar.endsWith(Comment.BULK_CLOSE.getSignature())) {
                                            returnValue.add(tempVar.replaceAll(".*!##", ""));
                                        }
                                        break;
                                    }
                                } else {
                                    break;
                                }
                            }
                    } else if (input.startsWith(Comment.BULK_CLOSE.getSignature())) {
                        new ScriptingException("Dangling bulk comment",
                                "Look over written bulk comment declarations",
                                1
                        ).printError();
                    } else if (input.startsWith(Comment.SINGLE.getSignature())) {
                        scanner.nextLine();
                    }
                    continue;
                }

                if (input != null && input.startsWith(Character.toString(TextSpecifications.COMMAND.getSymbol()))) {
                    if (Keyword.commandExists(input)) {
                        if (Objects.requireNonNull(Keyword.getKeywordFromName(input)) == RootCommand.START_COMMAND) {
                            int c = 0;
                            do {
                                if (input.equals(check)) {
                                    startLine = c;
                                    break;
                                }
                                c++;
                                check = reader.readLine();
                            } while (check != null);
                            System.out.printf("%c Found `@start` parameter on line %d, gathering entries:%n",
                                    Symbols.TOP_BAR_LEFT.getSymbol(), startLine);
                            return true;
                        } else if (Objects.requireNonNull(Keyword.getKeywordFromName(input)) == RootCommand.STOP_COMMAND) {
                            System.out.printf("%c Found a `@stop` parameter on line %d before the program could compile." +
                                            "%n%c Closing testing:%n",
                                    Symbols.TOP_BAR_LEFT.getSymbol(),
                                    i, Symbols.BOTTOM_BAR_LEFT.getSymbol());
                            return false;
                        }
                    }
                } else if (input != null && (input.isEmpty() || input.replaceAll("\\s++", "").charAt(0) == TextSpecifications.COMMENT.getSymbol())) {
                    scanner.nextLine();
                } else {
                    new ScriptingException(String
                            .format("Dangling code on line %d (Found: '%s')", i, input),
                            "Remove dangling code",
                            2
                    ).printError();
                }
            }
        } catch (Exception exception) {
            new ScriptingException(String.format("Native Error (%s)", exception.getMessage()), 5).printError();
        }
        new ScriptingException("Could not find parameter `@start` while parsing the file.",
                "add `@start` to declare where the compiler should start checking your code",
                4
        ).printError();
        return false;
    }

    /**
     * <b>Prints every occurrence of a {@link java.util.ArrayList} preceded by its index and other fancy formatting</b>
     *
     * @param array the {@link java.util.ArrayList} to be printed.
     * @implNote Make sure that the text both following and preceding the printed data from this method is formatted
     * to look correctly, otherwise it might look out of place.
     */
    private void printDataStack(ArrayList<String> array) {
        for (int i = 0; i < array.toArray().length; i++) {
            System.out.printf("%c #%d:%s%s%n", Symbols.MIDDLE_BAR_WITH_PIPE.getSymbol(), i,
                    this.buildSpaces(i < 10 ? 3 : (i < 100 ? 2 : 1)), array.toArray()[i]);
        }
    }

    /**
     * <b>Gets a {@link String} containing the file name, size, and location on the user's computer</b>
     *
     * @return A formatted {@link String} containing the file information.
     * @deprecated - created during testing, no longer useful.
     */
    @Deprecated
    public String getFileInformation() {
        return String.format(
                "File Information:" +
                        "%nText file size: %d bytes" +
                        "%nText file name: %s" +
                        "%nText file path: %s" +
                        "%nInitialized: %b",
                this.file.length(),
                this.file.getName(),
                this.file.getPath(),
                this.initialized
        );
    }

    /**
     * <b>Used in {@link #printDataStack(ArrayList)} to format the output</b>
     *
     * @param spaces int value of spaces to be printed
     * @return a blank {@link String} with a length of `spaces`
     */
    private String buildSpaces(int spaces) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < spaces; i++) {
            s.append(' ');
        }
        return s.toString();
    }

    /**
     * <b>Returns the file bound to the {@link Script} class</b>
     *
     * @return the {@link java.io.File} bound to this instance of the class.
     * @deprecated - No longer useful
     */
    @Deprecated
    public File getFile() {
        return file;
    }
}