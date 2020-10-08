package core;

import core.commands.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;

import static core.modules.Reader.programPath;


public class CommandManager {
    private static HashSet<Command> commands = new HashSet<>();

    static {
        commands.add(new ReserveTable(getDesc(4)));
        commands.add(new Unknown("unknown"));
        commands.add(new Weather("weather"));
        commands.add(new Begin(getDesc(0)));
        commands.add(new Sale(getDesc(2)));
        commands.add(new Menu(getDesc(1)));
        commands.add(new Vacancy(getDesc(3)));
        commands.add(new Question(getDesc(5)));

    }

    public static HashSet<Command> getCommands(){
        return commands;
    }
    public static void addCommand(Command command) { commands.add(command);}
    public static String getDesc(int position){

        List<String> lines = null;

        try {
            lines = Files.readAllLines(Paths.get(programPath + "\\src\\main\\resources\\commands.txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines.get(position);
    }
}
