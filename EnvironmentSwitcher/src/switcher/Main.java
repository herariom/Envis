package switcher;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        App a = new App("C:\\Program Files\\HeidiSQL\\heidisql.exe", "HeidiSQL");
        
        a.start();
        
        Thread.sleep(3500);
        
        System.out.println(a.end());
    }
}
