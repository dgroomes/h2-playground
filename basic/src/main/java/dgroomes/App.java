package dgroomes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

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

  public static void main(String... args) throws SQLException {
    var connection = DriverManager.getConnection(JDBC_URL);

    try (var stmt = connection.createStatement()) {

      stmt.executeUpdate(readClasspathResource("/observations-schema.sql"));
      stmt.executeUpdate(readClasspathResource("/observations-data.sql"));

      ResultSet rs = stmt.executeQuery("SELECT id, observation FROM observations");

      while (rs.next()) {
        var observation = rs.getString("observation");
        var id = rs.getInt("id");
        var record = new Observation(id, observation);
        log.info("Found this observation: {}", record);
      }
    }
  }

  private static String readClasspathResource(String path) {
    try {
      InputStream stream = Objects.requireNonNull(App.class.getResourceAsStream(path));
      return new String(stream.readAllBytes());
    } catch (IOException e) {
      throw new IllegalStateException("Unexpected exception while reading ");
    }
  }
}
