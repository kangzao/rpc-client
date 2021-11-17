package com.github.jep;

import java.lang.reflect.Proxy;

/*
 * @author: enping.jep
 * @create: 2021-11-17 10:35 上午
 */
public class RpcProxyClient {

  public <T> T clientProxy(final Class<T> interfaceCls, final String host, final int port) {

    return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(),
        new Class<?>[]{interfaceCls}, new RemoteInvocationHandler(host, port));
  }

}
