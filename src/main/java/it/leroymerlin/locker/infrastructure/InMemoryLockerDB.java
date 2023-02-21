package it.leroymerlin.locker.infrastructure;

import it.leroymerlin.locker.domain.Locker;
import it.leroymerlin.locker.domain.LockerCode;
import org.springframework.data.repository.CrudRepository;

import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;

public class InMemoryLockerDB implements CrudRepository<Locker, LockerCode> {

    private final Map<LockerCode, Locker> lockerMap;

    public InMemoryLockerDB() {
        this.lockerMap = new Hashtable<>();
    }

    @Override
    public boolean existsById(LockerCode lockerCode) {
        return lockerMap.containsKey(lockerCode);
    }

    @Override
    public Optional<Locker> findById(LockerCode lockerCode) {
        return Optional.ofNullable(lockerMap.get(lockerCode));
    }

    @Override
    public Iterable<Locker> findAll() {
        return lockerMap.values();
    }

    @Override
    public <S extends Locker> S save(S locker) {
        lockerMap.put(locker.getCode(), locker);
        return locker;
    }

    /////////////////////////////////////////////////////////////////

    // From here the methods aren't implemented

    @Override
    public <S extends Locker> Iterable<S> saveAll(Iterable<S> entities) {
        throw new UnsupportedOperationException("Missing custom implementation");
    }

    @Override
    public Iterable<Locker> findAllById(Iterable<LockerCode> lockerCodes) {
        throw new UnsupportedOperationException("Missing custom implementation");
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException("Missing custom implementation");
    }

    @Override
    public void deleteById(LockerCode lockerCode) {
        throw new UnsupportedOperationException("Missing custom implementation");
    }

    @Override
    public void delete(Locker entity) {
        throw new UnsupportedOperationException("Missing custom implementation");
    }

    @Override
    public void deleteAllById(Iterable<? extends LockerCode> lockerCodes) {
        throw new UnsupportedOperationException("Missing custom implementation");
    }

    @Override
    public void deleteAll(Iterable<? extends Locker> entities) {
        throw new UnsupportedOperationException("Missing custom implementation");
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Missing custom implementation");
    }

}
