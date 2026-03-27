package com.spliteasy.controller;

import com.spliteasy.model.Friend;
import com.spliteasy.service.SplitService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friends")
@RequiredArgsConstructor
public class FriendController {

    private final SplitService splitService;

    @GetMapping("/{sessionId}")
    public List<Friend> getFriends(@PathVariable String sessionId) {
        return splitService.getFriends(sessionId);
    }

    @PostMapping
    public Friend addFriend(@RequestParam String sessionId, @RequestParam @NotBlank String name) {
        return splitService.addFriend(sessionId, name);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeFriend(@PathVariable Long id) {
        splitService.removeFriend(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/toggle-paid")
    public Friend togglePaid(@PathVariable Long id) {
        return splitService.togglePaidStatus(id);
    }
}
