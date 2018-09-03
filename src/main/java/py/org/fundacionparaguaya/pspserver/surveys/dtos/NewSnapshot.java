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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;
import io.swagger.annotations.ApiModelProperty;

import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toMap;


/**
 * NewSnapshot
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewSnapshot   {

    @JsonProperty("survey_id")
    private Long surveyId = null;

    @JsonProperty("survey_version_id")
    private Long surveyVersionId = null;

    @JsonProperty("organization_id")
    private Long organizationId = null;

    @JsonProperty("personal_survey_data")
    private SurveyData personalSurveyData = null;

    @JsonProperty("economic_survey_data")
    private SurveyData economicSurveyData = null;

    @JsonProperty("indicator_survey_data")
    private SurveyData indicatorSurveyData = null;

    @JsonProperty("user_name")
    private String userName = null;

    @JsonProperty("term_cond_id")
    private Long termCondId = null;

    @JsonProperty("priv_pol_id")
    private Long privPolId = null;

    @JsonProperty(value = "dependencies", required = false)
    private SurveyData dependencies;

    public SurveyData getDependencies() {
        return dependencies;
    }

    public void setDependencies(SurveyData dependencies) {
        this.dependencies = dependencies;
    }

    public NewSnapshot personalSurveyData(SurveyData surveyData) {
        this.personalSurveyData = surveyData;
        return this;
    }

    public NewSnapshot socioEconomicsSurveyData(SurveyData surveyData) {
        this.economicSurveyData = surveyData;
        return this;
    }

    public NewSnapshot indicatorsSurveyData(SurveyData surveyData) {
        this.indicatorSurveyData = surveyData;
        return this;
    }

    /**
     * Key/value pairs representing the filled out 'Socio Economics' survey
     * @return surveyData
     **/
    @ApiModelProperty(value = "Key/value pairs"
            + " representing the filled out 'Personal' survey")
    public SurveyData getPersonalSurveyData() {
        return personalSurveyData;
    }

    public void setPersonalSurveyData(SurveyData surveyData) {
        this.personalSurveyData = surveyData;
    }

    /**
     * Key/value pairs representing the filled out 'Socio Economics' survey
     * @return surveyData
     **/
    @ApiModelProperty(value = "Key/value pairs representing"
            + " the filled out 'Socio Economics' survey")
    public SurveyData getEconomicSurveyData() {
        return economicSurveyData;
    }

    public void setEconomicSurveyData(SurveyData surveyData) {
        this.economicSurveyData = surveyData;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * Key/value pairs representing the filled out 'Indicators' survey
     * @return surveyData
     **/
    @ApiModelProperty(value = "Key/value pairs representing"
            + " the filled out 'Indicators' survey")
    public SurveyData getIndicatorSurveyData() {
        return indicatorSurveyData;
    }

    public void setIndicatorSurveyData(SurveyData indicatorSurveyData) {
        this.indicatorSurveyData = indicatorSurveyData;
    }

    public Long getSurveyVersionId() {
        return surveyVersionId;
    }

    public void setSurveyVersionId(Long surveyVersionId) {
        this.surveyVersionId = surveyVersionId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userId) {
        this.userName = userId;
    }

    public Long getTermCondId() {
        return termCondId;
    }

    public void setTermCondId(Long termCondId) {
        this.termCondId = termCondId;
    }

    public Long getPrivPolId() {
        return privPolId;
    }

    public void setPrivPolId(Long privPolId) {
        this.privPolId = privPolId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class NewSnapshot {\n");
        sb.append("    surveyId: ")
        .append(toIndentedString(surveyId))
        .append("\n");
        sb.append("    surveyVersionId: ")
                .append(toIndentedString(surveyVersionId))
                .append("\n");
        sb.append("    organizationId: ")
        .append(toIndentedString(organizationId))
        .append("\n");
        sb.append("    personalSurveyData: ")
        .append(toIndentedString(personalSurveyData))
        .append("\n");
        sb.append("    economicSurveyData: ")
        .append(toIndentedString(economicSurveyData))
        .append("\n");
        sb.append("    indicatorSurveyData: ")
        .append(toIndentedString(indicatorSurveyData))
        .append("\n");
        sb.append("    user: ")
        .append(toIndentedString(userName))
        .append("\n");
        sb.append("    termCond: ")
        .append(toIndentedString(termCondId))
        .append("\n");
        sb.append("    privPol: ")
        .append(toIndentedString(privPolId))
        .append("\n");
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NewSnapshot that = (NewSnapshot) o;

        return com.google.common.base.Objects.equal(this.surveyId,
                that.surveyId)
                && com.google.common.base.Objects.equal(this.organizationId,
                        that.organizationId)
                && com.google.common.base.Objects.equal(this.personalSurveyData,
                        that.personalSurveyData)
                && com.google.common.base.Objects.equal(this.economicSurveyData,
                        that.economicSurveyData)
                && com.google.common.base.Objects.equal(
                        this.indicatorSurveyData, that.indicatorSurveyData)
                && com.google.common.base.Objects.equal(this.userName,
                        that.userName)
                && com.google.common.base.Objects.equal(this.termCondId,
                        that.termCondId)
                && com.google.common.base.Objects.equal(this.privPolId,
                        that.privPolId);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(surveyId,
                personalSurveyData, economicSurveyData, indicatorSurveyData);
    }

    public SurveyData getMappedPersonalSurveyData(Predicate<String> nameFilter,
            Function<String, String> propertySchemaToSystem) {
        Map<String, Object> collect = personalSurveyData.entrySet().stream()
                .filter(entry -> nameFilter.test(entry.getKey()))
                .collect(toMap(entry ->
                propertySchemaToSystem.apply(entry.getKey()),
                entry -> entry.getValue()));
        return new SurveyData(collect);
    }

    public SurveyData getPersonalSurveyData(Predicate<String> nameFilter) {
        Map<String, Object> collect = personalSurveyData.entrySet().stream()
                .filter(entry -> nameFilter.test(entry.getKey()))
                .collect(toMap(Entry::getKey, Entry::getValue));
        return new SurveyData(collect);
    }

    public SurveyData getMappedEconomicSurveyData(Predicate<String> nameFilter,
            Function<String, String> propertySchemaToSystem) {
        Map<String, Object> collect = economicSurveyData.entrySet().stream()
                .filter(entry -> nameFilter.test(entry.getKey()))
                .collect(toMap(entry ->
                propertySchemaToSystem.apply(entry.getKey()),
                entry -> entry.getValue()));
        return new SurveyData(collect);
    }

    public SurveyData getEconomicSurveyData(Predicate<String> nameFilter) {
        Map<String, Object> collect = economicSurveyData.entrySet().stream()
                .filter(entry -> nameFilter.test(entry.getKey()))
                .collect(toMap(Entry::getKey, Entry::getValue));
        return new SurveyData(collect);
    }

    public SurveyData getMappedIndicatorSurveyData(Predicate<String> nameFilter,
            Function<String, String>  propertySchemaToSystem) {
        Map<String, Object> collect = indicatorSurveyData.entrySet().stream()
                .filter(entry -> nameFilter.test(entry.getKey()))
                .collect(toMap(entry ->
                propertySchemaToSystem.apply(entry.getKey()),
                entry -> entry.getValue()));
        return new SurveyData(collect);
    }

    public SurveyData getIndicatorSurveyData(Predicate<String> nameFilter) {
        Map<String, Object> collect = indicatorSurveyData.entrySet().stream()
                .filter(entry -> nameFilter.test(entry.getKey()))
                .collect(toMap(Entry::getKey, Entry::getValue));
        return new SurveyData(collect);
    }

    public SurveyData getAllSurveyData() {
        Map<String, Object> map = Maps.newHashMap();
        map.putAll(personalSurveyData);
        map.putAll(indicatorSurveyData);
        map.putAll(economicSurveyData);
        return new SurveyData(map);
    }
}

