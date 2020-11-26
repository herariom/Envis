package switcher;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        App a = new App("C:\\Program Files\\HeidiSQL\\heidisql.exe", "HeidiSQL");
        App a2 = new App("C:\\Program Files\\Git\\git-bash.exe", "Git-Bash");
        
        ArrayList<App> apps = new ArrayList<>();
        
        apps.add(a);
        apps.add(a2);
        
        Environment env = new Environment("Dev", apps);

        env.startApps();
        
        Thread.sleep(4500);
        
        System.out.println(env.closeApps().size());
    }
}
