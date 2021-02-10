package server.myservice.match;

import proto.ConnectorMsg;

public class MatchTools {


//    public static void main(String[] args) {
//        int a = 65537;
//        int e = a >> 16;
//        int f = a & 0x0000FFFF;
//        System.out.println(e + "," + f);
//    }

    /*
        //加密
        int b = 8;
        int c = 10;
        int d = (b << 16) | c;
        //结构
        int e = d >> 16;
        int f = d & 0x0000FFFF;
        System.out.println(e + "," + f);
     */
    public static boolean isAgeMatch(ConnectorMsg.FindMatch findMatch1, ConnectorMsg.FindMatch findMatch2) {
        int age1 = findMatch1.getUserInfo().getAge();
        int age2 = findMatch2.getUserInfo().getAge();
        int dstAge1 = findMatch1.getDstAgeRange();
        int dstAge2 = findMatch2.getDstAgeRange();


        //判断目标年龄是否匹配
        boolean b = (age1 >= (dstAge2 >> 16) && age1 <= (dstAge2 & 0x0000FFFF))
                &&
                (age2 >= (dstAge1 >> 16) && age2 <= (dstAge1 & 0x0000FFFF));
        System.out.println("Age match:" + b);
        return b;
    }

    public static boolean isSexMatch(ConnectorMsg.FindMatch findMatch1, ConnectorMsg.FindMatch findMatch2) {
        int sex1 = findMatch1.getUserInfo().getSex();
        int sex2 = findMatch2.getUserInfo().getSex();
        int dstSex1 = findMatch1.getDstSex();
        int dstSex2 = findMatch2.getDstSex();
        boolean b;
        //判断目标性别是否匹配
        if (dstSex1 == 2 && dstSex2 == 2) {
            b = true;
        } else if (dstSex1 == 2) {
            b = dstSex2 == sex1;
        } else if (dstSex2 == 2) {
            b = dstSex1 == sex2;
        } else {
            b = dstSex1 == sex2 && dstSex2 == sex1;
        }
        System.out.println("Sex match:" + b);
        return b;
    }

    public static boolean isHobbyMatch(ConnectorMsg.FindMatch findMatch1, ConnectorMsg.FindMatch findMatch2) {
        int hobby1 = findMatch1.getDstHobby();
        int hobby2 = findMatch2.getDstHobby();
        //判断目标兴趣是否匹配
        boolean b = hobby1 == hobby2;
        System.out.println("Hobby match:" + b);
        return b;
    }


}
