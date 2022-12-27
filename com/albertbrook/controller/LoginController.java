package com.albertbrook.controller;

import com.albertbrook.utils.MyConnection;

import java.sql.SQLException;
import java.util.ArrayList;

public class LoginController {
    public boolean login(String username, String password) {
        String sql = "SELECT * FROM user WHERE username=? and password=?";

        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(username);
        arrayList.add(password);

        boolean success = false;
        try {
            success = MyConnection.query(sql, arrayList).next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }
}
