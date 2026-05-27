package com.javadoterr.api.service;

import com.javadoterr.api.bean.User;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Slf4j
public class DataGenService {

    private final Faker faker;
    private final EasyRandom easyRandom;

    public DataGenService(){
        this.faker = new Faker(Locale.ENGLISH);
        EasyRandomParameters params = new EasyRandomParameters()
                .seed(System.currentTimeMillis())
                .stringLengthRange(5, 20);
        this.easyRandom = new EasyRandom(params);
    }

    public User randomUserViaDatafaker(){
        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .fullName(faker.name().fullName())
                .email(faker.internet().emailAddress())
                .phone(faker.phoneNumber().cellPhone())
                .address(faker.address().fullAddress())
                .build();
        log.info("SERVICE : Random User : {}", user);
        return user;
    }

    public User randomUserViaEasyRandom(){
        User user = easyRandom.nextObject(User.class);
        if(user.getId() == null || user.getId().isBlank()){
            user.setId(UUID.randomUUID().toString());
        }
        user.setFullName(faker.name().fullName());
        user.setEmail(faker.internet().emailAddress());
        log.info("SERVICE : EASY RANDOM : {}", user);
        return user;
    }

    public List<User> getAllUsers(int count, boolean easy){
        return IntStream.range(0, Math.max(0, count))
                .mapToObj(i -> easy ? randomUserViaEasyRandom() : randomUserViaDatafaker())
                .collect(Collectors.toList());
    }


}
