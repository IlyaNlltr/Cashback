package com.practic.cashback.dto;

import com.practic.cashback.model.AnswearEnum;
import com.practic.cashback.model.Cashback;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table
public class ProcessRes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "result", referencedColumnName = "money"),
            @JoinColumn(name = "persent", referencedColumnName = "persent")
    })

    private Cashback cashback;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private AnswearEnum status;
}
