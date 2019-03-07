
package domain;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
public class Position extends DomainEntity {

	private String	positionEsp;
	private String	positionEng;


	@NotBlank
	@SafeHtml
	public String getPositionEsp() {
		return this.positionEsp;
	}

	public void setPositionEsp(final String positionEsp) {
		this.positionEsp = positionEsp;
	}

	@NotBlank
	@SafeHtml
	public String getPositionEng() {
		return this.positionEng;
	}

	public void setPositionEng(final String positionEng) {
		this.positionEng = positionEng;
	}

}
