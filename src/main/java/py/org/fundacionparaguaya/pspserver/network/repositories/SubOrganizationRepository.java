package py.org.fundacionparaguaya.pspserver.network.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import py.org.fundacionparaguaya.pspserver.network.entities.SubOrganizationEntity;

import java.util.List;

public interface SubOrganizationRepository extends PagingAndSortingRepository<SubOrganizationEntity, Long>,
                                                JpaSpecificationExecutor<SubOrganizationEntity> {

    SubOrganizationEntity findById(Long id);

    List<SubOrganizationEntity> findAll();

    List<SubOrganizationEntity> findByOrganizationId(Long organizationId);

    Page<SubOrganizationEntity> findAll(Pageable page);

    @Query("SELECT sub FROM SubOrganizationEntity sub WHERE sub.organization.id = :organizationId")
    List<SubOrganizationEntity> findSubOrganizationsByOrganizationId( @Param("organizationId") Integer organizationId );
}
