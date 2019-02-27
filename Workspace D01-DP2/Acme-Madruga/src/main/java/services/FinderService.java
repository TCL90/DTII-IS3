
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.FinderRepository;
import domain.Finder;

@Service
@Transactional
public class FinderService {

	@Autowired
	private FinderRepository	finderRepository;


	public Finder save(final Finder f) {
		return this.finderRepository.save(f);
	}

}
