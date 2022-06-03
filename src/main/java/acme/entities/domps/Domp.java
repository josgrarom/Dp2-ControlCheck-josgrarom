package acme.entities.domps;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.items.Item;
import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Domp extends AbstractEntity{
	
	protected static final long serialVersionUID = 1L;
	
	@NotNull
	@Pattern(regexp = "^\\w{2,4}:[0-9]{2}:[0-9]{4}$")
	protected String code;

	@NotNull
	@Past
    @Temporal(TemporalType.TIMESTAMP)
    protected Date creationMoment;
	
	@NotNull
    @Temporal(TemporalType.TIMESTAMP)
	protected Date startDate;
	 
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date endDate;
	
	@NotBlank
	@Length(min=1, max = 100)
	protected String subject;
	
	@NotBlank
	@Length(min=1, max = 255)
	protected String summary;
	
	@NotNull
	@Valid
	protected Money helping;

	@URL
	protected String furtherInfo;
	
	@NotNull
	@Valid
	@OneToOne(optional = false)
	@JoinColumn(unique = true)
	protected Item	item;
	
}
