package core.modules;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static core.CommandManager.getDesc;
import static core.modules.FileHelper.clearLetter;
import static core.modules.FileHelper.readLetter;

public class SendLetter extends Thread {
//    public static String header = "Здравствуйте. Клиент хочет заказать стол, перезвоните ему по номеру, " +
//            "указанному ниже.\n";

    //public static String subString = "\nЭтописьмо создано автоматически. На него не нужно отвечать.";
    public int userId;

    public SendLetter(int userId) {
        this.userId = userId;
    }


    @Override
    public void run()    //Этот метод будет выполнен в побочном потоке
    {
        Properties prop = new Properties();

        try {
            prop.load(new FileInputStream("src/main/resources/vkconfig.properties"));
            String to = prop.getProperty("email");         // sender email
            String from = prop.getProperty("emailFrom");         // receiver email
            String host = "smtp.gmail.com";            // mail server host
            String password = prop.getProperty("emailPassword");
            ;
            Properties properties = System.getProperties();
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.user", from);
            properties.put("mail.smtp.password", password);
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(properties); // default session
            MimeMessage message = new MimeMessage(session); // email message

            message.setFrom(new InternetAddress(from)); // setting header fields

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject("Бронирование столика"); // subject line

            message.setText(getDesc(7) + readLetter(userId + "") + getDesc(8));


            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, password);
            transport.sendMessage(message, message.getAllRecipients());
            System.out.println("Email Sent successfully....");
            transport.close();
            System.out.println("Сделан заказ");
            clearLetter(userId + "");

        } catch (MessagingException | FileNotFoundException mex) {
            mex.printStackTrace();
            System.out.println("не отправил");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
