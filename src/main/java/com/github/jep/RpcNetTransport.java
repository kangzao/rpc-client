package com.github.jep;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/*
 * @author: enping.jep
 * @create: 2021-11-17 10:36 上午
 */
public class RpcNetTransport {

  private String host;
  private int port;

  public RpcNetTransport(String host, int port) {
    this.host = host;
    this.port = port;
  }


  public Object send(RpcRequest request) {
    Socket socket = null;
    Object result = null;
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;

    try {
      socket = new Socket(host, port); //建立连接

      outputStream = new ObjectOutputStream(socket.getOutputStream());//网络socket 网络IO
      outputStream.writeObject(request); //序列化()
      outputStream.flush();

      inputStream = new ObjectInputStream(socket.getInputStream());
      result = inputStream.readObject();


    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } finally {
      if (inputStream != null) {
        try {
          inputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (outputStream != null) {
        try {
          outputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return result;

  }
}
