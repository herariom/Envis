package switcher;

import java.util.ArrayList;
import java.util.HashMap;

public class EnvironmentManager {
    
    private HashMap<String, Environment> environments;
    
    private static EnvironmentManager singleton = new EnvironmentManager();

    private EnvironmentManager() {
        environments = new HashMap<>();
    }

    public static EnvironmentManager getInstance() {
        return singleton;
    }
    
    public HashMap<String, Environment> getEnvironments() {
        return environments;
    }
    
    public void clearEnvironments() {
        environments = new HashMap<>();
    }
    
    public Environment getEnvironment(String name) {
        return environments.get(name);
    }
    
    public void addEnvironment(String name) {
        environments.put(name, new Environment(name));
    }
    
    public void addEnvironment(String name, ArrayList<App> apps) {
        environments.put(name, new Environment(name, apps));
    }
    
}
