package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    @JsonProperty("title")
    public final String title;

    @JsonProperty("company")
    public final String company;

    @JsonProperty("amount")
    public final int amount;

    @JsonCreator
    public Product(@JsonProperty("title") String title,
                   @JsonProperty("company") String company,
                   @JsonProperty("amount") int amount) {
        this.title = title;
        this.company = company;
        this.amount = amount;
    }

    public Product(){
        this.title = null;
        this.company = null;
        this.amount = 0;
    }

    @Override
    public String toString() {
        return "{" +
                "\"title\"=\"" + title + "\",\n" +
                "\"company\"=\"" + company + "\",\n" +
                "\"amount\"=" + amount + "\n" +
                '}';
    }
}
