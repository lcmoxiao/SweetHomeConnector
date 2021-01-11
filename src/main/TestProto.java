package main;

import com.google.protobuf.InvalidProtocolBufferException;
import connector.proto.BeatMessage;

public class TestProto {

    public static void main(String[] args) throws InvalidProtocolBufferException {

        BeatMessage.Beat.Builder beatMessage = BeatMessage.Beat.newBuilder();
        beatMessage.setRequesttime(System.currentTimeMillis());
        beatMessage.setResponsetime(System.currentTimeMillis() + 10);
        byte[] bytes = beatMessage.build().toByteArray();


        BeatMessage.Beat beat = BeatMessage.Beat.parseFrom(bytes);


    }


}
