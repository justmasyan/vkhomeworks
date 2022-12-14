/*
 * This file is generated by jOOQ.
 */
package generated.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Products implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Integer id;
    private final String  title;
    private final Integer company;
    private final Integer amount;

    public Products(Products value) {
        this.id = value.id;
        this.title = value.title;
        this.company = value.company;
        this.amount = value.amount;
    }

    public Products(
        Integer id,
        String  title,
        Integer company,
        Integer amount
    ) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.amount = amount;
    }

    /**
     * Getter for <code>public.products.id</code>.
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Getter for <code>public.products.title</code>.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Getter for <code>public.products.company</code>.
     */
    public Integer getCompany() {
        return this.company;
    }

    /**
     * Getter for <code>public.products.amount</code>.
     */
    public Integer getAmount() {
        return this.amount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Products (");

        sb.append(id);
        sb.append(", ").append(title);
        sb.append(", ").append(company);
        sb.append(", ").append(amount);

        sb.append(")");
        return sb.toString();
    }
}
