package ro.agitman.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "rt_interval")
public class RentInterval extends AbstractModel {

	private Long id;
	private Date fromDate;
	private Boolean fromNow;
	private Date untilDate;
	private Boolean untilUndefined;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Basic
	@Temporal(TemporalType.DATE)
	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Boolean getFromNow() {
		return fromNow;
	}

	public void setFromNow(Boolean fromNow) {
		this.fromNow = fromNow;
	}

	@Basic
	@Temporal(TemporalType.DATE)
	public Date getUntilDate() {
		return untilDate;
	}

	public void setUntilDate(Date untilDate) {
		this.untilDate = untilDate;
	}

	public Boolean getUntilUndefined() {
		return untilUndefined;
	}

	public void setUntilUndefined(Boolean untilUndefined) {
		this.untilUndefined = untilUndefined;
	}

}
