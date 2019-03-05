
package services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountRepository;
import domain.Actor;
import domain.Box;

@Service
@Transactional
public class ActorService {

	//Repository
	@Autowired
	public ActorRepository			actorRepository;

	@Autowired
	public UserAccountRepository	userAccountRepository;

	@Autowired
	private BoxService				mbs;

	@Autowired
	private CustomisationService	cs;


	//Constructor
	public ActorService() {
		super();
	}

	//Simple CRUD methods
	//Returns logged actor
	public Actor findByPrincipal() {
		Actor res;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = this.findByUserAccount(userAccount);
		Assert.notNull(res);

		return res;
	}

	//Returns logged actor from his or her userAccount
	public Actor findByUserAccount(final UserAccount userAccount) {
		Actor res;
		Assert.notNull(userAccount);
		Assert.notNull(userAccount.getId());

		res = this.actorRepository.findByUserAccountId(userAccount.getId());

		return res;
	}

	public boolean checkBrotherhood() {
		boolean res;
		final Authority a = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.BROTHERHOOD);
		res = user.getAuthorities().contains(a);
		return res;
	}

	public boolean checkMember() {
		boolean res;
		final Authority a = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.MEMBER);
		res = user.getAuthorities().contains(a);
		return res;
	}

	public Collection<Actor> findAll() {
		return this.actorRepository.findAll();
	}
	public Actor findOne(final int actorId) {
		return this.actorRepository.findOne(actorId);
	}
	public Actor save(final Actor actor) {
		return this.actorRepository.save(actor);
	}
	public void delete(final Actor actor) {
		this.actorRepository.delete(actor);
	}

	public Collection<Box> getMyBoxes() {
		final UserAccount actual = LoginService.getPrincipal();
		final Actor a = this.actorRepository.getActor(actual);
		return a.getBoxes();
	}

	public Box editBox(final Box m) {
		final UserAccount actual = LoginService.getPrincipal();
		final Actor a = this.actorRepository.getActor(actual);

		Assert.isTrue(!a.getBan());

		//Compruebo que no se produce ninguna redundancia Padre-Hijo:
		boolean fine = true;
		final Collection<Box> desc = m.getDescendants();
		if (desc != null)
			for (final Box b : desc) {
				if (b.getId() == m.getId()) {
					fine = false;
					break;
				}
				for (final Box b2 : b.getDescendants())
					if (b2.getId() == m.getId()) {
						fine = false;
						break;
					}

			}
		Assert.isTrue(fine);
		//Fin de la comprobación

		final Box result = this.mbs.save(m);

		return result;

	}

	public void deleteMessageBox(final Box m) {
		final UserAccount actual = LoginService.getPrincipal();
		final Actor a = this.actorRepository.getActor(actual);

		Assert.isTrue(a.getBoxes().contains(m));
		Assert.isTrue(!a.getBan());

		Assert.isTrue(!m.getPredefined());
		final Collection<Box> actorBoxes = a.getBoxes();
		for (final Box b : actorBoxes)
			if (b.getDescendants().contains(m))
				b.getDescendants().remove(m);
		a.setBoxes(actorBoxes);
		actorBoxes.remove(m);
		a.setBoxes(actorBoxes);
		this.mbs.delete(m);

	}

	public boolean checkspammer(final String s) {
		boolean res = false;
		final List<String> spamwords = this.cs.getCustomisation().getSpamWords();

		for (final String spamword : spamwords)
			if (s.contains(spamword)) {
				final UserAccount actual = LoginService.getPrincipal();
				final Actor a = this.actorRepository.getActor(actual);
				a.setFlagSpam(true);
				res = true;
			}

		return res;
	}
}
