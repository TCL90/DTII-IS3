
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.MemberRepository;
import domain.Actor;
import domain.Brotherhood;
import domain.Enrolement;
import domain.Member;

@Service
@Transactional
public class MemberService {

	@Autowired
	public MemberRepository	memberRepository;

	@Autowired
	public ActorService		actorService;


	//Constructor
	public MemberService() {
		super();
	}

	public Collection<Member> membersByBrotherhood(final Brotherhood brotherhood) {
		return this.memberRepository.membersByBrotherhood(brotherhood.getId());
	}
	//Simple CRUD
	public Member findOne(final Member member) {
		return this.memberRepository.findOne(member.getId());
	}

	public Member findOnePrincipal() {
		final Actor a = this.actorService.findByPrincipal();
		final Member m = new Member();
		m.setId(a.getId());
		final Member res = this.findOne(m);

		return res;
	}

	public void save(final Member m) {
		// TODO Auto-generated method stub
		this.memberRepository.save(m);
	}

	public Member memberByEnrolemetId(final Integer enrolementId) {
		return this.memberRepository.memberByEnrolementId(enrolementId);
	}

	public List<Member> membersByEnrolemetId(final Collection<Enrolement> enrolements) {
		// TODO Auto-generated method stub
		final List<Member> res = new ArrayList<>();
		for (final Enrolement e : enrolements)
			res.add(this.memberByEnrolemetId(e.getId()));
		return null;
	}
}