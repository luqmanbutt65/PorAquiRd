package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.ImportFlag;
import io.realm.ProxyUtils;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsList;
import io.realm.internal.OsObject;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.Property;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.Table;
import io.realm.internal.android.JsonUtils;
import io.realm.internal.objectstore.OsObjectBuilder;
import io.realm.log.RealmLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("all")
public class com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy extends com.example.realestate.Model.REST.Properties.FavProperties.FavProperties
    implements RealmObjectProxy, com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface {

    static final class FavPropertiesColumnInfo extends ColumnInfo {
        long tttypeColKey;
        long idColKey;
        long use_idColKey;
        long titleColKey;
        long sale_typeColKey;
        long areaColKey;
        long locationColKey;
        long cityColKey;
        long priceColKey;
        long main_imageColKey;
        long top_offerColKey;
        long created_atColKey;
        long updated_atColKey;
        long bedroomColKey;
        long bathColKey;
        long property_typeColKey;
        long descriptionColKey;
        long sectorColKey;
        long unit_of_measureColKey;
        long date_of_constructionColKey;
        long ratingColKey;
        long commentColKey;
        long user_ratingColKey;
        long propertiesExtraArrayListColKey;
        long propertiesGalleryArrayListColKey;
        long likeColKey;
        long latitudeColKey;
        long longitudeColKey;

        FavPropertiesColumnInfo(OsSchemaInfo schemaInfo) {
            super(28);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("FavProperties");
            this.tttypeColKey = addColumnDetails("tttype", "tttype", objectSchemaInfo);
            this.idColKey = addColumnDetails("id", "id", objectSchemaInfo);
            this.use_idColKey = addColumnDetails("use_id", "use_id", objectSchemaInfo);
            this.titleColKey = addColumnDetails("title", "title", objectSchemaInfo);
            this.sale_typeColKey = addColumnDetails("sale_type", "sale_type", objectSchemaInfo);
            this.areaColKey = addColumnDetails("area", "area", objectSchemaInfo);
            this.locationColKey = addColumnDetails("location", "location", objectSchemaInfo);
            this.cityColKey = addColumnDetails("city", "city", objectSchemaInfo);
            this.priceColKey = addColumnDetails("price", "price", objectSchemaInfo);
            this.main_imageColKey = addColumnDetails("main_image", "main_image", objectSchemaInfo);
            this.top_offerColKey = addColumnDetails("top_offer", "top_offer", objectSchemaInfo);
            this.created_atColKey = addColumnDetails("created_at", "created_at", objectSchemaInfo);
            this.updated_atColKey = addColumnDetails("updated_at", "updated_at", objectSchemaInfo);
            this.bedroomColKey = addColumnDetails("bedroom", "bedroom", objectSchemaInfo);
            this.bathColKey = addColumnDetails("bath", "bath", objectSchemaInfo);
            this.property_typeColKey = addColumnDetails("property_type", "property_type", objectSchemaInfo);
            this.descriptionColKey = addColumnDetails("description", "description", objectSchemaInfo);
            this.sectorColKey = addColumnDetails("sector", "sector", objectSchemaInfo);
            this.unit_of_measureColKey = addColumnDetails("unit_of_measure", "unit_of_measure", objectSchemaInfo);
            this.date_of_constructionColKey = addColumnDetails("date_of_construction", "date_of_construction", objectSchemaInfo);
            this.ratingColKey = addColumnDetails("rating", "rating", objectSchemaInfo);
            this.commentColKey = addColumnDetails("comment", "comment", objectSchemaInfo);
            this.user_ratingColKey = addColumnDetails("user_rating", "user_rating", objectSchemaInfo);
            this.propertiesExtraArrayListColKey = addColumnDetails("propertiesExtraArrayList", "propertiesExtraArrayList", objectSchemaInfo);
            this.propertiesGalleryArrayListColKey = addColumnDetails("propertiesGalleryArrayList", "propertiesGalleryArrayList", objectSchemaInfo);
            this.likeColKey = addColumnDetails("like", "like", objectSchemaInfo);
            this.latitudeColKey = addColumnDetails("latitude", "latitude", objectSchemaInfo);
            this.longitudeColKey = addColumnDetails("longitude", "longitude", objectSchemaInfo);
        }

        FavPropertiesColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new FavPropertiesColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final FavPropertiesColumnInfo src = (FavPropertiesColumnInfo) rawSrc;
            final FavPropertiesColumnInfo dst = (FavPropertiesColumnInfo) rawDst;
            dst.tttypeColKey = src.tttypeColKey;
            dst.idColKey = src.idColKey;
            dst.use_idColKey = src.use_idColKey;
            dst.titleColKey = src.titleColKey;
            dst.sale_typeColKey = src.sale_typeColKey;
            dst.areaColKey = src.areaColKey;
            dst.locationColKey = src.locationColKey;
            dst.cityColKey = src.cityColKey;
            dst.priceColKey = src.priceColKey;
            dst.main_imageColKey = src.main_imageColKey;
            dst.top_offerColKey = src.top_offerColKey;
            dst.created_atColKey = src.created_atColKey;
            dst.updated_atColKey = src.updated_atColKey;
            dst.bedroomColKey = src.bedroomColKey;
            dst.bathColKey = src.bathColKey;
            dst.property_typeColKey = src.property_typeColKey;
            dst.descriptionColKey = src.descriptionColKey;
            dst.sectorColKey = src.sectorColKey;
            dst.unit_of_measureColKey = src.unit_of_measureColKey;
            dst.date_of_constructionColKey = src.date_of_constructionColKey;
            dst.ratingColKey = src.ratingColKey;
            dst.commentColKey = src.commentColKey;
            dst.user_ratingColKey = src.user_ratingColKey;
            dst.propertiesExtraArrayListColKey = src.propertiesExtraArrayListColKey;
            dst.propertiesGalleryArrayListColKey = src.propertiesGalleryArrayListColKey;
            dst.likeColKey = src.likeColKey;
            dst.latitudeColKey = src.latitudeColKey;
            dst.longitudeColKey = src.longitudeColKey;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private FavPropertiesColumnInfo columnInfo;
    private ProxyState<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties> proxyState;
    private RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties> propertiesExtraArrayListRealmList;
    private RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties> propertiesGalleryArrayListRealmList;

    com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (FavPropertiesColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$tttype() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.tttypeColKey);
    }

    @Override
    public void realmSet$tttype(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.tttypeColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.tttypeColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$id() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.idColKey);
    }

    @Override
    public void realmSet$id(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.idColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.idColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$use_id() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.use_idColKey);
    }

    @Override
    public void realmSet$use_id(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.use_idColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.use_idColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.use_idColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.use_idColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$title() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.titleColKey);
    }

    @Override
    public void realmSet$title(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.titleColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.titleColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.titleColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.titleColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$sale_type() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.sale_typeColKey);
    }

    @Override
    public void realmSet$sale_type(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.sale_typeColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.sale_typeColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.sale_typeColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.sale_typeColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$area() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.areaColKey);
    }

    @Override
    public void realmSet$area(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.areaColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.areaColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.areaColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.areaColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$location() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.locationColKey);
    }

    @Override
    public void realmSet$location(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.locationColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.locationColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.locationColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.locationColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$city() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.cityColKey);
    }

    @Override
    public void realmSet$city(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.cityColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.cityColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.cityColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.cityColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$price() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.priceColKey);
    }

    @Override
    public void realmSet$price(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.priceColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.priceColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.priceColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.priceColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$main_image() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.main_imageColKey);
    }

    @Override
    public void realmSet$main_image(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.main_imageColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.main_imageColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.main_imageColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.main_imageColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$top_offer() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.top_offerColKey);
    }

    @Override
    public void realmSet$top_offer(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.top_offerColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.top_offerColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.top_offerColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.top_offerColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$created_at() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.created_atColKey);
    }

    @Override
    public void realmSet$created_at(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.created_atColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.created_atColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.created_atColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.created_atColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$updated_at() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.updated_atColKey);
    }

    @Override
    public void realmSet$updated_at(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.updated_atColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.updated_atColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.updated_atColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.updated_atColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$bedroom() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.bedroomColKey);
    }

    @Override
    public void realmSet$bedroom(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.bedroomColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.bedroomColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.bedroomColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.bedroomColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$bath() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.bathColKey);
    }

    @Override
    public void realmSet$bath(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.bathColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.bathColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.bathColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.bathColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$property_type() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.property_typeColKey);
    }

    @Override
    public void realmSet$property_type(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.property_typeColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.property_typeColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.property_typeColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.property_typeColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$description() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.descriptionColKey);
    }

    @Override
    public void realmSet$description(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.descriptionColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.descriptionColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.descriptionColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.descriptionColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$sector() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.sectorColKey);
    }

    @Override
    public void realmSet$sector(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.sectorColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.sectorColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.sectorColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.sectorColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$unit_of_measure() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.unit_of_measureColKey);
    }

    @Override
    public void realmSet$unit_of_measure(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.unit_of_measureColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.unit_of_measureColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.unit_of_measureColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.unit_of_measureColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$date_of_construction() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.date_of_constructionColKey);
    }

    @Override
    public void realmSet$date_of_construction(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.date_of_constructionColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.date_of_constructionColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.date_of_constructionColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.date_of_constructionColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$rating() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.ratingColKey);
    }

    @Override
    public void realmSet$rating(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.ratingColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.ratingColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.ratingColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.ratingColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$comment() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.commentColKey);
    }

    @Override
    public void realmSet$comment(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.commentColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.commentColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.commentColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.commentColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$user_rating() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.user_ratingColKey);
    }

    @Override
    public void realmSet$user_rating(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.user_ratingColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.user_ratingColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.user_ratingColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.user_ratingColKey, value);
    }

    @Override
    public RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties> realmGet$propertiesExtraArrayList() {
        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (propertiesExtraArrayListRealmList != null) {
            return propertiesExtraArrayListRealmList;
        } else {
            OsList osList = proxyState.getRow$realm().getModelList(columnInfo.propertiesExtraArrayListColKey);
            propertiesExtraArrayListRealmList = new RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties>(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties.class, osList, proxyState.getRealm$realm());
            return propertiesExtraArrayListRealmList;
        }
    }

    @Override
    public void realmSet$propertiesExtraArrayList(RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties> value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("propertiesExtraArrayList")) {
                return;
            }
            // if the list contains unmanaged RealmObjects, convert them to managed.
            if (value != null && !value.isManaged()) {
                final Realm realm = (Realm) proxyState.getRealm$realm();
                final RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties> original = value;
                value = new RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties>();
                for (com.example.realestate.Model.REST.Properties.FavProperties.FavProperties item : original) {
                    if (item == null || RealmObject.isManaged(item)) {
                        value.add(item);
                    } else {
                        value.add(realm.copyToRealm(item));
                    }
                }
            }
        }

        proxyState.getRealm$realm().checkIfValid();
        OsList osList = proxyState.getRow$realm().getModelList(columnInfo.propertiesExtraArrayListColKey);
        // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
        if (value != null && value.size() == osList.size()) {
            int objects = value.size();
            for (int i = 0; i < objects; i++) {
                com.example.realestate.Model.REST.Properties.FavProperties.FavProperties linkedObject = value.get(i);
                proxyState.checkValidObject(linkedObject);
                osList.setRow(i, ((RealmObjectProxy) linkedObject).realmGet$proxyState().getRow$realm().getObjectKey());
            }
        } else {
            osList.removeAll();
            if (value == null) {
                return;
            }
            int objects = value.size();
            for (int i = 0; i < objects; i++) {
                com.example.realestate.Model.REST.Properties.FavProperties.FavProperties linkedObject = value.get(i);
                proxyState.checkValidObject(linkedObject);
                osList.addRow(((RealmObjectProxy) linkedObject).realmGet$proxyState().getRow$realm().getObjectKey());
            }
        }
    }

    @Override
    public RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties> realmGet$propertiesGalleryArrayList() {
        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (propertiesGalleryArrayListRealmList != null) {
            return propertiesGalleryArrayListRealmList;
        } else {
            OsList osList = proxyState.getRow$realm().getModelList(columnInfo.propertiesGalleryArrayListColKey);
            propertiesGalleryArrayListRealmList = new RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties>(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties.class, osList, proxyState.getRealm$realm());
            return propertiesGalleryArrayListRealmList;
        }
    }

    @Override
    public void realmSet$propertiesGalleryArrayList(RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties> value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("propertiesGalleryArrayList")) {
                return;
            }
            // if the list contains unmanaged RealmObjects, convert them to managed.
            if (value != null && !value.isManaged()) {
                final Realm realm = (Realm) proxyState.getRealm$realm();
                final RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties> original = value;
                value = new RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties>();
                for (com.example.realestate.Model.REST.Properties.FavProperties.FavProperties item : original) {
                    if (item == null || RealmObject.isManaged(item)) {
                        value.add(item);
                    } else {
                        value.add(realm.copyToRealm(item));
                    }
                }
            }
        }

        proxyState.getRealm$realm().checkIfValid();
        OsList osList = proxyState.getRow$realm().getModelList(columnInfo.propertiesGalleryArrayListColKey);
        // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
        if (value != null && value.size() == osList.size()) {
            int objects = value.size();
            for (int i = 0; i < objects; i++) {
                com.example.realestate.Model.REST.Properties.FavProperties.FavProperties linkedObject = value.get(i);
                proxyState.checkValidObject(linkedObject);
                osList.setRow(i, ((RealmObjectProxy) linkedObject).realmGet$proxyState().getRow$realm().getObjectKey());
            }
        } else {
            osList.removeAll();
            if (value == null) {
                return;
            }
            int objects = value.size();
            for (int i = 0; i < objects; i++) {
                com.example.realestate.Model.REST.Properties.FavProperties.FavProperties linkedObject = value.get(i);
                proxyState.checkValidObject(linkedObject);
                osList.addRow(((RealmObjectProxy) linkedObject).realmGet$proxyState().getRow$realm().getObjectKey());
            }
        }
    }

    @Override
    @SuppressWarnings("cast")
    public boolean realmGet$like() {
        proxyState.getRealm$realm().checkIfValid();
        return (boolean) proxyState.getRow$realm().getBoolean(columnInfo.likeColKey);
    }

    @Override
    public void realmSet$like(boolean value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setBoolean(columnInfo.likeColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setBoolean(columnInfo.likeColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public double realmGet$latitude() {
        proxyState.getRealm$realm().checkIfValid();
        return (double) proxyState.getRow$realm().getDouble(columnInfo.latitudeColKey);
    }

    @Override
    public void realmSet$latitude(double value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setDouble(columnInfo.latitudeColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setDouble(columnInfo.latitudeColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public double realmGet$longitude() {
        proxyState.getRealm$realm().checkIfValid();
        return (double) proxyState.getRow$realm().getDouble(columnInfo.longitudeColKey);
    }

    @Override
    public void realmSet$longitude(double value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setDouble(columnInfo.longitudeColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setDouble(columnInfo.longitudeColKey, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("FavProperties", 28, 0);
        builder.addPersistedProperty("tttype", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("id", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("use_id", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("title", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("sale_type", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("area", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("location", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("city", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("price", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("main_image", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("top_offer", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("created_at", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("updated_at", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("bedroom", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("bath", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("property_type", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("description", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("sector", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("unit_of_measure", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("date_of_construction", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("rating", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("comment", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("user_rating", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedLinkProperty("propertiesExtraArrayList", RealmFieldType.LIST, "FavProperties");
        builder.addPersistedLinkProperty("propertiesGalleryArrayList", RealmFieldType.LIST, "FavProperties");
        builder.addPersistedProperty("like", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("latitude", RealmFieldType.DOUBLE, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("longitude", RealmFieldType.DOUBLE, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static FavPropertiesColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new FavPropertiesColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "FavProperties";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "FavProperties";
    }

    @SuppressWarnings("cast")
    public static com.example.realestate.Model.REST.Properties.FavProperties.FavProperties createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = new ArrayList<String>(2);
        if (json.has("propertiesExtraArrayList")) {
            excludeFields.add("propertiesExtraArrayList");
        }
        if (json.has("propertiesGalleryArrayList")) {
            excludeFields.add("propertiesGalleryArrayList");
        }
        com.example.realestate.Model.REST.Properties.FavProperties.FavProperties obj = realm.createObjectInternal(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties.class, true, excludeFields);

        final com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface objProxy = (com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) obj;
        if (json.has("tttype")) {
            if (json.isNull("tttype")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'tttype' to null.");
            } else {
                objProxy.realmSet$tttype((int) json.getInt("tttype"));
            }
        }
        if (json.has("id")) {
            if (json.isNull("id")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'id' to null.");
            } else {
                objProxy.realmSet$id((int) json.getInt("id"));
            }
        }
        if (json.has("use_id")) {
            if (json.isNull("use_id")) {
                objProxy.realmSet$use_id(null);
            } else {
                objProxy.realmSet$use_id((String) json.getString("use_id"));
            }
        }
        if (json.has("title")) {
            if (json.isNull("title")) {
                objProxy.realmSet$title(null);
            } else {
                objProxy.realmSet$title((String) json.getString("title"));
            }
        }
        if (json.has("sale_type")) {
            if (json.isNull("sale_type")) {
                objProxy.realmSet$sale_type(null);
            } else {
                objProxy.realmSet$sale_type((String) json.getString("sale_type"));
            }
        }
        if (json.has("area")) {
            if (json.isNull("area")) {
                objProxy.realmSet$area(null);
            } else {
                objProxy.realmSet$area((String) json.getString("area"));
            }
        }
        if (json.has("location")) {
            if (json.isNull("location")) {
                objProxy.realmSet$location(null);
            } else {
                objProxy.realmSet$location((String) json.getString("location"));
            }
        }
        if (json.has("city")) {
            if (json.isNull("city")) {
                objProxy.realmSet$city(null);
            } else {
                objProxy.realmSet$city((String) json.getString("city"));
            }
        }
        if (json.has("price")) {
            if (json.isNull("price")) {
                objProxy.realmSet$price(null);
            } else {
                objProxy.realmSet$price((String) json.getString("price"));
            }
        }
        if (json.has("main_image")) {
            if (json.isNull("main_image")) {
                objProxy.realmSet$main_image(null);
            } else {
                objProxy.realmSet$main_image((String) json.getString("main_image"));
            }
        }
        if (json.has("top_offer")) {
            if (json.isNull("top_offer")) {
                objProxy.realmSet$top_offer(null);
            } else {
                objProxy.realmSet$top_offer((String) json.getString("top_offer"));
            }
        }
        if (json.has("created_at")) {
            if (json.isNull("created_at")) {
                objProxy.realmSet$created_at(null);
            } else {
                objProxy.realmSet$created_at((String) json.getString("created_at"));
            }
        }
        if (json.has("updated_at")) {
            if (json.isNull("updated_at")) {
                objProxy.realmSet$updated_at(null);
            } else {
                objProxy.realmSet$updated_at((String) json.getString("updated_at"));
            }
        }
        if (json.has("bedroom")) {
            if (json.isNull("bedroom")) {
                objProxy.realmSet$bedroom(null);
            } else {
                objProxy.realmSet$bedroom((String) json.getString("bedroom"));
            }
        }
        if (json.has("bath")) {
            if (json.isNull("bath")) {
                objProxy.realmSet$bath(null);
            } else {
                objProxy.realmSet$bath((String) json.getString("bath"));
            }
        }
        if (json.has("property_type")) {
            if (json.isNull("property_type")) {
                objProxy.realmSet$property_type(null);
            } else {
                objProxy.realmSet$property_type((String) json.getString("property_type"));
            }
        }
        if (json.has("description")) {
            if (json.isNull("description")) {
                objProxy.realmSet$description(null);
            } else {
                objProxy.realmSet$description((String) json.getString("description"));
            }
        }
        if (json.has("sector")) {
            if (json.isNull("sector")) {
                objProxy.realmSet$sector(null);
            } else {
                objProxy.realmSet$sector((String) json.getString("sector"));
            }
        }
        if (json.has("unit_of_measure")) {
            if (json.isNull("unit_of_measure")) {
                objProxy.realmSet$unit_of_measure(null);
            } else {
                objProxy.realmSet$unit_of_measure((String) json.getString("unit_of_measure"));
            }
        }
        if (json.has("date_of_construction")) {
            if (json.isNull("date_of_construction")) {
                objProxy.realmSet$date_of_construction(null);
            } else {
                objProxy.realmSet$date_of_construction((String) json.getString("date_of_construction"));
            }
        }
        if (json.has("rating")) {
            if (json.isNull("rating")) {
                objProxy.realmSet$rating(null);
            } else {
                objProxy.realmSet$rating((String) json.getString("rating"));
            }
        }
        if (json.has("comment")) {
            if (json.isNull("comment")) {
                objProxy.realmSet$comment(null);
            } else {
                objProxy.realmSet$comment((String) json.getString("comment"));
            }
        }
        if (json.has("user_rating")) {
            if (json.isNull("user_rating")) {
                objProxy.realmSet$user_rating(null);
            } else {
                objProxy.realmSet$user_rating((String) json.getString("user_rating"));
            }
        }
        if (json.has("propertiesExtraArrayList")) {
            if (json.isNull("propertiesExtraArrayList")) {
                objProxy.realmSet$propertiesExtraArrayList(null);
            } else {
                objProxy.realmGet$propertiesExtraArrayList().clear();
                JSONArray array = json.getJSONArray("propertiesExtraArrayList");
                for (int i = 0; i < array.length(); i++) {
                    com.example.realestate.Model.REST.Properties.FavProperties.FavProperties item = com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    objProxy.realmGet$propertiesExtraArrayList().add(item);
                }
            }
        }
        if (json.has("propertiesGalleryArrayList")) {
            if (json.isNull("propertiesGalleryArrayList")) {
                objProxy.realmSet$propertiesGalleryArrayList(null);
            } else {
                objProxy.realmGet$propertiesGalleryArrayList().clear();
                JSONArray array = json.getJSONArray("propertiesGalleryArrayList");
                for (int i = 0; i < array.length(); i++) {
                    com.example.realestate.Model.REST.Properties.FavProperties.FavProperties item = com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    objProxy.realmGet$propertiesGalleryArrayList().add(item);
                }
            }
        }
        if (json.has("like")) {
            if (json.isNull("like")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'like' to null.");
            } else {
                objProxy.realmSet$like((boolean) json.getBoolean("like"));
            }
        }
        if (json.has("latitude")) {
            if (json.isNull("latitude")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'latitude' to null.");
            } else {
                objProxy.realmSet$latitude((double) json.getDouble("latitude"));
            }
        }
        if (json.has("longitude")) {
            if (json.isNull("longitude")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'longitude' to null.");
            } else {
                objProxy.realmSet$longitude((double) json.getDouble("longitude"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.example.realestate.Model.REST.Properties.FavProperties.FavProperties createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        final com.example.realestate.Model.REST.Properties.FavProperties.FavProperties obj = new com.example.realestate.Model.REST.Properties.FavProperties.FavProperties();
        final com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface objProxy = (com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("tttype")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$tttype((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'tttype' to null.");
                }
            } else if (name.equals("id")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$id((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'id' to null.");
                }
            } else if (name.equals("use_id")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$use_id((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$use_id(null);
                }
            } else if (name.equals("title")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$title((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$title(null);
                }
            } else if (name.equals("sale_type")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$sale_type((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$sale_type(null);
                }
            } else if (name.equals("area")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$area((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$area(null);
                }
            } else if (name.equals("location")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$location((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$location(null);
                }
            } else if (name.equals("city")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$city((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$city(null);
                }
            } else if (name.equals("price")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$price((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$price(null);
                }
            } else if (name.equals("main_image")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$main_image((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$main_image(null);
                }
            } else if (name.equals("top_offer")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$top_offer((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$top_offer(null);
                }
            } else if (name.equals("created_at")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$created_at((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$created_at(null);
                }
            } else if (name.equals("updated_at")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$updated_at((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$updated_at(null);
                }
            } else if (name.equals("bedroom")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$bedroom((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$bedroom(null);
                }
            } else if (name.equals("bath")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$bath((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$bath(null);
                }
            } else if (name.equals("property_type")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$property_type((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$property_type(null);
                }
            } else if (name.equals("description")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$description((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$description(null);
                }
            } else if (name.equals("sector")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$sector((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$sector(null);
                }
            } else if (name.equals("unit_of_measure")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$unit_of_measure((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$unit_of_measure(null);
                }
            } else if (name.equals("date_of_construction")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$date_of_construction((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$date_of_construction(null);
                }
            } else if (name.equals("rating")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$rating((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$rating(null);
                }
            } else if (name.equals("comment")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$comment((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$comment(null);
                }
            } else if (name.equals("user_rating")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$user_rating((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$user_rating(null);
                }
            } else if (name.equals("propertiesExtraArrayList")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$propertiesExtraArrayList(null);
                } else {
                    objProxy.realmSet$propertiesExtraArrayList(new RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties>());
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.example.realestate.Model.REST.Properties.FavProperties.FavProperties item = com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy.createUsingJsonStream(realm, reader);
                        objProxy.realmGet$propertiesExtraArrayList().add(item);
                    }
                    reader.endArray();
                }
            } else if (name.equals("propertiesGalleryArrayList")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$propertiesGalleryArrayList(null);
                } else {
                    objProxy.realmSet$propertiesGalleryArrayList(new RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties>());
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.example.realestate.Model.REST.Properties.FavProperties.FavProperties item = com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy.createUsingJsonStream(realm, reader);
                        objProxy.realmGet$propertiesGalleryArrayList().add(item);
                    }
                    reader.endArray();
                }
            } else if (name.equals("like")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$like((boolean) reader.nextBoolean());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'like' to null.");
                }
            } else if (name.equals("latitude")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$latitude((double) reader.nextDouble());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'latitude' to null.");
                }
            } else if (name.equals("longitude")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$longitude((double) reader.nextDouble());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'longitude' to null.");
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return realm.copyToRealm(obj);
    }

    private static com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties.class), false, Collections.<String>emptyList());
        io.realm.com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy obj = new io.realm.com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.example.realestate.Model.REST.Properties.FavProperties.FavProperties copyOrUpdate(Realm realm, FavPropertiesColumnInfo columnInfo, com.example.realestate.Model.REST.Properties.FavProperties.FavProperties object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null) {
            final BaseRealm otherRealm = ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm();
            if (otherRealm.threadId != realm.threadId) {
                throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
            }
            if (otherRealm.getPath().equals(realm.getPath())) {
                return object;
            }
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.example.realestate.Model.REST.Properties.FavProperties.FavProperties) cachedRealmObject;
        }

        return copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.example.realestate.Model.REST.Properties.FavProperties.FavProperties copy(Realm realm, FavPropertiesColumnInfo columnInfo, com.example.realestate.Model.REST.Properties.FavProperties.FavProperties newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.example.realestate.Model.REST.Properties.FavProperties.FavProperties) cachedRealmObject;
        }

        com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface realmObjectSource = (com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) newObject;

        Table table = realm.getTable(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, flags);

        // Add all non-"object reference" fields
        builder.addInteger(columnInfo.tttypeColKey, realmObjectSource.realmGet$tttype());
        builder.addInteger(columnInfo.idColKey, realmObjectSource.realmGet$id());
        builder.addString(columnInfo.use_idColKey, realmObjectSource.realmGet$use_id());
        builder.addString(columnInfo.titleColKey, realmObjectSource.realmGet$title());
        builder.addString(columnInfo.sale_typeColKey, realmObjectSource.realmGet$sale_type());
        builder.addString(columnInfo.areaColKey, realmObjectSource.realmGet$area());
        builder.addString(columnInfo.locationColKey, realmObjectSource.realmGet$location());
        builder.addString(columnInfo.cityColKey, realmObjectSource.realmGet$city());
        builder.addString(columnInfo.priceColKey, realmObjectSource.realmGet$price());
        builder.addString(columnInfo.main_imageColKey, realmObjectSource.realmGet$main_image());
        builder.addString(columnInfo.top_offerColKey, realmObjectSource.realmGet$top_offer());
        builder.addString(columnInfo.created_atColKey, realmObjectSource.realmGet$created_at());
        builder.addString(columnInfo.updated_atColKey, realmObjectSource.realmGet$updated_at());
        builder.addString(columnInfo.bedroomColKey, realmObjectSource.realmGet$bedroom());
        builder.addString(columnInfo.bathColKey, realmObjectSource.realmGet$bath());
        builder.addString(columnInfo.property_typeColKey, realmObjectSource.realmGet$property_type());
        builder.addString(columnInfo.descriptionColKey, realmObjectSource.realmGet$description());
        builder.addString(columnInfo.sectorColKey, realmObjectSource.realmGet$sector());
        builder.addString(columnInfo.unit_of_measureColKey, realmObjectSource.realmGet$unit_of_measure());
        builder.addString(columnInfo.date_of_constructionColKey, realmObjectSource.realmGet$date_of_construction());
        builder.addString(columnInfo.ratingColKey, realmObjectSource.realmGet$rating());
        builder.addString(columnInfo.commentColKey, realmObjectSource.realmGet$comment());
        builder.addString(columnInfo.user_ratingColKey, realmObjectSource.realmGet$user_rating());
        builder.addBoolean(columnInfo.likeColKey, realmObjectSource.realmGet$like());
        builder.addDouble(columnInfo.latitudeColKey, realmObjectSource.realmGet$latitude());
        builder.addDouble(columnInfo.longitudeColKey, realmObjectSource.realmGet$longitude());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy realmObjectCopy = newProxyInstance(realm, row);
        cache.put(newObject, realmObjectCopy);

        // Finally add all fields that reference other Realm Objects, either directly or through a list
        RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties> propertiesExtraArrayListList = realmObjectSource.realmGet$propertiesExtraArrayList();
        if (propertiesExtraArrayListList != null) {
            RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties> propertiesExtraArrayListRealmList = realmObjectCopy.realmGet$propertiesExtraArrayList();
            propertiesExtraArrayListRealmList.clear();
            for (int i = 0; i < propertiesExtraArrayListList.size(); i++) {
                com.example.realestate.Model.REST.Properties.FavProperties.FavProperties propertiesExtraArrayListItem = propertiesExtraArrayListList.get(i);
                com.example.realestate.Model.REST.Properties.FavProperties.FavProperties cachepropertiesExtraArrayList = (com.example.realestate.Model.REST.Properties.FavProperties.FavProperties) cache.get(propertiesExtraArrayListItem);
                if (cachepropertiesExtraArrayList != null) {
                    propertiesExtraArrayListRealmList.add(cachepropertiesExtraArrayList);
                } else {
                    propertiesExtraArrayListRealmList.add(com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy.copyOrUpdate(realm, (com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy.FavPropertiesColumnInfo) realm.getSchema().getColumnInfo(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties.class), propertiesExtraArrayListItem, update, cache, flags));
                }
            }
        }

        RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties> propertiesGalleryArrayListList = realmObjectSource.realmGet$propertiesGalleryArrayList();
        if (propertiesGalleryArrayListList != null) {
            RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties> propertiesGalleryArrayListRealmList = realmObjectCopy.realmGet$propertiesGalleryArrayList();
            propertiesGalleryArrayListRealmList.clear();
            for (int i = 0; i < propertiesGalleryArrayListList.size(); i++) {
                com.example.realestate.Model.REST.Properties.FavProperties.FavProperties propertiesGalleryArrayListItem = propertiesGalleryArrayListList.get(i);
                com.example.realestate.Model.REST.Properties.FavProperties.FavProperties cachepropertiesGalleryArrayList = (com.example.realestate.Model.REST.Properties.FavProperties.FavProperties) cache.get(propertiesGalleryArrayListItem);
                if (cachepropertiesGalleryArrayList != null) {
                    propertiesGalleryArrayListRealmList.add(cachepropertiesGalleryArrayList);
                } else {
                    propertiesGalleryArrayListRealmList.add(com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy.copyOrUpdate(realm, (com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy.FavPropertiesColumnInfo) realm.getSchema().getColumnInfo(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties.class), propertiesGalleryArrayListItem, update, cache, flags));
                }
            }
        }

        return realmObjectCopy;
    }

    public static long insert(Realm realm, com.example.realestate.Model.REST.Properties.FavProperties.FavProperties object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties.class);
        long tableNativePtr = table.getNativePtr();
        FavPropertiesColumnInfo columnInfo = (FavPropertiesColumnInfo) realm.getSchema().getColumnInfo(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties.class);
        long colKey = OsObject.createRow(table);
        cache.put(object, colKey);
        Table.nativeSetLong(tableNativePtr, columnInfo.tttypeColKey, colKey, ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$tttype(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.idColKey, colKey, ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$id(), false);
        String realmGet$use_id = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$use_id();
        if (realmGet$use_id != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.use_idColKey, colKey, realmGet$use_id, false);
        }
        String realmGet$title = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$title();
        if (realmGet$title != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.titleColKey, colKey, realmGet$title, false);
        }
        String realmGet$sale_type = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$sale_type();
        if (realmGet$sale_type != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.sale_typeColKey, colKey, realmGet$sale_type, false);
        }
        String realmGet$area = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$area();
        if (realmGet$area != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.areaColKey, colKey, realmGet$area, false);
        }
        String realmGet$location = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$location();
        if (realmGet$location != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.locationColKey, colKey, realmGet$location, false);
        }
        String realmGet$city = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$city();
        if (realmGet$city != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.cityColKey, colKey, realmGet$city, false);
        }
        String realmGet$price = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$price();
        if (realmGet$price != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.priceColKey, colKey, realmGet$price, false);
        }
        String realmGet$main_image = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$main_image();
        if (realmGet$main_image != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.main_imageColKey, colKey, realmGet$main_image, false);
        }
        String realmGet$top_offer = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$top_offer();
        if (realmGet$top_offer != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.top_offerColKey, colKey, realmGet$top_offer, false);
        }
        String realmGet$created_at = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$created_at();
        if (realmGet$created_at != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.created_atColKey, colKey, realmGet$created_at, false);
        }
        String realmGet$updated_at = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$updated_at();
        if (realmGet$updated_at != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.updated_atColKey, colKey, realmGet$updated_at, false);
        }
        String realmGet$bedroom = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$bedroom();
        if (realmGet$bedroom != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.bedroomColKey, colKey, realmGet$bedroom, false);
        }
        String realmGet$bath = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$bath();
        if (realmGet$bath != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.bathColKey, colKey, realmGet$bath, false);
        }
        String realmGet$property_type = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$property_type();
        if (realmGet$property_type != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.property_typeColKey, colKey, realmGet$property_type, false);
        }
        String realmGet$description = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.descriptionColKey, colKey, realmGet$description, false);
        }
        String realmGet$sector = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$sector();
        if (realmGet$sector != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.sectorColKey, colKey, realmGet$sector, false);
        }
        String realmGet$unit_of_measure = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$unit_of_measure();
        if (realmGet$unit_of_measure != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.unit_of_measureColKey, colKey, realmGet$unit_of_measure, false);
        }
        String realmGet$date_of_construction = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$date_of_construction();
        if (realmGet$date_of_construction != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.date_of_constructionColKey, colKey, realmGet$date_of_construction, false);
        }
        String realmGet$rating = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$rating();
        if (realmGet$rating != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.ratingColKey, colKey, realmGet$rating, false);
        }
        String realmGet$comment = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$comment();
        if (realmGet$comment != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.commentColKey, colKey, realmGet$comment, false);
        }
        String realmGet$user_rating = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$user_rating();
        if (realmGet$user_rating != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.user_ratingColKey, colKey, realmGet$user_rating, false);
        }

        RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties> propertiesExtraArrayListList = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$propertiesExtraArrayList();
        if (propertiesExtraArrayListList != null) {
            OsList propertiesExtraArrayListOsList = new OsList(table.getUncheckedRow(colKey), columnInfo.propertiesExtraArrayListColKey);
            for (com.example.realestate.Model.REST.Properties.FavProperties.FavProperties propertiesExtraArrayListItem : propertiesExtraArrayListList) {
                Long cacheItemIndexpropertiesExtraArrayList = cache.get(propertiesExtraArrayListItem);
                if (cacheItemIndexpropertiesExtraArrayList == null) {
                    cacheItemIndexpropertiesExtraArrayList = com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy.insert(realm, propertiesExtraArrayListItem, cache);
                }
                propertiesExtraArrayListOsList.addRow(cacheItemIndexpropertiesExtraArrayList);
            }
        }

        RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties> propertiesGalleryArrayListList = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$propertiesGalleryArrayList();
        if (propertiesGalleryArrayListList != null) {
            OsList propertiesGalleryArrayListOsList = new OsList(table.getUncheckedRow(colKey), columnInfo.propertiesGalleryArrayListColKey);
            for (com.example.realestate.Model.REST.Properties.FavProperties.FavProperties propertiesGalleryArrayListItem : propertiesGalleryArrayListList) {
                Long cacheItemIndexpropertiesGalleryArrayList = cache.get(propertiesGalleryArrayListItem);
                if (cacheItemIndexpropertiesGalleryArrayList == null) {
                    cacheItemIndexpropertiesGalleryArrayList = com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy.insert(realm, propertiesGalleryArrayListItem, cache);
                }
                propertiesGalleryArrayListOsList.addRow(cacheItemIndexpropertiesGalleryArrayList);
            }
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.likeColKey, colKey, ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$like(), false);
        Table.nativeSetDouble(tableNativePtr, columnInfo.latitudeColKey, colKey, ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$latitude(), false);
        Table.nativeSetDouble(tableNativePtr, columnInfo.longitudeColKey, colKey, ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$longitude(), false);
        return colKey;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties.class);
        long tableNativePtr = table.getNativePtr();
        FavPropertiesColumnInfo columnInfo = (FavPropertiesColumnInfo) realm.getSchema().getColumnInfo(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties.class);
        com.example.realestate.Model.REST.Properties.FavProperties.FavProperties object = null;
        while (objects.hasNext()) {
            object = (com.example.realestate.Model.REST.Properties.FavProperties.FavProperties) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            long colKey = OsObject.createRow(table);
            cache.put(object, colKey);
            Table.nativeSetLong(tableNativePtr, columnInfo.tttypeColKey, colKey, ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$tttype(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.idColKey, colKey, ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$id(), false);
            String realmGet$use_id = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$use_id();
            if (realmGet$use_id != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.use_idColKey, colKey, realmGet$use_id, false);
            }
            String realmGet$title = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$title();
            if (realmGet$title != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.titleColKey, colKey, realmGet$title, false);
            }
            String realmGet$sale_type = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$sale_type();
            if (realmGet$sale_type != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.sale_typeColKey, colKey, realmGet$sale_type, false);
            }
            String realmGet$area = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$area();
            if (realmGet$area != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.areaColKey, colKey, realmGet$area, false);
            }
            String realmGet$location = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$location();
            if (realmGet$location != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.locationColKey, colKey, realmGet$location, false);
            }
            String realmGet$city = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$city();
            if (realmGet$city != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.cityColKey, colKey, realmGet$city, false);
            }
            String realmGet$price = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$price();
            if (realmGet$price != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.priceColKey, colKey, realmGet$price, false);
            }
            String realmGet$main_image = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$main_image();
            if (realmGet$main_image != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.main_imageColKey, colKey, realmGet$main_image, false);
            }
            String realmGet$top_offer = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$top_offer();
            if (realmGet$top_offer != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.top_offerColKey, colKey, realmGet$top_offer, false);
            }
            String realmGet$created_at = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$created_at();
            if (realmGet$created_at != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.created_atColKey, colKey, realmGet$created_at, false);
            }
            String realmGet$updated_at = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$updated_at();
            if (realmGet$updated_at != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.updated_atColKey, colKey, realmGet$updated_at, false);
            }
            String realmGet$bedroom = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$bedroom();
            if (realmGet$bedroom != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.bedroomColKey, colKey, realmGet$bedroom, false);
            }
            String realmGet$bath = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$bath();
            if (realmGet$bath != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.bathColKey, colKey, realmGet$bath, false);
            }
            String realmGet$property_type = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$property_type();
            if (realmGet$property_type != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.property_typeColKey, colKey, realmGet$property_type, false);
            }
            String realmGet$description = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$description();
            if (realmGet$description != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.descriptionColKey, colKey, realmGet$description, false);
            }
            String realmGet$sector = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$sector();
            if (realmGet$sector != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.sectorColKey, colKey, realmGet$sector, false);
            }
            String realmGet$unit_of_measure = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$unit_of_measure();
            if (realmGet$unit_of_measure != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.unit_of_measureColKey, colKey, realmGet$unit_of_measure, false);
            }
            String realmGet$date_of_construction = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$date_of_construction();
            if (realmGet$date_of_construction != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.date_of_constructionColKey, colKey, realmGet$date_of_construction, false);
            }
            String realmGet$rating = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$rating();
            if (realmGet$rating != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.ratingColKey, colKey, realmGet$rating, false);
            }
            String realmGet$comment = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$comment();
            if (realmGet$comment != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.commentColKey, colKey, realmGet$comment, false);
            }
            String realmGet$user_rating = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$user_rating();
            if (realmGet$user_rating != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.user_ratingColKey, colKey, realmGet$user_rating, false);
            }

            RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties> propertiesExtraArrayListList = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$propertiesExtraArrayList();
            if (propertiesExtraArrayListList != null) {
                OsList propertiesExtraArrayListOsList = new OsList(table.getUncheckedRow(colKey), columnInfo.propertiesExtraArrayListColKey);
                for (com.example.realestate.Model.REST.Properties.FavProperties.FavProperties propertiesExtraArrayListItem : propertiesExtraArrayListList) {
                    Long cacheItemIndexpropertiesExtraArrayList = cache.get(propertiesExtraArrayListItem);
                    if (cacheItemIndexpropertiesExtraArrayList == null) {
                        cacheItemIndexpropertiesExtraArrayList = com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy.insert(realm, propertiesExtraArrayListItem, cache);
                    }
                    propertiesExtraArrayListOsList.addRow(cacheItemIndexpropertiesExtraArrayList);
                }
            }

            RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties> propertiesGalleryArrayListList = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$propertiesGalleryArrayList();
            if (propertiesGalleryArrayListList != null) {
                OsList propertiesGalleryArrayListOsList = new OsList(table.getUncheckedRow(colKey), columnInfo.propertiesGalleryArrayListColKey);
                for (com.example.realestate.Model.REST.Properties.FavProperties.FavProperties propertiesGalleryArrayListItem : propertiesGalleryArrayListList) {
                    Long cacheItemIndexpropertiesGalleryArrayList = cache.get(propertiesGalleryArrayListItem);
                    if (cacheItemIndexpropertiesGalleryArrayList == null) {
                        cacheItemIndexpropertiesGalleryArrayList = com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy.insert(realm, propertiesGalleryArrayListItem, cache);
                    }
                    propertiesGalleryArrayListOsList.addRow(cacheItemIndexpropertiesGalleryArrayList);
                }
            }
            Table.nativeSetBoolean(tableNativePtr, columnInfo.likeColKey, colKey, ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$like(), false);
            Table.nativeSetDouble(tableNativePtr, columnInfo.latitudeColKey, colKey, ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$latitude(), false);
            Table.nativeSetDouble(tableNativePtr, columnInfo.longitudeColKey, colKey, ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$longitude(), false);
        }
    }

    public static long insertOrUpdate(Realm realm, com.example.realestate.Model.REST.Properties.FavProperties.FavProperties object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties.class);
        long tableNativePtr = table.getNativePtr();
        FavPropertiesColumnInfo columnInfo = (FavPropertiesColumnInfo) realm.getSchema().getColumnInfo(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties.class);
        long colKey = OsObject.createRow(table);
        cache.put(object, colKey);
        Table.nativeSetLong(tableNativePtr, columnInfo.tttypeColKey, colKey, ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$tttype(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.idColKey, colKey, ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$id(), false);
        String realmGet$use_id = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$use_id();
        if (realmGet$use_id != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.use_idColKey, colKey, realmGet$use_id, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.use_idColKey, colKey, false);
        }
        String realmGet$title = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$title();
        if (realmGet$title != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.titleColKey, colKey, realmGet$title, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.titleColKey, colKey, false);
        }
        String realmGet$sale_type = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$sale_type();
        if (realmGet$sale_type != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.sale_typeColKey, colKey, realmGet$sale_type, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.sale_typeColKey, colKey, false);
        }
        String realmGet$area = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$area();
        if (realmGet$area != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.areaColKey, colKey, realmGet$area, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.areaColKey, colKey, false);
        }
        String realmGet$location = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$location();
        if (realmGet$location != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.locationColKey, colKey, realmGet$location, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.locationColKey, colKey, false);
        }
        String realmGet$city = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$city();
        if (realmGet$city != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.cityColKey, colKey, realmGet$city, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.cityColKey, colKey, false);
        }
        String realmGet$price = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$price();
        if (realmGet$price != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.priceColKey, colKey, realmGet$price, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.priceColKey, colKey, false);
        }
        String realmGet$main_image = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$main_image();
        if (realmGet$main_image != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.main_imageColKey, colKey, realmGet$main_image, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.main_imageColKey, colKey, false);
        }
        String realmGet$top_offer = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$top_offer();
        if (realmGet$top_offer != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.top_offerColKey, colKey, realmGet$top_offer, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.top_offerColKey, colKey, false);
        }
        String realmGet$created_at = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$created_at();
        if (realmGet$created_at != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.created_atColKey, colKey, realmGet$created_at, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.created_atColKey, colKey, false);
        }
        String realmGet$updated_at = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$updated_at();
        if (realmGet$updated_at != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.updated_atColKey, colKey, realmGet$updated_at, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.updated_atColKey, colKey, false);
        }
        String realmGet$bedroom = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$bedroom();
        if (realmGet$bedroom != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.bedroomColKey, colKey, realmGet$bedroom, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.bedroomColKey, colKey, false);
        }
        String realmGet$bath = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$bath();
        if (realmGet$bath != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.bathColKey, colKey, realmGet$bath, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.bathColKey, colKey, false);
        }
        String realmGet$property_type = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$property_type();
        if (realmGet$property_type != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.property_typeColKey, colKey, realmGet$property_type, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.property_typeColKey, colKey, false);
        }
        String realmGet$description = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.descriptionColKey, colKey, realmGet$description, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.descriptionColKey, colKey, false);
        }
        String realmGet$sector = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$sector();
        if (realmGet$sector != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.sectorColKey, colKey, realmGet$sector, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.sectorColKey, colKey, false);
        }
        String realmGet$unit_of_measure = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$unit_of_measure();
        if (realmGet$unit_of_measure != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.unit_of_measureColKey, colKey, realmGet$unit_of_measure, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.unit_of_measureColKey, colKey, false);
        }
        String realmGet$date_of_construction = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$date_of_construction();
        if (realmGet$date_of_construction != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.date_of_constructionColKey, colKey, realmGet$date_of_construction, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.date_of_constructionColKey, colKey, false);
        }
        String realmGet$rating = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$rating();
        if (realmGet$rating != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.ratingColKey, colKey, realmGet$rating, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.ratingColKey, colKey, false);
        }
        String realmGet$comment = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$comment();
        if (realmGet$comment != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.commentColKey, colKey, realmGet$comment, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.commentColKey, colKey, false);
        }
        String realmGet$user_rating = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$user_rating();
        if (realmGet$user_rating != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.user_ratingColKey, colKey, realmGet$user_rating, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.user_ratingColKey, colKey, false);
        }

        OsList propertiesExtraArrayListOsList = new OsList(table.getUncheckedRow(colKey), columnInfo.propertiesExtraArrayListColKey);
        RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties> propertiesExtraArrayListList = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$propertiesExtraArrayList();
        if (propertiesExtraArrayListList != null && propertiesExtraArrayListList.size() == propertiesExtraArrayListOsList.size()) {
            // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
            int objects = propertiesExtraArrayListList.size();
            for (int i = 0; i < objects; i++) {
                com.example.realestate.Model.REST.Properties.FavProperties.FavProperties propertiesExtraArrayListItem = propertiesExtraArrayListList.get(i);
                Long cacheItemIndexpropertiesExtraArrayList = cache.get(propertiesExtraArrayListItem);
                if (cacheItemIndexpropertiesExtraArrayList == null) {
                    cacheItemIndexpropertiesExtraArrayList = com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy.insertOrUpdate(realm, propertiesExtraArrayListItem, cache);
                }
                propertiesExtraArrayListOsList.setRow(i, cacheItemIndexpropertiesExtraArrayList);
            }
        } else {
            propertiesExtraArrayListOsList.removeAll();
            if (propertiesExtraArrayListList != null) {
                for (com.example.realestate.Model.REST.Properties.FavProperties.FavProperties propertiesExtraArrayListItem : propertiesExtraArrayListList) {
                    Long cacheItemIndexpropertiesExtraArrayList = cache.get(propertiesExtraArrayListItem);
                    if (cacheItemIndexpropertiesExtraArrayList == null) {
                        cacheItemIndexpropertiesExtraArrayList = com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy.insertOrUpdate(realm, propertiesExtraArrayListItem, cache);
                    }
                    propertiesExtraArrayListOsList.addRow(cacheItemIndexpropertiesExtraArrayList);
                }
            }
        }


        OsList propertiesGalleryArrayListOsList = new OsList(table.getUncheckedRow(colKey), columnInfo.propertiesGalleryArrayListColKey);
        RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties> propertiesGalleryArrayListList = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$propertiesGalleryArrayList();
        if (propertiesGalleryArrayListList != null && propertiesGalleryArrayListList.size() == propertiesGalleryArrayListOsList.size()) {
            // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
            int objects = propertiesGalleryArrayListList.size();
            for (int i = 0; i < objects; i++) {
                com.example.realestate.Model.REST.Properties.FavProperties.FavProperties propertiesGalleryArrayListItem = propertiesGalleryArrayListList.get(i);
                Long cacheItemIndexpropertiesGalleryArrayList = cache.get(propertiesGalleryArrayListItem);
                if (cacheItemIndexpropertiesGalleryArrayList == null) {
                    cacheItemIndexpropertiesGalleryArrayList = com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy.insertOrUpdate(realm, propertiesGalleryArrayListItem, cache);
                }
                propertiesGalleryArrayListOsList.setRow(i, cacheItemIndexpropertiesGalleryArrayList);
            }
        } else {
            propertiesGalleryArrayListOsList.removeAll();
            if (propertiesGalleryArrayListList != null) {
                for (com.example.realestate.Model.REST.Properties.FavProperties.FavProperties propertiesGalleryArrayListItem : propertiesGalleryArrayListList) {
                    Long cacheItemIndexpropertiesGalleryArrayList = cache.get(propertiesGalleryArrayListItem);
                    if (cacheItemIndexpropertiesGalleryArrayList == null) {
                        cacheItemIndexpropertiesGalleryArrayList = com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy.insertOrUpdate(realm, propertiesGalleryArrayListItem, cache);
                    }
                    propertiesGalleryArrayListOsList.addRow(cacheItemIndexpropertiesGalleryArrayList);
                }
            }
        }

        Table.nativeSetBoolean(tableNativePtr, columnInfo.likeColKey, colKey, ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$like(), false);
        Table.nativeSetDouble(tableNativePtr, columnInfo.latitudeColKey, colKey, ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$latitude(), false);
        Table.nativeSetDouble(tableNativePtr, columnInfo.longitudeColKey, colKey, ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$longitude(), false);
        return colKey;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties.class);
        long tableNativePtr = table.getNativePtr();
        FavPropertiesColumnInfo columnInfo = (FavPropertiesColumnInfo) realm.getSchema().getColumnInfo(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties.class);
        com.example.realestate.Model.REST.Properties.FavProperties.FavProperties object = null;
        while (objects.hasNext()) {
            object = (com.example.realestate.Model.REST.Properties.FavProperties.FavProperties) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            long colKey = OsObject.createRow(table);
            cache.put(object, colKey);
            Table.nativeSetLong(tableNativePtr, columnInfo.tttypeColKey, colKey, ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$tttype(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.idColKey, colKey, ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$id(), false);
            String realmGet$use_id = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$use_id();
            if (realmGet$use_id != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.use_idColKey, colKey, realmGet$use_id, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.use_idColKey, colKey, false);
            }
            String realmGet$title = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$title();
            if (realmGet$title != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.titleColKey, colKey, realmGet$title, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.titleColKey, colKey, false);
            }
            String realmGet$sale_type = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$sale_type();
            if (realmGet$sale_type != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.sale_typeColKey, colKey, realmGet$sale_type, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.sale_typeColKey, colKey, false);
            }
            String realmGet$area = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$area();
            if (realmGet$area != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.areaColKey, colKey, realmGet$area, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.areaColKey, colKey, false);
            }
            String realmGet$location = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$location();
            if (realmGet$location != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.locationColKey, colKey, realmGet$location, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.locationColKey, colKey, false);
            }
            String realmGet$city = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$city();
            if (realmGet$city != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.cityColKey, colKey, realmGet$city, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.cityColKey, colKey, false);
            }
            String realmGet$price = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$price();
            if (realmGet$price != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.priceColKey, colKey, realmGet$price, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.priceColKey, colKey, false);
            }
            String realmGet$main_image = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$main_image();
            if (realmGet$main_image != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.main_imageColKey, colKey, realmGet$main_image, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.main_imageColKey, colKey, false);
            }
            String realmGet$top_offer = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$top_offer();
            if (realmGet$top_offer != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.top_offerColKey, colKey, realmGet$top_offer, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.top_offerColKey, colKey, false);
            }
            String realmGet$created_at = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$created_at();
            if (realmGet$created_at != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.created_atColKey, colKey, realmGet$created_at, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.created_atColKey, colKey, false);
            }
            String realmGet$updated_at = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$updated_at();
            if (realmGet$updated_at != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.updated_atColKey, colKey, realmGet$updated_at, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.updated_atColKey, colKey, false);
            }
            String realmGet$bedroom = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$bedroom();
            if (realmGet$bedroom != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.bedroomColKey, colKey, realmGet$bedroom, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.bedroomColKey, colKey, false);
            }
            String realmGet$bath = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$bath();
            if (realmGet$bath != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.bathColKey, colKey, realmGet$bath, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.bathColKey, colKey, false);
            }
            String realmGet$property_type = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$property_type();
            if (realmGet$property_type != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.property_typeColKey, colKey, realmGet$property_type, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.property_typeColKey, colKey, false);
            }
            String realmGet$description = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$description();
            if (realmGet$description != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.descriptionColKey, colKey, realmGet$description, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.descriptionColKey, colKey, false);
            }
            String realmGet$sector = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$sector();
            if (realmGet$sector != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.sectorColKey, colKey, realmGet$sector, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.sectorColKey, colKey, false);
            }
            String realmGet$unit_of_measure = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$unit_of_measure();
            if (realmGet$unit_of_measure != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.unit_of_measureColKey, colKey, realmGet$unit_of_measure, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.unit_of_measureColKey, colKey, false);
            }
            String realmGet$date_of_construction = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$date_of_construction();
            if (realmGet$date_of_construction != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.date_of_constructionColKey, colKey, realmGet$date_of_construction, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.date_of_constructionColKey, colKey, false);
            }
            String realmGet$rating = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$rating();
            if (realmGet$rating != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.ratingColKey, colKey, realmGet$rating, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.ratingColKey, colKey, false);
            }
            String realmGet$comment = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$comment();
            if (realmGet$comment != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.commentColKey, colKey, realmGet$comment, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.commentColKey, colKey, false);
            }
            String realmGet$user_rating = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$user_rating();
            if (realmGet$user_rating != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.user_ratingColKey, colKey, realmGet$user_rating, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.user_ratingColKey, colKey, false);
            }

            OsList propertiesExtraArrayListOsList = new OsList(table.getUncheckedRow(colKey), columnInfo.propertiesExtraArrayListColKey);
            RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties> propertiesExtraArrayListList = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$propertiesExtraArrayList();
            if (propertiesExtraArrayListList != null && propertiesExtraArrayListList.size() == propertiesExtraArrayListOsList.size()) {
                // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
                int objectCount = propertiesExtraArrayListList.size();
                for (int i = 0; i < objectCount; i++) {
                    com.example.realestate.Model.REST.Properties.FavProperties.FavProperties propertiesExtraArrayListItem = propertiesExtraArrayListList.get(i);
                    Long cacheItemIndexpropertiesExtraArrayList = cache.get(propertiesExtraArrayListItem);
                    if (cacheItemIndexpropertiesExtraArrayList == null) {
                        cacheItemIndexpropertiesExtraArrayList = com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy.insertOrUpdate(realm, propertiesExtraArrayListItem, cache);
                    }
                    propertiesExtraArrayListOsList.setRow(i, cacheItemIndexpropertiesExtraArrayList);
                }
            } else {
                propertiesExtraArrayListOsList.removeAll();
                if (propertiesExtraArrayListList != null) {
                    for (com.example.realestate.Model.REST.Properties.FavProperties.FavProperties propertiesExtraArrayListItem : propertiesExtraArrayListList) {
                        Long cacheItemIndexpropertiesExtraArrayList = cache.get(propertiesExtraArrayListItem);
                        if (cacheItemIndexpropertiesExtraArrayList == null) {
                            cacheItemIndexpropertiesExtraArrayList = com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy.insertOrUpdate(realm, propertiesExtraArrayListItem, cache);
                        }
                        propertiesExtraArrayListOsList.addRow(cacheItemIndexpropertiesExtraArrayList);
                    }
                }
            }


            OsList propertiesGalleryArrayListOsList = new OsList(table.getUncheckedRow(colKey), columnInfo.propertiesGalleryArrayListColKey);
            RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties> propertiesGalleryArrayListList = ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$propertiesGalleryArrayList();
            if (propertiesGalleryArrayListList != null && propertiesGalleryArrayListList.size() == propertiesGalleryArrayListOsList.size()) {
                // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
                int objectCount = propertiesGalleryArrayListList.size();
                for (int i = 0; i < objectCount; i++) {
                    com.example.realestate.Model.REST.Properties.FavProperties.FavProperties propertiesGalleryArrayListItem = propertiesGalleryArrayListList.get(i);
                    Long cacheItemIndexpropertiesGalleryArrayList = cache.get(propertiesGalleryArrayListItem);
                    if (cacheItemIndexpropertiesGalleryArrayList == null) {
                        cacheItemIndexpropertiesGalleryArrayList = com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy.insertOrUpdate(realm, propertiesGalleryArrayListItem, cache);
                    }
                    propertiesGalleryArrayListOsList.setRow(i, cacheItemIndexpropertiesGalleryArrayList);
                }
            } else {
                propertiesGalleryArrayListOsList.removeAll();
                if (propertiesGalleryArrayListList != null) {
                    for (com.example.realestate.Model.REST.Properties.FavProperties.FavProperties propertiesGalleryArrayListItem : propertiesGalleryArrayListList) {
                        Long cacheItemIndexpropertiesGalleryArrayList = cache.get(propertiesGalleryArrayListItem);
                        if (cacheItemIndexpropertiesGalleryArrayList == null) {
                            cacheItemIndexpropertiesGalleryArrayList = com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy.insertOrUpdate(realm, propertiesGalleryArrayListItem, cache);
                        }
                        propertiesGalleryArrayListOsList.addRow(cacheItemIndexpropertiesGalleryArrayList);
                    }
                }
            }

            Table.nativeSetBoolean(tableNativePtr, columnInfo.likeColKey, colKey, ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$like(), false);
            Table.nativeSetDouble(tableNativePtr, columnInfo.latitudeColKey, colKey, ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$latitude(), false);
            Table.nativeSetDouble(tableNativePtr, columnInfo.longitudeColKey, colKey, ((com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) object).realmGet$longitude(), false);
        }
    }

    public static com.example.realestate.Model.REST.Properties.FavProperties.FavProperties createDetachedCopy(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.example.realestate.Model.REST.Properties.FavProperties.FavProperties unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.example.realestate.Model.REST.Properties.FavProperties.FavProperties();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.example.realestate.Model.REST.Properties.FavProperties.FavProperties) cachedObject.object;
            }
            unmanagedObject = (com.example.realestate.Model.REST.Properties.FavProperties.FavProperties) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface unmanagedCopy = (com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) unmanagedObject;
        com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface realmSource = (com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$tttype(realmSource.realmGet$tttype());
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$use_id(realmSource.realmGet$use_id());
        unmanagedCopy.realmSet$title(realmSource.realmGet$title());
        unmanagedCopy.realmSet$sale_type(realmSource.realmGet$sale_type());
        unmanagedCopy.realmSet$area(realmSource.realmGet$area());
        unmanagedCopy.realmSet$location(realmSource.realmGet$location());
        unmanagedCopy.realmSet$city(realmSource.realmGet$city());
        unmanagedCopy.realmSet$price(realmSource.realmGet$price());
        unmanagedCopy.realmSet$main_image(realmSource.realmGet$main_image());
        unmanagedCopy.realmSet$top_offer(realmSource.realmGet$top_offer());
        unmanagedCopy.realmSet$created_at(realmSource.realmGet$created_at());
        unmanagedCopy.realmSet$updated_at(realmSource.realmGet$updated_at());
        unmanagedCopy.realmSet$bedroom(realmSource.realmGet$bedroom());
        unmanagedCopy.realmSet$bath(realmSource.realmGet$bath());
        unmanagedCopy.realmSet$property_type(realmSource.realmGet$property_type());
        unmanagedCopy.realmSet$description(realmSource.realmGet$description());
        unmanagedCopy.realmSet$sector(realmSource.realmGet$sector());
        unmanagedCopy.realmSet$unit_of_measure(realmSource.realmGet$unit_of_measure());
        unmanagedCopy.realmSet$date_of_construction(realmSource.realmGet$date_of_construction());
        unmanagedCopy.realmSet$rating(realmSource.realmGet$rating());
        unmanagedCopy.realmSet$comment(realmSource.realmGet$comment());
        unmanagedCopy.realmSet$user_rating(realmSource.realmGet$user_rating());

        // Deep copy of propertiesExtraArrayList
        if (currentDepth == maxDepth) {
            unmanagedCopy.realmSet$propertiesExtraArrayList(null);
        } else {
            RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties> managedpropertiesExtraArrayListList = realmSource.realmGet$propertiesExtraArrayList();
            RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties> unmanagedpropertiesExtraArrayListList = new RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties>();
            unmanagedCopy.realmSet$propertiesExtraArrayList(unmanagedpropertiesExtraArrayListList);
            int nextDepth = currentDepth + 1;
            int size = managedpropertiesExtraArrayListList.size();
            for (int i = 0; i < size; i++) {
                com.example.realestate.Model.REST.Properties.FavProperties.FavProperties item = com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy.createDetachedCopy(managedpropertiesExtraArrayListList.get(i), nextDepth, maxDepth, cache);
                unmanagedpropertiesExtraArrayListList.add(item);
            }
        }

        // Deep copy of propertiesGalleryArrayList
        if (currentDepth == maxDepth) {
            unmanagedCopy.realmSet$propertiesGalleryArrayList(null);
        } else {
            RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties> managedpropertiesGalleryArrayListList = realmSource.realmGet$propertiesGalleryArrayList();
            RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties> unmanagedpropertiesGalleryArrayListList = new RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties>();
            unmanagedCopy.realmSet$propertiesGalleryArrayList(unmanagedpropertiesGalleryArrayListList);
            int nextDepth = currentDepth + 1;
            int size = managedpropertiesGalleryArrayListList.size();
            for (int i = 0; i < size; i++) {
                com.example.realestate.Model.REST.Properties.FavProperties.FavProperties item = com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy.createDetachedCopy(managedpropertiesGalleryArrayListList.get(i), nextDepth, maxDepth, cache);
                unmanagedpropertiesGalleryArrayListList.add(item);
            }
        }
        unmanagedCopy.realmSet$like(realmSource.realmGet$like());
        unmanagedCopy.realmSet$latitude(realmSource.realmGet$latitude());
        unmanagedCopy.realmSet$longitude(realmSource.realmGet$longitude());

        return unmanagedObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("FavProperties = proxy[");
        stringBuilder.append("{tttype:");
        stringBuilder.append(realmGet$tttype());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{use_id:");
        stringBuilder.append(realmGet$use_id() != null ? realmGet$use_id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{title:");
        stringBuilder.append(realmGet$title() != null ? realmGet$title() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{sale_type:");
        stringBuilder.append(realmGet$sale_type() != null ? realmGet$sale_type() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{area:");
        stringBuilder.append(realmGet$area() != null ? realmGet$area() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{location:");
        stringBuilder.append(realmGet$location() != null ? realmGet$location() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{city:");
        stringBuilder.append(realmGet$city() != null ? realmGet$city() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{price:");
        stringBuilder.append(realmGet$price() != null ? realmGet$price() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{main_image:");
        stringBuilder.append(realmGet$main_image() != null ? realmGet$main_image() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{top_offer:");
        stringBuilder.append(realmGet$top_offer() != null ? realmGet$top_offer() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{created_at:");
        stringBuilder.append(realmGet$created_at() != null ? realmGet$created_at() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{updated_at:");
        stringBuilder.append(realmGet$updated_at() != null ? realmGet$updated_at() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{bedroom:");
        stringBuilder.append(realmGet$bedroom() != null ? realmGet$bedroom() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{bath:");
        stringBuilder.append(realmGet$bath() != null ? realmGet$bath() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{property_type:");
        stringBuilder.append(realmGet$property_type() != null ? realmGet$property_type() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{description:");
        stringBuilder.append(realmGet$description() != null ? realmGet$description() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{sector:");
        stringBuilder.append(realmGet$sector() != null ? realmGet$sector() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{unit_of_measure:");
        stringBuilder.append(realmGet$unit_of_measure() != null ? realmGet$unit_of_measure() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{date_of_construction:");
        stringBuilder.append(realmGet$date_of_construction() != null ? realmGet$date_of_construction() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{rating:");
        stringBuilder.append(realmGet$rating() != null ? realmGet$rating() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{comment:");
        stringBuilder.append(realmGet$comment() != null ? realmGet$comment() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{user_rating:");
        stringBuilder.append(realmGet$user_rating() != null ? realmGet$user_rating() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{propertiesExtraArrayList:");
        stringBuilder.append("RealmList<FavProperties>[").append(realmGet$propertiesExtraArrayList().size()).append("]");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{propertiesGalleryArrayList:");
        stringBuilder.append("RealmList<FavProperties>[").append(realmGet$propertiesGalleryArrayList().size()).append("]");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{like:");
        stringBuilder.append(realmGet$like());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{latitude:");
        stringBuilder.append(realmGet$latitude());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{longitude:");
        stringBuilder.append(realmGet$longitude());
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public ProxyState<?> realmGet$proxyState() {
        return proxyState;
    }

    @Override
    public int hashCode() {
        String realmName = proxyState.getRealm$realm().getPath();
        String tableName = proxyState.getRow$realm().getTable().getName();
        long colKey = proxyState.getRow$realm().getObjectKey();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (colKey ^ (colKey >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy aFavProperties = (com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy)o;

        BaseRealm realm = proxyState.getRealm$realm();
        BaseRealm otherRealm = aFavProperties.proxyState.getRealm$realm();
        String path = realm.getPath();
        String otherPath = otherRealm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;
        if (realm.isFrozen() != otherRealm.isFrozen()) return false;
        if (!realm.sharedRealm.getVersionID().equals(otherRealm.sharedRealm.getVersionID())) {
            return false;
        }

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aFavProperties.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getObjectKey() != aFavProperties.proxyState.getRow$realm().getObjectKey()) return false;

        return true;
    }
}
