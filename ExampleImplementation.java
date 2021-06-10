import tools.mscript.Script;
import java.io.IOException;
import java.io.File;

public class ExampleImplementation {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\users\\Personal\\Documents\\TestingDOC.txt");
        Script myScript = new Script(file, true);
        for (String str : myScript.read()) System.out.println(str);
    }
}
