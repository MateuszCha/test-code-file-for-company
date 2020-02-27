import java.io.*;
import java.util.*;

public class ProperitLoadReadHM {
    public static Map<String, String> properties = new HashMap<>();

    public void fillInPropertiesMap() throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        InputStream inputStream = new FileInputStream(fileName);
        reader.close();
        this.load(inputStream);
        inputStream.close();
    }

    public void save(OutputStream outputStream) throws Exception {
        Properties pop = new Properties();
        if(ProperitLoadReadHM.properties == null){
            outputStream.write("no".getBytes());
            outputStream.flush();
            return;
        }
        for(Map.Entry<String,String> pair: ProperitLoadReadHM.properties.entrySet()) {
            pop.put(pair.getKey(),pair.getValue());
        }
        pop.store(outputStream,"");

    }

    public void load(InputStream inputStream) throws Exception {
        BufferedReader fileIn = new BufferedReader(new InputStreamReader(inputStream));
        Properties prop = new Properties();
        prop.load(fileIn);
        for(Map.Entry<Object,Object> propki : prop.entrySet())
        {
            if(propki.getKey().equals("no")){
                ProperitLoadReadHM.properties = null;
                break;
            }
            ProperitLoadReadHM.properties.put(propki.getKey().toString(),propki.getValue().toString());
        }
    }

    public static void main(String[] args) throws Exception {
        ProperitLoadReadHM sol = new ProperitLoadReadHM();
        sol.fillInPropertiesMap();
    }
}
