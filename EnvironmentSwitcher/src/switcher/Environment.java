package switcher;

import java.io.IOException;
import java.util.ArrayList;

public class Environment {
    
    private String name;
    private ArrayList<App> apps;
    private Enum<OperatingSystem> operatingSystem;
    private boolean running = false;
    
    public Environment(String name) {
        this(name, null, null);
    }
    
    public Environment(String name, Enum<OperatingSystem> operatingSystem) {
        this(name, operatingSystem, null);
    }
    
    public Environment(String name, Enum<OperatingSystem> operatingSystem, ArrayList<App> apps) {
        this.name = name;
        this.operatingSystem = operatingSystem;
        if (apps == null) {
            this.apps = new ArrayList<>();
        } else {
            this.apps = apps;
        }
    }

    public void startApps() throws IOException {
        for (App a : apps) {
            a.start();
        }
        running = true;
    }
    
    // Returns apps that failed to close
    public ArrayList<App> closeApps() {
        ArrayList<App> notClosed = new ArrayList<>();
        
        for (App a : apps) {
            if (!a.end()) {
                notClosed.add(a);
            }
        }
        
        running = false;
        return notClosed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean addApp(App app) {
        return apps.add(app);
    }

    public boolean removeApp(App app) {
        return apps.remove(app);
    } 
    
    public ArrayList<App> getApps() {
        return apps;
    }

    public void setApps(ArrayList<App> apps) {
        this.apps = apps;
    }
    
    public Enum<OperatingSystem> getOperatingSystem() {
        return operatingSystem;
    }
    
    public void setOperatingSystem(Enum<OperatingSystem> operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return running;
    }

    @Override
    public String toString() {
        return name;
    }
}
