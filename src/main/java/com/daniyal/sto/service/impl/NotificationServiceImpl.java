package com.daniyal.sto.service.impl;

import com.daniyal.sto.event.NotificationEvent;
import com.daniyal.sto.model.RepairRequest;
import com.daniyal.sto.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${topic.notification}")
    private String notificationTopic;

    @Override
    public void notifyClient(RepairRequest request) {
        String message = String.format(
                "Уважаемый %s, ваш автомобиль %s готов к выдаче. Номер заявки: %d",
                request.getClientName(),
                request.getCarModel(),
                request.getId()
        );

        var event = NotificationEvent.fromRequest(request, message);
        kafkaTemplate.send(notificationTopic, event);
    }
}
