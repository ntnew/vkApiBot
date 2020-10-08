package core.modules;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import static core.modules.Reader.programPath;

public class FileHelper {

    public static void writeLetter(String fileName, String data){

        try {
            Files.write(Paths.get(programPath + "\\src\\main\\Reservers\\" + fileName +".txt"), data.getBytes(),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String  readLetter(String fileName) {
        String  s = "";
        List<String> lines = null;

        try {
            lines = Files.readAllLines(Paths.get(programPath + "\\src\\main\\Reservers\\" + fileName +".txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(String line: lines){
            s = s + line + "\n";
        }
        return s;
    }
    public static void clearLetter(String fileName){
        String data ="";
        try {
            Files.write(Paths.get(programPath + "\\src\\main\\Reservers\\" + fileName +".txt"), data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
