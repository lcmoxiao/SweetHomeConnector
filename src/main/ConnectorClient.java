package main;

import connector.proto.BeatMessage;
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
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class ConnectorClient {
    static final ChannelHandler[] handlers = new ChannelHandler[]{
            new ProtobufVarint32FrameDecoder(),
            new ProtobufDecoder(BeatMessage.Beat.getDefaultInstance()),
            new ProtobufVarint32LengthFieldPrepender(),
            new ProtobufEncoder(),
            new IdleStateHandler(1, 1, 0, TimeUnit.SECONDS),
            new BeatClientHandler(),
    };
    static int port = 8081;
    static String host = "192.168.0.106";

    public static void main(String[] args) {
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException ignored) {
            }
        }

        ConnectorClient.connect(port, host, handlers);
    }


    public static void connect(int port, String host, ChannelHandler[] handlers) {
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
                            sc.pipeline().addLast(handlers);
                        }
                    });
            ChannelFuture f = null;
            try {
                f = b.connect(host, port).sync();
                f.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void reconnect() {
        final ChannelHandler[] handlers = new ChannelHandler[]{
                new ProtobufVarint32FrameDecoder(),
                new ProtobufDecoder(BeatMessage.Beat.getDefaultInstance()),
                new ProtobufVarint32LengthFieldPrepender(),
                new ProtobufEncoder(),
                new IdleStateHandler(1, 1, 0, TimeUnit.SECONDS),
                new BeatClientHandler(),
        };
        connect(port, host, handlers);
    }

}
