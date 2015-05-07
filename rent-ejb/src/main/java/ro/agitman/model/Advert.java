package ro.agitman.model;

import ro.agitman.dto.AdTypeEnum;
import ro.agitman.dto.AdvertStatusEnum;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Edi on 11/12/2014.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = Advert.FIND_ALL, query = "select distinct a from Advert a where a.status in :stats"),
        @NamedQuery(name = Advert.FIND_FOR_USER, query = "select a from Advert a where a.user = :user"),
        @NamedQuery(name = Advert.FIND_FAV_BY_USER, query = "select a from Advert a JOIN a.favorites fs where fs.user = :user"),
        @NamedQuery(name = Advert.FIND_STATUS_TO_EXPIRED, query = "select a from Advert a where a.status = :status and a.statusUpdate <= :date"),
        @NamedQuery(name = Advert.FIND_STATUS_TO_REMOVED, query =
                "select a from Advert a where (a.status = :statusExpired and a.statusUpdate <= :dateExp) or (a.status = :statusRetired and a.statusUpdate <= :dateRet)")

// @NamedQuery(name = "findActive", query =
// "select a from Advert a where a.state = 1")
})
@Table(name = "rt_advert")
public class Advert extends AbstractModel {

    public static final String FIND_ALL = "Advert.findAll";
    public static final String FIND_FOR_USER = "Advert.findForUser";
    public static final String FIND_FAV_BY_USER = "Advert.findFavForUser";
    public static final String FIND_STATUS_TO_EXPIRED = "Advert.updateStatusToExpired";
    public static final String FIND_STATUS_TO_REMOVED = "Advert.updateStatus";

    private Long id;
    private User user;
    private String description;
    private Address address;
    private MdBuildingType buildingType;
    private RentInterval interval;
    private RentValue value;
    private RentInfo info;
    private List<EstimationUnit> estimationUnits;
    private List<Image> imageList = new ArrayList<>();
    private AdTypeEnum adType;
    private long dotari;
    private Boolean privateOwner;
    private List<RentFavorite> favorites;
    private AdvertStatusEnum status;
    private Date statusUpdate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "adusr")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "dotari")
    public long getDotari() {
        return dotari;
    }

    public void setDotari(long dotari) {
        this.dotari = dotari;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId")
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @ManyToOne
    @JoinColumn(name = "buildingTypeId")
    public MdBuildingType getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(MdBuildingType buildingType) {
        this.buildingType = buildingType;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "intervalId")
    public RentInterval getInterval() {
        return interval;
    }

    public void setInterval(RentInterval interval) {
        this.interval = interval;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "valueId")
    public RentValue getValue() {
        return value;
    }

    public void setValue(RentValue value) {
        this.value = value;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "infoId")
    public RentInfo getInfo() {
        return info;
    }

    public void setInfo(RentInfo info) {
        this.info = info;
    }

    @OneToMany(mappedBy = "advert", cascade = CascadeType.ALL)
    public List<EstimationUnit> getEstimationUnits() {
        return estimationUnits;
    }

    public void setEstimationUnits(List<EstimationUnit> estimationUnits) {
        this.estimationUnits = estimationUnits;
    }

    @Basic()
    @Temporal(TemporalType.DATE)
    public Date getStatusUpdate() {
        return statusUpdate;
    }

    public void setStatusUpdate(Date statusUpdate) {
        this.statusUpdate = statusUpdate;
    }

    @OneToMany(mappedBy = "advert", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "adType")
    public AdTypeEnum getAdType() {
        return adType;
    }

    public void setAdType(AdTypeEnum adType) {
        this.adType = adType;
    }

    @Basic
    @Column(name = "private_owned_bl")
    public Boolean getPrivateOwner() {
        return privateOwner;
    }

    public void setPrivateOwner(Boolean privateOwner) {
        this.privateOwner = privateOwner;
    }

    @OneToMany(mappedBy = "advert")
    public List<RentFavorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<RentFavorite> favorites) {
        this.favorites = favorites;
    }

    @Enumerated(EnumType.STRING)
    public AdvertStatusEnum getStatus() {
        return status;
    }

    public void setStatus(AdvertStatusEnum status) {
        this.status = status;
    }
}
