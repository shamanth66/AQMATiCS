package com.spliteasy.controller;

import com.spliteasy.model.Item;
import com.spliteasy.service.SplitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final SplitService splitService;

    @GetMapping("/{sessionId}")
    public List<Item> getItems(@PathVariable String sessionId) {
        return splitService.getItems(sessionId);
    }

    @PostMapping
    public Item addItem(@RequestParam String sessionId, @RequestParam String name, @RequestParam BigDecimal price) {
        return splitService.addItem(sessionId, name, price);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeItem(@PathVariable Long id) {
        splitService.removeItem(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{itemId}/assign/{friendId}")
    public Item assignParticipant(@PathVariable Long itemId, @PathVariable Long friendId) {
        return splitService.assignParticipant(itemId, friendId);
    }

    @DeleteMapping("/{itemId}/assign/{friendId}")
    public Item removeParticipant(@PathVariable Long itemId, @PathVariable Long friendId) {
        return splitService.removeParticipant(itemId, friendId);
    }
}
