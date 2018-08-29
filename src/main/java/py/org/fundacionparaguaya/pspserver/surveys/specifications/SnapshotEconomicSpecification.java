package py.org.fundacionparaguaya.pspserver.surveys.specifications;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import py.org.fundacionparaguaya.pspserver.families.entities.FamilyEntity_;
import py.org.fundacionparaguaya.pspserver.network.dtos.ApplicationDTO;
import py.org.fundacionparaguaya.pspserver.network.dtos.OrganizationDTO;
import py.org.fundacionparaguaya.pspserver.network.entities.ApplicationEntity_;
import py.org.fundacionparaguaya.pspserver.network.entities.OrganizationEntity_;
import py.org.fundacionparaguaya.pspserver.security.dtos.UserDetailsDTO;
import py.org.fundacionparaguaya.pspserver.security.entities.UserEntity_;
import py.org.fundacionparaguaya.pspserver.surveys.dtos.Property;
import py.org.fundacionparaguaya.pspserver.surveys.dtos.SurveyData;
import py.org.fundacionparaguaya.pspserver.surveys.entities.PropertyAttributeEntity;
import py.org.fundacionparaguaya.pspserver.surveys.entities.SnapshotEconomicEntity;
import py.org.fundacionparaguaya.pspserver.surveys.entities.SnapshotEconomicEntity_;
import py.org.fundacionparaguaya.pspserver.surveys.entities.SnapshotIndicatorEntity;
import py.org.fundacionparaguaya.pspserver.surveys.entities.StopLightGroup;
import py.org.fundacionparaguaya.pspserver.surveys.mapper.PropertyAttributeSupport;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author mcespedes
 *
 */
@Component
public class SnapshotEconomicSpecification {

    private static final String SHORT_DATE_FORMAT = "dd/MM/yyyy";

    private static final long MONTH_AGO = 12;

    private static PropertyAttributeSupport propertyAttributeSupport;

    private SnapshotEconomicSpecification(PropertyAttributeSupport propertyAttributeSupport) {
        this.propertyAttributeSupport = propertyAttributeSupport;
    }

    public static Specification<SnapshotEconomicEntity> byLoggedUser(UserDetailsDTO user) {
        return (root, query, builder) ->
                builder.and(
                        byApplication(Optional.ofNullable(user)
                                .map(UserDetailsDTO::getApplication)
                                .map(ApplicationDTO::getId)
                                .orElse(null))
                                .toPredicate(root, query, builder),
                        byOrganization(Optional.ofNullable(user)
                                .map(UserDetailsDTO::getOrganization)
                                .map(OrganizationDTO::getId)
                                .orElse(null))
                                .toPredicate(root, query, builder));
    }

