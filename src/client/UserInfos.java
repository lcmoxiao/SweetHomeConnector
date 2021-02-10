package client;

import server.myservice.pojo.MatchRelation;
import server.myservice.pojo.User;

import java.util.List;

public class UserInfos {

    public static User user;
    public static List<MatchRelation> matchRelation;
    public static UsingState usingState = UsingState.SINGLE_MATCH;

    public static enum UsingState {
        SINGLE_MATCH,
        GROUP_MATCH
    }
}
