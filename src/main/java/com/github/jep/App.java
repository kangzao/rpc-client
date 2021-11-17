package com.github.jep;

/**
 * Hello world!
 */
public class App {

  public static void main(String[] args) {
    RpcProxyClient rpcProxyClient = new RpcProxyClient();

    IHelloService iHelloService = rpcProxyClient.clientProxy
        (IHelloService.class, "localhost", 8080);

    String result = iHelloService.sayHello("Jep");
    System.out.println(result);
  }
}
