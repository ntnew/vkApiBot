package core.modules;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.apache.logging.log4j.core.impl.ThrowableFormatOptions.FILE_NAME;

public class Reader {

    public static String  programPath = System.getProperty("user.dir");
    public static String  readTxtFile(String fileName) {
        String  s = "";
        List<String> lines = null;

        try {
            lines = Files.readAllLines(Paths.get(programPath + "\\src\\main\\resources\\"+ fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(String line: lines){
            s = s + line + "\n";
        }
        return s;
    }

}
