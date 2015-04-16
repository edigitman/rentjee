package ro.agitman.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rt_details")
public class RentDetails extends AbstractModel {


	private Long id;
	private MdHeatSource heatSource;
	private Boolean gaz;
	private Boolean parchet;
	private Boolean trmopan;
	private Boolean gresie;
	private Boolean faianta;
	private Boolean usaMetal;
	private Boolean cabluTv;
	private Boolean cabluInternet;
	private Boolean apometre;
	private Boolean ac;
	private Boolean aragaz;
	private Boolean frigider;
	private Boolean tv;
	private Boolean masinaVase;
	private Boolean masinaRufe;
	private Boolean uscatorRufe;
	private Boolean balcon;
	private Boolean termopane;
	private Boolean lift;
	private Boolean cuptorMicroUnde;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getGaz() {
		return gaz;
	}

	public void setGaz(Boolean gaz) {
		this.gaz = gaz;
	}

	public Boolean getParchet() {
		return parchet;
	}

	public void setParchet(Boolean parchet) {
		this.parchet = parchet;
	}

	public Boolean getTrmopan() {
		return trmopan;
	}

	public void setTrmopan(Boolean trmopan) {
		this.trmopan = trmopan;
	}

	public Boolean getGresie() {
		return gresie;
	}

	public void setGresie(Boolean gresie) {
		this.gresie = gresie;
	}

	public Boolean getFaianta() {
		return faianta;
	}

	public void setFaianta(Boolean faianta) {
		this.faianta = faianta;
	}

	public Boolean getUsaMetal() {
		return usaMetal;
	}

	public void setUsaMetal(Boolean usaMetal) {
		this.usaMetal = usaMetal;
	}

	public Boolean getCabluTv() {
		return cabluTv;
	}

	public void setCabluTv(Boolean cabluTv) {
		this.cabluTv = cabluTv;
	}

	public Boolean getCabluInternet() {
		return cabluInternet;
	}

	public void setCabluInternet(Boolean cabluInternet) {
		this.cabluInternet = cabluInternet;
	}

	public Boolean getApometre() {
		return apometre;
	}

	public void setApometre(Boolean apometre) {
		this.apometre = apometre;
	}

	public Boolean getAc() {
		return ac;
	}

	public void setAc(Boolean ac) {
		this.ac = ac;
	}

	@ManyToOne
	@JoinColumn(name = "heatId")
	public MdHeatSource getHeatSource() {
		return heatSource;
	}

	public void setHeatSource(MdHeatSource heatSource) {
		this.heatSource = heatSource;
	}

	public Boolean getAragaz() {
		return aragaz;
	}

	public void setAragaz(Boolean aragaz) {
		this.aragaz = aragaz;
	}

	public Boolean getFrigider() {
		return frigider;
	}

	public void setFrigider(Boolean frigider) {
		this.frigider = frigider;
	}

	public Boolean getTv() {
		return tv;
	}

	public void setTv(Boolean tv) {
		this.tv = tv;
	}

	public Boolean getMasinaVase() {
		return masinaVase;
	}

	public void setMasinaVase(Boolean masinaVase) {
		this.masinaVase = masinaVase;
	}

	public Boolean getMasinaRufe() {
		return masinaRufe;
	}

	public void setMasinaRufe(Boolean masinaRufe) {
		this.masinaRufe = masinaRufe;
	}

	public Boolean getUscatorRufe() {
		return uscatorRufe;
	}

	public void setUscatorRufe(Boolean uscatorRufe) {
		this.uscatorRufe = uscatorRufe;
	}

	public Boolean getBalcon() {
		return balcon;
	}

	public void setBalcon(Boolean balcon) {
		this.balcon = balcon;
	}

	public Boolean getTermopane() {
		return termopane;
	}

	public void setTermopane(Boolean termopane) {
		this.termopane = termopane;
	}

	public Boolean getLift() {
		return lift;
	}

	public void setLift(Boolean lift) {
		this.lift = lift;
	}

	public Boolean getCuptorMicroUnde() {
		return cuptorMicroUnde;
	}

	public void setCuptorMicroUnde(Boolean cuptorMicroUnde) {
		this.cuptorMicroUnde = cuptorMicroUnde;
	}

}
