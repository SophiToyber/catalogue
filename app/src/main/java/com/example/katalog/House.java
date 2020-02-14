package com.example.katalog;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class House implements Serializable {


    String name;
    String address;
    Double price;
    String data;
    Integer quantity;
    Boolean prepayment;

}