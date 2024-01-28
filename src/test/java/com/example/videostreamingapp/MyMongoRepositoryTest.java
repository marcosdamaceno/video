/*ackage com.example.videostreamingapp;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
/*
@Testcontainers
public class MongoDbIntegrationTest {

    @Container
    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

    private static MongoTemplate mongoTemplate;

    @BeforeAll
    public static void setUp() {
        mongoDBContainer.start();
        String connectionString = mongoDBContainer.getReplicaSetUrl();
        // Configure a conexão com o MongoDB usando a connectionString

        // Exemplo de configuração usando Spring Data MongoDB
        //mongoTemplate = new MongoTemplate(new SimpleMongoClientDbFactory(connectionString));
    }

    @Test
    public void testMongoDbIntegration() {
        // Seu código de teste aqui
    }

    @AfterAll
    public static void tearDown() {
        mongoDBContainer.stop();
    }
}
*/