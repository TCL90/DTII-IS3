
package repositories;

import java.util.Collection;
import java.util.Date;

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

	//Finder
	@Query("select p from Procession p where p.title like %?1% or p.description like %?1%")
	Collection<Procession> findProcessionsByKeyword(String keyword);

	@Query("select p from Procession p where p.brotherhood.area.id=?1")
	Collection<Procession> findProcessionsByAreaId(int id);

	@Query("select p from Procession p where p.departureDate > ?1")
	Collection<Procession> findProcessionsByMinimumDate(Date minDate);

	@Query("select p from Procession p where p.departureDate < ?1")
	Collection<Procession> findProcessionsByMaximumDate(Date maxDate);

	@Query("select p from Procession p where p.departureDate between ?1 and ?2")
	Collection<Procession> findProcessionsByDateRange(Date min, Date max);
}
