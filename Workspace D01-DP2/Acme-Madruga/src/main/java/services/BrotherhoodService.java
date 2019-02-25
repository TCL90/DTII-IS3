
package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.BrotherhoodRepository;
import domain.Actor;
import domain.Brotherhood;
import domain.Enrolement;
import domain.Member;

@Service
@Transactional
public class BrotherhoodService {

	@Autowired
	public BrotherhoodRepository	brotherhoodRepository;

	@Autowired
	public MemberService			memberService;

	@Autowired
	public ActorService				actorService;

	@Autowired
	public EnrolementService		enrolementService;


	//Constructor
	public BrotherhoodService() {
		super();
	}

	public Brotherhood findOnePrincipal() {
		final Actor a = this.actorService.findByPrincipal();
		return this.brotherhoodRepository.findOne(a.getId());
	}

	public Brotherhood findOne(final Brotherhood brotherhood) {
		return this.brotherhoodRepository.findOne(brotherhood.getId());
	}

	public void deleteMember(final Member member) {
		final Actor b = this.actorService.findByPrincipal();
		final Brotherhood b1 = this.brotherhoodRepository.findOne(b.getId());

		final Enrolement e = this.enrolementService.findEnrolementByIds(b1, member);
		final Date today = Calendar.getInstance().getTime();
		e.setDropOutMoment(today);
		e.setStatus("EXPELLED");
		this.enrolementService.saveDirectly(e);

	}

	public void acceptMember(final Enrolement enrolement) {
		enrolement.setStatus("APPROVED");
		Assert.isTrue(enrolement.getPosition() != null);
		this.enrolementService.save(enrolement);
	}

	public void rejectMember(final Enrolement enrolement) {
		enrolement.setStatus("REJECTED");
		this.enrolementService.save(enrolement);
	}

	public void save(final Brotherhood b) {
		this.brotherhoodRepository.save(b);

	}

	public Collection<Brotherhood> findAll() {
		return this.brotherhoodRepository.findAll();
	}
}