    public static Specification<SnapshotEconomicEntity> byApplication(Long applicationId) {
        return new Specification<SnapshotEconomicEntity>() {
            @Override
            public Predicate toPredicate(Root<SnapshotEconomicEntity> root, CriteriaQuery<?> query,
                    CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                if (applicationId != null) {
                    //FIXME These join expressions are not typesafe and can lead to errors, use metamodels to avoid this
                    predicates.add(cb.equal(root.join("family").join("application").get("id"), applicationId));
                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

    public static Specification<SnapshotEconomicEntity> byApplications(List<Long> applications) {
        return (Root<SnapshotEconomicEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            if (applications == null || applications.size() == 0) {
                return builder.conjunction();
            }
            Path<Long> applicationIdPath = root.join(SnapshotEconomicEntity_.getFamily())
                    .get(FamilyEntity_.getApplication()).get(ApplicationEntity_.getId());
            return applicationIdPath.in(applications);
        };
    }

    public static Specification<SnapshotEconomicEntity> byOrganization(Long organizationId) {
        return (Root<SnapshotEconomicEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            if (organizationId == null) {
                return builder.conjunction();
            }
            Expression<Long> organizationIdExpression =
                    root.join(SnapshotEconomicEntity_.getFamily())
                            .join(FamilyEntity_.getOrganization())
                            .get(OrganizationEntity_.getId());

            return builder.equal(organizationIdExpression, organizationId);
        };
    }

    public static Specification<SnapshotEconomicEntity> byOrganizations(List<Long> organizations) {
        return (Root<SnapshotEconomicEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            if (organizations == null || organizations.size() == 0) {
                return builder.conjunction();
            }
            Path<Long> organizationPath = root.join(SnapshotEconomicEntity_.getFamily())
                    .get(FamilyEntity_.getOrganization()).get(OrganizationEntity_.getId());
            return organizationPath.in(organizations);
        };
    }

    public static Specification<SnapshotEconomicEntity> byUser(Long userId) {
        return (Root<SnapshotEconomicEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            if (userId == null) {
                return null;
            }
            Expression<Long> userIdExpression = root.join(SnapshotEconomicEntity_.getUser()).get(UserEntity_.getId());
            return builder.equal(userIdExpression, userId);
        };
    }

    public static Specification<SnapshotEconomicEntity> createdAtLess2Months() {
        return new Specification<SnapshotEconomicEntity>() {
            @Override
            public Predicate toPredicate(Root<SnapshotEconomicEntity> root, CriteriaQuery<?> query,
                    CriteriaBuilder cb) {

                LocalDateTime limit = LocalDateTime.now();
                limit = limit.minusMonths(MONTH_AGO).withDayOfMonth(1);

                return cb.and(cb.greaterThan(root.<LocalDateTime>get(SnapshotEconomicEntity_.getCreatedAt()), limit));

            }
        };
    }

    public static Specification<SnapshotEconomicEntity> createdAtBetween2Dates(String dateFrom, String dateTo) {
        return new Specification<SnapshotEconomicEntity>() {
            @Override
            public Predicate toPredicate(Root<SnapshotEconomicEntity> root, CriteriaQuery<?> query,
                    CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                if (dateFrom != null && dateTo != null) {

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(SHORT_DATE_FORMAT);

                    predicates.add(cb.greaterThanOrEqualTo(root.get(SnapshotEconomicEntity_.getCreatedAt()),
                            LocalDate.parse(dateFrom, formatter).atStartOfDay()));
                    predicates.add(cb.lessThan(root.get(SnapshotEconomicEntity_.getCreatedAt()),
                            LocalDate.parse(dateTo, formatter).plusDays(1).atStartOfDay()));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

    public static Specification<SnapshotEconomicEntity> byTimePeriod(Long fromDate, Long toDate) {
        return (root, query, builder) -> {
            if (fromDate != null && toDate != null) {
                return builder.and(
                        builder.greaterThanOrEqualTo(root.get(SnapshotEconomicEntity_.getCreatedAt()),
                                LocalDateTime.ofInstant(Instant.ofEpochMilli(fromDate), ZoneId.of("UTC"))),
                        builder.lessThanOrEqualTo(root.get(SnapshotEconomicEntity_.getCreatedAt()),
                                LocalDateTime.ofInstant(Instant.ofEpochMilli(toDate), ZoneId.of("UTC"))));
            } else {
                return builder.conjunction();
            }
        };
    }

    public static Specification<SnapshotEconomicEntity> byCoreIndicatorsFilters(
                                                Map<String, List<String>> indicatorsFilters, String matchQuantifier) {
        return (root, query, builder) -> {
            if (indicatorsFilters == null) {
                return builder.conjunction();
            }

            List<String> coreIndicators =
                    propertyAttributeSupport.getPropertyAttributesByGroup(StopLightGroup.INDICATOR)
                            .stream()
                            .map(PropertyAttributeEntity::getPropertySchemaName)
                            .collect(Collectors.toList());

            List<Predicate> predicates = new ArrayList<>();

            indicatorsFilters.forEach((key, colorsFilters) -> {
                Join<SnapshotEconomicEntity, SnapshotIndicatorEntity> snapshotIndicator =
                        root.join(SnapshotEconomicEntity_.getSnapshotIndicator());
                if (coreIndicators.contains(key)) {
                    Path<String> indicatorValue = snapshotIndicator.get(key);
                    predicates.add(indicatorValue.in(colorsFilters));
                }
            });

            if (matchQuantifier == null
                    || matchQuantifier.equalsIgnoreCase("ALL")) {
                return builder.and(predicates.toArray(new Predicate[0]));
            } else if (matchQuantifier.equalsIgnoreCase("ANY")) {
                return builder.or(predicates.toArray(new Predicate[0]));
            }

            // Default to quantifier ALL
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static java.util.function.Predicate<SnapshotEconomicEntity> byAdditionalIndicatorsFilters(
                                                Map<String, List<String>> indicatorsFilters, String matchQuantifier) {
        return snapshot -> {
            if (indicatorsFilters == null) {
                return true;
            }

            List<String> coreIndicators =
                    propertyAttributeSupport.getPropertyAttributesByGroup(StopLightGroup.INDICATOR)
                            .stream()
                            .map(PropertyAttributeEntity::getPropertySchemaName)
                            .collect(Collectors.toList());

            SurveyData additionalProperties = snapshot.getSnapshotIndicator().getAdditionalProperties();

            for (Map.Entry<String, List<String>> indicatorsFilter : indicatorsFilters.entrySet()) {
                String filterKey = indicatorsFilter.getKey();
                // We can check here if the survey definition contains filterKey to continue,
                // but its not necessary as match will result false, and its a costly operation

                if (!coreIndicators.contains(filterKey)) {
                    List<String> filtersArray = indicatorsFilter.getValue();
                    String storedValue = (String) additionalProperties.get(filterKey);
                    boolean match = filtersArray.contains(storedValue);
                    // If matchQuantifier is NULL, default to ALL
                    if (!match && (matchQuantifier == null
                            || matchQuantifier.equalsIgnoreCase("ALL"))) {
                        return false;
                    }
                    if (match && matchQuantifier.equalsIgnoreCase("ANY")) {
                        return true;
                    }
                }
            }

            if (matchQuantifier.equalsIgnoreCase("ALL")) {
                return true;
            }
            if (matchQuantifier.equalsIgnoreCase("ANY")) {
                return false;
            }

            // Default to quantifier ALL
            return true;
        };
    }

    public static java.util.function.Predicate<SnapshotEconomicEntity> bySocioeconomicFilters(
                                                                    Map<String, List<String>> socioeconomicFilters) {
        return snapshot -> {
            if (socioeconomicFilters == null) {
                return true;
            }

            List<String> coreSocioeconomics =
                    propertyAttributeSupport.getPropertyAttributesByGroup(StopLightGroup.ECONOMIC)
                            .stream()
                            .map(PropertyAttributeEntity::getPropertySchemaName)
                            .collect(Collectors.toList());

            SurveyData additionalProperties = snapshot.getAdditionalProperties();

            Map<String, Property> surveyProperties =
                    snapshot.getSurveyDefinition().getSurveyDefinition().getSurveySchema().getProperties();

            for (Map.Entry<String, List<String>> socioeconomicFilter : socioeconomicFilters.entrySet()) {

                String filterKey = socioeconomicFilter.getKey();
                List<String> filterValues = socioeconomicFilter.getValue();
                if (!surveyProperties.containsKey(filterKey)) {
                    continue;
                }

                Property.TypeEnum type = surveyProperties.get(filterKey).getType();

                if (type.name().equalsIgnoreCase("Number")) {
                    Number numberValue;
                    if (coreSocioeconomics.contains(filterKey)) {
                        try {
                            // Use reflection to get the value from the attribute column in the table
                            numberValue = (Number) PropertyUtils.getProperty(snapshot, filterKey);
                        } catch (Exception e) {
                            throw new RuntimeException("Could not get property '" + filterKey + "' from Snapshot", e);
                        }
                    } else {
                        // Get the value from the additional properties JSON
                        numberValue = (Number) additionalProperties.get(filterKey);
                    }
                    Double storedValue = 0.0;
                    // Number got from the table is always Double, Number got from JSON can be Integer or Double
                    if (numberValue instanceof Double) {
                        storedValue = (Double) numberValue;
                    } else if (numberValue instanceof Integer) {
                        storedValue = numberValue.doubleValue();
                    }

                    Double minimumFilter = Double.parseDouble(filterValues.get(0));
                    Double maximumFilter = Double.parseDouble(filterValues.get(1));

                    if (!(storedValue >= minimumFilter && storedValue <= maximumFilter)) {
                        return false;
                    }
                }

                if (type.name().equalsIgnoreCase("String")) {
                    String storedValue;
                    if (coreSocioeconomics.contains(filterKey)) {
                        try {
                            // Use reflection to get the value from the attribute column in the table
                            storedValue = (String) PropertyUtils.getProperty(snapshot, filterKey);
                        } catch (Exception e) {
                            throw new RuntimeException("Could not get property '" + filterKey + "' from Snapshot", e);
                        }
                    } else {
                        // Get the value from the additional properties JSON
                        storedValue = (String) additionalProperties.get(filterKey);
                    }

                    if (!filterValues.contains(storedValue)) {
                        return false;
                    }
                }
            }

            return true;
        };
    }

    public static java.util.function.Predicate<SnapshotEconomicEntity> byMultipleSnapshots(
                                                    Boolean multipleSnapshots, List<SnapshotEconomicEntity> snapshots) {
        return snapshot -> {
            if (multipleSnapshots) {
                return true;
            } else {
                LocalDateTime maxDate = snapshots
                        .stream()
                        .filter(s -> s.getFamily().getCode().equals(snapshot.getFamily().getCode()))
                        .map(SnapshotEconomicEntity::getCreatedAt)
                        .max(LocalDateTime::compareTo).get();

                return snapshot.getCreatedAt().compareTo(maxDate) == 0;
            }
        };
    }

    public static Specification<SnapshotEconomicEntity> forFamily(Long familyId) {
        return (root, query, cb) -> {
            if (familyId == null) {
                return null;
            }
            // FIXME These join expressions are not typesafe and can lead to errors, use metamodels to avoid this
            return cb.equal(root.join("family").get("familyId"), familyId);
        };
    }

    public static Specification<SnapshotEconomicEntity> forSurvey(Long surveyId) {
        return (root, query, cb) -> {
            if (surveyId == null) {
                return cb.disjunction();
            }
            // FIXME These join expressions are not typesafe and can lead to errors, use metamodels to avoid this
            return cb.equal(root.join("surveyDefinition").get("id"), surveyId);
        };
    }
}
