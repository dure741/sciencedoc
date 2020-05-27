package com.ucar.train.week1.dianzheng.wu.ch01;

import com.sun.org.apache.xpath.internal.operations.Bool;
import jdk.nashorn.internal.ir.IdentNode;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author wudianzheng
 * @date 2020.03.14 11:08
 */
public class Main {
    //声明Connection对象
    static Connection con;
    //驱动程序名
    static String driver = "com.mysql.jdbc.Driver";
    //URL指向要访问的数据库名mydata
    static String url = "jdbc:mysql://121.36.47.138:3306/homework";
    //MySQL配置时的用户名
    static String user = "wdz";
    //MySQL配置时的密码
    static String password = "wu19980119";

    public static void main(String[] agrs) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream("E:\\eclipse\\work\\train_group1\\A_wudianzheng\\ch01\\src\\main\\resources\\CJFD2005.txt"),"gbk");
        BufferedReader br = new BufferedReader(inputStreamReader);
        List<String> list = new ArrayList<>();
        int a;
        while((a=br.read())!=-1){
            if(a == '='){
                StringBuilder str = new StringBuilder();
                int tmp;
                while((tmp=br.read())!='<'&&tmp!=-1){
                    str.append((char) tmp);
                }
                list.add(str.toString());
            }
        }
        list.add("CJFD_ZGDX");
        try {
            //加载驱动程序
            Class.forName(driver);

            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
            //要执行的SQL语句
            String sql = "insert into t_passage values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            //2.创建statement类对象，用来执行SQL语句！！
            PreparedStatement pstmt=con.prepareStatement(sql);
            Object[] objects = list.toArray();
            for(int i = 0;i<objects.length;i++){
                if(i%65==0&&i!=0){
                    for(int j =i-65;j<i;j++){
                        System.out.println((String) objects[j]);
                        pstmt.setString((j%65)+1,(String) objects[j]);
                    }
                    int res=pstmt.executeUpdate();
                }
            }

            //3.ResultSet类，用来存放获取的结果集！！
            //当用preparedStatement时候就不需要绑定sql，因为preparedStatement已经绑定了sql
//
//            String province = null;
//            while(rs.next()){
//                //获取stuname这列数据
//                province = rs.getString("Province");
//
//                //输出结果
//                System.out.println(province);
//            }
//            rs.close();
            pstmt.close();
            con.close();
        } catch(ClassNotFoundException e) {
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        } catch(SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{

        }
    }

}
