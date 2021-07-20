package ir.maktab.hw1.controller;

import java.sql.Connection;

public class Controller {
     ConnectDb connectDb = new ConnectDb("hw6db", "root", "@A135246789a");
     Connection connection = connectDb.getConnection();
}
