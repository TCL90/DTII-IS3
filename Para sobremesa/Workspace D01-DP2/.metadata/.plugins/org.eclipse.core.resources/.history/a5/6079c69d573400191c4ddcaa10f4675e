
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

@Entity
@Access(AccessType.PROPERTY)
public class Enrolement {

	private Date	enrolMoment;
	private Date	dropOutMoment;


	@Past
	@NotNull
	public Date getEnrolMoment() {
		return this.enrolMoment;
	}

	public void setEnrolMoment(final Date enrolMoment) {
		this.enrolMoment = enrolMoment;
	}

	@Past
	public Date getDropOutMoment() {
		return this.dropOutMoment;
	}

	public void setDropOutMoment(final Date dropOutMoment) {
		this.dropOutMoment = dropOutMoment;
	}


	//Relationships
	private Brotherhood	brotherhood;
	private Position	position;


	@ManyToOne(optional = false)
	public Brotherhood getBrotherhood() {
		return this.brotherhood;
	}

	public void setBrotherhood(final Brotherhood brotherhood) {
		this.brotherhood = brotherhood;
	}

	@ManyToOne(optional = false)
	public Position getPosition() {
		return this.position;
	}

	public void setPosition(final Position position) {
		this.position = position;
	}

}
