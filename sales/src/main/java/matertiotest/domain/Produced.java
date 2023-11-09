package matertiotest.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import matertiotest.domain.*;
import matertiotest.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class Produced extends AbstractEvent {

    private Long id;
    private Long stock;

    public Produced(SalesOrder aggregate) {
        super(aggregate);
    }

    public Produced() {
        super();
    }
}
//>>> DDD / Domain Event
