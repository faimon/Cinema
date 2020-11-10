package ru.service;

import ru.persistance.Account;
import ru.persistance.Place;

import java.util.Collection;

public interface Store {
    Collection<Place> findAllReservedPlaces();

    void savePurchasedTicket(Account account, Place place);
}
