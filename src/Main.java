import tools.mscript.Script;

public class Main {
    public static void main(String[] args) {
        Script a = new Script("C:\\Users\\mateo\\Documents\\JavaTestingDoc.txt", false);
        String[] testResults = a.read();
        for (String str : testResults) System.out.println(str);
    }
}
