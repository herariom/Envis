package switcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WindowsApp extends App {
    
    public WindowsApp(String filePath, String name) {
        this(filePath, name, null);
    }
    
    public WindowsApp(String filePath, String name, List<String> arguments) {
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

    public void setArguments(List<String> arguments) {
        if (arguments == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        
        this.arguments = arguments;
    }
    
    public List<String> getArguments() {
        return new ArrayList<>(arguments);
    }
    
    public void addArgument(String argument) {
        if (argument == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        
        arguments.add(argument);
    }
    
    public String removeArgument(int idx) {
        return arguments.remove(idx);
    }
    
    public Process getProcess() {
        return process;
    }
    
    public void start() throws IOException {
        ArrayList<String> command = new ArrayList<>();
        
        // The first entry in the list passed to ProcessBuilder is path to executable
        command.add(filePath);
        
        if (arguments != null) {
            // Tokenize arguments by spaces because spaces are not parsed by ProcessBuilder in a correct manner
            arguments.forEach(arg -> {
                String[] splitArg = arg.split(" ");
                for (int i = 0; i < splitArg.length; i++) {
                    command.add(splitArg[i]);
                }
            });
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
