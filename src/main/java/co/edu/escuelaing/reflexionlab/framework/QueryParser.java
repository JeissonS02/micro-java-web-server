package co.edu.escuelaing.reflexionlab.framework;

import java.util.HashMap;

public class QueryParser {

    public static HashMap<String,String> parse(String query){

        HashMap<String,String> params = new HashMap<>();

        if(query == null || query.isEmpty()){
            return params;
        }

        String[] pairs = query.split("&");

        for(String p : pairs){

            String[] keyValue = p.split("=");

            if(keyValue.length == 2){

                params.put(keyValue[0], keyValue[1]);

            }
        }

        return params;
    }
}