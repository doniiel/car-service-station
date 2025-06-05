package com.daniyal.sto.service;

import com.daniyal.sto.model.RepairRequest;

public interface NotificationService {
    void notifyClient(RepairRequest request);
}
