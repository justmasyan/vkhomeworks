/*
 * This file is generated by jOOQ.
 */
package generated.tables.pojos;


import java.io.Serializable;
import java.time.LocalDate;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class InvoiceInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Integer   id;
    private final LocalDate date;
    private final Integer   provider;

    public InvoiceInfo(InvoiceInfo value) {
        this.id = value.id;
        this.date = value.date;
        this.provider = value.provider;
    }

    public InvoiceInfo(
        Integer   id,
        LocalDate date,
        Integer   provider
    ) {
        this.id = id;
        this.date = date;
        this.provider = provider;
    }

    /**
     * Getter for <code>public.invoice_info.id</code>.
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Getter for <code>public.invoice_info.date</code>.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Getter for <code>public.invoice_info.provider</code>.
     */
    public Integer getProvider() {
        return this.provider;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("InvoiceInfo (");

        sb.append(id);
        sb.append(", ").append(date);
        sb.append(", ").append(provider);

        sb.append(")");
        return sb.toString();
    }
}
