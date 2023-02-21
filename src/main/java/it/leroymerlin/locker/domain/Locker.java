package it.leroymerlin.locker.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Locker {

    private final LockerCode code;
    private final LockerSize size;
    private final Map<Slot, OrderId> slots;

    public Locker(LockerCode code, LockerSize size) {
        this.code = code;
        this.size = size;
        this.slots = new HashMap<>();
    }

    public LockerCode getCode() {
        return code;
    }

    public LockerSize getSize() {
        return size;
    }

    public List<Slot> getSlots() {
        return List.copyOf(slots.keySet());
    }

    public void addSlot(Slot slot) throws DomainException {
        if (slots.containsKey(slot)) throw new DomainException("The slot '" + slot.date() + "' already exists for the locker '" + code.code() + "'");
        slots.put(slot, NO_ORDER);
    }

    public void reserveSlot(Slot slot, OrderId orderId) throws DomainException {
        if (!slots.containsKey(slot)) throw new DomainException("The slot '" + slot + "' doesn't exist");
        if (slots.get(slot) != null) throw new DomainException("The slot '" + slot + "' is already reserved");
        slots.put(slot, orderId);
    }

    private static final OrderId NO_ORDER = null;

}
