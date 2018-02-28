package py.org.fundacionparaguaya.pspserver.surveys.dtos;
/*
 * FP-PSP Server
 * A sample API to manage surveys
 *
 * OpenAPI spec version: 1.0.0
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import py.org.fundacionparaguaya.pspserver.network.dtos.OrganizationDTO;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * SurveyDefinition
 */
@Access(AccessType.FIELD)
public class SurveyDefinition implements StoreableDefinition, Serializable {

    private Long id;

    private String title;

    private String description;

    @JsonProperty("survey_schema")
    private SurveySchema surveySchema = null;

    @JsonProperty("survey_ui_schema")
    private SurveyUISchema surveyUISchema = null;

    @JsonProperty("created_at")
    private String createdAt = null;

    @JsonProperty("last_modified_at")
    private String lastModifiedAt = null;

    @JsonProperty("user_created")
    private Integer userCreated = null;

    @JsonProperty("organizations")
    private List<OrganizationDTO> organizations;


    public SurveyDefinition surveySchema(SurveySchema surveySchema) {
        this.surveySchema = surveySchema;
        return this;
    }

    /**
     * Get surveySchema
     * @return surveySchema
     **/
    @JsonProperty("survey_schema")
    @ApiModelProperty(required = true, value = "The schema of this survey."
            + "For each key/value pair, defines the type and other attributes")
    @NotNull
    public SurveySchema getSurveySchema() {
        return surveySchema;
    }

    public void setSurveySchema(SurveySchema surveySchema) {
        this.surveySchema = surveySchema;
    }

    public SurveyDefinition surveyUiSchema(SurveyUISchema surveyUISchema) {
        this.surveyUISchema = surveyUISchema;
        return this;
    }

    /**
     * Get surveyUISchema
     * @return surveyUISchema
     **/
    @JsonProperty("survey_ui_schema")
    @ApiModelProperty(required = true, value = "The UI schema of this survey. Similar"
            + "to survey_schema, this property describes the attributes of this survey"
            + "that should be taken into consideration when rendering this survey.")
    @NotNull
    public SurveyUISchema getSurveyUISchema() {
        return surveyUISchema;
    }

    public void setSurveyUISchema(SurveyUISchema surveyUISchema) {
        this.surveyUISchema = surveyUISchema;
    }

    public SurveyDefinition createdAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    /**
     * [ISO 8601](https://es.wikipedia.org/wiki/ISO_8601) formatted creation date
     * @return createdAt
     **/
    @JsonProperty("created_at")
    @ApiModelProperty(value = "[ISO 8601](https://es.wikipedia.org/wiki/ISO_8601) formatted creation date")
    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public SurveyDefinition lastModifiedAt(String lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
        return this;
    }


    public SurveyDefinition id(Long id) {
        this.id = id;
        return this;
    }

    /**
     * [ISO 8601](https://es.wikipedia.org/wiki/ISO_8601) formatted modification date
     * @return lastModifiedAt
     **/
    @JsonProperty("last_modified_at")
    @ApiModelProperty(value = "[ISO 8601](https://es.wikipedia.org/wiki/ISO_8601) formatted modification date")
    public String getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(String lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    public SurveyDefinition userCreated(Integer userCreated) {
        this.userCreated = userCreated;
        return this;
    }

    /**
     * the id of the user that created this definition
     * @return userCreated
     **/
    @JsonProperty("user_created")
    @ApiModelProperty(value = "the id of the user that created this definition")
    public Integer getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(Integer userCreated) {
        this.userCreated = userCreated;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SurveyDefinition surveyDefinition = (SurveyDefinition) o;
        return Objects.equals(this.surveySchema, surveyDefinition.surveySchema)
                && Objects.equals(this.surveyUISchema, surveyDefinition.surveyUISchema)
                && Objects.equals(this.createdAt, surveyDefinition.createdAt)
                && Objects.equals(this.lastModifiedAt, surveyDefinition.lastModifiedAt)
                && Objects.equals(this.userCreated, surveyDefinition.userCreated)
                && Objects.equals(this.organizations, surveyDefinition.organizations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surveySchema, surveyUISchema, createdAt, lastModifiedAt, userCreated);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SurveyDefinition {\n");

        sb.append("    surveySchema: ").append(toIndentedString(surveySchema)).append("\n");
        sb.append("    surveyUISchema: ").append(toIndentedString(surveyUISchema)).append("\n");
        sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
        sb.append("    lastModifiedAt: ").append(toIndentedString(lastModifiedAt)).append("\n");
        sb.append("    userCreated: ").append(toIndentedString(userCreated)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SurveyDefinition surveyUISchema(SurveyUISchema surveyUISchema) {
        this.surveyUISchema = surveyUISchema;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SurveyDefinition title(String title) {
        this.title = title;
        return this;
    }

    public SurveyDefinition description(String description) {
        this.description = description;
        return this;
    }

    public SurveyDefinition organizations(List<OrganizationDTO> organizations){
        this.organizations = organizations;
        return this;
    }

    public List<OrganizationDTO> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<OrganizationDTO> organizations) {
        this.organizations = organizations;
    }

}


