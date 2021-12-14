package switcher;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Loader {
    public static Environment loadEnvironment(String uri) throws JsonParseException, JsonMappingException, IOException {
        String json = loadFileAsString(uri);
        
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(json);
        
        JsonNode appsNode = rootNode.path("apps");
        Iterator<JsonNode> iterator = appsNode.elements();

        JsonNode envNameNode = rootNode.path("name");
        String envName = envNameNode.textValue();
        
        JsonNode osNode = rootNode.path("operatingSystem");
        OperatingSystem operatingSystem = OperatingSystem.valueOf(osNode.textValue());
        
        Environment env = new Environment(envName, operatingSystem);
        
        while (iterator.hasNext()) {
            JsonNode appNode = iterator.next();
            
            JsonNode filePathNode = appNode.path("filePath");
            String filePath = filePathNode.textValue();

            JsonNode nameNode = appNode.path("name");
            String name = nameNode.textValue();

            JsonNode argumentsNode = appNode.path("arguments");
            Iterator<JsonNode> appItr = argumentsNode.elements();

            ArrayList<String> arguments = new ArrayList<String>();

            while (appItr.hasNext()) {
                JsonNode argument = appItr.next();
                arguments.add(argument.textValue());
            }
            
            switch(operatingSystem) {
            case WINDOWS:
                env.addApp(new WindowsApp(filePath, name, arguments));
                break;
            case MAC:
                System.out.println("Unsupported");
                break;
            case LINUX:
                System.out.println("Unsupported");
                break;
            }
        }
        
        
        return env;
    }
    
    public static void saveEnvironment(Environment environment) {
        try {
            
            Map<String, Object> jsonNodes = new HashMap<>();

            jsonNodes.put("name", "Test Environment");
            jsonNodes.put("operatingSystem", "WINDOWS");
            
            List<HashMap<String, Object>> appList = new ArrayList<HashMap<String, Object>>();

            for (int i = 0; i < environment.getApps().size(); i++) {
                HashMap<String, Object> app = new HashMap<>();
                app.put("filePath", environment.getApps().get(i).getFilePath());
                app.put("name", environment.getApps().get(i).getName());
                app.put("arguments", environment.getApps().get(i).getArguments());
                
                appList.add(app);
            }
            
            jsonNodes.put("apps", appList);
            
            ObjectMapper mapper = new ObjectMapper();

            // convert map to JSON file
            mapper.writeValue(Paths.get("res/" + UUID.randomUUID().toString().replace("-", "") + ".json").toFile(), jsonNodes);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static String loadFileAsString(String uri) throws IOException {
        return Files.readString(Paths.get(uri), StandardCharsets.US_ASCII);
    }
}