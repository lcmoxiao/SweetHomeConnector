package server.myservice.userstat;

import com.google.gson.Gson;
import server.myservice.pojo.UserState;
import server.myservice.pojo.UserStateInfo;

import java.io.IOException;

import static server.tools.HttpRequestTools.*;

public class UserStateService {

    public static UserStateService instance = new UserStateService();


    public static void main(String[] args) {
        System.out.println(instance.isOnline(1));
    }

    public boolean isOnline(Integer userid) {
        String resp = null;
        try {
            resp = get("http://localhost/userstate?userid=" + userid);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resp != null && resp.length() != 0;
    }

    public void online(Integer userid, UserState userState) {
        String resp = null;
        try {
            UserStateInfo userStateInfo = new UserStateInfo();
            userStateInfo.setUserState(userState);
            userStateInfo.setUserid(userid);
            Gson gson = new Gson();
            resp = post("http://localhost/userstate", gson.toJson(userStateInfo));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(resp);
    }

    public void offline(Integer userid) {
        String resp = null;
        try {
            Gson gson = new Gson();
            resp = delete("http://localhost/userstate", gson.toJson(userid));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(resp);
    }


}
