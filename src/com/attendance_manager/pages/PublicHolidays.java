package com.attendance_manager.pages;


import org.json.JSONArray;
import org.json.JSONObject;
import javax.swing.*;
import java.awt.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public  class PublicHolidays extends JFrame {
    private static HttpURLConnection connection;
    String return_string="";
    GridBagConstraints gbc = new GridBagConstraints();
    public PublicHolidays() {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://holidayapi" +
                ".com/v1/holidays?pretty&country=IN&year=2020&key=60f5cc91-b484-44a2-a01f-af5d4571c758")).build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).thenAccept(Parser::parse).join();

        ///gbc.insets = new Insets(0,0,0,0);


        //OUTER FRAME
        setIconImage(new ImageIcon("src/resources/images/logo_1.png").getImage());
        setTitle("Attendance Manager");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setContentPane(new JLabel(new ImageIcon("src/resources/images/background.jpg")));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }



}



