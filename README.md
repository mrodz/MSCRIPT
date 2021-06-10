# MSCRIPT - A scripting language that can be used to run tests in java projects.

## What is it?
MSCRIPT reads data from a raw text document, and can return it as an array of Strings to be parsed by your own program. 
It follows a couple of simple rules, and is relatively easy to learn. 

## Documentation:
### Basics
To declare a text file as an MSCRIPT, the first line must be set as: ` ```mscript `  
  
For the file to be parsed, it is required for it to contain the `@start` parameter. Below this line, all text is saved as an **entry**.  
  
There are four types of entries:  
| Symbol | Meaning                                                                          |
|--------|----------------------------------------------------------------------------------|
|   @    | The '@' symbol declares a **Root Command**                                       |
|   *    | The '*' symbol declares an **Annotation**                                        |
|   #    | The '#' symbol introduces a comment.                                             |
| {none} | If an entry does not start with any of the above three, it is saved as a String. |
  
Dangling text is not allowed! Any non-commented text above or below the reading parameters will throw an exception.

### Exceptions
Exceptions can be thrown for various reasons, but they all follow the format of:  
  
&nbsp; &nbsp; &nbsp; &nbsp; _Exception: {reason}  
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;╚ Possible Fix: {supplied information}  
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;╚ Severity Level: #/5_   

<ul>
    Common exceptions are:
    <li>No `@start` Parameter Found Exception</li>
    <li>Dangling Code Exception</li>
    <li>Non-Existent Root Command Exception</li>
    <li>Non-Existent Annotation Exception</li>
    <li>Dangling Data Group Exception</li>
</ul>
  
An exception's "Reason:" tag describes the error thrown; it's "Possible fixes:" tag might help point the user in the right direction to solving their problem.  
Every exception has a "Severity Level:" tag, which returns a value out of five following the rule:
  
<ol>
    The `Severity Level` entry refers to a value out of five:
    <li>Minor error, and can easily be fixed.</li>
    <li>Slight syntax error, can be fixed</li>
    <li>More complex syntax error</li>
    <li>The script encounters a user error while parsing the text</li>
    <li>The script cannot compile or run the file</li>
</ol>  
  
Although this should never be thrown, you might come across a...
  
&nbsp; &nbsp; &nbsp; &nbsp; _Exception: Native Error ({reason})  
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; ╚ Severity Level: 5/5_ 
  
If this is ever thrown, it means something went wrong with the native language and the MSCRIPT source code! Please report any occurrences of this happening.  

### Root Commands
Root Commands represent a specific action for the compiler. As of release 1.0, there are two available:
| Command | Function                                                                                                                                                                                                  |
|---------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| @start  | Indicates where the compiler should start saving entries. **REQUIRED TO PARSE THE FILE**                                                                                                                  |
| @stop   | Indicates where the compiler should terminate and return all saved entries.<br>If this Root Command is not present, the compiler will read every entry present until  <br>it reaches the end of the file. |
  
### Annotations
Annotations are used to call certain functions that modify an entry. The list of Annotations consists of:
| Annotation | Function                                                                                                          |
|------------|-------------------------------------------------------------------------------------------------------------------|
| \*[@]       | Allows an entry to start with the '@' character (Instead of declaring a Root Command)                            |
| \*[\*]       | Allows an entry to start with the '*' character (Instead of declaring an annotation)                            |
| \*[#]       | Allows an entry to start with the '#' character (Instead of introducing a comment)                               |
| \*[_]       | Stores a blank entry                                                                                             |
| \*[^+]      | Stores the current line's data as a single entry (Doubled String)                                                |
| \*[{{]      | Opens a data group entry that will store every entry read until it is closed.<br>(Not really practical just yet) |
| \*[}}]      | Closes a data group entry                                                                                        |

## Example Script:
See the `ExampleScript.txt` file to see how a completed MSCRIPT file would look like. 

## Implementation:
Once all the files are built in to your project, you can initialize your script by using one of four constructors:
<ol>
    <li>` Script var = new Script(java.io.File file) `</li>
    <li>` Script var = new Script(String filePath) `</li>
    <li>` Script var = new Script(java.io.File file, boolean logSuccessfulOutputs) `</li>
    <li>` Script var = new Script(String filePath, boolean logSuccessfulOutputs) `</li>
</ol>
  
_where..._  
<ul> 
    <li> ` file ` is an ` Object ` of the ` File ` class </li>
    <li> ` filePath ` is a ` String ` containing the full path to the file. (Example: "C:\\users\\boo\\Documents\\MyFile.txt") </li>
    <li> ` logSuccessfulOutputs ` is a ` boolean ` value that determines whether you'd like to log completion times as comments on the file </li>
</ul>