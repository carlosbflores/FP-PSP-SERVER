package py.org.fundacionparaguaya.pspserver.network.specifications;

import org.springframework.data.jpa.domain.Specification;
import py.org.fundacionparaguaya.pspserver.network.dtos.ApplicationDTO;
import py.org.fundacionparaguaya.pspserver.network.entities.ApplicationEntity;
import py.org.fundacionparaguaya.pspserver.network.entities.ApplicationEntity_;
import py.org.fundacionparaguaya.pspserver.security.dtos.UserDetailsDTO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

public class ApplicationSpecification {

    private ApplicationSpecification() {}

    public static Specification<ApplicationEntity> byLoggedUser(UserDetailsDTO userDetails) {
        return (Root<ApplicationEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {

            ApplicationDTO application = userDetails.getApplication();
            if (application == null) {
                return null;
            }

            Expression<Long> application_Id = root.get(ApplicationEntity_.getId());
            return builder.equal(application_Id, application.getId());
        };
    }

    public static Specification<ApplicationEntity> byFilter(String filter) {
        return (Root<ApplicationEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            if (filter == null || filter.isEmpty()) {
                return null;
            }

            Expression<String> application_Name = root.get(ApplicationEntity_.getName());
            Expression<String> application_Code = root.get(ApplicationEntity_.getCode());
            Expression<String> application_Description = root.get(ApplicationEntity_.getDescription());
            Expression<String> application_Information = root.get(ApplicationEntity_.getInformation());

            return builder.or(
                    builder.like(builder.lower(application_Name), "%" + filter.toLowerCase() + "%"),
                    builder.like(builder.lower(application_Code), "%" + filter.toLowerCase() + "%"),
                    builder.like(builder.lower(application_Description), "%" + filter.toLowerCase() + "%"),
                    builder.like(builder.lower(application_Information), "%" + filter.toLowerCase() + "%")
            );
        };
    }

    public static Specification<ApplicationEntity> isActive() {
        return (Root<ApplicationEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            Expression<Boolean> application_isActive = root.get(ApplicationEntity_.getIsActive());
            return builder.isTrue(application_isActive);
        };
    }
}