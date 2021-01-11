package main;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;

@ChannelHandler.Sharable
public class BeatClientHandler extends ChannelHandlerAdapter {

    private boolean isFirst = true;
    private int readIdleTimes = 0;
    private int writeIdleTimes = 0;
    private int reconnectTimes = 0;

    private static connector.proto.BeatMessage.Beat generateBeatMessage() {
        connector.proto.BeatMessage.Beat.Builder beatMessage = connector.proto.BeatMessage.Beat.newBuilder();
        beatMessage.setRequesttime(System.currentTimeMillis());
        beatMessage.setResponsetime(0);
        return beatMessage.build();
    }

    private void resetIdleTimes() {
        readIdleTimes = 0;
        writeIdleTimes = 0;
        reconnectTimes = 0;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        if (isFirst) {
            System.out.println("channelActive First");
            ctx.writeAndFlush(generateBeatMessage());
            isFirst = false;
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        IdleStateEvent event = (IdleStateEvent) evt;

        String eventType = null;
        switch (event.state()) {
            case READER_IDLE:
                eventType = "读空闲第" + ++readIdleTimes + "次";
                break;
            case WRITER_IDLE:
                eventType = "写空闲第" + ++writeIdleTimes + "次";
                break;
            case ALL_IDLE:
                eventType = "读写空闲";
                // 不处理
                break;
        }
        System.out.println(ctx.channel().remoteAddress() + "超时事件：" + eventType);
        if (Math.max(readIdleTimes, writeIdleTimes) > 3) {
            System.out.println("超过9s未有读写，尝试确认连接,第" + reconnectTimes++ + "次");
            ctx.channel().writeAndFlush(generateBeatMessage());
        }
        if (Math.max(readIdleTimes, writeIdleTimes) > 6) {
            System.out.println("三次重连失败，断开连接,尝试重连");
            ConnectorClient.reconnect();

            ctx.channel().close();
         }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        resetIdleTimes();
        System.out.println(msg);
    }

}
