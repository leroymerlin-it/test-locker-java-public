package it.leroymerlin.locker.reserveLocker;

import it.leroymerlin.locker.domain.DomainException;
import it.leroymerlin.locker.domain.Locker;
import it.leroymerlin.locker.domain.LockerCode;
import it.leroymerlin.locker.domain.LockerSize;
import it.leroymerlin.locker.infrastructure.InMemoryLockerDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static it.leroymerlin.locker.TestEnvironment.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class ReserveLockerCommandTest {

    InMemoryLockerDB lockers;
    ReserveLockerCommand command;

    @BeforeEach
    void setUpTest() {
        lockers = new InMemoryLockerDB();
        command = new ReserveLockerCommand(lockers);
    }

    @Test
    @DisplayName("It's ok, you reserved a slot")
    void reserveLocker_ok() throws DomainException {
        Locker locker = new Locker(G_75, LockerSize.LARGE);
        locker.addSlot(Slot_2023_11_17);
        lockers.save(locker);
        Optional<LockerCode> lockerCode = command.reserveLocker(
                Slot_2023_11_17.date().getYear(),
                Slot_2023_11_17.date().getMonthValue(),
                Slot_2023_11_17.date().getDayOfMonth(),
                VerySmallPackage.getDim().get(0),
                VerySmallPackage.getDim().get(1),
                VerySmallPackage.getDim().get(2),
                GGS.id()
        );
        assertThat(lockerCode.get(), is(G_75));
    }

    @Disabled
    @Test
    @DisplayName("You take the smallest locker available")
    void reserveLocker_ok_the_smaller() throws DomainException {
        Locker lockerL = new Locker(G_75, LockerSize.LARGE);
        lockerL.addSlot(Slot_2023_11_17);
        lockers.save(lockerL);
        Locker lockerM = new Locker(A_19, LockerSize.MEDIUM);
        lockerM.addSlot(Slot_2023_11_17);
        lockers.save(lockerM);
        Locker lockerS = new Locker(F_34, LockerSize.SMALL);
        lockerS.addSlot(Slot_2023_11_17);
        lockers.save(lockerS);
        Optional<LockerCode> lockerCode = command.reserveLocker(
                Slot_2023_11_17.date().getYear(),
                Slot_2023_11_17.date().getMonthValue(),
                Slot_2023_11_17.date().getDayOfMonth(),
                VerySmallPackage.getDim().get(0),
                VerySmallPackage.getDim().get(1),
                VerySmallPackage.getDim().get(2),
                GGS.id()
        );
        assertThat(lockerCode.get(), is(F_34));
    }

    @Test
    @DisplayName("There aren't lockers in the selected slot")
    void reserveLocker_no_lockers_for_this_slot() throws DomainException {
        Locker locker = new Locker(A_19, LockerSize.SMALL);
        locker.addSlot(Slot_2023_10_20);
        locker.addSlot(Slot_2023_10_22);
        lockers.save(locker);
        Optional<LockerCode> lockerCode = command.reserveLocker(
                Slot_2023_10_21.date().getYear(),
                Slot_2023_10_21.date().getMonthValue(),
                Slot_2023_10_21.date().getDayOfMonth(),
                VerySmallPackage.getDim().get(0),
                VerySmallPackage.getDim().get(1),
                VerySmallPackage.getDim().get(2),
                TTN.id()
        );
        assertThat(lockerCode.isEmpty(), is(true));
    }

    @Disabled
    @Test
    @DisplayName("There aren't slots compatible with the request size")
    void reserveLocker_no_slots_of_compatible_size() throws DomainException {
        Locker locker1 = new Locker(A_19, LockerSize.SMALL);
        locker1.addSlot(Slot_2023_10_21);
        lockers.save(locker1);
        Locker locker2 = new Locker(G_75, LockerSize.MEDIUM);
        locker2.addSlot(Slot_2023_10_21);
        lockers.save(locker2);
        Optional<LockerCode> lockerCode = command.reserveLocker(
                Slot_2023_10_21.date().getYear(),
                Slot_2023_10_21.date().getMonthValue(),
                Slot_2023_10_21.date().getDayOfMonth(),
                VeryBigPackage.getDim().get(0),
                VeryBigPackage.getDim().get(1),
                VeryBigPackage.getDim().get(2),
                CLL.id()
        );
        assertThat(lockerCode.isEmpty(), is(true));
    }

    @Test
    @DisplayName("The locker is already reserved")
    void reserveLocker_already_reserved() throws DomainException {
        Locker locker = new Locker(F_34, LockerSize.MEDIUM);
        locker.addSlot(Slot_2023_10_21);
        locker.reserveSlot(Slot_2023_10_21, CLL);
        lockers.save(locker);
        Optional<LockerCode> lockerCode = command.reserveLocker(
                Slot_2023_10_21.date().getYear(),
                Slot_2023_10_21.date().getMonthValue(),
                Slot_2023_10_21.date().getDayOfMonth(),
                VerySmallPackage.getDim().get(0),
                VerySmallPackage.getDim().get(1),
                VerySmallPackage.getDim().get(2),
                TTN.id()
        );
        assertThat(lockerCode.isEmpty(), is(true));
    }

}