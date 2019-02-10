package socketmvc;

import javafx.concurrent.Task;

import java.io.*;
import java.net.Socket;

public class SocketClient {
    private String serverAddress;
    private int portNumber;
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private BufferedReader inputBufferedReader;
    private PrintWriter outputPrintWriter;
    private String response;

    private String userName;


    public SocketClient(String serverAddress, int portNumber) {
        this.serverAddress = serverAddress;
        this.portNumber = portNumber;
    }

    public void openSocket() throws IOException {
        this.socket = new Socket(serverAddress, portNumber);
        this.inputStream = socket.getInputStream();
        this.outputStream = socket.getOutputStream();
        inputBufferedReader = new BufferedReader(new InputStreamReader(this.inputStream));
        outputPrintWriter = new PrintWriter(this.outputStream, true);
    }

    public void startThread() {
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws IOException {
                System.out.println("Thread started");
                while (true) {
                    String msg = inputBufferedReader.readLine();
                    System.out.println(msg);
                    if (msg.startsWith("RDY")) {
                        outputPrintWriter.println(userName);
                    } else if (msg.startsWith("UID")) {

                    } else if (msg.startsWith("MSG")) {

                    }
                }

            }
        };
        new Thread(task).start();
    }

    public String readLine() throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        this.response = bufferedReader.readLine();
        bufferedReader.close();
        inputStreamReader.close();
        return this.response;
    }

    public void sendMsg(String mgs) {
//        this.outputPrintWriter.println("Test");
    }

    public String getResponse() {
        return this.response;
    }

    public void closeSocket() throws IOException {
        this.inputStream.close();
        this.outputStream.close();
        this.socket.close();
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
