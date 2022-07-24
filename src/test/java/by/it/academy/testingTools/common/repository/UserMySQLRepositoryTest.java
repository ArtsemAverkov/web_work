package by.it.academy.testingTools.common.repository;


import by.it.academy.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RequiredArgsConstructor
public class UserMySQLRepositoryTest {
    private final EntityManager entityManager;
    private final UserRepository userRepository;
    private final TestEntityManager testEntityManager;
    private final DataSource dataSource;

    private static final DockerImageName MySQL_IMAGE = DockerImageName.parse("mysqlnd 8.0.8");
    private static MySQLContainer<?> mySQLContainer = new MySQLContainer<>(MySQL_IMAGE)
            .withDatabaseName("test")
            .withUsername("root")
            .withPassword("root")
            .withExposedPorts(9090);

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
    }


@Test
@Sql(scripts = "/scripts/INITIAL_USER_DB_SCRIPT.sql")
@DisplayName("Тест для проверки уникальности Login")
    void shouldFindActiveUserLogin(){
    int activeLogin = userRepository.existActiveLogin(String.valueOf("qwerty"));
    testEntityManager.flush();
    testEntityManager.getEntityManager().getTransaction().commit();
    Assertions.assertEquals(0, activeLogin);
    }

    @Test
    @Sql(scripts = "/scripts/INITIAL_USER_DB_SCRIPT.sql")
    @DisplayName("тест для проверки уникальности Email")
    void shouldFindActiveUserEmail(){
        int existActiveEmail = userRepository.existActiveEmail(String.valueOf("it@mail.ru"));
        testEntityManager.flush();
        testEntityManager.getEntityManager().getTransaction().commit();
        Assertions.assertEquals(0, existActiveEmail);

    }
}
