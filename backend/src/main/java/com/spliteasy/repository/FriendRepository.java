package com.spliteasy.repository;

import com.spliteasy.model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    List<Friend> findBySessionId(String sessionId);
    void deleteBySessionId(String sessionId);
}
