package switcher;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class EnvironmentTest {

    final String ENV_NAME = "Dev";

    ArrayList<App> empty = new ArrayList<>();
    ArrayList<App> apps;

    @Test
    public void testEnvironmentString() {
        Environment env = new Environment(ENV_NAME);

        assertEquals(ENV_NAME, env.getName());
        assertEquals(empty, env.getApps());
    }

    @Test
    public void testEnvironmentStringArrayListOfApp() {
        WindowsApp a = new WindowsApp("C:\\Program Files\\HeidiSQL\\heidisql.exe", "HeidiSQL");
        WindowsApp a2 = new WindowsApp("C:\\Program Files\\Git\\git-bash.exe", "Git-Bash");

        apps = new ArrayList<>();

        apps.add(a);
        apps.add(a2);

        Environment env = new Environment(ENV_NAME, apps);

        assertEquals(ENV_NAME, env.getName());
        assertEquals(apps, env.getApps());

    }

    @Test
    public void testGetName() {
        Environment env = new Environment(ENV_NAME);

        assertEquals(ENV_NAME, env.getName());
    }

    @Test
    public void testSetName() {
        Environment env = new Environment(ENV_NAME);

        assertEquals(ENV_NAME, env.getName());
        
        env.setName("NewName");

        assertEquals("NewName", env.getName());
    }

    @Test
    public void testAddApp() {
        Environment env = new Environment(ENV_NAME);

        assertEquals(0, env.getApps().size());
        
        env.addApp(new WindowsApp("C:\\Test.exe", "Test"));
        
        assertEquals(1, env.getApps().size());
    }

    @Test
    public void testGetApps() {
        WindowsApp a = new WindowsApp("C:\\Program Files\\HeidiSQL\\heidisql.exe", "HeidiSQL");
        WindowsApp a2 = new WindowsApp("C:\\Program Files\\Git\\git-bash.exe", "Git-Bash");

        apps = new ArrayList<>();

        apps.add(a);
        apps.add(a2);

        Environment env = new Environment(ENV_NAME, apps);

        assertEquals(apps, env.getApps());
        
        assertEquals(a, env.getApps().get(0));
    }

    @Test
    public void testSetApps() {
        Environment env = new Environment(ENV_NAME);
        
        assertEquals(empty, env.getApps());
        
        WindowsApp a = new WindowsApp("C:\\Program Files\\HeidiSQL\\heidisql.exe", "HeidiSQL");
        WindowsApp a2 = new WindowsApp("C:\\Program Files\\Git\\git-bash.exe", "Git-Bash");

        apps = new ArrayList<>();

        apps.add(a);
        apps.add(a2);
        
        env.setApps(apps);
        
        assertEquals(apps, env.getApps());
        
    }

    @Test
    public void testToString() {
        Environment env = new Environment(ENV_NAME);
        
        assertEquals(ENV_NAME, env.toString());
    }
}
