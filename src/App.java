import com.formdev.flatlaf.FlatDarkLaf;

public class App {
    public static void main(String[] args) throws Exception {
        FlatDarkLaf.setup();
        LoginWindow login = new LoginWindow();
    }
}
