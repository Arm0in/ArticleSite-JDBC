package ir.maktab.hw7.repository;

import ir.maktab.hw7.domain.User;

import java.sql.*;

public class UserRepository implements BaseRepository {

    public User getUser(String userName, String password) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("select * from users where username = ? and password = ?");
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            ResultSet loginResult = preparedStatement.executeQuery();
            loginResult.next();
            User user = new User(
                    loginResult.getInt(1),
                    loginResult.getString(2),
                    loginResult.getString(3),
                    loginResult.getDate(4).toLocalDate()
            );
            return user;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public boolean addUser(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("insert into users (username, nationalcode, birthday, pass) values(?, ?, ?, ?)");
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getNationalCode());
            preparedStatement.setDate(3, Date.valueOf(user.getBirthday()));
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

    }

    public boolean updatePass(User currentUser, String newPass) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("update users set password = ? where id = ?");
            preparedStatement.setString(1, newPass);
            preparedStatement.setInt(2, currentUser.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

    }


}
