package com.molot.developer.graphqloverview.repositories;

import com.molot.developer.graphqloverview.entities.Attack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttackRepository extends JpaRepository<Attack, Long> {
    Attack findByName(String name);
}
