package com.auth_service.controller;

import com.auth_service.model.UserApp;
import com.auth_service.service.IUserAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserAppController {

    private final IUserAppService userAppService;

    @GetMapping
    public ResponseEntity<List<UserApp>> getUsers() {
        return ResponseEntity.ok(userAppService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserApp> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userAppService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UserApp> createUserApp(@RequestBody UserApp userApp) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userAppService.save(userApp));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        userAppService.deleteBYId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserApp> updateBYId(@PathVariable long id, @RequestBody UserApp userApp) {
        return ResponseEntity.ok(userAppService.updateById(id,userApp));
    }
}
