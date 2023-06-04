package ru.otus.listener.homework;

import ru.otus.listener.Listener;
import ru.otus.model.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HistoryListener implements Listener, HistoryReader {

    private final  Map<Long, Message> historyMessages = new HashMap<>();
    @Override
    public void onUpdated(Message msg) {
        Message copyMes = new Message(msg.getId(), msg.getField1(), msg.getField2(),msg.getField3(), msg.getField4(), msg.getField5(),
                msg.getField6(),msg.getField7(),msg.getField8(),msg.getField9(),msg.getField10(),msg.getField11(),msg.getField12(),msg.getField13());

        historyMessages.put(msg.getId(), copyMes);

    }

    @Override
    public Optional<Message> findMessageById(long id) {
        return Optional.of(historyMessages.get(id));
    }
}
