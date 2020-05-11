package Json.HasMapList;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;

/* 
Strange mistake
{"id":1,"name":"first","additionalMap":{"KEY#1":"VALUE#1","KEY#3":"VALUE#3","KEY#2":"VALUE#2"}}
List    	@JsonDeserialize(contentAs = ValueTypeImpl.class)
Map	        @JsonDeserialize(keyAs = KeyTypeImpl.class)
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter stringWriter = new StringWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        String sampleJsonString = "{\"id\":1,\"name\":\"first\",\"KEY#1\":\"VALUE#1\",\"KEY#3\":\"VALUE#3\",\"KEY#2\":\"VALUE#2\"}";
        RealBean realBean = objectMapper.readValue(sampleJsonString, RealBean.class);

        objectMapper.writeValue(stringWriter, realBean);
        System.out.println(stringWriter.toString());
    }
}