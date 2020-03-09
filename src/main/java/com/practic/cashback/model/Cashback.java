package com.practic.cashback.model;

import com.practic.cashback.dto.ProcessRes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table
@Data
public class Cashback {
    @Id
    private UUID id;

    @Column(name = "money")
    private Double money;

    @Column(name = "persent")
    private Long persent;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orders_id")
    private Orders orders;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "result")
    private ProcessRes processRes;

    private UUID uuid() {
        return UUID.randomUUID();
    }
}
