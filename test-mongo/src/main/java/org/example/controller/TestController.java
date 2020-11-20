package org.example.controller;


import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TestController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping("/testInsert")
    public ResponseEntity<String> save() {
        User user = new User();
        user.setName("凌康");
        user.setSex("男");
        mongoTemplate.insert(user);

        return ResponseEntity.of(Optional.of("success"));
    }
}
