package server.myservice.trans;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import proto.ConnectorMsg;

public class TransClient {

    static int port = 8082;
    static String host = "192.168.0.105";
    static Channel channel;

    public static void start() {
        System.out.println("启动TransClient");
        connect(port, host);
    }

    public static Channel getChannel() {
        return channel;
    }


    public static void sendMsg(Object msg) {
        System.out.println("转发" + msg + "至TransServer");
        if (TransClient.getChannel() != null) {
            TransClient.getChannel().writeAndFlush(msg);
        } else {
            System.out.println("TransServer未初始化成功，转发失败");
        }
    }

    public static void connect(int port, String host) {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) {
                            sc.pipeline().addLast(
                                    new ProtobufVarint32FrameDecoder(),
                                    new ProtobufDecoder(ConnectorMsg.cMsgInfo.getDefaultInstance()),
                                    new ProtobufVarint32LengthFieldPrepender(),
                                    new ProtobufEncoder(),
                                    new TransHandler()
                            );
                        }
                    });
            try {
                ChannelFuture f = b.connect(host, port).sync();
                channel = f.channel();
                System.out.println("成功获取TransChannel:" + channel);
                channel.closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            group.shutdownGracefully();
        }
    }


}