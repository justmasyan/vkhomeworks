/*
 * This file is generated by jOOQ.
 */
package generated.tables;


import generated.Keys;
import generated.Public;
import generated.tables.records.ProvidersRecord;

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
public class Providers extends TableImpl<ProvidersRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.providers</code>
     */
    public static final Providers PROVIDERS = new Providers();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ProvidersRecord> getRecordType() {
        return ProvidersRecord.class;
    }

    /**
     * The column <code>public.providers.id</code>.
     */
    public final TableField<ProvidersRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.providers.title</code>.
     */
    public final TableField<ProvidersRecord, String> TITLE = createField(DSL.name("title"), SQLDataType.VARCHAR(50).nullable(false), this, "");

    /**
     * The column <code>public.providers.tin</code>.
     */
    public final TableField<ProvidersRecord, String> TIN = createField(DSL.name("tin"), SQLDataType.VARCHAR(15).nullable(false), this, "");

    /**
     * The column <code>public.providers.payment_account</code>.
     */
    public final TableField<ProvidersRecord, String> PAYMENT_ACCOUNT = createField(DSL.name("payment_account"), SQLDataType.VARCHAR(20).nullable(false), this, "");

    private Providers(Name alias, Table<ProvidersRecord> aliased) {
        this(alias, aliased, null);
    }

    private Providers(Name alias, Table<ProvidersRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.providers</code> table reference
     */
    public Providers(String alias) {
        this(DSL.name(alias), PROVIDERS);
    }

    /**
     * Create an aliased <code>public.providers</code> table reference
     */
    public Providers(Name alias) {
        this(alias, PROVIDERS);
    }

    /**
     * Create a <code>public.providers</code> table reference
     */
    public Providers() {
        this(DSL.name("providers"), null);
    }

    public <O extends Record> Providers(Table<O> child, ForeignKey<O, ProvidersRecord> key) {
        super(child, key, PROVIDERS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<ProvidersRecord, Integer> getIdentity() {
        return (Identity<ProvidersRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<ProvidersRecord> getPrimaryKey() {
        return Keys.PROVIDERS_PKEY;
    }

    @Override
    public Providers as(String alias) {
        return new Providers(DSL.name(alias), this);
    }

    @Override
    public Providers as(Name alias) {
        return new Providers(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Providers rename(String name) {
        return new Providers(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Providers rename(Name name) {
        return new Providers(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, String, String, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }
}
