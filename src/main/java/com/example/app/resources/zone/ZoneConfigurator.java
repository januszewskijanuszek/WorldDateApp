package com.example.app.resources.zone;

import com.example.app.resources.network.WebsiteOutputInterpreter;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ZoneConfigurator {
    private final File file;
    private final URL apiUrl;

    public ZoneConfigurator(File file) throws IOException {
        this.file = file;
        System.out.println("Config File exist: " + file.exists());
        if(file.exists()){
            this.apiUrl = new URL("https://worldtimeapi.org/api/timezone/" + extractConfigFile());
            System.out.println("Try connect to: " + apiUrl);
        } else {
            this.apiUrl = null;
            System.out.println("Failed to find Config file! - Creating new one");
            file.createNewFile();
        }
    }

    private String extractConfigFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        return scanner.nextLine();
    }

    private String establishConnection() throws IOException {
        BufferedReader reader;
        StringBuilder responseContent = new StringBuilder();
        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
        System.out.println(connection);

        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        int statusCode = connection.getResponseCode();
        System.out.println("Connection status with " + apiUrl + " : " + statusCode);
        if(statusCode <= 299){
            String line;
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while((line = reader.readLine()) != null){
                responseContent.append(line);
            }
            reader.close();
        } else {
            System.out.println("Connection failed!");
        }
        return responseContent.toString();
    }
    public ZoneDate getZoneDate() throws IOException {
        return new WebsiteOutputInterpreter(establishConnection()).getZoneDateInformation();
    }
}
