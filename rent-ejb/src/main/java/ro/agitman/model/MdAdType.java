package ro.agitman.model;

import javax.persistence.*;

@Entity
@Table(name = "md_ad_type")
@NamedQueries({
		@NamedQuery(name = MdAdType.FIND_ALL, query = "select c from MdAdType c")
})
public class MdAdType extends AbstractModel {

	public static final String FIND_ALL = "MdAdType.findAll";

	private Long id;
	private String name;
	private Integer ordering;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrdering() {
		return ordering;
	}

	public void setOrdering(Integer ordering) {
		this.ordering = ordering;
	}

}
