import java.util.ArrayList;
import java.io.IOException;
public class ClaseDemo {

    public static void main(String[] args) throws IOException {
        LoginSystem loginSystem = new LoginSystem();
        try {
            loginSystem.login();


        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}