
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EnrolementRepository;
import domain.Brotherhood;
import domain.Enrolement;
import domain.Member;

@Service
@Transactional
public class EnrolementService {

	@Autowired
	public EnrolementRepository	enrolementRepository;

	@Autowired
	public BrotherhoodService	brotherhoodService;

	@Autowired
	public MemberService		memberService;


	//Constructor
	public EnrolementService() {
		super();
	}

	public Enrolement findEnrolementByIds(final Brotherhood brotherhood, final Member member) {
		return this.enrolementRepository.findEnrolementByIds(brotherhood.getId(), member.getId());
	}

	public Enrolement save(final Enrolement enrolement) {
		Enrolement res;

		final Member m = this.memberService.findOnePrincipal();
		if (enrolement.getId() == 0) {
			Assert.isTrue(this.enrolementRepository.existEnrolement(m.getId(), enrolement.getBrotherhood().getId()) == null);

			Assert.isTrue(enrolement.getStatus() == "PENDING");
		}
		res = this.enrolementRepository.save(enrolement);

		if (enrolement.getId() == 0) {
			final List<Enrolement> enrolementsM = new ArrayList<>(m.getEnrolements());
			enrolementsM.add(res);
			m.setEnrolements(enrolementsM);

			this.memberService.save(m);

			final Brotherhood b = new Brotherhood();
			b.setId(res.getBrotherhood().getId());

			final Brotherhood updaB = this.brotherhoodService.findOne(b);
			final List<Enrolement> le = new ArrayList<>(updaB.getEnrolements());
			le.add(res);

			this.brotherhoodService.save(updaB);
		}
		return res;
	}
	public Collection<Enrolement> findEnrolementsByMemberId(final Member member) {
		return this.enrolementRepository.findEnrolementsByMemberId(member.getId());
	}

	public void deleteEnrolement(final Enrolement enrolement) {
		Assert.isTrue(enrolement.getStatus() == "PENDING");
		final Brotherhood b = enrolement.getBrotherhood();
		final List<Enrolement> lb = new ArrayList<>(b.getEnrolements());
		lb.remove(enrolement);
		b.setEnrolements(lb);
		this.brotherhoodService.save(b);
		this.enrolementRepository.delete(enrolement);
	}

	public void leaveBrotherhood(final Enrolement e) {
		e.setDropOutMoment(Calendar.getInstance().getTime());
		e.setStatus("EXPELLED");
		e.setPosition(null);
		this.save(e);
	}
	public Enrolement create() {
		final Enrolement res = new Enrolement();
		res.setId(0);
		res.setEnrolMoment(Calendar.getInstance().getTime());
		res.setStatus("PENDING");

		return res;
	}

	public Collection<Enrolement> enrolementsPending(final Integer brotherhoodId) {
		return this.enrolementRepository.enrolementsPending(brotherhoodId);
	}

	public void enrolMember(final Enrolement e) {
		e.setStatus("APPROVED");
		this.save(e);

	}

	public Enrolement findOne(final Enrolement e) {
		// TODO Auto-generated method stub
		return this.enrolementRepository.findOne(e.getId());
	}

	public void rejectMember(final Enrolement enrolement) {
		// TODO Auto-generated method stub
		enrolement.setStatus("REJECTED");
		this.save(enrolement);

	}

}
