package main.model.repository;

import main.model.entity.GlobalSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// искусственный коммит 9.5.22
@Repository
public interface GlobalSettingsRepository extends JpaRepository <GlobalSettings, Integer> {
}
