package ru.namibios.arduino.model.state.service;

import ru.namibios.arduino.model.Rod;

public class RodService {

    private Rod rod;

    public RodService(Rod rod) {
        this.rod = rod;
    }

    public boolean hasFree(){
        return  rod.hasNext();
    }

    public String getNextFree(){
        return rod.getNext().toCommandRod();
    }

}
