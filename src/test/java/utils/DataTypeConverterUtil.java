package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class DataTypeConverterUtil {

    public String convertPrettyJsonOutput(String uglyJson) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyOutput = gson.toJson(new JsonParser().parse(uglyJson));

        return prettyOutput;
    }

    public List<String> convertWebElementListToStringList(List<WebElement> webElementList) {

        final List<String> stringList = new ArrayList<>();

        for (WebElement element: webElementList) {
            stringList.add(element.getText().trim());
        }

        // remove from the list all empty/verification values
        while(stringList.remove("Yes"));
        while(stringList.remove("No"));
        while(stringList.remove(" "));
        while(stringList.remove(""));

        return stringList;
    }

    public List<String> convertTrimStringList(List<String> dirtyList) {

        final List<String> stringList = new ArrayList<>();

        for (String element: dirtyList) {
            stringList.add(element.trim());
        }

        return stringList;
    }
}