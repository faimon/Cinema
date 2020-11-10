package ru.service;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.persistance.Account;
import ru.persistance.Place;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store {
    private final BasicDataSource pool = new BasicDataSource();

    private PsqlStore() {
        Properties cfg = new Properties();
        try (InputStream in = PsqlStore.class
                .getClassLoader().getResourceAsStream("db.properties")) {
            cfg.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        pool.setDriverClassName(cfg.getProperty("jdbc.driver"));
        pool.setUrl(cfg.getProperty("jdbc.url"));
        pool.setUsername(cfg.getProperty("jdbc.username"));
        pool.setPassword(cfg.getProperty("jdbc.password"));
        pool.setMinIdle(5);
        pool.setMaxIdle(10);
        pool.setMaxOpenPreparedStatements(100);
    }

    private static final class Lazy {
        private static final Store INST = new PsqlStore();
    }

    public static Store instOf() {
        return Lazy.INST;
    }


    @Override
    public Collection<Place> findAllReservedPlaces() {
        List<Place> places = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM places")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    places.add(new Place(it.getInt("id"), it.getInt("place")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return places;
    }

    private void reservePlace(Place place, Connection cn) {
        try (PreparedStatement ps = cn.prepareStatement("INSERT INTO places(place) VALUES (?)",
                PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setInt(1, place.getPlace());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    place.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void savePurchasedTicket(Account account, Place place) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn
                     .prepareStatement("INSERT INTO accounts(name, phone_number, placeid) VALUES (?, ?, ?)",
                             PreparedStatement.RETURN_GENERATED_KEYS)) {
            try {
                cn.setAutoCommit(false);
                reservePlace(place, cn);
                account.setPlaceId(place.getId());
                ps.setString(1, account.getName());
                ps.setInt(2, account.getPhoneNumber());
                ps.setInt(3, account.getPlaceId());
                ps.execute();
                try (ResultSet id = ps.getGeneratedKeys()) {
                    if (id.next()) {
                        account.setId(id.getInt(1));
                    }
                }
                cn.commit();
            } catch (SQLException e) {
                cn.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
