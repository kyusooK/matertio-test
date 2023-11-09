package matertiotest.domain;

import java.util.*;
import lombok.*;
import matertiotest.domain.*;
import matertiotest.infra.AbstractEvent;

@Data
@ToString
public class Produced extends AbstractEvent {

    private Long id;
    private Long stock;
}
