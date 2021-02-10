package client;

import client.function.Login;
import client.function.Match;
import client.tool.MsgGenerateTools;
import server.myservice.pojo.User;

import java.util.Scanner;

public class MainTest {
    static int port = 8081;
    static String host = "192.168.0.105";

    public static void main(String[] args) {
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException ignored) {

            }
        }
        new Thread(ConnectorClient::connect).start();

        System.out.println("输入邮箱和密码进行登录");
        Scanner sc = new Scanner(System.in);
        String mail = sc.next();
        String pwd = sc.next();
        System.out.println("登录中，请稍等");
        User user = Login.login(mail, pwd);
        if (user == null) {
            System.out.println("登录失败");
            return;
        }
        System.out.println("登陆成功" + user);
        UserInfos.user = user;

        ConnectorClient.getChannel().write(MsgGenerateTools.generateConnectMessage(user.getUserid()));

        while (true) {
            int i = sc.nextInt();
            switch (i) {
                case -1: {
                    System.exit(1);
                }
                case 0: {
                    match(sc);
                    break;
                }
                case 1: {
                    System.out.println(UserInfos.matchRelation);
                }
            }

        }
    }

    private static void match(Scanner sc) {
        User user = UserInfos.user;
        System.out.println("请输入目标的最小年龄和最大年龄，以及性别，以及兴趣");
//        int minAge = sc.nextInt();
//        int maxAge = sc.nextInt();
//        int dstSex = sc.nextInt();
//        int dstHobby = sc.nextInt();
        int minAge = 1;
        int maxAge = 100;
        int dstSex = 0;
        int dstHobby = 0;
        Match.singleMatch(user, minAge, maxAge, dstSex, dstHobby);
        System.out.println("匹配中,请等待");
    }


}
