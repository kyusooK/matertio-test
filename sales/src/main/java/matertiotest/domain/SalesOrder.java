package matertiotest.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import matertiotest.SalesApplication;
import matertiotest.domain.Produced;

@Entity
@Table(name = "SalesOrder_table")
@Data
//<<< DDD / Aggregate Root
public class SalesOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long salesOrderNum;

    private String salesPerson;

    private String status;

    private SalesType salesType;

    @Embedded
    private CompanyId companyId;

    @ElementCollection
    private List<SalesItem> salesItems;

    @PostPersist
    public void onPostPersist() {
        Produced produced = new Produced(this);
        produced.publishAfterCommit();
    }

    public static SalesOrderRepository repository() {
        SalesOrderRepository salesOrderRepository = SalesApplication.applicationContext.getBean(
            SalesOrderRepository.class
        );
        return salesOrderRepository;
    }

    //<<< Clean Arch / Port Method
    public void produce(ProduceCommand produceCommand) {
        //implement business logic here:

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
