package switcher;

import static org.junit.Assert.*;

import org.junit.Test;

public class EnvironmentManagerTest {

    @Test
    public void testGetInstance() {
        assertNotNull(EnvironmentManager.getInstance());
    }

    @Test
    public void testGetEnvironments() {
        EnvironmentManager n = EnvironmentManager.getInstance();
        
        assertEquals(0, n.getEnvironments().size());
    }

    @Test
    public void testGetEnvironment() {
        EnvironmentManager n = EnvironmentManager.getInstance();

        n.addEnvironment("Test");
        
        assertEquals("Test", n.getEnvironment("Test").getName());
        
        n.clearEnvironments();
    }
    
    @Test
    public void testAddEnvironmentString() {
        EnvironmentManager n = EnvironmentManager.getInstance();
        
        assertEquals(0, n.getEnvironments().size());
        
        n.addEnvironment("Test");
        
        assertEquals(1, n.getEnvironments().size());
        
        n.clearEnvironments();
    }

    @Test
    public void testAddEnvironmentStringArrayListOfApp() {
        EnvironmentManager n = EnvironmentManager.getInstance();
        
        assertEquals(0, n.getEnvironments().size());
        
        n.addEnvironment("Test", OperatingSystem.WINDOWS, null);
        
        assertEquals(1, n.getEnvironments().size());
        
        n.clearEnvironments();
    }

}
