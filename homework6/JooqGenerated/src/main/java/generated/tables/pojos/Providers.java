/*
 * This file is generated by jOOQ.
 */
package generated.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Providers implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Integer id;
    private final String  title;
    private final String  tin;
    private final String  paymentAccount;

    public Providers(Providers value) {
        this.id = value.id;
        this.title = value.title;
        this.tin = value.tin;
        this.paymentAccount = value.paymentAccount;
    }

    public Providers(
        Integer id,
        String  title,
        String  tin,
        String  paymentAccount
    ) {
        this.id = id;
        this.title = title;
        this.tin = tin;
        this.paymentAccount = paymentAccount;
    }

    /**
     * Getter for <code>public.providers.id</code>.
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Getter for <code>public.providers.title</code>.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Getter for <code>public.providers.tin</code>.
     */
    public String getTin() {
        return this.tin;
    }

    /**
     * Getter for <code>public.providers.payment_account</code>.
     */
    public String getPaymentAccount() {
        return this.paymentAccount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Providers (");

        sb.append(id);
        sb.append(", ").append(title);
        sb.append(", ").append(tin);
        sb.append(", ").append(paymentAccount);

        sb.append(")");
        return sb.toString();
    }
}
