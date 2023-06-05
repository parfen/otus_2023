package ru.otus.listener.homework;

import ru.otus.listener.Listener;
import ru.otus.model.Message;
import ru.otus.model.ObjectForMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HistoryListener implements Listener, HistoryReader {

    private final  Map<Long, Message> historyMessages = new HashMap<>();
    @Override
    public void onUpdated(Message msg) {

        var objForMessage13 = new ObjectForMessage();
        var field13Data = new ArrayList<String>();

        for(var data:msg.getField13().getData()) {
            field13Data.add(data);
        }
        objForMessage13.setData(field13Data);
        var copyMess = new Message.Builder(msg)
                .field13(objForMessage13).build();

        historyMessages.put(msg.getId(), copyMess);

    }

    @Override
    public Optional<Message> findMessageById(long id) {
        return Optional.of(historyMessages.get(id));
    }
}
