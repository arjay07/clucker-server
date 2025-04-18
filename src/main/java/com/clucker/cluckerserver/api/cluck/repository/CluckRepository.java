package com.clucker.cluckerserver.api.cluck.repository;

import com.clucker.cluckerserver.model.Cluck;
import com.clucker.cluckerserver.model.User;
import com.clucker.cluckerserver.repository.JpaRepositoryWithSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Repository
public interface CluckRepository extends JpaRepositoryWithSpecification<Cluck, UUID> {
    Page<Cluck> getAllByAuthorId(int id, Pageable pageable);
    Page<Cluck> findClucksByAuthorIn(Collection<User> authors, Pageable pageable);

    @Query(value = "SELECT * FROM cluck c " +
                    "INNER JOIN user_liked_clucks lc ON lc.cluck_id = c.id " +
                    "INNER JOIN user u ON u.id = lc.user_id " +
                    "WHERE u.id = ?1",
            countQuery = "SELECT COUNT(*) FROM cluck c " +
                    "INNER JOIN user_liked_clucks lc ON lc.cluck_id = c.id " +
                    "INNER JOIN user u ON u.id = lc.user_id " +
                    "WHERE u.id = ?1",
            nativeQuery = true)
    Page<Cluck> findLikedClucksByUserId(int id, Pageable pageable);
}
