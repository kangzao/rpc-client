package com.github.jep;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/*
 * @author: enping.jep
 * @create: 2021-11-17 10:34 上午
 * 远程代理类
 */
public class RemoteInvocationHandler implements InvocationHandler {

  private String host;
  private int port;

  public RemoteInvocationHandler(String host, int port) {
    this.host = host;
    this.port = port;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    //请求会进入到这里
    System.out.println("come in");
    //请求数据的包装
    RpcRequest rpcRequest = new RpcRequest();
    rpcRequest.setClassName(method.getDeclaringClass().getName());
    rpcRequest.setMethodName(method.getName());
    rpcRequest.setParameters(args);
    //远程通信
    RpcNetTransport netTransport = new RpcNetTransport(host, port);
    Object result = netTransport.send(rpcRequest);

    return result;
  }
}
