import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created on 2017/6/1.
 * 监听80端口，接收来自Android手机端 （Client端）的数据包，计数、计时，后关闭。
 */
public class Server {
    //测试时手机客户端发送的数据内容为"hello world"
    private static final String compared = "hello world";

    public static void main(String[] args)throws IOException{
        //创建一个ServerSocket，用于监听客户端的Socket请求。
        int count = 0;
        long startTime = System.currentTimeMillis();
        //指定监听端口:80
        ServerSocket serverSocket = new ServerSocket(80);
//        serverSocket.setReceiveBufferSize(11);
        while (true){
            Socket socket = serverSocket.accept();

            //接收输入流并输出
            InputStream inputStream = socket.getInputStream();
            //转换流，InputStreamReader方法将从socket接口中读到的 字节流 转换为 字符流 ,并包装成BufferedReader
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            //BufferedReader的readLine()方法可以一次读取一行内容
            String line = reader.readLine();
            System.out.println("数据包内容为:"+line);

            //数据包计数、计时
            count ++;
            long endTime = System.currentTimeMillis();
            long countTime = endTime - startTime;
            //System.out.println("第"+ count +"个包到达，当前耗时"+countTime);

            /**
             * 以下代码用于测试只能连包发送的一款Android客户端测试软件，因此做人工间隔计数;
             * 测试内容为"hello world",因此先简单以空格作为计数标志进行接收数据包的测试。
             */
            int blockCount = line.split(" ").length;
            System.out.println("接收包数量为:"+ (blockCount-1));
//          socket.close();
        }

    }
}
