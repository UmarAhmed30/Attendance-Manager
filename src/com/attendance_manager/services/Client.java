package com.attendance_manager.services;

import java.net.*;
import java.io.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import javax.imageio.ImageIO;

public class Client {

    public Client() {

    }

    public void sendRequest(String file_path) throws Exception{

        Socket soc;
        BufferedImage img = null;
        soc=new Socket("localhost",4000);
        System.out.println("Client is running. ");

        try {
            System.out.println("Reading image from disk. ");
            img = ImageIO.read(new File(file_path));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            ImageIO.write(img, "jpeg", baos);
            baos.flush();

            byte[] bytes = baos.toByteArray();
            baos.close();

            System.out.println("Sending image to server. ");

            InputStream in = soc.getInputStream();
            OutputStream out = soc.getOutputStream();
            DataOutputStream dos = new DataOutputStream(out);

            dos.writeInt(bytes.length);
            dos.write(bytes, 0, bytes.length);

            System.out.println("Image sent to server. ");


//            try {
//
//            } catch (Exception e) {
//                System.out.println(e);
//                return null;
//            }
//            finally {
//                dos.close();
//                out.close();
//            }

            dos.close();
            out.close();

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            soc.close();
        }
        soc.close();
    }
}
