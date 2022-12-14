/*
 * This file is generated by jOOQ.
 */
package generated.tables;


import generated.Keys;
import generated.Public;
import generated.tables.pojos.records.ProductsRecord;

import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row4;
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
public class Products extends TableImpl<ProductsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.products</code>
     */
    public static final Products PRODUCTS = new Products();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ProductsRecord> getRecordType() {
        return ProductsRecord.class;
    }

    /**
     * The column <code>public.products.id</code>.
     */
    public final TableField<ProductsRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.products.title</code>.
     */
    public final TableField<ProductsRecord, String> TITLE = createField(DSL.name("title"), SQLDataType.VARCHAR(40).nullable(false), this, "");

    /**
     * The column <code>public.products.company</code>.
     */
    public final TableField<ProductsRecord, Integer> COMPANY = createField(DSL.name("company"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.products.amount</code>.
     */
    public final TableField<ProductsRecord, Integer> AMOUNT = createField(DSL.name("amount"), SQLDataType.INTEGER.nullable(false), this, "");

    private Products(Name alias, Table<ProductsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Products(Name alias, Table<ProductsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.products</code> table reference
     */
    public Products(String alias) {
        this(DSL.name(alias), PRODUCTS);
    }

    /**
     * Create an aliased <code>public.products</code> table reference
     */
    public Products(Name alias) {
        this(alias, PRODUCTS);
    }

    /**
     * Create a <code>public.products</code> table reference
     */
    public Products() {
        this(DSL.name("products"), null);
    }

    public <O extends Record> Products(Table<O> child, ForeignKey<O, ProductsRecord> key) {
        super(child, key, PRODUCTS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<ProductsRecord, Integer> getIdentity() {
        return (Identity<ProductsRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<ProductsRecord> getPrimaryKey() {
        return Keys.PRODUCTS_PKEY;
    }

    @Override
    public List<ForeignKey<ProductsRecord, ?>> getReferences() {
        return Arrays.asList(Keys.PRODUCTS__PRODUCTS_COMPANY_FKEY);
    }

    private transient Firms _firms;

    /**
     * Get the implicit join path to the <code>public.firms</code> table.
     */
    public Firms firms() {
        if (_firms == null)
            _firms = new Firms(this, Keys.PRODUCTS__PRODUCTS_COMPANY_FKEY);

        return _firms;
    }

    @Override
    public Products as(String alias) {
        return new Products(DSL.name(alias), this);
    }

    @Override
    public Products as(Name alias) {
        return new Products(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Products rename(String name) {
        return new Products(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Products rename(Name name) {
        return new Products(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, String, Integer, Integer> fieldsRow() {
        return (Row4) super.fieldsRow();
    }
}
