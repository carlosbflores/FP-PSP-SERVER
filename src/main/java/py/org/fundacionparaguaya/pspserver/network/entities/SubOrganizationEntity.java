package py.org.fundacionparaguaya.pspserver.network.entities;


import com.google.common.base.MoreObjects;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import py.org.fundacionparaguaya.pspserver.common.entities.LocalDateTimeConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Table(name = "sub_organizations", schema = "ps_network")
public class SubOrganizationEntity {

    @Id
    @GenericGenerator(
            name = "subOrganizationsSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = SequenceStyleGenerator.SCHEMA,
                            value = "ps_network"),
                    @Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM,
                            value = "sub_organizations_id_seq"),
                    @Parameter(name = SequenceStyleGenerator.INITIAL_PARAM,
                            value = "1"),
                    @Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM,
                            value = "1")
            }
    )
    @GeneratedValue(generator = "subOrganizationsSequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    private String description;

    @ManyToOne(targetEntity = OrganizationEntity.class)
    @JoinColumn(name = "organization_id")
    private OrganizationEntity organization;

    @ManyToOne(targetEntity = OrganizationEntity.class)
    @JoinColumn(name = "sub_organization_id")
    private OrganizationEntity subOrganization;

    @Column(name = "created_date")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdDate;

    @ManyToOne(targetEntity = ApplicationEntity.class)
    @JoinColumn(name = "application_id")
    private ApplicationEntity application;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OrganizationEntity getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationEntity organization) {
        this.organization = organization;
    }

    public OrganizationEntity getSubOrganization() {
        return subOrganization;
    }

    public void setSubOrganization(OrganizationEntity subOrganization) {
        this.subOrganization = subOrganization;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public ApplicationEntity getApplication() {
        return application;
    }

    public void setApplication(ApplicationEntity application) {
        this.application = application;
    }

    @PrePersist
    public void preSave() {
        this.createdDate = LocalDateTime.now(ZoneId.of("GMT+00:00"));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (id == null || obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SubOrganizationEntity toCompare = (SubOrganizationEntity) obj;
        return id.equals(toCompare.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("description", description)
                .add("organization", organization)
                .add("application",application)
                .toString();
    }

    public static SubOrganizationEntity of() {
        return new SubOrganizationEntity();
    }
}
