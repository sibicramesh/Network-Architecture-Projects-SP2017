import java.io.{BufferedReader, InputStreamReader, PrintStream}
import java.net.ServerSocket
import scala.util.control.Breaks._
object ServerProgram {
  def main(args: Array[String]): Unit = {
    val server = new ServerSocket(1994)
    val client = server.accept
    val in = new BufferedReader(new InputStreamReader(client.getInputStream()))
    val out = new PrintStream(client.getOutputStream)
    breakable {
     while (true) {
       var fClient = in.readLine
       println("Server received: " + fClient)
       if (fClient.equals("Hello from Client-Sibi")){
         out.println("Hello from Server-Sibi")
       }
       else {
         out.println("Echo Message from Server: "+fClient)
       }
       val tClient = readLine()
       out.println(tClient)
       if (tClient.equals("Bye from Server-Sibi")) {
         client.close;
         server.close;
         println("Server is closing!")
         break
       }
       fClient=in.readLine()
       println(fClient)
     }
   }
  }
}
