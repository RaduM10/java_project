import java.io.IOException;

class MenuCommand extends MenuItem  {
    Comenzi command;

    public MenuCommand(String name, String description, Comenzi command) {
        super(name, description);
        this.command = command;
    }

    @Override
    public void execute() throws IOException {
        command.execute();
    }
}