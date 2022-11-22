/*
 * This file is generated by jOOQ.
 */
package generated;


import generated.tables.FlywaySchemaHistory;
import generated.tables.InvoiceData;
import generated.tables.InvoiceInfo;
import generated.tables.Products;
import generated.tables.Providers;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.flyway_schema_history</code>.
     */
    public final FlywaySchemaHistory FLYWAY_SCHEMA_HISTORY = FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY;

    /**
     * The table <code>public.invoice_data</code>.
     */
    public final InvoiceData INVOICE_DATA = InvoiceData.INVOICE_DATA;

    /**
     * The table <code>public.invoice_info</code>.
     */
    public final InvoiceInfo INVOICE_INFO = InvoiceInfo.INVOICE_INFO;

    /**
     * The table <code>public.products</code>.
     */
    public final Products PRODUCTS = Products.PRODUCTS;

    /**
     * The table <code>public.providers</code>.
     */
    public final Providers PROVIDERS = Providers.PROVIDERS;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY,
            InvoiceData.INVOICE_DATA,
            InvoiceInfo.INVOICE_INFO,
            Products.PRODUCTS,
            Providers.PROVIDERS
        );
    }
}
