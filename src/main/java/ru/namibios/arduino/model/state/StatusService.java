package ru.namibios.arduino.model.state;

import ru.namibios.arduino.model.status.Status;

public class StatusService<T> {

    public T getTemplate(Status status) {

        return (T) status.getNameTemplate();
    }
}
