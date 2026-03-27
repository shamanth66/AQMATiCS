package com.spliteasy.controller;

import com.spliteasy.service.SplitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/split")
@RequiredArgsConstructor
public class SplitController {

    private final SplitService splitService;

    @GetMapping("/equal/{sessionId}")
    public Map<String, BigDecimal> equalSplit(@PathVariable String sessionId, @RequestParam BigDecimal totalBill) {
        return splitService.calculateEqualSplit(sessionId, totalBill);
    }

    @GetMapping("/dutch/{sessionId}")
    public Map<String, BigDecimal> dutchSplit(
            @PathVariable String sessionId,
            @RequestParam(defaultValue = "0") BigDecimal taxPercent,
            @RequestParam(defaultValue = "0") BigDecimal tipPercent) {
        return splitService.calculateDutchSplit(sessionId, taxPercent, tipPercent);
    }

    @DeleteMapping("/reset/{sessionId}")
    public ResponseEntity<Void> resetSession(@PathVariable String sessionId) {
        splitService.resetSession(sessionId);
        return ResponseEntity.noContent().build();
    }
}
