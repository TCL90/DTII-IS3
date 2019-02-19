
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Procession extends DomainEntity {

	private String	title;
	private String	description;
	private Date	departureDate;
	private String	ticker;
	private Boolean	finalMode;


	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@Temporal(TemporalType.DATE)
	@NotNull
	public Date getdepartureDate() {
		return this.departureDate;
	}

	public void setdepartureDate(final Date departureDate) {
		this.departureDate = departureDate;
	}

	@Column(unique = true)
	@Pattern(regexp = "^([0][0-9]|[1][0-9])(0[0-9]|1[0-2])(0[0-9]|[12][0-9]|3[01])-[A-Z0-9_]{6}$")
	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}

	@NotNull
	public Boolean getFinalMode() {
		return this.finalMode;
	}

	public void setFinalMode(final Boolean finalMode) {
		this.finalMode = finalMode;
	}


	//Relaciones
	private Collection<FloatMadruga>	floats;


	@ManyToMany
	public Collection<FloatMadruga> getFloats() {
		return this.floats;
	}

	public void setFloats(final Collection<FloatMadruga> floats) {
		this.floats = floats;
	}

}
