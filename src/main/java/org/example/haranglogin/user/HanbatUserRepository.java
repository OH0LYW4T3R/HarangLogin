package org.example.haranglogin.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HanbatUserRepository extends JpaRepository<HanbatUser, Long> {
    Optional<HanbatUser> findByStudentNumber(String studentNumber);
}
