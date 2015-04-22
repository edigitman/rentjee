package ro.agitman.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Created by Edi on 11/12/2014.
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Advert.findAll", query = "select a from Advert a"),
		@NamedQuery(name = Advert.FIND_FOR_USER, query = "select a from Advert a where a.user = :user"),
		@NamedQuery(name = Advert.FIND_FAV_BY_USER, query = "select a from Advert a where a.user = :user")
// @NamedQuery(name = "findActive", query =
// "select a from Advert a where a.state = 1")
})
@Table(name = "rt_advert")
public class Advert extends AbstractModel {

	public static final String FIND_FOR_USER = "Advert.findForUser";
	public static final String FIND_FAV_BY_USER = "Advert.findFavForUser";

	private Long id;
	private User user;
	private String description;
	private Address address;
	private MdBuildingType buildingType;
	private RentInterval interval;
	private RentValue value;
	private RentInfo info;
	private List<EstimationUnit> estimationUnits;
	private Date dateCreated;
	private Date dateExpires;
	private List<Image> imageList = new ArrayList<>();
	private MdAdType adType;
	private long dotari;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Basic()
	@Temporal(TemporalType.DATE)
	public Date getDateExpires() {
		return dateExpires;
	}

	public void setDateExpires(Date dateExpires) {
		this.dateExpires = dateExpires;
	}

	@OneToMany(mappedBy = "advert", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<Image> getImageList() {
		return imageList;
	}

	public void setImageList(List<Image> imageList) {
		this.imageList = imageList;
	}

	@ManyToOne
	@JoinColumn(name = "typeId")
	public MdAdType getAdType() {
		return adType;
	}

	public void setAdType(MdAdType adType) {
		this.adType = adType;
	}
}
