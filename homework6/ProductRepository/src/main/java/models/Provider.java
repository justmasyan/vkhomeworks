package models;

import lombok.Data;

@Data
public class Provider {
    private final String title;
    private final String TIN;
    private final String payment_account;
}
