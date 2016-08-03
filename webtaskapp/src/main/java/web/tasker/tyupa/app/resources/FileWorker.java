package web.tasker.tyupa.app.resources;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileWorker {

    private static Logger log = Logger.getLogger(FileWorker.class.getName());

    public static boolean exists(String filePath) {
            File file = new File(filePath);
            if (file.exists()) {
                log.info("File exists.");
                return true;
            } else {
                log.log(Level.WARNING, "File not found!");
                return false;
            }
    }

    public static String[] parse(String string) {
        return (string.split(" "));
    }

    public static HashMap<String, String> read(String filePath) {
        StringBuilder stringBuilder = new StringBuilder();
        if (exists(filePath)) {
            try {
                File file = new File(filePath);
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getAbsolutePath()));
                try {
                    String string;
                    while((string = bufferedReader.readLine()) != null) {
                        stringBuilder.append(string);
                        stringBuilder.append(" ");
                    }
                } catch (IOException ioException) {
                    log.log(Level.WARNING, "Error with reading line!", ioException);
                }
            } catch (FileNotFoundException fileNotFoundException) {
                log.log(Level.WARNING, "File not found", fileNotFoundException);
            }
        }
        String[] stringArray =  parse(stringBuilder.toString());
        HashMap<String,String> configHashMap = new HashMap<String, String>();
        configHashMap.put("Driver", stringArray[0]);
        configHashMap.put("Url", stringArray[1]);
        configHashMap.put("DataBase", stringArray[2]);
        configHashMap.put("User", stringArray[3]);
        configHashMap.put("Password",stringArray[4]);

        return configHashMap;
    }
}
