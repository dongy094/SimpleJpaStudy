package jpastudy.jpaboard.Repository;

import jpastudy.jpaboard.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardJpaRepository extends JpaRepository<Board, Long> {

}
