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
        App a = new App(FILE_PATH, NAME);
        
        assertEquals(FILE_PATH, a.getFilePath());
        assertEquals(NAME, a.getName());
        assertEquals(empty, a.getArguments());
    }

    @Test
    public void testAppStringStringArrayListOfString() {
        
        arguments = new ArrayList<>();
        
        arguments.add("arg 1");
        arguments.add("arg 2");

        App a = new App(FILE_PATH, NAME, arguments);
        
        assertEquals(FILE_PATH, a.getFilePath());
        assertEquals(NAME, a.getName());
        assertEquals(arguments, a.getArguments());
        
        App a2 = new App(FILE_PATH, NAME, null);
        
        assertEquals(FILE_PATH, a2.getFilePath());
        assertEquals(NAME, a2.getName());
        assertEquals(empty, a2.getArguments());
    }

    @Test
    public void testGetFilePath() {
        App a = new App(FILE_PATH, NAME);
        
        assertEquals(FILE_PATH, a.getFilePath());
    }

    @Test
    public void testSetFilePath() {
        App a = new App(FILE_PATH, NAME);
        
        assertEquals(FILE_PATH, a.getFilePath());
        
        a.setFilePath("C:\\Newexecutable.exe");
        
        assertEquals("C:\\Newexecutable.exe", a.getFilePath());
        
    }

    @Test
    public void testGetName() {
        App a = new App(FILE_PATH, NAME, arguments);
        
        assertEquals(NAME, a.getName());
    }

    @Test
    public void testSetName() {
        App a = new App(FILE_PATH, NAME, arguments);
        
        assertEquals(NAME, a.getName());
        
        a.setName("NewName");
        
        assertEquals("NewName", a.getName());
    }

    @Test
    public void testSetArguments() {
        arguments = new ArrayList<>();
        
        arguments.add("arg 1");
        arguments.add("arg 2");

        App a = new App(FILE_PATH, NAME);
        
        assertEquals(empty, a.getArguments());
        
        a.setArguments(arguments);
        
        assertEquals(arguments, a.getArguments());
    }

    @Test
    public void testGetArguments() {
        arguments = new ArrayList<>();
        
        arguments.add("arg 1");
        arguments.add("arg 2");

        App a = new App(FILE_PATH, NAME);
        
        assertEquals(empty, a.getArguments());
        
        a.setArguments(arguments);
        
        assertEquals(arguments, a.getArguments());
    }

    @Test
    public void testToString() {
        App a = new App(FILE_PATH, NAME);
        
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
