package com.practic.cashback.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@Data
public class Orders implements Serializable {
    @Id // первичный ключ таблицы
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER) // FetchType.EAGER - данные будут привязыватся сразу (не lazy)
    @JoinColumn(columnDefinition = "client_id") //  - колонка для связи с другой таблицей
    private Client clientOrders;

    @Column(name = "date")
    private String date;

    @Column(name = "orderName")
    private String orderName;

    @Column(name = "summ")
    private Double summ;
}
