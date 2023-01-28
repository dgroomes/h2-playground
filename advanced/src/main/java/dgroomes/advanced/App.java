package dgroomes.advanced;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
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
   * Read about H2's variegated JDBC URL forms here: https://www.h2database.com/html/features.html#database_url
   * <p>
   * This JDBC URL style is for running H2 in an embedded file mode.
   * <p>
   * This JDBC URL is not complete because we need to hydrate it with the absolute path to the database directory/file
   * (?). H2 requires absolute paths so we'll build the path at runtime.
   */
  private static final String JDBC_EMBEDDED_MODE_URL_TEMPLATE = "jdbc:h2:file:%s/mydb;TRACE_LEVEL_FILE=4";

  /*
   * This JDBC URL style is for running H2 in "server mode".
   */
  private static final String JDBC_SERVER_MODE_URL = "jdbc:h2:tcp://localhost:8084/mydb;TRACE_LEVEL_FILE=4";

  public static void main(String... args) {
    // Note: I think this is the best way (although so verbose) to get the name of the current directory. The
    // "normalize()" call is important or else you'll get "." as the file name.
    Path currentDir = Path.of(".").toAbsolutePath().normalize();
    if (!"advanced".equals(currentDir.getFileName().toString())) {
      log.error("Please execute this program from the 'advanced/' directory. The current working directory is: {}", currentDir);
      return;
    }

    // "Pre-create" the H2 database. Opening a connection triggers H2 to create the database.
    //
    // If you don't pre-create a database, then H2 will complain later when you try to start H2 in server mode
    // because it doesn't allow remote connections to create new databases (unless you use an non-recommended option).
    var jdbcEmbeddedModeUrl = String.format(JDBC_EMBEDDED_MODE_URL_TEMPLATE, currentDir);
    try (var ignore = DriverManager.getConnection(jdbcEmbeddedModeUrl)) {
      log.info("The database file should have been created. Look for a file with the file extension '.mv.db' (this is an H2 thing) in the  directory {}", currentDir);
    } catch (SQLException e) {
      throw new RuntimeException("Something went wrong while creating the database", e);
    }

    Server server;
    try {
      server = Server.createTcpServer("-tcpPort", "8084", "-baseDir", currentDir.toString(), "-trace").start();
    } catch (SQLException e) {
      throw new RuntimeException("Something went wrong while creating the TCP server", e);
    }

    // Not 100% sure what is the idiomatic way to do this, but I'll register a shutdown hook to stop the H2 server.
    Runtime.getRuntime().addShutdownHook(new Thread(server::stop));

    try (var connection = DriverManager.getConnection(JDBC_SERVER_MODE_URL);
         var stmt = connection.createStatement()) {

      stmt.executeUpdate(readClasspathResource("/observations-schema.sql"));
      stmt.executeUpdate(readClasspathResource("/observations-data.sql"));

      ResultSet rs = stmt.executeQuery("SELECT id, observation FROM observations");

      while (rs.next()) {
        var observation = rs.getString("observation");
        var id = rs.getInt("id");
        var record = new Observation(id, observation);
        log.info("Found this observation: {}", record);
      }
    } catch (SQLException e) {
      server.stop();
      throw new RuntimeException("Something went wrong while initializing the database schema and sample data", e);
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
