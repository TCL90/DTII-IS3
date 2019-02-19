
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Brotherhood extends Actor {

	private String					title;
	private Date					stablishmentDate;
	private Collection<String>		urls;
	private Collection<Enrolement>	enrolements;


	@OneToMany(mappedBy = "brotherhood")
	public Collection<Enrolement> getEnrolements() {
		return this.enrolements;
	}

	public void setEnrolements(final Collection<Enrolement> enrolements) {
		this.enrolements = enrolements;
	}

	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotNull
	@Past
	public Date getStablishmentDate() {
		return this.stablishmentDate;
	}

	public void setStablishmentDate(final Date stablishmentDate) {
		this.stablishmentDate = stablishmentDate;
	}

	public Collection<String> getUrls() {
		return this.urls;
	}

	public void setUrls(final Collection<String> urls) {
		this.urls = urls;
	}

}
