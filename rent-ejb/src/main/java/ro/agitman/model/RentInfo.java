package ro.agitman.model;

import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table(name = "rt_info")
public class RentInfo extends AbstractModel {

	private Long id;
	private BigDecimal sqrm;
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

	public BigDecimal getSqrm() {
		return sqrm;
	}

	public void setSqrm(BigDecimal sqrm) {
		this.sqrm = sqrm;
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
