import commands.PasswordCommand;
import commands.PinCommand;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {
    String message;
    int chatId;
    Long id;
    String currentCommand;
    int l;
    PasswordCommand psw;
    PinCommand pin;
    private String botToken = "";

    @Override
    public String getBotUsername() {
        return "PassPinbot";
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        message = update.getMessage().getText();
        chatId = Math.toIntExact(update.getMessage().getChatId());
        id = (long) chatId;

        if (update.getMessage().isCommand()) {
            switch (message) {
                case "/start" -> {
                    currentCommand = "/start";
                    sendMessage(id, "Hi! I can generate random PINs and passwords. \nSend: \n/pin to generate a new PIN \n/password to generate a new password");
                }
                case "/help" -> {
                    currentCommand = "/help";
                    sendMessage(id, "Send: \n/pin to generate a new PIN \n/password to generate a new password");
                }
                case "/pin" -> {
                    currentCommand = "/pin";
                    sendMessage(id, "How long should the PIN be?");
                }
                case "/password" -> {
                    currentCommand = "/password";
                    sendMessage(id, "How long should the password be?");
                }
                case "/newPassword" ->{
                    currentCommand = "/newPassword";
                    sendMessage(id, "Here your new password");
                    psw = new PasswordCommand();
                    sendMessage(id, psw.generatePassword(l));
                    sendMessage(id, "Send /password to change the length");
                }
                case "/newPIN" ->{
                    currentCommand = "/newPIN";
                    sendMessage(id, "Here your new PIN");
                    pin = new PinCommand();
                    sendMessage(id, pin.generatePin(l));
                    sendMessage(id, "Send /pin to change the length");
                }
                default -> sendMessage(id, "Invalid Command!!");
            }


        } else {
            if (isInteger(message)){
                if(currentCommand.equals("/password")) {
                    l = Integer.parseInt(message);
                    sendMessage(id, "Here your password");
                    psw = new PasswordCommand();
                    sendMessage(id, psw.generatePassword(l));
                    sendMessage(id, "Send /newPassword to generate another password of the same length");
                } else if (currentCommand.equals("/pin")) {
                    l = Integer.parseInt(message);
                    sendMessage(id, "Here your PIN");
                    pin = new PinCommand();
                    sendMessage(id, pin.generatePin(l));
                    sendMessage(id, "Send /newPIN to generate another PIN of the same length");
                }

            } else {
                sendMessage(id,"Invalid value, please enter a number");
                }

            }

    }

    private void sendMessage(Long id, String text){
        SendMessage sm = SendMessage.builder()
                .chatId(id.toString())
                .text(text).build();

        try {
            execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }

    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
