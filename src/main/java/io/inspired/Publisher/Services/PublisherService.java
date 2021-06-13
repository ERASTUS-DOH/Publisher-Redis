package io.inspired.Publisher.Services;

import io.inspired.Publisher.models.Joke;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;

@Service
public class PublisherService {

    @Value("${jokeApi}")
    private String  JOKE_API_ENDPOINT;

    @Value("${jokeChannel}")
    private String channel;

    private WebClient webClient;

    @Autowired
    private ReactiveRedisOperations<String, Joke> redisTemplate;

    @PostConstruct
    private void init(){
        this.webClient = WebClient.builder()
                .baseUrl(JOKE_API_ENDPOINT)
                .build();
    }

    @Scheduled(fixedRate = 3000)
    public void publish(){
        System.out.println("heello");
        this.webClient.get()
                .retrieve()
                .bodyToMono(Joke.class)
                .flatMap(joke -> this.redisTemplate.convertAndSend(channel, joke))
                .subscribe();
    }
}
