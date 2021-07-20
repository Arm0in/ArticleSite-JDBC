package ir.maktab.hw7.repository;

import ir.maktab.hw7.domain.Admin;
import ir.maktab.hw7.domain.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepository implements BaseRepository {

    public Admin getAdmin(String userName, String password) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("select * from admins where username = ? and password = ?");
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            ResultSet loginResult = preparedStatement.executeQuery();
            loginResult.next();
            Admin admin = new Admin(
                    loginResult.getInt(1),
                    loginResult.getString(2)
            );
            return admin;
        } catch (SQLException throwables) {
//            throwables.printStackTrace();
            return null;
        }
    }

    public User getUserByName(String userName) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("select * from users where username = ?");
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            User user = new User(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4).toLocalDate()
            );
            user.setStatus(resultSet.getBoolean(6));
            user.setBalance(resultSet.getBigDecimal(7));
            return user;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public void updateUserStatus(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("update users set status = ? where username = ?");
            preparedStatement.setBoolean(1, user.isStatus());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
