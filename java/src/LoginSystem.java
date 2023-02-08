import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LoginSystem {


    private static final String ACCOUNTS_FILE = "C:\\Users\\mihai\\OneDrive\\Desktop\\java\\src\\accounts.csv";

    private String username;
    private String password;

    public LoginSystem() {
        this.username = "";
        this.password = "";
    }

    public void login() throws IOException {
        // Create a scanner for reading input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for their username and password
        System.out.print("Username: ");
        this.username = scanner.nextLine();
        System.out.print("Password: ");
        this.password = scanner.nextLine();

        // Check if the username and password match an account in the CSV file
        if (checkCredentials(this.username, this.password)) {
            System.out.println("Welcome, " + this.username + "!");
            Aplicatie app = new Aplicatie(new DatabaseManager());
            app.execute();
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    public boolean isLoggedIn() {
        return !this.username.isEmpty() && !this.password.isEmpty();
    }

    public void logout() {
        this.username = "";
        this.password = "";
    }

    public boolean checkCredentials(String username, String password) throws IOException {
        // Open the accounts CSV file for reading
        BufferedReader reader = new BufferedReader(new FileReader(ACCOUNTS_FILE));

        // Read each line of the file
        String line;
        while ((line = reader.readLine()) != null) {
            // Split the line into fields
            String[] fields = line.split(",");
            if (fields.length >= 2) {
                // Check if the username and password match the current line
                if (fields[0].equals(username) && fields[1].equals(password)) {
                    reader.close();
                    return true;
                }
            }
        }

        // Close the reader
        reader.close();

        // If we reached here, the username and password were not found
        return false;
    }

    public void addAccount(String username, String password) throws IOException {
        // Open the accounts CSV file for appending
        BufferedWriter writer = new BufferedWriter(new FileWriter(ACCOUNTS_FILE, true));

        // Write the new account as a line in the file
        writer.write(username + "," + password);
        writer.newLine();

        // Close the writer
        writer.close();
    }
}


