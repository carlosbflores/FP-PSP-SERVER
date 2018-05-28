package py.org.fundacionparaguaya.pspserver.system.services.impl;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import py.org.fundacionparaguaya.pspserver.families.entities.FamilyEntity;
import py.org.fundacionparaguaya.pspserver.security.constants.Role;
import py.org.fundacionparaguaya.pspserver.security.dtos.UserDetailsDTO;
import py.org.fundacionparaguaya.pspserver.system.constants.ActivityType;
import py.org.fundacionparaguaya.pspserver.system.dtos.ActivityDTO;
import py.org.fundacionparaguaya.pspserver.system.dtos.ActivityFeedDTO;
import py.org.fundacionparaguaya.pspserver.system.services.ActivityFeedManager;
import py.org.fundacionparaguaya.pspserver.system.services.ActivityService;

import java.util.List;

import static py.org.fundacionparaguaya.pspserver.system.constants.ActivityMessage.*;

/**
 * Created by bsandoval on 05/05/18.
 */
@Service
public class ActivityFeedManagerImpl implements ActivityFeedManager {

    private final ActivityService activityService;

    public ActivityFeedManagerImpl(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public void createHouseholdFirstSnapshotActivity(UserDetailsDTO details, FamilyEntity family) {
        activityService.addActivity(ActivityDTO.builder()
                .activityKey(HOUSEHOLD_FIRST_SNAPSHOT.getKey())
                .activityRole(Role.ROLE_ROOT)
                .activityType(ActivityType.SNAPSHOTS)
                .addActivityParam(family.getName())
                .addActivityParam(family.getCity().getCity())
                .build());

        activityService.addActivity(ActivityDTO.builder()
                .activityKey(HOUSEHOLD_FIRST_SNAPSHOT.getKey())
                .activityRole(Role.ROLE_HUB_ADMIN)
                .activityType(ActivityType.SNAPSHOTS)
                .application(details.getApplication())
                .addActivityParam(family.getName())
                .addActivityParam(family.getCity().getCity())
                .build());

        activityService.addActivity(ActivityDTO.builder()
                .activityKey(HOUSEHOLD_FIRST_SNAPSHOT.getKey())
                .activityRole(Role.ROLE_APP_ADMIN)
                .activityType(ActivityType.SNAPSHOTS)
                .application(details.getApplication())
                .organization(details.getOrganization())
                .addActivityParam(family.getName())
                .addActivityParam(family.getCity().getCity())
                .build());
    }

    @Override
    public void createHouseholdSnapshotActivity(UserDetailsDTO details, FamilyEntity family) {
        activityService.addActivity(ActivityDTO.builder()
                .activityKey(ADMIN_SNAPSHOTS.getKey())
                .activityRole(Role.ROLE_ROOT)
                .activityType(ActivityType.SNAPSHOTS)
                .addActivityParam(details.getOrganization().getDescription())
                .addActivityParam(details.getApplication().getDescription())
                .build());

        activityService.addActivity(ActivityDTO.builder()
                .activityKey(HUB_SNAPSHOTS.getKey())
                .activityRole(Role.ROLE_HUB_ADMIN)
                .activityType(ActivityType.SNAPSHOTS)
                .application(details.getApplication())
                .addActivityParam(details.getOrganization().getDescription())
                .addActivityParam(family.getCity().getCity())
                .build());

        activityService.addActivity(ActivityDTO.builder()
                .activityKey(ORG_SNAPSHOTS.getKey())
                .activityRole(Role.ROLE_APP_ADMIN)
                .activityType(ActivityType.SNAPSHOTS)
                .application(details.getApplication())
                .organization(details.getOrganization())
                .addActivityParam(details.getUsername())
                .addActivityParam(family.getCity().getCity())
                .build());
    }

    @Override
    public List<ActivityFeedDTO> showActivityFeedByUserDetails(UserDetailsDTO details) {
        Sort sortByDate = new Sort(Sort.Direction.DESC, "createdAt");
        return activityService.getActivitiesByUserDetails(details, sortByDate);
    }
}
