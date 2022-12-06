/*
 * This file is generated by jOOQ.
 */
package generated.tables;


import generated.Keys;
import generated.Public;
import generated.tables.records.InvoiceDataRecord;

import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row5;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class InvoiceData extends TableImpl<InvoiceDataRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.invoice_data</code>
     */
    public static final InvoiceData INVOICE_DATA = new InvoiceData();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<InvoiceDataRecord> getRecordType() {
        return InvoiceDataRecord.class;
    }

    /**
     * The column <code>public.invoice_data.id</code>.
     */
    public final TableField<InvoiceDataRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.invoice_data.invoice_id</code>.
     */
    public final TableField<InvoiceDataRecord, Integer> INVOICE_ID = createField(DSL.name("invoice_id"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.invoice_data.product</code>.
     */
    public final TableField<InvoiceDataRecord, Integer> PRODUCT = createField(DSL.name("product"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.invoice_data.price</code>.
     */
    public final TableField<InvoiceDataRecord, Integer> PRICE = createField(DSL.name("price"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.invoice_data.amount</code>.
     */
    public final TableField<InvoiceDataRecord, Integer> AMOUNT = createField(DSL.name("amount"), SQLDataType.INTEGER.nullable(false), this, "");

    private InvoiceData(Name alias, Table<InvoiceDataRecord> aliased) {
        this(alias, aliased, null);
    }

    private InvoiceData(Name alias, Table<InvoiceDataRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.invoice_data</code> table reference
     */
    public InvoiceData(String alias) {
        this(DSL.name(alias), INVOICE_DATA);
    }

    /**
     * Create an aliased <code>public.invoice_data</code> table reference
     */
    public InvoiceData(Name alias) {
        this(alias, INVOICE_DATA);
    }

    /**
     * Create a <code>public.invoice_data</code> table reference
     */
    public InvoiceData() {
        this(DSL.name("invoice_data"), null);
    }

    public <O extends Record> InvoiceData(Table<O> child, ForeignKey<O, InvoiceDataRecord> key) {
        super(child, key, INVOICE_DATA);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<InvoiceDataRecord, Integer> getIdentity() {
        return (Identity<InvoiceDataRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<InvoiceDataRecord> getPrimaryKey() {
        return Keys.INVOICE_DATA_PKEY;
    }

    @Override
    public List<ForeignKey<InvoiceDataRecord, ?>> getReferences() {
        return Arrays.asList(Keys.INVOICE_DATA__INVOICE_DATA_INVOICE_ID_FKEY, Keys.INVOICE_DATA__INVOICE_DATA_PRODUCT_FKEY);
    }

    private transient InvoiceInfo _invoiceInfo;
    private transient Products _products;

    /**
     * Get the implicit join path to the <code>public.invoice_info</code> table.
     */
    public InvoiceInfo invoiceInfo() {
        if (_invoiceInfo == null)
            _invoiceInfo = new InvoiceInfo(this, Keys.INVOICE_DATA__INVOICE_DATA_INVOICE_ID_FKEY);

        return _invoiceInfo;
    }

    /**
     * Get the implicit join path to the <code>public.products</code> table.
     */
    public Products products() {
        if (_products == null)
            _products = new Products(this, Keys.INVOICE_DATA__INVOICE_DATA_PRODUCT_FKEY);

        return _products;
    }

    @Override
    public InvoiceData as(String alias) {
        return new InvoiceData(DSL.name(alias), this);
    }

    @Override
    public InvoiceData as(Name alias) {
        return new InvoiceData(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public InvoiceData rename(String name) {
        return new InvoiceData(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public InvoiceData rename(Name name) {
        return new InvoiceData(name, null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<Integer, Integer, Integer, Integer, Integer> fieldsRow() {
        return (Row5) super.fieldsRow();
    }
}