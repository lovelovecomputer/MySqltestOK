package com.jisuanjixi.wen.mysqltest;

import android.net.sip.SipSession;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    java.sql.Connection cn = DriverManager.getConnection
                            ("jdbc:mysql://192.168.1.104:3306/librarydb","phill","123456");
                    String sql = "select B_Name from book";
                    Statement st = (Statement)cn.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    while (rs.next()){
                        String mybook = rs.getString("B_Name");
                        Log.i("MainActivity",mybook);
                    }
                    cn.close();
                    st.close();
                    rs.close();
                }catch (ClassNotFoundException e){
                    e.printStackTrace();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
