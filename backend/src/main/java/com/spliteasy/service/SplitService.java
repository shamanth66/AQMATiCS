package com.spliteasy.service;

import com.spliteasy.model.Friend;
import com.spliteasy.model.Item;
import com.spliteasy.repository.FriendRepository;
import com.spliteasy.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SplitService {

    private final FriendRepository friendRepo;
    private final ItemRepository itemRepo;

    // ===== Friend Operations =====

    public Friend addFriend(String sessionId, String name) {
        return friendRepo.save(Friend.builder().name(name).sessionId(sessionId).build());
    }

    public List<Friend> getFriends(String sessionId) {
        return friendRepo.findBySessionId(sessionId);
    }

    public void removeFriend(Long id) {
        friendRepo.deleteById(id);
    }

    public Friend togglePaidStatus(Long id) {
        Friend f = friendRepo.findById(id).orElseThrow(() -> new RuntimeException("Friend not found"));
        f.setPaidStatus(!f.isPaidStatus());
        return friendRepo.save(f);
    }

    // ===== Item Operations =====

    public Item addItem(String sessionId, String name, BigDecimal price) {
        return itemRepo.save(Item.builder().name(name).price(price).sessionId(sessionId).build());
    }

    public List<Item> getItems(String sessionId) {
        return itemRepo.findBySessionId(sessionId);
    }

    public void removeItem(Long id) {
        itemRepo.deleteById(id);
    }

    public Item assignParticipant(Long itemId, Long friendId) {
        Item item = itemRepo.findById(itemId).orElseThrow();
        Friend friend = friendRepo.findById(friendId).orElseThrow();
        item.getParticipants().add(friend);
        return itemRepo.save(item);
    }

    public Item removeParticipant(Long itemId, Long friendId) {
        Item item = itemRepo.findById(itemId).orElseThrow();
        item.getParticipants().removeIf(f -> f.getId().equals(friendId));
        return itemRepo.save(item);
    }

    // ===== Split Calculations =====

    public Map<String, BigDecimal> calculateEqualSplit(String sessionId, BigDecimal totalBill) {
        List<Friend> friends = friendRepo.findBySessionId(sessionId);
        if (friends.isEmpty()) return Collections.emptyMap();

        BigDecimal share = totalBill.divide(BigDecimal.valueOf(friends.size()), 2, RoundingMode.HALF_UP);
        BigDecimal allocated = share.multiply(BigDecimal.valueOf(friends.size()));
        BigDecimal remainder = totalBill.subtract(allocated);

        Map<String, BigDecimal> shares = new LinkedHashMap<>();
        for (int i = 0; i < friends.size(); i++) {
            BigDecimal amount = (i == 0) ? share.add(remainder) : share;
            shares.put(friends.get(i).getName(), amount);
        }
        return shares;
    }

    public Map<String, BigDecimal> calculateDutchSplit(String sessionId, BigDecimal taxPercent, BigDecimal tipPercent) {
        List<Item> items = itemRepo.findBySessionId(sessionId);
        Map<String, BigDecimal> shares = new HashMap<>();

        BigDecimal subtotal = items.stream()
                .map(Item::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal multiplier = BigDecimal.ONE
                .add(taxPercent.divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP))
                .add(tipPercent.divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP));

        for (Item item : items) {
            int count = item.getParticipants().size();
            if (count == 0) continue;
            BigDecimal perPerson = item.getPrice()
                    .multiply(multiplier)
                    .divide(BigDecimal.valueOf(count), 2, RoundingMode.HALF_UP);
            for (Friend f : item.getParticipants()) {
                shares.merge(f.getName(), perPerson, BigDecimal::add);
            }
        }
        return shares;
    }

    // ===== Session Reset =====

    @Transactional
    public void resetSession(String sessionId) {
        itemRepo.deleteBySessionId(sessionId);
        friendRepo.deleteBySessionId(sessionId);
    }
}
