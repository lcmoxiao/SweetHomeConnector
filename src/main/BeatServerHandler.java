package main;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

@ChannelHandler.Sharable
public class BeatServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        connector.proto.BeatMessage.Beat beatMessage = (connector.proto.BeatMessage.Beat) msg;
        connector.proto.BeatMessage.Beat.Builder builder = beatMessage.toBuilder().setResponsetime(System.currentTimeMillis());

        connector.proto.BeatMessage.Beat beat = builder.build();
        System.out.println(beat);
//        ctx.writeAndFlush(beat);
    }


}
