package ro.agitman.model;

import javax.persistence.*;

/**
 * Created by AlexandruG on 4/24/2015.
 */
@Entity
@Table(name = "md_neighborhood")
@NamedQuery(name = MdNeighborhood.FIND_BY_CITY, query = "select n from MdNeighborhood n where n.city = :city")
public class MdNeighborhood extends AbstractModel {

    public static final String FIND_BY_CITY = "MdNeighborhood.findByCity";

    private Long id;
    private String name;
    private MdCity city;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne
    @JoinColumn(name = "city_id")
    public MdCity getCity() {
        return city;
    }

    public void setCity(MdCity city) {
        this.city = city;
    }

}
