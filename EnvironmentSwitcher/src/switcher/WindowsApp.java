package switcher;

import java.io.IOException;
import java.util.ArrayList;

public class WindowsApp extends App {
    
    public WindowsApp(String filePath, String name) {
        this(filePath, name, null);
    }
    
    public WindowsApp(String filePath, String name, ArrayList<String> arguments) {
        super(filePath, name, arguments);
    }
    
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArguments(ArrayList<String> arguments) {
        if (arguments == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        
        this.arguments = arguments;
    }
    
    public ArrayList<String> getArguments() {
        return new ArrayList<>(arguments);
    }
    
    public Process getProcess() {
        return process;
    }
    
    public void start() throws IOException {
        ArrayList<String> command = new ArrayList<>();
        
        // The first entry in the list passed to ProcessBuilder is path to executable
        command.add(filePath);
        
        if (arguments != null) {
            command.addAll(arguments);
        }
        
        Process process = new ProcessBuilder(command).start();
        this.process = process;
    }
    
    // Destroy process and return if it was successfully ended
    public boolean end() {
        if (process.isAlive()) {
            process.destroy();
            process.children().forEach(a -> a.destroy());
        } else {
            return true;
        }
        
        return !process.isAlive();
    }
    
    @Override
    public String toString() {
        return name + " - " + filePath;
    }
}
