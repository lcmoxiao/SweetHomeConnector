package client.function;


import client.tool.OkHttpTools;
import com.google.gson.reflect.TypeToken;
import server.myservice.pojo.User;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Friend {

    public static void main(String[] args) {
        List<User> friends = getFriends(1);
        System.out.println(friends.size());
    }
    public static List<User> getFriends(int userid) {
        return parseUsers(OkHttpTools.get("friendrelation/toUser?userid=" + userid));
    }

    public static List<User> parseUsers(String json) {
        Type type = new TypeToken<ArrayList<User>>() {
        }.getType();
        return client.tool.GsonTools.getGson().fromJson(json, type);
    }

}
