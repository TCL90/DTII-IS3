
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ProcessionRepository;
import repositories.RequestRepository;
import utilities.TickerGenerator;
import domain.Brotherhood;
import domain.Member;
import domain.Procession;
import domain.Request;

@Service
@Transactional
public class ProcessionService {

	@Autowired
	public ActorService			actorService;

	@Autowired
	public ProcessionRepository	processionRepository;

	@Autowired
	public RequestRepository	requestRepository;

	@Autowired
	public BrotherhoodService	brotherhoodService;

	@Autowired
	public MemberService		memberService;


	public ProcessionService() {
		super();
	}

	public Procession create() {
		final Procession res = new Procession();

		res.setDepartureDate(new Date());
		res.setDescription("");
		res.setFinalMode(false);
		res.setTitle("");
		res.setTicker(TickerGenerator.generateTicker());

		return res;
	}

	public Procession save(final Procession proc) {

		final Procession procOld = this.processionRepository.findOne(proc.getId());

		Assert.isTrue(!procOld.getFinalMode());

		Brotherhood res;

		res = this.brotherhoodService.findByPrincipal();
		proc.setBrotherhood(res);

		if (proc.getId() != 0) {
			final Collection<Request> reqs = this.requestRepository.findByProcessionId(proc.getId());
			for (final Request r : reqs) {
				r.setProcession(proc);
				final Request reqSave = this.requestRepository.save(r);
				final Member mem = this.memberService.findByRequestId(r.getId());
				final Collection<Request> reqsMember = mem.getRequests();
				reqsMember.remove(r);
				reqsMember.add(reqSave);
				this.memberService.save(mem);
			}
		}
		return this.processionRepository.save(proc);
	}
	public void delete(final Procession proc) {
		Assert.isTrue(!proc.getFinalMode());

		final Collection<Request> reqs = this.requestRepository.findByProcessionId(proc.getId());

		for (final Request r : reqs)
			this.requestRepository.delete(r.getId());

		this.processionRepository.delete(proc);
	}

	public Collection<Procession> findAll() {
		return this.processionRepository.findAll();
	}

	public Procession findOne(final int processionId) {
		return this.processionRepository.findOne(processionId);
	}

	public Collection<Procession> findByBrotherhoodId(final int brotherhoodId) {
		return this.processionRepository.findByBrotherhoodId(brotherhoodId);
	}
	
	public Collection<Procession> findAllFinalMode() {
		return this.processionRepository.findAllFinalMode();
	}
	

	public Procession findByRequestId(final Integer requestId) {
		// TODO Auto-generated method stub
		return this.processionRepository.findByRequestId(requestId);
	}

	public Collection<Procession> findByBrotherhood(final Brotherhood b) {
		return this.processionRepository.findByBrotherhoodId(b.getId());

	}

	public Procession findOne(final Procession p1) {
		return this.processionRepository.findOne(p1.getId());
	}


}
