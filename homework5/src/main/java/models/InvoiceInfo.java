package models;

import lombok.Data;

import java.sql.Date;

@Data
public class InvoiceInfo {
    private final Date date;
    private final int provider;
}
