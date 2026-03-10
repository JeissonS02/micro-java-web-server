package co.edu.escuelaing.reflexionlab;

import co.edu.escuelaing.reflexionlab.annotations.QueryParam;
import co.edu.escuelaing.reflexionlab.framework.ControllerScanner;
import co.edu.escuelaing.reflexionlab.framework.QueryParser;
import co.edu.escuelaing.reflexionlab.framework.RouteTable;
import co.edu.escuelaing.reflexionlab.framework.StaticFileHandler;

import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class MicroServer {

    private static Object controllerInstance;

    public static void main(String[] args) throws Exception {

        // Escanea automáticamente los controllers
        controllerInstance = ControllerScanner.scan(
                "co.edu.escuelaing.reflexionlab.demo");

        startServer();
    }

    private static void startServer() throws Exception {

        ServerSocket server = new ServerSocket(35000);

        System.out.println("Server running at http://localhost:35000");

        while (true) {

            Socket client = server.accept();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));

            OutputStream out = client.getOutputStream();

            String request = in.readLine();

            if (request == null) {
                client.close();
                continue;
            }

            System.out.println("Request: " + request);

            String fullPath = request.split(" ")[1];

            String path;
            String query = null;

            if (fullPath.contains("?")) {
                path = fullPath.split("\\?")[0];
                query = fullPath.split("\\?")[1];
            } else {
                path = fullPath;
            }

            System.out.println("Path: " + path);

            HashMap<String, String> queryParams = QueryParser.parse(query);

            RouteTable.Route route = RouteTable.get(path);

            String response;

            if (route != null) {

                Method m = route.method;

                Object[] args = buildArguments(m, queryParams);

                Object result = m.invoke(route.instance, args);

                response = "HTTP/1.1 200 OK\r\n" +
                        "Content-Type: text/html\r\n\r\n" +
                        result;

                out.write(response.getBytes());

            } else {

                byte[] file = StaticFileHandler.getFile(path);

                if (file != null) {

                    String header = "HTTP/1.1 200 OK\r\n" +
                            "Content-Type: " + StaticFileHandler.getContentType(path) + "\r\n\r\n";

                    out.write(header.getBytes());
                    out.write(file);

                } else {

                    response = "HTTP/1.1 404 Not Found\r\n" +
                            "Content-Type: text/html\r\n\r\n" +
                            "404 Not Found";

                    out.write(response.getBytes());
                }

            }
            

            out.close();
            client.close();
        
        }
    }

    private static Object[] buildArguments(Method method,
            HashMap<String, String> queryParams) {

        Parameter[] params = method.getParameters();

        Object[] args = new Object[params.length];

        for (int i = 0; i < params.length; i++) {

            if (params[i].isAnnotationPresent(QueryParam.class)) {

                QueryParam qp = params[i].getAnnotation(QueryParam.class);

                String value = queryParams.get(qp.value());

                if (value == null) {
                    value = qp.defaultValue();
                }

                args[i] = value;
            }
        }

        return args;
    }
}