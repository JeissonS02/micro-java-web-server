package co.edu.escuelaing.reflexionlab.framework;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;

public class StaticFileHandler {

    public static byte[] getFile(String path) {

        try {

            String filePath = "src/main/resources" + path;

            File file = new File(filePath);

            if(file.exists()){

                return Files.readAllBytes(file.toPath());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getContentType(String path){

        if(path.endsWith(".html")){
            return "text/html";
        }

        if(path.endsWith(".png")){
            return "image/png";
        }

        return "text/plain";
    }
}