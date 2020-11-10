package ru.persistance;

import java.util.Objects;

public class Account {
    private int id;
    private String name;
    private int phoneNumber;
    private int placeId;

    public Account(int id, String name, int phoneNumber, int placeId) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.placeId = placeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id &&
                phoneNumber == account.phoneNumber &&
                placeId == account.placeId &&
                Objects.equals(name, account.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phoneNumber, placeId);
    }
}
