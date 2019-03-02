
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.BrotherhoodRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Box;
import domain.Brotherhood;
import domain.Customisation;
import domain.Enrolement;
import domain.Member;
import domain.SocialProfile;
import forms.BrotherhoodForm;

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

	@Autowired
	public AdministratorService		administratorService;


	//Constructor
	public BrotherhoodService() {
		super();
	}

	public Brotherhood create() {
		// User can't be logged to register
		//		final Authority a = new Authority();
		//		final Authority b = new Authority();
		//		final Authority c = new Authority();
		//		final Authority d = new Authority();
		//		final Authority e = new Authority();
		//		final UserAccount user = LoginService.getPrincipal();
		//		a.setAuthority(Authority.ADMIN);
		//		b.setAuthority(Authority.HANDYWORKER);
		//		c.setAuthority(Authority.CUSTOMER);
		//		d.setAuthority(Authority.REFEREE);
		//		e.setAuthority(Authority.SPONSOR);
		//		Assert.isTrue(!(user.getAuthorities().contains(a) || user.getAuthorities().contains(b) || user.getAuthorities().contains(c) || user.getAuthorities().contains(d) || user.getAuthorities().contains(e)));

		Brotherhood result;
		result = new Brotherhood();

		// Actor
		//		final Box trash = new Box();
		//		final Box out = new Box();
		//		final Box spam = new Box();
		//		final Box in = new Box();
		//		trash.setName("trash");
		//		in.setName("in");
		//		out.setName("out");
		//		spam.setName("spam");
		//		out.setPredefined(true);
		//		in.setPredefined(true);
		//		spam.setPredefined(true);
		//		trash.setPredefined(true);
		final Collection<Box> predefined = new ArrayList<Box>();
		//		predefined.add(in);
		//		predefined.add(out);
		//		predefined.add(spam);
		//		predefined.add(trash);

		//	result.setBoxes(predefined);

		final UserAccount newUser = new UserAccount();
		final Authority f = new Authority();
		f.setAuthority(Authority.BROTHERHOOD);
		newUser.addAuthority(f);
		result.setUserAccount(newUser);

		result.setSocialProfiles(new ArrayList<SocialProfile>());
		result.setName("");
		result.setEmail("");
		result.setAddress("");
		result.setSurname("");
		result.setPhoneNumber("");
		result.setPhoto("");

		// Brotherhood
		final Collection<Enrolement> enrols = new ArrayList<Enrolement>();
		result.setEnrolements(enrols);
		final Collection<SocialProfile> socPros = new ArrayList<SocialProfile>();
		result.setSocialProfiles(socPros);

		return result;
	}

	public Brotherhood findByPrincipal() {
		Brotherhood res;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = this.findByUserAccount(userAccount);
		Assert.notNull(res);

		return res;
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

	public void checkBrotherhood(final Enrolement enrolement) {
		final Brotherhood b = this.findOnePrincipal();
		Assert.isTrue(enrolement.getBrotherhood().getId() == b.getId());

	}

	public Brotherhood save(final Brotherhood brotherhood) {
		Assert.notNull(brotherhood);
		Assert.isTrue(!brotherhood.getBan());
		//		// Logged user must be a brotherhood
		//		final Authority a = new Authority();
		//		final UserAccount user = LoginService.getPrincipal();
		//		a.setAuthority(Authority.BROTHERHOOD);
		//		Assert.isTrue(user.getAuthorities().contains(a));

		if (brotherhood.getId() != 0) {
			Assert.isTrue(this.actorService.checkBrotherhood());

			// Modified brotherhood must be logged brotherhood
			final Brotherhood logBrotherhood;
			logBrotherhood = this.findByPrincipal();
			Assert.notNull(logBrotherhood);
			Assert.notNull(logBrotherhood.getId());

			//	La brotherhood editada no puede tener un área asociada
			final Brotherhood oldBro = this.findOne(brotherhood.getId());
			Assert.isTrue(oldBro.getArea() != null);
		} else {
			final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			final String oldpass = brotherhood.getUserAccount().getPassword();
			final String hash = encoder.encodePassword(oldpass, null);

			final UserAccount cuenta = brotherhood.getUserAccount();
			cuenta.setPassword(hash);
			brotherhood.setUserAccount(cuenta);
		}
		// Restrictions
		Brotherhood res;

		res = this.brotherhoodRepository.save(brotherhood);
		return res;
	}

	public Brotherhood findByUserAccount(final UserAccount userAccount) {
		Brotherhood res;
		Assert.notNull(userAccount);

		res = this.brotherhoodRepository.findByUserAccountId(userAccount.getId());

		return res;
	}

	public Collection<Brotherhood> findAll() {
		return this.brotherhoodRepository.findAll();
	}

	public Brotherhood saveScore(final Brotherhood brotherhood, final Customisation custo) {
		//		// Logged user must be an administrator
		//		final Authority a = new Authority();
		//		final UserAccount user = LoginService.getPrincipal();
		//		a.setAuthority(Authority.ADMIN);
		//		Assert.isTrue(user.getAuthorities().contains(a));

		Assert.isTrue(this.actorService.checkBrotherhood());

		Assert.notNull(brotherhood);

		final Integer score = 0;

		final List<String> positive = new ArrayList<String>(custo.getPositiveWords());
		final List<String> negative = new ArrayList<String>(custo.getNegativeWords());
		//		final List<Endorsement> endorsements = new ArrayList<Endorsement>(brotherhood.getEndorsements());
		//
		//		for (final Endorsement e : endorsements) {
		//			final String text = e.getComment();
		//			for (final String p : positive)
		//				if (text.contains(p))
		//					score++;
		//			for (final String n : negative)
		//				if (text.contains(n))
		//					score--;
		//		}
		//		brotherhood.setScore(score);
		return this.brotherhoodRepository.save(brotherhood);
	}

	public Brotherhood saveForTest(final Brotherhood bro) {

		// Restrictions
		Assert.isTrue(bro.getBan() != true);

		if (bro.getId() == 0) {
			final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			final String oldpass = bro.getUserAccount().getPassword();
			final String hash = encoder.encodePassword(oldpass, null);

			final UserAccount cuenta = bro.getUserAccount();
			cuenta.setPassword(hash);
			bro.setUserAccount(cuenta);

			//			final Box in1 = new Box();
			//			in1.setName("In");
			//			in1.setPredefined(true);
			//			final Box in = this.boxService.save(in1);
			//
			//			final Collection<Box> boxesPredefined = new ArrayList<Box>();
			//			boxesPredefined.add(in);
			//			cus.setBoxes(boxesPredefined);
		}
		if (bro.getId() == 0) {
			//			final Collection<Box> boxes = new ArrayList<>();
			//			final Box trash = new Box();
			//			final Box out = new Box();
			//			final Box spam = new Box();
			//			final Box in = new Box();
			//			trash.setName("Trash");
			//			in.setName("In");
			//			out.setName("Out");
			//			spam.setName("Spam");
			//			out.setPredefined(true);
			//			in.setPredefined(true);
			//			spam.setPredefined(true);
			//			trash.setPredefined(true);
			//			final Box box1 = this.boxService.save(trash);
			//			final Box box2 = this.boxService.save(in);
			//			final Box box3 = this.boxService.save(out);
			//			final Box box4 = this.boxService.save(spam);
			//
			//			boxes.add(box1);
			//			boxes.add(box2);
			//			boxes.add(box3);
			//			boxes.add(box4);
			//			cus.setBoxes(boxes);
		}

		return this.brotherhoodRepository.save(bro);
	}
	public Brotherhood findOne(final int brotherhoodId) {
		Brotherhood c;

		Assert.notNull(brotherhoodId);
		Assert.isTrue(brotherhoodId != 0);
		c = this.brotherhoodRepository.findOne(brotherhoodId);

		Assert.notNull(c);
		return c;
	}

	public Brotherhood reconstruct(final BrotherhoodForm brotherhoodForm, final BindingResult binding) {
		final Brotherhood brotherhood = this.create();

		Assert.isTrue(brotherhoodForm.isConditionsAccepted());
		final Authority bro = new Authority();
		bro.setAuthority(Authority.BROTHERHOOD);
		Assert.isTrue(brotherhoodForm.getUserAccount().getAuthorities().contains(bro));
		final Collection<Authority> colMem = new ArrayList<Authority>();
		colMem.add(bro);
		//Assert.isTrue(brotherhoodForm.getUserAccount().getAuthorities() == colMem);
		//Damos valores a los atributos de la hermandad con los datos que nos llegan

		brotherhood.setAddress(brotherhoodForm.getAddress());
		brotherhood.setEmail(brotherhoodForm.getEmail());
		brotherhood.setMiddleName(brotherhoodForm.getMiddleName());
		brotherhood.setName(brotherhoodForm.getName());
		brotherhood.setPhoneNumber(brotherhoodForm.getPhoneNumber());
		brotherhood.setPhoto(brotherhoodForm.getPhoto());
		brotherhood.setSurname(brotherhoodForm.getSurname());
		brotherhood.setUserAccount(brotherhoodForm.getUserAccount());

		brotherhood.setArea(brotherhoodForm.getArea());
		brotherhood.setStablishmentDate(brotherhoodForm.getStablishmentDate());
		brotherhood.setTitle(brotherhoodForm.getTitle());

		//		member.setFlagSpam(memberForm.isFlagSpam());
		//		member.setPolarityScore(memberForm.getPolarityScore());
		//		member.setBan(memberForm.getBan());

		brotherhood.setBan(false);

		return brotherhood;
	}


	@Autowired
	private Validator	validator;


	public Brotherhood reconstruct(final Brotherhood brotherhood, final BindingResult binding) {
		Brotherhood res;

		//Check authority
		final Authority a = new Authority();
		final UserAccount user = brotherhood.getUserAccount();
		a.setAuthority(Authority.BROTHERHOOD);
		Assert.isTrue(user.getAuthorities().contains(a) && user.getAuthorities().size() == 1);

		if (brotherhood.getId() == 0)
			res = brotherhood;
		else {
			res = this.brotherhoodRepository.findOne(brotherhood.getId());
			//			res.setBan(member.getBan());
			//			res.setFlagSpam(member.isFlagSpam());
			//			res.setBoxes(member.getBoxes());
			//			res.setEnrolements(member.getEnrolements());
			//			res.setRequests(member.getRequests());
			//			res.setSocialProfiles(member.getSocialProfiles());
			//res.setUserAccount(member.getUserAccount());
			res.setName(brotherhood.getName());
			res.setEmail(brotherhood.getEmail());
			res.setMiddleName(brotherhood.getMiddleName());
			res.setSurname(brotherhood.getSurname());
			res.setAddress(brotherhood.getAddress());
			res.setPhoneNumber(brotherhood.getPhoneNumber());
			res.setPhoto(brotherhood.getPhoto());
			res.setArea(brotherhood.getArea());

			this.validator.validate(res, binding);
		}
		return res;
	}
}
