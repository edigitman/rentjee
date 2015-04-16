package ro.agitman.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rt_value")
public class RentValue extends AbstractModel {

	private Long id;
	private BigDecimal value;
	private BigDecimal deposit;
	private Boolean negotiable;
	private MdCurrency currency;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getDeposit() {
		return deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public Boolean getNegotiable() {
		return negotiable;
	}

	public void setNegotiable(Boolean negotiable) {
		this.negotiable = negotiable;
	}

	@ManyToOne
	@JoinColumn(name = "currencyId")
	public MdCurrency getCurrency() {
		return currency;
	}

	public void setCurrency(MdCurrency currency) {
		this.currency = currency;
	}

}
