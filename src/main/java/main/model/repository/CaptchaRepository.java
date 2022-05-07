package main.model.repository;

import main.model.entity.CaptchaCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CaptchaRepository extends JpaRepository<CaptchaCode, Integer> {
    Optional<CaptchaCode> findBySecretCode (String secretCode);
}
