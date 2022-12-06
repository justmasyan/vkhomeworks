/*
 * This file is generated by jOOQ.
 */
package generated.tables.records;


import generated.tables.Providers;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ProvidersRecord extends UpdatableRecordImpl<ProvidersRecord> implements Record4<Integer, String, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.providers.id</code>.
     */
    public ProvidersRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.providers.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.providers.title</code>.
     */
    public ProvidersRecord setTitle(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.providers.title</code>.
     */
    public String getTitle() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.providers.tin</code>.
     */
    public ProvidersRecord setTin(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.providers.tin</code>.
     */
    public String getTin() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.providers.payment_account</code>.
     */
    public ProvidersRecord setPaymentAccount(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.providers.payment_account</code>.
     */
    public String getPaymentAccount() {
        return (String) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, String, String, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Integer, String, String, String> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Providers.PROVIDERS.ID;
    }

    @Override
    public Field<String> field2() {
        return Providers.PROVIDERS.TITLE;
    }

    @Override
    public Field<String> field3() {
        return Providers.PROVIDERS.TIN;
    }

    @Override
    public Field<String> field4() {
        return Providers.PROVIDERS.PAYMENT_ACCOUNT;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getTitle();
    }

    @Override
    public String component3() {
        return getTin();
    }

    @Override
    public String component4() {
        return getPaymentAccount();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getTitle();
    }

    @Override
    public String value3() {
        return getTin();
    }

    @Override
    public String value4() {
        return getPaymentAccount();
    }

    @Override
    public ProvidersRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public ProvidersRecord value2(String value) {
        setTitle(value);
        return this;
    }

    @Override
    public ProvidersRecord value3(String value) {
        setTin(value);
        return this;
    }

    @Override
    public ProvidersRecord value4(String value) {
        setPaymentAccount(value);
        return this;
    }

    @Override
    public ProvidersRecord values(Integer value1, String value2, String value3, String value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ProvidersRecord
     */
    public ProvidersRecord() {
        super(Providers.PROVIDERS);
    }

    /**
     * Create a detached, initialised ProvidersRecord
     */
    public ProvidersRecord(Integer id, String title, String tin, String paymentAccount) {
        super(Providers.PROVIDERS);

        setId(id);
        setTitle(title);
        setTin(tin);
        setPaymentAccount(paymentAccount);
    }

    /**
     * Create a detached, initialised ProvidersRecord
     */
    public ProvidersRecord(generated.tables.pojos.Providers value) {
        super(Providers.PROVIDERS);

        if (value != null) {
            setId(value.getId());
            setTitle(value.getTitle());
            setTin(value.getTin());
            setPaymentAccount(value.getPaymentAccount());
        }
    }
}