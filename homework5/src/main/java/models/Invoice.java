package models;

import lombok.Data;

import java.sql.Date;

@Data
public class Invoice {
    private final Date date;
    private final String provider;

    private final int[] idProducts;
    private final int[] prices;
    private final int[] amount;
}
