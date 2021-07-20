package ir.maktab.hw7.repository;

import ir.maktab.hw7.domain.Article;
import ir.maktab.hw7.domain.Category;
import ir.maktab.hw7.domain.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ArticleRepository implements BaseRepository {

    public ArrayList<Article> getPublishedArticles() {
        ArrayList<Article> publishedArticles = new ArrayList<Article>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select articles.*, users.username, categories.title from articles " +
                    "join users on articles.user_id = users.id join categories on articles.category_id = categories.id" +
                    " where isPublished = 1");
            while (resultSet.next()) {
                Article article = new Article(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDate(5).toLocalDate(),
                        resultSet.getBoolean(6),
                        resultSet.getDate(7).toLocalDate(),
                        resultSet.getDate(8).toLocalDate(),
                        new User(
                                resultSet.getInt(9),
                                resultSet.getString(12)
                        ),
                        new Category(
                                resultSet.getInt(10),
                                resultSet.getString(13)
                        )
                );
                article.setPrice(resultSet.getBigDecimal(11));
                publishedArticles.add(article);
            }
            return publishedArticles;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public ArrayList<Article> getUserArticles(User currentUser) {
        ArrayList<Article> articles = new ArrayList<Article>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from articles join categories on articles.category_id = categories.id where user_id = ?"
            );
            preparedStatement.setInt(1, currentUser.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            int i = 0;
            while (resultSet.next()) {
                articles.add(new Article());
                articles.get(i).setId(resultSet.getInt(1));
                articles.get(i).setTitle(resultSet.getString(2));
                articles.get(i).setBrief(resultSet.getString(3));
                articles.get(i).setContent(resultSet.getString(4));
                articles.get(i).setCreateDate(resultSet.getDate(5) != null ? resultSet.getDate(5).toLocalDate() : null);
                articles.get(i).setPublished(resultSet.getBoolean(6));
                articles.get(i).setLastUpdateDate(resultSet.getDate(7) != null ? resultSet.getDate(7).toLocalDate() : null);
                articles.get(i).setPublishDate(resultSet.getDate(8) != null ? resultSet.getDate(8).toLocalDate() : null);
                articles.get(i).setPrice(resultSet.getBigDecimal(11));
                articles.get(i).setCategory(new Category(
                   resultSet.getInt(12),
                   resultSet.getString(13),
                   resultSet.getString(14)
                ));
                articles.get(i).setUser(currentUser);
                i++;
            }
            return articles;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public Article getArticleById(User currentUser, int articleId) {
        Article article = new Article();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from articles where user_id = ? and id = ?"
            );
            preparedStatement.setInt(1, currentUser.getId());
            preparedStatement.setInt(2, articleId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            article.setId(resultSet.getInt(1));
            article.setTitle(resultSet.getString(2));
            article.setBrief(resultSet.getString(3));
            article.setContent(resultSet.getString(4));
            article.setCreateDate(resultSet.getDate(5).toLocalDate());
            article.setPublished(resultSet.getBoolean(6));
            article.setLastUpdateDate(resultSet.getDate(7).toLocalDate());
            article.setPublishDate(resultSet.getDate(8).toLocalDate());
            article.setUser(currentUser);
            return article;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public void update(User currentUser, int articleId, Article editedArticle) {
        try {
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update articles set title = ?, brief = ?, content = ?, isPublished = ?, lastUpdateDate = ?, publishDate = ?, price = ? where user_id = ? and id = ?"
            );
            preparedStatement.setString(1, editedArticle.getTitle());
            preparedStatement.setString(2, editedArticle.getBrief());
            preparedStatement.setString(3, editedArticle.getContent());
            preparedStatement.setBoolean(4, editedArticle.isPublished());
            preparedStatement.setDate(5, Date.valueOf(LocalDate.now()));
            preparedStatement.setDate(6, editedArticle.isPublished() ? Date.valueOf(editedArticle.getPublishDate()) : null);
            preparedStatement.setInt(7, currentUser.getId());
            preparedStatement.setInt(8, articleId);
            preparedStatement.setBigDecimal(9, editedArticle.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void save(User currentUser, Article article) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into articles (title, brief, content, createdate, isPublished, " +
                            "lastUpdateDate, publishDate, user_id, category_id, price) values (?,?,?,?,?,?,?,?,?,?)"
            );
            preparedStatement.setString(1, article.getTitle());
            preparedStatement.setString(2, article.getBrief());
            preparedStatement.setString(3, article.getContent());
            preparedStatement.setDate(4, Date.valueOf(article.getCreateDate()));
            preparedStatement.setBoolean(5, article.isPublished());
            preparedStatement.setDate(6, Date.valueOf(article.getLastUpdateDate()));
            preparedStatement.setDate(7, article.getPublishDate() != null ? Date.valueOf(article.getPublishDate()) : null);
            preparedStatement.setInt(8, currentUser.getId());
            preparedStatement.setInt(9, article.getCategory().getId());
            preparedStatement.setBigDecimal(10, article.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
