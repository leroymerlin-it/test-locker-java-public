package it.leroymerlin.locker.infrastructure;

import it.leroymerlin.locker.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FakeLockerDB extends InMemoryLockerDB {

    public FakeLockerDB() {
        fakeDataInit();
    }

    private void fakeDataInit() {
        try {
            List<Slot> slots = new ArrayList<>();
            for (int d = 1; d < 8; ++d) {
                slots.add(new Slot(LocalDate.of(2023, 11, d)));
            }
            for (int i = 0; i < LockerSize.values().length; ++i) {
                LockerSize lockerSize = LockerSize.values()[i];
                for (int j = 1; j < 6; ++j) {
                    String generateName = lockerSize.name().substring(0, 2) + "-" + j;
                    Locker locker = new Locker(new LockerCode(generateName), lockerSize);
                    for (int k = 0; k < slots.size(); ++k) {
                        locker.addSlot(slots.get(k));
                        if (i + j < 5) {
                            locker.reserveSlot(slots.get(k), new OrderId("ORDER_" + generateName));
                        }
                    }
                    save(locker);
                }
            }
            System.out.println("Fake DB initialized ...");
        } catch(DomainException e) {
            throw new RuntimeException(e);
        }
    }

}
