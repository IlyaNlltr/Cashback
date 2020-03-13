package com.practic.cashback.dto;

import com.practic.cashback.status.StatusEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProcessResDTO implements Serializable {

    private Long id;
    private StatusEnum status;
}
