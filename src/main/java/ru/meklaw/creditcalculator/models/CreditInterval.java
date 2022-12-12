package ru.meklaw.creditcalculator.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreditInterval {
    private int month;
    private double sumInTheBeginning;
    private double percent;
    private double creditPlusPercent;
    private double annuity;
    private double sumInTheEnd;
}
