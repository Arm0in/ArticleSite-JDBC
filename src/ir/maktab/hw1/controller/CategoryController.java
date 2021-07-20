package ir.maktab.hw1.controller;

import ir.maktab.hw1.model.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CategoryController extends Controller {

    public ArrayList<Category> getAllCategories() {
        ArrayList<Category> categories = new ArrayList<Category>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from categories");
            while (resultSet.next()) {
                categories.add(
                        new Category(
                                resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getString(3)
                        )
                );
            }
            return categories;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public void addCategory(Category category) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into categories (title, categorydesc) values (?,?)");
        preparedStatement.setString(1, category.getTitle());
        preparedStatement.setString(2, category.getDescription());
        preparedStatement.executeUpdate();
    }


}
