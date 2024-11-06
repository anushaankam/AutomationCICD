package anushaankam.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException
	{
		
		//Use FileUtilis to scan the json
		String jsonData = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//anushaankam//data//PurchaseOrder.json"),
				StandardCharsets.UTF_8);
		
		//jsonContent to HashMap need Jackson Databind dependency in pom.xml
		// To Convert create a class belongs to Jackson  DataBind dependency
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonData, new TypeReference<List<HashMap<String,String>>>() {
		});
		return data;
		}
	}
