package by.scooter.api.sevice;

import by.scooter.entity.event.Maintenance;

public interface MaintenanceService {
    Maintenance addMaintenance(Maintenance maintenance);

    void removeMaintenance(Long id);

    void updateMaintenance(Long updatedId, Maintenance update);
}
