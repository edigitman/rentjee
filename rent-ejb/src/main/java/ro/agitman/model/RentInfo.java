package ro.agitman.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rt_info")
public class RentInfo extends AbstractModel {

	private Long id;
	private RentDetails details;
	private BigDecimal sqrm;
	private Boolean pets;
	private String advertDetails;
	private Integer noRooms;
	private Integer noBaths;
	private Integer maxPersons;
	private Boolean furnished;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToOne
	@JoinColumn(name = "detailsId")
	public RentDetails getDetails() {
		return details;
	}

	public void setDetails(RentDetails details) {
		this.details = details;
	}

	public BigDecimal getSqrm() {
		return sqrm;
	}

	public void setSqrm(BigDecimal sqrm) {
		this.sqrm = sqrm;
	}

	public Boolean getPets() {
		return pets;
	}

	public void setPets(Boolean pets) {
		this.pets = pets;
	}

	public String getAdvertDetails() {
		return advertDetails;
	}

	public void setAdvertDetails(String advertDetails) {
		this.advertDetails = advertDetails;
	}

	public Integer getNoRooms() {
		return noRooms;
	}

	public void setNoRooms(Integer noRooms) {
		this.noRooms = noRooms;
	}

	public Integer getNoBaths() {
		return noBaths;
	}

	public void setNoBaths(Integer noBaths) {
		this.noBaths = noBaths;
	}

	public Integer getMaxPersons() {
		return maxPersons;
	}

	public void setMaxPersons(Integer maxPersons) {
		this.maxPersons = maxPersons;
	}

	public Boolean getFurnished() {
		return furnished;
	}

	public void setFurnished(Boolean furnished) {
		this.furnished = furnished;
	}

}
