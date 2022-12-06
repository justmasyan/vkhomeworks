package models;

import lombok.Data;

@Data
public class Product {
    final String title;
    final int companyId;
    final int amount;
}
