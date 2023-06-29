package org.andromeda.notifications;

import org.andromeda.amqp.RabbitMQMessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(
        scanBasePackages = {
                "org.andromeda.notifications",
                "org.andromeda.amqp"
        }
)
@PropertySources({
        @PropertySource("classpath:clients-${spring.profiles.active}.properties")
})
public class NotificationApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(NotificationApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(RabbitMQMessageProducer producer, NotificationConfig notificationConfig) {
        return args -> {
            producer.publish(
                    new Person("Andromeda", 21),
                    notificationConfig.getInternalExchange(),
                    notificationConfig.getInternalNotificationRoutingKeyExchange()
            );
        };
    }

    record Person(String name, int age) {}
}
