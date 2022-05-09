package main.model.repository;

import main.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
// искусственный коммит 9.5.22
@Repository
public interface TagRepository extends JpaRepository <Tag, Integer>  {
    @Query(value = "FROM Tag t WHERE t.name = ?1")
    Optional<Tag> findTagByName(String tagName);
}
