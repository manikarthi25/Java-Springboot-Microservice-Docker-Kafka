package com.service.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}

}


/*
Understanding spring.kafka.consumer.auto-offset-reset

The spring.kafka.consumer.auto-offset-reset property in Spring Kafka determines the behavior of a Kafka consumer when there is no committed offset for a partition or when the committed offset is no longer valid (e.g., due to log retention policies). It controls where the consumer starts reading messages from in such scenarios.

Key Values for auto-offset-reset

earliest: The consumer starts reading messages from the beginning of the partition. This ensures that all messages in the topic are processed, even if the consumer is starting for the first time or after a long downtime.

latest: The consumer starts reading messages from the end of the partition. This means it will only process new messages published after the consumer starts.

Behavior and Configuration

Default Behavior: If not explicitly set, the default value is typically earliest in Spring Kafka.

Use Case for earliest: Ideal for applications that need to process all messages in a topic, ensuring no data is missed.

Use Case for latest: Suitable for real-time processing where only new messages are relevant, and historical data is not required.

Example Configuration

You can configure this property in your application.properties or application.yml file:

spring.kafka.consumer.group-id=my-consumer-group
spring.kafka.consumer.auto-offset-reset=earliest
*/