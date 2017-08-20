package utils;

import java.io.*;
import java.util.List;

import static java.nio.file.Files.readAllLines;

public class InputOutputUtil {

    private final String USER_TARGET_DIRECTORY_PATH = System.getProperty("user.dir") + "\\target\\";
    private final String USER_JSON_DIRECTORY_PATH = System.getProperty("user.dir") + "\\json\\";

    public String jsonReader (String jsonFileName) {

        String prettyJsonOutput = null;

        try {
            String fileName = USER_TARGET_DIRECTORY_PATH  + jsonFileName;
            ClassLoader classLoader = InputOutputUtil.class.getClassLoader();
            File file = new File(classLoader.getResource(fileName).getFile());
            List<String> strings = readAllLines(file.toPath());
            String jsonString = String.join(" ", strings);

            prettyJsonOutput = new DataTypeConverterUtil().convertPrettyJsonOutput(jsonString);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return prettyJsonOutput;
    }

    public String bufferReader(InputStream input) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuilder builder = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return builder.toString();
    }

    private void crateFileIfNotExist(String fileName) throws IOException {

        File file = new File(USER_TARGET_DIRECTORY_PATH);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public void deleteFileIfExist(String fileName) {

        File file = new File(USER_TARGET_DIRECTORY_PATH + fileName);
        if (file.exists()) {
            file.delete();
        }
    }

    public void writeInFile (String fileName) throws Exception {

        crateFileIfNotExist(fileName);

        PrintWriter printWriter = new PrintWriter(new FileWriter(USER_TARGET_DIRECTORY_PATH));
        printWriter.println(fileName);
    }
}
