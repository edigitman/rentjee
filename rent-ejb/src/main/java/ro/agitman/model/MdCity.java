package ro.agitman.model;

import javax.persistence.*;

@Entity
@Table(name = "md_city")
@NamedQueries({
		@NamedQuery(name = MdCity.FIND_ALL, query = "select c from MdCity c")
})
public class MdCity extends AbstractModel {

	public static final String FIND_ALL = "MdCity.findAll";


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
