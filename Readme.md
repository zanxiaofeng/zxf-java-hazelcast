# Key classes for Hazelcast entry
- com.hazelcast.client.HazelcastClient
- com.hazelcast.core.HazelcastInstance
- com.hazelcast.map.IMap
- com.hazelcast.client.properties.ClientProperty
- com.hazelcast.client.config.ClientConfig;
- com.hazelcast.client.config.ClientNetworkConfig;
- com.hazelcast.config.SSLConfig
- com.hazelcast.config.SerializationConfig
- com.hazelcast.config.GlobalSerializerConfig

# Key classes for Hazelcast Serialization
- com.hazelcast.nio.serialization.DataSerializable
- com.hazelcast.nio.serialization.IdentifiedDataSerializable
- com.hazelcast.nio.serialization.DataSerializableFactory
- com.hazelcast.nio.serialization.Portable
- com.hazelcast.nio.serialization.PortableFactory
- com.hazelcast.nio.serialization.ByteArraySerializer
- com.hazelcast.nio.serialization.StreamSerializer
- java.io.Externalizable
- java.io.Serializable
- com.hazelcast.internal.serialization.Data[interface]
- com.hazelcast.internal.serialization.SerializationService
- com.hazelcast.internal.serialization.impl.HeapData
- com.hazelcast.internal.nio.Packet

# Key classes for Hazelcast Web
- com.hazelcast.web.WebFilter
- com.hazelcast.web.WebFilterConfig
- com.hazelcast.web.HazelcastHttpSession
- com.hazelcast.web.ClusteredSessionService
- com.hazelcast.web.SessionState
- com.hazelcast.web.SessionListener
- com.hazelcast.web.entryprocessor.GetAttributeEntryProcessor
- com.hazelcast.web.entryprocessor.SessionUpdateEntryProcessor;
- com.hazelcast.web.WebDataSerializerHook

# Key classes for Communication
- com.hazelcast.client.impl.connection.tcp.HeartbeatManager
- com.hazelcast.client.impl.connection.tcp.TcpClientConnectionManager
- void com.hazelcast.client.impl.connection.tcp.TcpClientConnection.handleClientMessage(ClientMessage message)
- com.hazelcast.client.impl.protocol.ClientMessage
- com.hazelcast.client.impl.protocol.ClientMessageReader
- com.hazelcast.client.impl.protocol.ClientMessageWriter
- com.hazelcast.client.impl.protocol.ClientProtocolErrorCodes
- com.hazelcast.client.impl.protocol.DefaultMessageTaskFactoryProvider
- com.hazelcast.spi.impl.operationservice.BlockingOperation
- com.hazelcast.spi.impl.operationservice.ReadonlyOperation
- com.hazelcast.map.impl.operation.MapOperation
- com.hazelcast.map.impl.operation.GetOperation
- com.hazelcast.scheduledexecutor.impl.operations.GetStatisticsOperation
- com.hazelcast.client.impl.spi.impl.ClientInvocation
- void com.hazelcast.client.impl.spi.impl.ClientInvocation.notify(ClientMessage message)
- void com.hazelcast.client.impl.spi.impl.ClientInvocation.complete(Object response)
- void com.hazelcast.client.impl.spi.impl.ClientInvocation.notifyException(long correlationId, Throwable exception)
- void com.hazelcast.client.impl.spi.impl.ClientInvocation.completeExceptionally(Throwable t)
- com.hazelcast.client.impl.spi.impl.ClientInvocationFuture
- void com.hazelcast.client.impl.spi.impl.ClientResponseHandlerSupplier.handleResponse(ClientMessage message)
- Throwable com.hazelcast.client.impl.protocol.ClientExceptionFactory.createException(ClientMessage message)
- com.hazelcast.client.impl.protocol.util.ClientMessageDecoder;
- com.hazelcast.client.impl.protocol.util.ClientMessageEncoder;
- com.hazelcast.internal.networking.Channel;
- com.hazelcast.internal.networking.ChannelInitializer;
- com.hazelcast.client.impl.connection.tcp.ClientPlainChannelInitializer

# 异常 - 服务器端异常会在客户端还原
## APP Thread
- com.hazelcast.client.impl.proxy.ClientMapProxy.executeOnKey(ClientMapProxy.java:1357)
- com.hazelcast.client.impl.proxy.ClientMapProxy.executeOnKeyInternal(ClientMapProxy.java:1365)
- com.hazelcast.client.impl.spi.ClientProxy.invoke(ClientProxy.java:182)
- com.hazelcast.client.impl.spi.ClientProxy.invokeOnPartition(ClientProxy.java:188)
- V com.hazelcast.spi.impl.AbstractInvocationFuture.get()
- ClientMessage com.hazelcast.client.impl.spi.impl.ClientInvocationFuture.resolveAndThrowIfExceptionn(Object response)
- static <T> T com.hazelcast.spi.impl.operationservice.impl.InvocationFuture.returnOrThrowWithGetConventions(Object response)
- <T extends Throwable> T com.hazelcast.internal.util.ExceptionUtil.cloneExceptionWithFixedAsyncStackTrace(T original)
## IO Thread
- void com.hazelcast.client.impl.spi.impl.ClientResponseHandlerSupplier.handleResponse(ClientMessage message)
- Throwable com.hazelcast.client.impl.protocol.ClientExceptionFactory.createException(ClientMessage message)
- void com.hazelcast.client.impl.spi.impl.ClientInvocation.notifyException(long correlationId, Throwable exception)
- void com.hazelcast.client.impl.spi.impl.ClientInvocation.completeExceptionally(Throwable t)
