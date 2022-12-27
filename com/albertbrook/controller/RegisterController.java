package com.albertbrook.controller;

import com.albertbrook.utils.MyConnection;

import java.sql.SQLException;
import java.util.ArrayList;

public class RegisterController {
    public boolean check(String username) {
        String sql = "SELECT * FROM user WHERE username=?";

        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(username);

        boolean have = false;
        try {
            have = MyConnection.query(sql, arrayList).next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return have;
    }

    public int register(String username, String password) {
        String sql = "INSERT INTO user VALUES(null, ?, ?)";

        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(username);
        arrayList.add(password);

        return MyConnection.update(sql, arrayList);
    }
}
