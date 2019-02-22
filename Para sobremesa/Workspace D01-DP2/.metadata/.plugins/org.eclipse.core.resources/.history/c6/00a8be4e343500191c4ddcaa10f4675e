
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@Access(AccessType.PROPERTY)
public class Member extends Actor {

	//Relationships
	private Collection<Enrolement>	enrolements;
	private Collection<Request>		requests;
	private Finder					finder;


	@OneToMany
	public Collection<Enrolement> getEnrolements() {
		return this.enrolements;
	}

	public void setEnrolements(final Collection<Enrolement> enrolements) {
		this.enrolements = enrolements;
	}

	@OneToMany
	public Collection<Request> getRequests() {
		return this.requests;
	}

	public void setRequests(final Collection<Request> requests) {
		this.requests = requests;
	}

	@OneToOne(optional = false)
	public Finder getFinder() {
		return this.finder;
	}

	public void setFinder(final Finder finder) {
		this.finder = finder;
	}

}
