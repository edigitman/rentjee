package ro.agitman.model;

import javax.persistence.*;

@Entity
@Table(name = "md_building_type")
@NamedQueries({
		@NamedQuery(name = MdBuildingType.FIND_ALL, query = "select c from MdBuildingType c")
})
public class MdBuildingType extends AbstractModel {

	public static final String FIND_ALL = "MdBuildingType.findAll";


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
