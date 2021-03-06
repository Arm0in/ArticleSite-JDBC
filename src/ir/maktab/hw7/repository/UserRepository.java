package ir.maktab.hw7.repository;

import ir.maktab.hw7.domain.User;

import java.sql.*;

public class UserRepository implements BaseRepository {

    public User getUser(String userName, String password) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("select * from users where username = ? and password = ? and status = 1");
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            ResultSet loginResult = preparedStatement.executeQuery();
            if (loginResult.next()){
                User user = new User(
                        loginResult.getInt(1),
                        loginResult.getString(2),
                        loginResult.getString(3),
                        loginResult.getDate(4).toLocalDate()
                );
                user.setBalance(loginResult.getBigDecimal(7));
                return user;
            } else {
                System.out.println("you don't have an account or your account is not active");
                return null;
            }
        } catch (SQLException throwables) {
//            throwables.printStackTrace();
            System.out.println("you don't have an account or your account is not active");
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

    public boolean update(User currentUser) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("update users set username = ?, nationalcode = ?, birthday = ?, password = ?, status = ?, balance = ? where id = ?");
            preparedStatement.setString(1, currentUser.getUserName());
            preparedStatement.setString(2, currentUser.getNationalCode());
            preparedStatement.setDate(3, Date.valueOf(currentUser.getBirthday()));
            preparedStatement.setString(4, currentUser.getPassword());
            preparedStatement.setBoolean(5, currentUser.isStatus());
            preparedStatement.setBigDecimal(6, currentUser.getBalance());
            preparedStatement.setInt(7, currentUser.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

    }

    public User getUserByName(String userName) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("select * from users where username = ?");
            preparedStatement.setString(1, userName);
            ResultSet loginResult = preparedStatement.executeQuery();
            if (loginResult.next()){
                User user = new User(
                        loginResult.getInt(1),
                        loginResult.getString(2),
                        loginResult.getString(3),
                        loginResult.getDate(4).toLocalDate()
                );
                user.setBalance(loginResult.getBigDecimal(7));
                return user;
            } else {
                System.out.println("you don't have an account");
                return null;
            }
        } catch (SQLException throwables) {
//            throwables.printStackTrace();
            System.out.println("you don't have an account");
            return null;
        }
    }


}
