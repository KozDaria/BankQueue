package org.example;

import java.util.ArrayDeque;
import java.util.Deque;

public class ElectronicQueue {

    Deque<Integer> numberAtWork = new ArrayDeque<>();


    public void addNewPerson() {
        numberAtWork.addLast(numberAtWork.getLast() + 1);
    }

    public int getPersonInQueue() {
        return numberAtWork.getLast() - numberAtWork.getFirst();
    }

}
