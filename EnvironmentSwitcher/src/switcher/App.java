package switcher;

import java.io.IOException;
import java.util.ArrayList;

public abstract class App {
    protected String filePath;
    protected String name;
    protected ArrayList<String> arguments;
    protected Process process;
    
    public App(String filePath, String name) {
        this(filePath, name, null);
    }
    
    public App(String filePath, String name, ArrayList<String> arguments) {
        this.filePath = filePath;
        this.name = name;
        if (arguments != null) {
            this.arguments = arguments;
        } else {
            this.arguments = new ArrayList<String>();
        }
    }
    
    public abstract String getFilePath();

    public abstract void setFilePath(String filePath);

    public abstract String getName();

    public abstract void setName(String name);

    public abstract void setArguments(ArrayList<String> arguments);
    public abstract ArrayList<String> getArguments();
    
    public abstract Process getProcess();
    
    public abstract void start() throws IOException;
    
    public abstract boolean end();
}
