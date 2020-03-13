package com.practic.cashback.dto;

import com.practic.cashback.model.Client;
import com.practic.cashback.model.Orders;
import com.practic.cashback.model.ProcessRes;
import lombok.Data;

import java.io.Serializable;

@Data
public class CashbackDTO implements Serializable {

    private String id;
    private Orders orders;
    private Client client;
    private ProcessRes processRes;


}
