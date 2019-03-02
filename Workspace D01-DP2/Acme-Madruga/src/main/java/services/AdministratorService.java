
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import domain.Box;
import domain.SocialProfile;

@Service
@Transactional
public class AdministratorService {

	// Repository
	@Autowired
	private AdministratorRepository	administratorRepository;

	// Services

	@Autowired
	public ActorService				actorService;

	@Autowired
	public FinderService			finderService;


	public AdministratorService() {
		super();
	}

	// Simple CRUD

	// 8.1
	public Administrator create() {
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

		Administrator result;
		result = new Administrator();

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
		f.setAuthority(Authority.MEMBER);
		newUser.addAuthority(f);
		result.setUserAccount(newUser);

		result.setSocialProfiles(new ArrayList<SocialProfile>());
		result.setName("");
		result.setEmail("");
		result.setAddress("");
		result.setSurname("");
		result.setPhoneNumber("");
		result.setPhoto("");

		// Administrator

		return result;
	}

	// Returns logged administrator
	public Administrator findByPrincipal() {
		Administrator res;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = this.findByUserAccount(userAccount);
		Assert.notNull(res);

		return res;
	}

	// 11.1
	//	public Administrator findByFixUpTask(final FixUpTask fixUpTask) {
	//		// Logged user must be a handyWorker
	//		final Authority a = new Authority();
	//		final UserAccount user = LoginService.getPrincipal();
	//		a.setAuthority(Authority.HANDYWORKER);
	//		Assert.isTrue(user.getAuthorities().contains(a));
	//
	//		Administrator res;
	//		res = this.administratorRepository.findFixUpTask(fixUpTask.getId());
	//		return res;
	//	}

	// Returns logged administrator from his or her userAccount
	public Administrator findByUserAccount(final UserAccount userAccount) {
		Administrator res;
		Assert.notNull(userAccount);

		res = this.administratorRepository.findByUserAccountId(userAccount.getId());

		return res;
	}

	public Collection<Administrator> findAll() {
		return this.administratorRepository.findAll();
	}

	public Administrator save(final Administrator mem) {

		// Restrictions
		Assert.isTrue(mem.getBan() != true);

		if (mem.getId() == 0) {
			final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			final String oldpass = mem.getUserAccount().getPassword();
			final String hash = encoder.encodePassword(oldpass, null);

			final UserAccount cuenta = mem.getUserAccount();
			cuenta.setPassword(hash);
			mem.setUserAccount(cuenta);

			//			final Box in1 = new Box();
			//			in1.setName("In");
			//			in1.setPredefined(true);
			//			final Box in = this.boxService.save(in1);
			//
			//			final Collection<Box> boxesPredefined = new ArrayList<Box>();
			//			boxesPredefined.add(in);
			//			cus.setBoxes(boxesPredefined);

		}
		return this.administratorRepository.save(mem);
	}
	public Administrator findOne(final int administratorId) {
		Administrator c;

		Assert.notNull(administratorId);
		Assert.isTrue(administratorId != 0);
		c = this.administratorRepository.findOne(administratorId);

		Assert.notNull(c);
		return c;
	}
}