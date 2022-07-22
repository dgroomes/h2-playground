package dgroomes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * An H2 example program. See the README for more information.
 */
public class App {

  private static final Logger log = LoggerFactory.getLogger(App.class);

  /**
   * There is a lot of stuff encoded in a JDBC URL. This particular URL starts with the normal "jdbc" part. Next
   * is "h2", which indicates I think that JDBC should try to load a JDBC driver on the classpath that is fit for H2.
   * Next is "mem", which is a special string that tells the H2 driver to create an in-memory database. Finally,
   * "h2playground" indicates that the in-memory database should be named "h2playground". This is a pretty arbitrary
   * thing. I suppose if you want to make multiple in-memory databases maybe this is useful.
   */
  private static final String JDBC_URL = "jdbc:h2:mem:h2playground";

  public static final String SETUP_SQL = """
          create table observations(
              id int not null primary key,
              observation text not null
          );
                      
          insert into observations (id, observation)
          values (1, 'The sky is blue'),
                 (2, 'The speed of light can circle the earth 7 times in a second');
          """;

  private static final String SQL_QUERY = "SELECT id, observation FROM observations";

  public static void main(String... args) throws SQLException {
    var connection = DriverManager.getConnection(JDBC_URL);

    try (var stmt = connection.createStatement()) {

      stmt.executeUpdate(SETUP_SQL);

      var rs = stmt.executeQuery(SQL_QUERY);
      while (rs.next()) {
        var observation = rs.getString("observation");
        var id = rs.getInt("id");
        var record = new Observation(id, observation);
        log.info("Found this observation: {}", record);
      }
    }
  }
}
