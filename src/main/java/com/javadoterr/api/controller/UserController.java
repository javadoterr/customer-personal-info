package com.javadoterr.api.controller;

import com.javadoterr.api.bean.User;
import com.javadoterr.api.service.DataGenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class UserController {

    private DataGenService dataGenService;

    public UserController(DataGenService service){
        this.dataGenService = service;
    }

    @GetMapping(path = "/{count}")
    public ResponseEntity<List<User>> getAllUsers(@PathVariable(name = "count") int count,
                                                  @RequestParam(name = "easy", defaultValue = "false") boolean easy){
        List<User> users = dataGenService.getAllUsers(count, easy);
        log.info("CONTROLLER : getAllUsers response ");
        return ResponseEntity.ok().body(users);
    }

    @GetMapping(path = "/one")
    public ResponseEntity<User> getUser(){
        log.info("CONTROLLER : getUser response ");
        return ResponseEntity.ok()
                .body(dataGenService.randomUserViaDatafaker());
    }


}
