
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FloatRepository;
import domain.Brotherhood;
import domain.Float;

@Service
@Transactional
public class FloatService {

	//Managed repository
	@Autowired
	private FloatRepository		floatRepository;

	@Autowired
	private BrotherhoodService	brotherhoodService;


	//Simple CRUD Methods
	public Float create() {
		final Float res = new Float();

		res.setPictures(new ArrayList<String>());

		return res;
	}

	public Float save(final Float f) {
		Assert.notNull(f);

		if (f.getId() == 0) {
			final Brotherhood logBro = this.brotherhoodService.findByPrincipal();
			f.setBrotherhood(logBro);
		}

		return this.floatRepository.save(f);
	}
	public Float findOne(final Integer id) {
		Assert.isTrue(id != 0);
		return this.floatRepository.findOne(id);
	}

	public Collection<Float> findAll() {
		return this.floatRepository.findAll();
	}

	public void delete(final Float f) {
		Assert.notNull(f);
		this.floatRepository.delete(f);
	}

	public Collection<domain.Float> findByBrotherhoodId(final int brotherhoodId) {
		return this.floatRepository.findByBrotherhoodId(brotherhoodId);
	}
}
