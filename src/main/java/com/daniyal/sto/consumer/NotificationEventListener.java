package com.daniyal.sto.consumer;

import com.daniyal.sto.event.NotificationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationEventListener {

    @KafkaListener(topics = "${topic.notification}", groupId = "sto-group")
    public void handleNotification(NotificationEvent event) {
        log.info("""
            Mock SMS notification sent:
            To: {}
            Message: {}
            Request ID: {}
            Car: {}
            """,
                event.clientPhone(),
                event.message(),
                event.requestId(),
                event.carModel()
        );
    }
}
