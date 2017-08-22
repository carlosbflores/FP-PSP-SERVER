package py.org.fundacionparaguaya.pspserver.network.application.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import py.org.fundacionparaguaya.pspserver.base.BaseEntity;
import py.org.fundacionparaguaya.pspserver.system.city.domain.City;
import py.org.fundacionparaguaya.pspserver.system.country.domain.Country;

/**
 * Application DAO Layer
 * 
 * <p>
 * This class represents the Application mapped with the database table
 * <p>
 * 
 * @author Marcos Cespedes
 * @since 2017-08-14
 * @version 1.0
 *
 */
@Entity
@Table(name = "application", schema = "ps_network")
public class Application extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ps_network.application_application_id_seq")
    @SequenceGenerator(name="ps_network.application_application_id_seq", sequenceName="ps_network.application_application_id_seq", allocationSize=1)
	@Column(name = "application_id")
	private Long applicationId;
	
	private String name;
	
	private String code;
	
	private String description;
	
	private boolean isActive;
	
	@ManyToOne(targetEntity = Country.class)
	@JoinColumn(name = "country")
	private Country country;
	
	@ManyToOne(targetEntity = City.class)
	@JoinColumn(name = "city")
	private City city;
	
	private String information;
	
	private boolean isHub;
	
	private boolean isDirect;

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public boolean isHub() {
		return isHub;
	}

	public void setHub(boolean isHub) {
		this.isHub = isHub;
	}

	public boolean isDirect() {
		return isDirect;
	}

	public void setDirect(boolean isDirect) {
		this.isDirect = isDirect;
	}

	@Override
	public String toString() {
		return "Application [applicationId=" + applicationId + ", name=" + name + ", code=" + code + ", description="
				+ description + ", isActive=" + isActive + ", country=" + country + ", city=" + city + ", information="
				+ information + ", isHub=" + isHub + ", isDirect=" + isDirect + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (applicationId == null || obj == null || getClass() != obj.getClass())
			return false;
		Application toCompare = (Application) obj;
		return applicationId.equals(toCompare.applicationId);
	}
	
	@Override
	public int hashCode() {
		return applicationId == null ? 0 : applicationId.hashCode();
	}
	
}
