package com.daniyal.sto.consumer;

import com.daniyal.sto.event.StatusChangeEvent;
import com.daniyal.sto.service.StatusHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatusChangeEventListener {
    private final StatusHistoryService statusHistoryService;

    @KafkaListener(topics = "${topic.notification}", groupId = "sto-group")
    public void handleStatusChange(StatusChangeEvent event) {
      log.info("Received status change event: {}", event);
      statusHistoryService.addStatusHistory(
              event.requestId(),
              event.oldStatus(),
              event.newStatus(),
              event.changeBy(),
              event.changeReason());
    }
}
