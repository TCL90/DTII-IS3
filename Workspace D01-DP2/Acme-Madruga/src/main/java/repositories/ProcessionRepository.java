
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Procession;

@Repository
public interface ProcessionRepository extends JpaRepository<Procession, Integer> {

	@Query("select p from Procession p where p.brotherhood.id = ?1")
	Collection<Procession> findByBrotherhoodId(int brotherhoodId);

	@Query("select p from Request r join r.procession p where r.id=?1")
	Procession findByRequestId(Integer requestId);


	@Query("select p from Procession p where p.finalMode='1'")
	Collection<Procession> findAllFinalMode();


}
