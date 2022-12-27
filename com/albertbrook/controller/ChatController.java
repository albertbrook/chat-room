package com.albertbrook.controller;

import com.albertbrook.settings.Settings;
import com.albertbrook.utils.MyConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

public class ChatController {
    public int save(String username, String chat) {
        String sql = "INSERT INTO chat VALUES(?, ?, ?)";

        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(Settings.SIMPLE_DATE_FORMAT.format(Calendar.getInstance().getTime()));
        arrayList.add(username);
        arrayList.add(chat);

        return MyConnection.update(sql, arrayList);
    }

    public boolean say(String username) {
        String sql = "SELECT * FROM chat WHERE username=?";

        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(username);

        ResultSet resultSet = MyConnection.query(sql, arrayList);
        Calendar lastTime = Calendar.getInstance(), now = Calendar.getInstance();
        int sayCount = 0;
        boolean canSay = false;
        try {
            while (resultSet.next()) {
                try {
                    lastTime.setTime(Settings.SIMPLE_DATE_FORMAT.parse(resultSet.getString("time")));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (now.getTimeInMillis() - lastTime.getTimeInMillis() <= 5000)
                    sayCount++;
            }
            canSay = sayCount <= 2;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return canSay;
    }
}
