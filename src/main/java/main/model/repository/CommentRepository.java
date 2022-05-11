package main.model.repository;

import main.model.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentRepository extends JpaRepository<PostComment, Integer> {
        @Query("FROM PostComment pc WHERE pc.post_id = ?1")
        List<PostComment> findCommentsByPostId (int post_id);

        @Query("SELECT count(pc) FROM PostComment pc WHERE pc.post_id = ?1")
        int getCommentCountByPostId(int post_id);
}
