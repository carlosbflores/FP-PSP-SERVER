package py.org.fundacionparaguaya.pspserver.network.dtos;

import javax.validation.constraints.NotNull;

import com.google.common.base.MoreObjects;

import py.org.fundacionparaguaya.pspserver.system.dtos.CountryDTO;

public class OrganizationDTO {

	private Long id;

	@NotNull
	private String name;

	private Integer code;

	private String description;

	private boolean isActive;

	private CountryDTO country;

	private String information;

	private ApplicationDTO application;
	
	
	public OrganizationDTO() {}
	

	private OrganizationDTO(Long organizationId, String name, Integer code, String description, boolean isActive,
			CountryDTO country, String information, ApplicationDTO application) {
		this.id = organizationId;
		this.name = name;
		this.code = code;
		this.description = description;
		this.isActive = isActive;
		this.country = country;
		this.information = information;
		this.application = application;
	}

	public static class Builder {
		private Long id;
		private String name;
		private Integer code;
		private String description;
		private boolean isActive;
		private CountryDTO country;
		private String information;
		private ApplicationDTO application;
		
		public Builder id(Long organizationId) {
			this.id = organizationId;
			return this;
		}
		
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		
		public Builder code(Integer code) {
			this.code = code;
			return this;
		}
		
		public Builder description(String description) {
			this.description = description;
			return this;
		}
		
		public Builder isActive(boolean isActive) {
			this.isActive = isActive;
			return this;
		}
		
		public Builder country(CountryDTO country) {
			this.country = country;
			return this;
		}
		
		public Builder information(String information) {
			this.information = information;
			return this;
		}
		
		public Builder application(ApplicationDTO application) {
			this.application = application;
			return this;
		}
		
		public OrganizationDTO build() {
			return new OrganizationDTO(id, name, code, description, isActive, country, information, application);
		}
	}
	
	public static Builder builder() {
		return new Builder();
	}

	public Long getId() {
		return id;
	}


	public void setId(Long organizationId) {
		this.id = organizationId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getCode() {
		return code;
	}


	public void setCode(Integer code) {
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


	public CountryDTO getCountry() {
		return country;
	}


	public void setCountry(CountryDTO country) {
		this.country = country;
	}


	public String getInformation() {
		return information;
	}


	public void setInformation(String information) {
		this.information = information;
	}


	public ApplicationDTO getApplication() {
		return application;
	}


	public void setApplication(ApplicationDTO application) {
		this.application = application;
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", id)
				.add("name", name)
				.add("code", code)
				.add("description", description)
				.add("isActive", isActive)
				.add("country", country.toString())
				.add("information", information)
				.add("application", application.toString())
				.toString();
	}

	
}