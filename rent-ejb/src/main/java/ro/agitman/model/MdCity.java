package ro.agitman.model;

import javax.persistence.*;
import java.util.List;

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
    private List<MdNeighborhood> neighborhoods;

    public MdCity() {
    }

    public MdCity(Long id) {
        this.id = id;
    }

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

    @OneToMany(mappedBy = "city")
    public List<MdNeighborhood> getNeighborhoods() {
        return neighborhoods;
    }

    public void setNeighborhoods(List<MdNeighborhood> neighborhoods) {
        this.neighborhoods = neighborhoods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MdCity city = (MdCity) o;

        if (!id.equals(city.id)) return false;
        if (name != null ? !name.equals(city.name) : city.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
    }
}
