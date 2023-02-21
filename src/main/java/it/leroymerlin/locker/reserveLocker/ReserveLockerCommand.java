package it.leroymerlin.locker.reserveLocker;

import it.leroymerlin.locker.domain.*;
import it.leroymerlin.locker.infrastructure.InMemoryLockerDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class ReserveLockerCommand {

    private InMemoryLockerDB lockers;

    @Autowired
    public ReserveLockerCommand(InMemoryLockerDB lockers) {
        this.lockers = lockers;
    }

    public Optional<LockerCode> reserveLocker(
            int year,
            int month,
            int day,
            int length,
            int width,
            int height,
            String orderId
    ) {
        try {
            Iterable<Locker> lockerList = lockers.findAll();
            Optional<Locker> candidateLocker = StreamSupport.stream(lockerList.spliterator(), false)
                    .findFirst();
            if (candidateLocker.isEmpty()) {
                return Optional.empty();
            }
            Locker locker = candidateLocker.get();
            locker.reserveSlot(new Slot(LocalDate.of(year, month, day)), new OrderId(orderId));
            lockers.save(locker);
            return Optional.of(locker.getCode());
        } catch (DomainException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

}
