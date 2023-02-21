package it.leroymerlin.locker.reserveLocker;

import it.leroymerlin.locker.domain.DomainException;
import it.leroymerlin.locker.domain.LockerCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ReserveLockerController {

    @Autowired
    ReserveLockerCommand reserveLockerCommand;

    @GetMapping("lockers/reserveLocker")
    public @ResponseBody ReserveLockerResponse reserveLocker(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month,
            @RequestParam(required = false) Integer day,
            @RequestParam(required = false) Integer length,
            @RequestParam(required = false) Integer width,
            @RequestParam(required = false) Integer height,
            @RequestParam(required = false) String orderId
    ) throws DomainException {
        return ReserveLockerResponse.from(reserveLockerCommand.reserveLocker(year, month, day, length, width, height, orderId));
    }

    public record ReserveLockerResponse(String lockerCode) {
        public static ReserveLockerResponse from(Optional<LockerCode> lockerCode) {
            if (lockerCode.isPresent()) {
                return new ReserveLockerResponse(lockerCode.get().code());
            } else {
                return new ReserveLockerResponse("");
            }
        }
    };

}
