import java.io.*;
import java.net.*;

class ClientProgram
{
    public static void main(String argv[]) throws Exception
    {
        String fClient;
        String tServer;
        Socket clientSocket = new Socket("192.168.235.1", 1994);
        BufferedReader inFromUser =new BufferedReader(new InputStreamReader(System.in));
        PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(),true);
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        while (true)
        {
            tServer = inFromUser.readLine();
            outToServer.println(tServer);
            if (tServer.equals("Bye from Client-Sibi"))
            {
                outToServer.println (tServer) ;
                clientSocket.close();
                System.out.println("Client is Closing!");
                break;
            }
            fClient = inFromServer.readLine();
            System.out.println(fClient);

            fClient = inFromServer.readLine();
            System.out.println("Client Received: "+fClient);
            outToServer.println("Echo Message from Client: "+fClient);

        }
    }
}
