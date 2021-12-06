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
    
    public void addEnvironment(String name, Enum<OperatingSystem> operatingSystem, ArrayList<App> apps) {
        environments.put(name, new Environment(name, operatingSystem, apps));
    }
    
    public void addEnvironment(Environment env) {
        environments.put(env.getName(), env);
    }
    
    public Environment removeEnvironment(String name) {
        return environments.remove(name);
    }
    
}
