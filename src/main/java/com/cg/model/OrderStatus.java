package com.cg.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum OrderStatus {

    ORDER("ORDER"), //0
    LOADING("LOADING"),
    SHIPPING("SHIPPING"),
    DONE("DONE"),   //1
    CANCEL("CANCEL"),
    BOMB("BOMB");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public static OrderStatus parse(String value) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.getValue().equalsIgnoreCase(value))
                return status;
        }
        throw new IllegalArgumentException("order status invalid");
    }

}