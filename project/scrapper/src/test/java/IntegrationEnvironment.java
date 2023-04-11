import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.DirectoryResourceAccessor;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

abstract public class IntegrationEnvironment {

    public static PostgreSQLContainer postgreSQLContainer;

    static {
        postgreSQLContainer = new PostgreSQLContainer("postgres:15")
                .withDatabaseName("scrapper")
                .withUsername("Enkognit")
                .withPassword("228");
        postgreSQLContainer.start();
        Properties props = new Properties();
        props.setProperty("user", postgreSQLContainer.getUsername());
        props.setProperty("password", postgreSQLContainer.getPassword());

        try {
            Connection connection = DriverManager.getConnection(postgreSQLContainer.getJdbcUrl(), props);
            Database database = DatabaseFactory.getInstance()
                    .findCorrectDatabaseImplementation(new JdbcConnection(connection));
            Path pathToChangelog = new File("").toPath()
                    .resolve("migrations")
                    .resolve("master.xml").toAbsolutePath();
            new DirectoryResourceAccessor(pathToChangelog.getParent());
            Liquibase liquibase = new Liquibase(pathToChangelog.getFileName().toString(),
                    new DirectoryResourceAccessor(pathToChangelog.getParent()),
                    database);
            liquibase.update();
        } catch (SQLException | LiquibaseException | FileNotFoundException e) {
            throw new IllegalArgumentException("Error while creating: " + e.getMessage() + " " + e.getClass().toString());
        }
    }
}
