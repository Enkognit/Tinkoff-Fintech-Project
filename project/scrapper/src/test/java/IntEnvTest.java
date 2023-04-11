import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.*;

@Testcontainers
public class IntEnvTest extends IntegrationEnvironment {
    @Test
    public void db_name_is_correct() {
        assertThat(postgreSQLContainer.getDatabaseName()).isEqualTo("scrapper");
    }
}
