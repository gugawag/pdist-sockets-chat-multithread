import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws Exception {
        System.out.println("== Servidor ==");
        ServerSocket serverSocket = new ServerSocket(6000);

        while (true) {
            Socket socket = serverSocket.accept();
            Thread thread = new Thread(() -> {
                try {
                    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                    DataInputStream dis = new DataInputStream(socket.getInputStream());

                    while (true) {
                        String mensagemRecebida = dis.readUTF();
                        System.out.println(socket.getInetAddress()
                                + ":" + socket.getPort()
                                + "-" + mensagemRecebida);
                        Scanner teclado = new Scanner(System.in);
                        dos.writeUTF(teclado.nextLine());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }
    }
}
