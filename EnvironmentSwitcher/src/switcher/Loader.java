package switcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Loader {
    public static Environment loadEnvironment(String jsonPath) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonPath);
        
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
}