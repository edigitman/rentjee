package ro.agitman.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by edi on 4/23/2015.
 */
@Entity
@Table(name = "rt_favorites")
@NamedQueries({
        @NamedQuery(name = RentFavorite.FIND_ONE,
                query = "select r from RentFavorite r where r.advert = :advert and r.user = :user"),
        @NamedQuery(name = RentFavorite.DELETE_ONE,
                query = "DELETE FROM RentFavorite r WHERE r.advert = :advert and r.user = :user")
})

public class RentFavorite extends AbstractModel {

    public final static String FIND_ONE = "RentFavorite.findOne";
    public final static String DELETE_ONE = "RentFavorite.deleteOne";

    private Long id;
    private User user;
    private Advert advert;
    private Date dateCreated;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "userId")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "advertId")
    public Advert getAdvert() {
        return advert;
    }

    public void setAdvert(Advert advert) {
        this.advert = advert;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
