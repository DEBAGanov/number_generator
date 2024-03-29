package com.example.numbergenerator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


@Repository
public interface NumberRepository extends JpaRepository<NumberEntity, Long> {
}
