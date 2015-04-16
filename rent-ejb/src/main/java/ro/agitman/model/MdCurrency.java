package ro.agitman.model;

import javax.persistence.*;

@Entity
@Table(name = "md_currency")
@NamedQueries({
		@NamedQuery(name = MdCurrency.FIND_ALL, query = "select c from MdCurrency c")
})
public class MdCurrency extends AbstractModel {

	public static final String FIND_ALL = "MdCurrency.findAll";


	private Long id;

	private String name;
	private String symbol;
	private String acronim;
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

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getAcronim() {
		return acronim;
	}

	public void setAcronim(String acronim) {
		this.acronim = acronim;
	}

	public Integer getOrdering() {
		return ordering;
	}

	public void setOrdering(Integer ordering) {
		this.ordering = ordering;
	}

}
