import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main {
    public static void main(String[] args) {
        System.out.println("debug");
        ApiContextInitializer.init();
        TelegramBotsApi tba = new TelegramBotsApi();
        System.out.println("debug");
        try {
            System.out.println("debug");
            tba.registerBot(new FvckTikTokBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
