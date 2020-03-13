package com.practic.cashback.model;

import com.practic.cashback.Status.StatusEnum;
import lombok.Data;

import javax.persistence.*;

@Data
@Table
@Entity
public class ProcessRes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double money;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
}
