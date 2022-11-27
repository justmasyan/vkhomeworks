package models;

import lombok.Data;

@Data
public class InvoiceData {
    private final int invoiceId;
    private final int idProduct;
    private final int price;
    private final int amount;
}
