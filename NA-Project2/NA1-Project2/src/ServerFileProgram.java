/**
 * Created by bn4n5 on 4/30/2017.
 */
import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class ServerFileProgram {

    public static void main(String[] args) throws IOException {

        ServerSocket servSocket = new ServerSocket(1996);
        Socket sock = servSocket.accept();
        InputStream in = sock.getInputStream();

        FileOutputStream fileOutputStream = new FileOutputStream("fromClient.txt");
        byte[] buffer = new byte[64 * 1024];
        int bytesRead = 0;
        while ((bytesRead = in.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, bytesRead);
        }


        Scanner input = new Scanner(new File("fromClient.txt"));
        System.out.println("****************File received from Client****************");
        while (input.hasNextLine())
        {
            System.out.println(input.nextLine());
        }

        Files.write(Paths.get("fromClient.txt"), "\n\nThis is an added line from a server".getBytes(), StandardOpenOption.APPEND);

        ServerSocket servSocket1 = new ServerSocket(1995);

        Socket sock1 = servSocket1.accept();
        OutputStream out = sock1.getOutputStream();

        FileInputStream fileInputStream = new FileInputStream("fromClient.txt");
        int bytesRead2 = 0;
        byte[] buffer1 = new byte[64 * 1024];
        while ((bytesRead2 = fileInputStream.read(buffer1)) != -1) {
            if (bytesRead2 > 0) {
                out.write(buffer1, 0, bytesRead2);
            }
        }

        sock1.close();

    }

}