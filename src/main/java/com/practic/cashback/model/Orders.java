package com.practic.cashback.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@NoArgsConstructor
@Data
public class Orders implements Serializable {
    @Id // первичный ключ таблицы
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER) // FetchType.EAGER - данные будут привязыватся сразу (не lazy)
    @JoinColumn
    private Client clientOrders;

    @Column
    private String date;

    @Column
    private String orderName;

    @Column
    private Double summ;
}
