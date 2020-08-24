import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class FvckTikTokBot extends TelegramLongPollingBot {

    public void onUpdateReceived(Update update) {
        updateImpl(update);
    }

    public void onUpdatesReceived(List<Update> updates) {
        for (Update update : updates) {
            updateImpl(update);
        }
    }

    private void updateImpl(Update update) {
        if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().contains("tiktok")) {
            long chatId = update.getMessage().getChatId();
            int messageId = update.getMessage().getMessageId();
            System.out.println(update.getMessage().getText());
            DeleteMessage deleteMessage = new DeleteMessage().setChatId(chatId).setMessageId(messageId);
            try {
                execute(deleteMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            System.out.println("Success");
        }
    }

    public String getBotUsername() {
        return "FvckTikTokBot";
    }

    public String getBotToken() {
        String returnable = null;
        try {
            Scanner sc = new Scanner(new FileInputStream(".api"));
            if (sc.hasNextLine())
                returnable = sc.nextLine();
            else
                throw new TelegramApiException("No api found");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        System.out.printf("Your KEY is %s", returnable);
        return returnable;
    }
}
