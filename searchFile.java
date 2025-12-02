import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class searchFile {

    //syntax of “search <pattern> <file>"
    private static int REQUIRED_ARGUMENT_LENGTH = 3;

    public static void main(String[] args) {
        if (args.length != REQUIRED_ARGUMENT_LENGTH || !args[0].equals("search")) {
            printAndExit("syntax error");
        }
        
        File file = new File(args[2]); 
        FileReader fileReader = null;
       
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            printAndExit(file.getName() + " is not a file.");
        }

        List<String> allLines = null; 

        try {
            allLines = fileReader.readAllLines();
        } catch (IOException e) {
            printAndExit("reading error");
        } 

        try {
            Pattern pattern = Pattern.compile(args[1], Pattern.CASE_INSENSITIVE);

            allLines.stream().filter((s) -> {
            Matcher matcher = pattern.matcher(s);
            boolean matchFound = matcher.find();
            return matchFound; 
            }).forEach((s) -> { System.out.println(s); });

        } catch (PatternSyntaxException e) {
            printAndExit("pattern error");
        }

    }

    private static void printAndExit(String errorMessange)  {
        System.out.println(errorMessange);
        System.exit(1);
    }

}
