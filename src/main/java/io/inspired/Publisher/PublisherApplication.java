package io.inspired.Publisher;

import io.inspired.Publisher.models.Joke;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PublisherApplication {

	public static void main(String[] args) {
		SpringApplication.run(PublisherApplication.class, args);
	}


	@Bean
	public ReactiveRedisOperations<String, Joke> jokeTemplate(LettuceConnectionFactory lettuceConnectionFactory){
		RedisSerializer<Joke> jokeRedisSerializer = new Jackson2JsonRedisSerializer<Joke>(Joke.class);
		RedisSerializationContext<String,Joke> serializationContext = RedisSerializationContext.<String ,Joke> newSerializationContext(RedisSerializer.string()).value(jokeRedisSerializer).build();
		return new ReactiveRedisTemplate<String,Joke>(lettuceConnectionFactory, serializationContext);
	}
}
