package ru.persistance;

import java.util.Objects;

public class Place {
    private int id;
    private int place;

    public Place(int id, int place) {
        this.id = id;
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place1 = (Place) o;
        return id == place1.id &&
                place == place1.place;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, place);
    }
}
