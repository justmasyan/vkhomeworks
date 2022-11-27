package models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InvoiceInfo {
    private final LocalDate date;
    private final int provider;
}
