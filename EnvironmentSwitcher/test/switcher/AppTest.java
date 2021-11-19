package switcher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

public class AppTest {

    final String NAME = "Eclipse";
    final String FILE_PATH = "C:\\Sample.exe";
    
    ArrayList<String> empty = new ArrayList<>();
    ArrayList<String> arguments;
    
    
    @Test
    public void testAppStringString() {
        WindowsApp a = new WindowsApp(FILE_PATH, NAME);
        
        assertEquals(FILE_PATH, a.getFilePath());
        assertEquals(NAME, a.getName());
        assertEquals(empty, a.getArguments());
    }

    @Test
    public void testAppStringStringArrayListOfString() {
        
        arguments = new ArrayList<>();
        
        arguments.add("arg 1");
        arguments.add("arg 2");

        WindowsApp a = new WindowsApp(FILE_PATH, NAME, arguments);
        
        assertEquals(FILE_PATH, a.getFilePath());
        assertEquals(NAME, a.getName());
        assertEquals(arguments, a.getArguments());
        
        WindowsApp a2 = new WindowsApp(FILE_PATH, NAME, null);
        
        assertEquals(FILE_PATH, a2.getFilePath());
        assertEquals(NAME, a2.getName());
        assertEquals(empty, a2.getArguments());
    }

    @Test
    public void testGetFilePath() {
        WindowsApp a = new WindowsApp(FILE_PATH, NAME);
        
        assertEquals(FILE_PATH, a.getFilePath());
    }

    @Test
    public void testSetFilePath() {
        WindowsApp a = new WindowsApp(FILE_PATH, NAME);
        
        assertEquals(FILE_PATH, a.getFilePath());
        
        a.setFilePath("C:\\Newexecutable.exe");
        
        assertEquals("C:\\Newexecutable.exe", a.getFilePath());
        
    }

    @Test
    public void testGetName() {
        WindowsApp a = new WindowsApp(FILE_PATH, NAME, arguments);
        
        assertEquals(NAME, a.getName());
    }

    @Test
    public void testSetName() {
        WindowsApp a = new WindowsApp(FILE_PATH, NAME, arguments);
        
        assertEquals(NAME, a.getName());
        
        a.setName("NewName");
        
        assertEquals("NewName", a.getName());
    }

    @Test
    public void testSetArguments() {
        arguments = new ArrayList<>();
        
        arguments.add("arg 1");
        arguments.add("arg 2");

        WindowsApp a = new WindowsApp(FILE_PATH, NAME);
        
        assertEquals(empty, a.getArguments());
        
        a.setArguments(arguments);
        
        assertEquals(arguments, a.getArguments());
    }

    @Test
    public void testGetArguments() {
        arguments = new ArrayList<>();
        
        arguments.add("arg 1");
        arguments.add("arg 2");

        WindowsApp a = new WindowsApp(FILE_PATH, NAME);
        
        assertEquals(empty, a.getArguments());
        
        a.setArguments(arguments);
        
        assertEquals(arguments, a.getArguments());
    }

    @Test
    public void testToString() {
        WindowsApp a = new WindowsApp(FILE_PATH, NAME);
        
        assertEquals(NAME + " - " + FILE_PATH, a.toString());
    }
    
//    @Test
//    public void testGetProcess() {
//        fail("Not yet implemented");
//    }
//    
//    @Test
//    public void testStart() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testEnd() {
//        fail("Not yet implemented");
//    }

}
