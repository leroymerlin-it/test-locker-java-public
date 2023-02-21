package it.leroymerlin.locker;

import it.leroymerlin.locker.domain.LockerCode;
import it.leroymerlin.locker.domain.OrderId;
import it.leroymerlin.locker.domain.Size;
import it.leroymerlin.locker.domain.Slot;

import java.time.LocalDate;

public class TestEnvironment {

    public static final LockerCode A_19 = new LockerCode("A_19");
    public static final LockerCode F_34 = new LockerCode("F_34");
    public static final LockerCode G_75 = new LockerCode("G_75");

    public static final Slot Slot_2023_10_20 = new Slot(LocalDate.of(2023, 10, 20));
    public static final Slot Slot_2023_10_21 = new Slot(LocalDate.of(2023, 10, 21));
    public static final Slot Slot_2023_10_22 = new Slot(LocalDate.of(2023, 10, 22));
    public static final Slot Slot_2023_11_17 = new Slot(LocalDate.of(2023, 11, 17));
    public static final Slot Slot_2023_11_27 = new Slot(LocalDate.of(2023, 11, 27));

    public static final Size VerySmallPackage = Size.of(1, 1, 1);
    public static final Size VeryBigPackage = Size.of(100, 100, 100);

    public static final OrderId GGS = new OrderId("GGS-123");
    public static final OrderId TTN = new OrderId("TTN-456");
    public static final OrderId CLL = new OrderId("CLL-928");

}
