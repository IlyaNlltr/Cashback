package com.practic.cashback.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table
@Data
public class Cashback {

    @Id
    @Column
    @GeneratedValue(generator = "doc-uuid")
    @GenericGenerator(name = "doc-uuid", strategy = "uuid")
    private String id;

    @Column
    private Double money;

    @Column
    private Long persent;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Orders orders;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Client client;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private ProcessRes processRes;
}
