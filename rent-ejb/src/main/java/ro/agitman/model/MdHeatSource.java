package ro.agitman.model;

import javax.persistence.*;

@Entity
@Table(name = "md_heat_source")
@NamedQueries({
        @NamedQuery(name = MdHeatSource.FIND_ALL, query = "select c from MdHeatSource c")
})
public class MdHeatSource extends AbstractModel {

    public static final String FIND_ALL = "MdHeatSource.findAll";


    private Long id;
    private String name;
    private Integer ordering;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
