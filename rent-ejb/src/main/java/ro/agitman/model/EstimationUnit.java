package ro.agitman.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "rt_estimated_unit")
public class EstimationUnit extends AbstractModel {

	private Long id;
	private Advert advert;
	private String name;
	private Date fromDate;
	private Date toDate;
	private BigDecimal value;
	private MdCurrency currency;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Basic
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Basic
	@Temporal(TemporalType.DATE)
	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	@Basic
	@Temporal(TemporalType.DATE)
	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	@Basic
	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@ManyToOne
	@JoinColumn(name = "currencyId")
	public MdCurrency getCurrency() {
		return currency;
	}

	public void setCurrency(MdCurrency currency) {
		this.currency = currency;
	}

	@ManyToOne
	@JoinColumn(name = "advert_id")
	public Advert getAdvert() {
		return advert;
	}

	public void setAdvert(Advert advert) {
		this.advert = advert;
	}
}
