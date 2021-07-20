package ir.maktab.hw7.repository;

import ir.maktab.hw7.domain.Article;
import ir.maktab.hw7.domain.User;

import java.sql.Connection;

public interface BaseRepository {
     ConnectDb connectDb = new ConnectDb("hw7db", "root", "@A135246789a");
     Connection connection = connectDb.getConnection();

//     findAll();
//     void save(User currentUser, Article article);
//     void update();
//     void delete();
}
