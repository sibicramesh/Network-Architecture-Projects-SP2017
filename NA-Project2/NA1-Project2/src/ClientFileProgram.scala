import java.io.{File, FileInputStream, FileOutputStream}
import java.net.Socket
import java.util.Scanner
import scala.util.control.Breaks._


object ClientFileProgram {
  def main(args: Array[String]): Unit = {
    val sock: Socket = new Socket("192.168.235.1", 1996)
    val out= sock.getOutputStream
    //send file
    val fileInputStream: FileInputStream = new FileInputStream("input.txt")
    val buffer: Array[Byte] = new Array[Byte](64 * 1024)
    var bytesRead: Int = 0
    breakable {
      while ((bytesRead = fileInputStream.read(buffer)) != -1) {
        if (bytesRead > 0) {
          out.write(buffer, 0, bytesRead)
        }
        break
      }
    }

    sock.close()

    val sock1: Socket = new Socket("192.168.235.1", 1995)

    val in = sock1.getInputStream

    val fileOutputStream: FileOutputStream = new FileOutputStream("fromServer.txt")
    var bytesRead2: Int = 0
    val buffer1: Array[Byte] = new Array[Byte](64 * 1024)
    breakable {
      while ((bytesRead2 = in.read(buffer1)) != -1) {
        fileOutputStream.write(buffer1, 0, bytesRead2)
        break
      }
    }

    val input: Scanner = new Scanner(new File("fromServer.txt"))
    System.out.println("****************File received from Server****************")
    while (input.hasNextLine) System.out.println(input.nextLine)

  }
}
