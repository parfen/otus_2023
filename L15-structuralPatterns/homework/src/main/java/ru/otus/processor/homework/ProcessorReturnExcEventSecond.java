package ru.otus.processor.homework;

import ru.otus.model.Message;
import ru.otus.processor.Processor;

import java.time.LocalDateTime;


public class ProcessorReturnExcEventSecond implements Processor {
    private DateTimeProvider dateTime = null;
    public ProcessorReturnExcEventSecond(DateTimeProvider dateTime){
        this.dateTime=dateTime;
    }
    @Override
    public Message process(Message message) {
        int second = dateTime.getDate().getSecond();
        if (second%2 == 0){
            throw new RuntimeException("Event Second");
        }
        return message;
    }
}
