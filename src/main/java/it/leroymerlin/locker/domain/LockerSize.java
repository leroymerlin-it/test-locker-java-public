package it.leroymerlin.locker.domain;

public enum LockerSize {
    SMALL(Size.of(10, 10, 40)),
    MEDIUM(Size.of(20, 20, 40)),
    LARGE(Size.of(30, 30, 40));

    private final Size size;

    LockerSize(Size size) {
        this.size = size;
    }

    public boolean couldContain(Size targetSize) {
        return this.size.couldContain(targetSize);
    }

}
