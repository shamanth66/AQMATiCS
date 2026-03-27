package com.spliteasy.repository;

import com.spliteasy.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findBySessionId(String sessionId);
    void deleteBySessionId(String sessionId);
}
