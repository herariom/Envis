package switcher;

import java.io.IOException;
import java.util.ArrayList;

public class Environment {
    
    private String name;
    private ArrayList<App> apps;
    
    public Environment(String name) {
        this(name, null);
    }
    
    public Environment(String name, ArrayList<App> apps) {
        this.name = name;
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
    }
    
    // Returns apps that failed to close
    public ArrayList<App> closeApps() {
        ArrayList<App> notClosed = new ArrayList<>();
        
        for (App a : apps) {
            if (!a.end()) {
                notClosed.add(a);
            }
        }
        
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
    
    @Override
    public String toString() {
        return name;
    }
}
