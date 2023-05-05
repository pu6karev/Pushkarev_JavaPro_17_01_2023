package ua.ithillel.lesson22;

import lombok.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class HeroDaoImpl implements HeroDao {

    private final DataSource dataSource;

    @Override
    public List<Hero> findAll() {

        String sql = "SELECT * FROM heroinfo";

        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {
                ResultSet result = statement.executeQuery(sql);
                return mapHeroes(result);
        } catch (SQLException e){
            throw new RuntimeException();
        }
    }

    private List<Hero> mapHeroes(ResultSet result) throws SQLException {
        var heroes = new ArrayList<Hero>();

        while (result.next()){
            Hero hero = Hero.builder()
                    .name(result.getString("name"))
                    .gender(result.getString("gender"))
                    .eyeColor(result.getString("eye_color"))
                    .race(result.getString("race"))
                    .hairColor(result.getString("hair_color"))
                    .height(result.getDouble("height"))
                    .publisher(result.getString("publisher"))
                    .skinColor(result.getString("skin_color"))
                    .alignment(result.getString("alignment"))
                    .weight(result.getInt("weight"))
                    .build();

            heroes.add(hero);

        }
        return  heroes;
    }

    @Override
    public List<Hero> findByName(String name) {

        String sql = "SELECT * FROM heroinfo WHERE name = '" + name + "'";

        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(sql);
            return mapHeroes(result);
        } catch (SQLException e){
            throw new RuntimeException();
        }
    }

    @Override
    public void create(Hero hero) {
        String sql = "INSERT INTO heroinfo (name, gender) VALUES ('" + hero.getName() + "','" + hero.getGender() + "')";

        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e){
            throw new RuntimeException("Error creating hero", e);
        }
    }

    @Override
    public void update(Hero hero) {
        String sql = "UPDATE heroinfo SET " +
                "eye_color = '" + hero.getEyeColor() + "', " +
                "race = '" + hero.getRace() + "', " +
                "publisher = '" + hero.getPublisher() + "', " +
                "weight = " + hero.getWeight() + " " +
                "WHERE name = '" + hero.getName() + "'";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating hero", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM heroinfo WHERE id = " + id;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            int rowsDeleted = statement.executeUpdate(sql);
            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
