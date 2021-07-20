package ir.maktab.hw1.controller;

import ir.maktab.hw1.model.Article;
import ir.maktab.hw1.model.Category;
import ir.maktab.hw1.model.Tag;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TagController extends Controller {

    public ArrayList<Tag> getAllTags() {
        ArrayList<Tag> tags = new ArrayList<Tag>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from tags");
            while (resultSet.next()) {
                tags.add(
                        new Tag(
                                resultSet.getInt(1),
                                resultSet.getString(2)
                        )
                );
            }
            return tags;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public void addTag(Tag tag) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into tags (title) values (?)");
        preparedStatement.setString(1, tag.getTitle());
        preparedStatement.executeUpdate();
    }

    public ArrayList<Tag> getArticleTags(Article article) {
        ArrayList<Tag> tags = new ArrayList<Tag>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select tags.* from tags join articles_tags on tags.id = articles_tags.tag_id" +
                            "join articles on articles_tags.article_id = ?");
            preparedStatement.setInt(1, article.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tags.add(new Tag(resultSet.getInt(1), resultSet.getString(2)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tags;
    }

}
