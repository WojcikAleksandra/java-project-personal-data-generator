package PeopleCollectioner;

import Human.Human;

import java.util.ArrayList;
import java.util.List;

public class ClassWithCollection {
    private final List<Human> peopleCollection;

    public ClassWithCollection(int n) {
        peopleCollection = new ArrayList<>(n); // Optymalna pod względem złożoności

        // Tworzenie ludzi
        for (int i = 0; i < n; i++) {
            peopleCollection.add(new Human());
        }
    }


    public List<Human> getPeopleCollection() {
        return peopleCollection;
    }


}
