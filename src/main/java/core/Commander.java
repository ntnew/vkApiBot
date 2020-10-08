package core;

import com.vk.api.sdk.objects.messages.Message;
import core.modules.Send;
import core.modules.SendLetter;
import vk.VKManager;

import static core.commands.ReserveTable.getReserveStepMessage;
import static core.modules.FileHelper.*;
import static vk.VKServer.reserveQueue;

public class Commander {


    /**
     * Обработка сообщений, получаемых через сервис Вконтакте. Имеет ряд дополнительной информации.
     * @param message сообщение (запрос) пользователя
     */
    public static void execute(Message message){
        if(reserveQueue.contains(message.getFromId() + "res2")){
            new VKManager().sendMessage(getReserveStepMessage(2), message.getFromId());
            reserveQueue.remove(message.getFromId() + "res2");
            reserveQueue.add(message.getFromId() + "res3");
            /// TODO: 04.10.2020 сохранить колво персон
            writeLetter(message.getFromId() + "","Количество персон: " + message.getText() +";\n");
        }
        else if (reserveQueue.contains(message.getFromId() + "res3")) {
            new VKManager().sendMessage(getReserveStepMessage(3), message.getFromId());
            reserveQueue.remove(message.getFromId() + "res3");
            reserveQueue.add(message.getFromId() + "res4");
            /// TODO: 04.10.2020 сохранить время
            writeLetter(message.getFromId() + "","Время прибытия гостей: " + message.getText() +";\n");
        }else if (reserveQueue.contains(message.getFromId() + "res4")) {
            new VKManager().sendMessage(getReserveStepMessage(4), message.getFromId());
            reserveQueue.remove(message.getFromId() + "res4");
            reserveQueue.add(message.getFromId() + "res5");
            /// TODO: 04.10.2020 сохранить телефон
            writeLetter(message.getFromId() + "","Телефон : " + message.getText() +";\n");
        }else if (reserveQueue.contains(message.getFromId() + "res5")) {
            writeLetter(message.getFromId() + "","Имя : " + message.getText() +";\n");
            new VKManager().sendMessage(getReserveStepMessage(5) + "\n" +
                    readLetter(message.getFromId()+""), message.getFromId());
            new Send().sendKeyboard("keyboardConfirm.json",message.getFromId());
            reserveQueue.remove(message.getFromId() + "res5");
            reserveQueue.add(message.getFromId() + "res6");
            /// TODO: 04.10.2020 имя сохр
        }else if (reserveQueue.contains(message.getFromId() + "res6") && message.getText().equals("Да")) {
            new VKManager().sendMessage(getReserveStepMessage(6), message.getFromId());
            new Send().sendKeyboard("keyboardStart.json",message.getFromId());
            reserveQueue.remove(message.getFromId() + "res6");
            /// TODO: 04.10.2020 отправить письмо
            SendLetter sendLetter = new SendLetter(message.getFromId());
            sendLetter.start();
        }else if (reserveQueue.contains(message.getFromId() + "res6") && message.getText().equals("Нет")) {
            new VKManager().sendMessage("Попробуйте заполнить ещё раз", message.getFromId());
            new Send().sendKeyboard("keyboardStart.json",message.getFromId());
            reserveQueue.remove(message.getFromId() + "res6");
            clearLetter(message.getFromId()+"");
        }
        else CommandDeterminant.getCommand(CommandManager.getCommands(), message).exec(message);
    }

}
