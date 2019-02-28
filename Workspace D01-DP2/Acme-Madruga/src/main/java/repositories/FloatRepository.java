
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FloatRepository extends JpaRepository<domain.Float, Integer> {

}
