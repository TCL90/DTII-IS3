
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FloatRepository;
import repositories.ProcessionRepository;
import domain.Brotherhood;
import domain.Float;
import domain.Procession;

@Service
@Transactional
public class FloatService {

	//Managed repository
	@Autowired
	private FloatRepository			floatRepository;

	@Autowired
	private BrotherhoodService		brotherhoodService;

	@Autowired
	private ProcessionRepository	processionRepository;

	@Autowired
	private ProcessionService		processionService;


	//Simple CRUD Methods
	public Float create() {
		final Float res = new Float();

		final Brotherhood logBro = this.brotherhoodService.findByPrincipal();
		res.setBrotherhood(logBro);

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
		//Si alguna procesión saca el paso, se le quita.
		//	final Collection<Procession> procs = this.processionRepository.findByFloatId(f.getId());
		final Collection<Procession> process = this.processionService.findAll();
		//final List<Procession> procs = new ArrayList<>();
		for (final Procession pro : process)
			if (pro.getFloats().contains(f)) {
				final Collection<domain.Float> floats = pro.getFloats();
				floats.remove(f);
				pro.setFloats(floats);
				this.processionService.save(pro);
			}

		//		for (final Procession p : procs) {
		//			final Collection<domain.Float> floats = p.getFloats();
		//			floats.remove(f);
		//			p.setFloats(floats);
		//			this.processionService.save(p);
		//		}

		this.floatRepository.delete(f);
	}

	public Collection<domain.Float> findByBrotherhoodId(final int brotherhoodId) {
		return this.floatRepository.findByBrotherhoodId(brotherhoodId);
	}
}
