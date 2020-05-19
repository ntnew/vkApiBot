package core.commands;

import com.vk.api.sdk.objects.messages.Message;
import core.Command;
import core.modules.Reader;
import vk.VKManager;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Begin extends Command {
    public Begin(String name) {
        super(name);
    }

    private String getStartMessage(){
        String fileName = "C:\\Users\\Ahab\\BotOnJavaSDKExample\\src\\main\\resources\\startPage.txt";
        return Reader.readTxtFile(fileName);
    }

    @Override
    public void exec(Message message) {
        new VKManager().sendMessage(getStartMessage(), message.getUserId());
    }
}
