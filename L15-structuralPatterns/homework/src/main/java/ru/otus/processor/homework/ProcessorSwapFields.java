package ru.otus.processor.homework;

import ru.otus.model.Message;
import ru.otus.processor.Processor;

public class ProcessorSwapFields implements Processor {


    @Override
    public Message process(Message message) {
        var valueField11 = message.getField11();
        var valueField12 = message.getField12();

        return message.toBuilder().field11(valueField12).field12(valueField11).build();
    }
}
