package core;

import core.commands.*;

import java.util.HashSet;


public class CommandManager {
    private static HashSet<Command> commands = new HashSet<>();

    static {
        commands.add(new Unknown("unknown"));
        commands.add(new Weather("weather"));
        commands.add(new Begin("Начать"));
        commands.add(new Sale("Акции"));
        commands.add(new Menu("Меню"));
        commands.add(new commandOrder("Заказать"));
        commands.add(new Begin("Вернуться"));
        commands.add(new orderPizza("Пицца"));

    }

    public static HashSet<Command> getCommands(){
        return commands;
    }
    public static void addCommand(Command command) { commands.add(command);}
}
