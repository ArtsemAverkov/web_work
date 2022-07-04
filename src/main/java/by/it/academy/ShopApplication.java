package by.it.academy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*
@SpringBootApplication включает в себя:
-@SpringBootConfiguration - включение конфигураций по спринг буту
-@EnableAutoConfiguration  -
-@ComponentScan - указывает в каком месте искать спрингу бины
 */
@SpringBootApplication
public class ShopApplication {
    /**
     * this method receives the set value from the
     * session and returns it as a string
     * @param session gets attribute "productRead"
     * @return the received attribute value
     */
    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }
}
