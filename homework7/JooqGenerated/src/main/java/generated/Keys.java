/*
 * This file is generated by jOOQ.
 */
package generated;


import generated.tables.Firms;
import generated.tables.Products;
import generated.tables.pojos.records.FirmsRecord;
import generated.tables.pojos.records.ProductsRecord;

import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * public.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<FirmsRecord> FIRMS_PKEY = Internal.createUniqueKey(Firms.FIRMS, DSL.name("firms_pkey"), new TableField[] { Firms.FIRMS.ID }, true);
    public static final UniqueKey<ProductsRecord> PRODUCTS_PKEY = Internal.createUniqueKey(Products.PRODUCTS, DSL.name("products_pkey"), new TableField[] { Products.PRODUCTS.ID }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<ProductsRecord, FirmsRecord> PRODUCTS__PRODUCTS_COMPANY_FKEY = Internal.createForeignKey(Products.PRODUCTS, DSL.name("products_company_fkey"), new TableField[] { Products.PRODUCTS.COMPANY }, Keys.FIRMS_PKEY, new TableField[] { Firms.FIRMS.ID }, true);
}
