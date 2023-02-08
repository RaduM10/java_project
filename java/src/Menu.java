
import java.io.IOException;
import java.util.Scanner;
 public class  Menu {
    String name;
    MenuItem[] items;

    public Menu(String name, MenuItem[] items) {
        this.name = name;
        this.items = items;
    }

    public void display() {
        System.out.println(name);
        for (int i = 0; i < items.length; i++) {
            System.out.println((i + 1) + ") " + items[i].getNume() + " - " + items[i].getDescriere());
        }
        System.out.print("Enter choice: ");
    }

    public void handleInput() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if (choice > 0 && choice <= items.length) {
            items[choice - 1].execute();
        } else {
            System.out.println("Invalid choice.");
        }
    }
}