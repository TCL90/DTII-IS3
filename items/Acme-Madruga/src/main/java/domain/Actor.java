
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {
	@Index(columnList = "flagSpam")
})
public abstract class Actor extends DomainEntity {

	private String	name;
	private String	email;
	private String	phoneNumber;
	private String	address;
	private boolean	ban;
	private String	middleName;
	private String	surname;
	private String	photo;
	private boolean	flagSpam;
	private double	polarityScore;


	@NotBlank
	public String getName() {
		return this.name;
	}
	public void setName(final String name) {
		this.name = name;
	}

	@NotBlank
	public String getEmail() {
		return this.email;
	}
	public void setEmail(final String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@NotBlank
	public String getAddress() {
		return this.address;
	}
	public void setAddress(final String address) {
		this.address = address;
	}

	@NotNull
	public boolean getBan() {
		return this.ban;
	}
	public void setBan(final boolean ban) {
		this.ban = ban;
	}

	public String getMiddleName() {
		return this.middleName;
	}
	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}

	@NotBlank
	public String getSurname() {
		return this.surname;
	}
	public void setSurname(final String surname) {
		this.surname = surname;
	}

	@URL
	public String getPhoto() {
		return this.photo;
	}
	public void setPhoto(final String photo) {
		this.photo = photo;
	}

	public boolean isFlagSpam() {
		return this.flagSpam;
	}

	public void setFlagSpam(final boolean flagSpam) {
		this.flagSpam = flagSpam;
	}

	public double getPolarityScore() {
		return this.polarityScore;
	}

	public void setPolarityScore(final double polarityScore) {
		this.polarityScore = polarityScore;
	}


	//Relationships

	private Collection<SocialProfile>	socialProfiles;


	@Valid
	@OneToMany(cascade = CascadeType.ALL)
	public Collection<SocialProfile> getSocialProfiles() {
		return this.socialProfiles;
	}
	public void setSocialProfiles(final Collection<SocialProfile> socialProfiles) {
		this.socialProfiles = socialProfiles;
	}


	private UserAccount	userAccount;


	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	public UserAccount getUserAccount() {
		return this.userAccount;
	}
	public void setUserAccount(final UserAccount userAccount) {
		this.userAccount = userAccount;
	}


	private Collection<Box>	boxes;


	@OneToMany
	public Collection<Box> getBoxes() {
		return this.boxes;
	}
	public void setBoxes(final Collection<Box> boxes) {
		this.boxes = boxes;
	}

}
