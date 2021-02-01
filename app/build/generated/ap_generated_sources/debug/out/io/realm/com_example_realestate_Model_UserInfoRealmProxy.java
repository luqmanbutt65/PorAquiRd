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
public class com_example_realestate_Model_UserInfoRealmProxy extends com.example.realestate.Model.UserInfo
    implements RealmObjectProxy, com_example_realestate_Model_UserInfoRealmProxyInterface {

    static final class UserInfoColumnInfo extends ColumnInfo {
        long idColKey;
        long user_imageColKey;
        long nameColKey;
        long numberColKey;
        long emailColKey;
        long passwordColKey;
        long addressColKey;
        long cityColKey;
        long sectorColKey;
        long your_idColKey;
        long rncColKey;
        long cell_numberColKey;
        long company_nameColKey;
        long upload_limitColKey;
        long expiry_dateColKey;
        long plan_buy_dateColKey;

        UserInfoColumnInfo(OsSchemaInfo schemaInfo) {
            super(16);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("UserInfo");
            this.idColKey = addColumnDetails("id", "id", objectSchemaInfo);
            this.user_imageColKey = addColumnDetails("user_image", "user_image", objectSchemaInfo);
            this.nameColKey = addColumnDetails("name", "name", objectSchemaInfo);
            this.numberColKey = addColumnDetails("number", "number", objectSchemaInfo);
            this.emailColKey = addColumnDetails("email", "email", objectSchemaInfo);
            this.passwordColKey = addColumnDetails("password", "password", objectSchemaInfo);
            this.addressColKey = addColumnDetails("address", "address", objectSchemaInfo);
            this.cityColKey = addColumnDetails("city", "city", objectSchemaInfo);
            this.sectorColKey = addColumnDetails("sector", "sector", objectSchemaInfo);
            this.your_idColKey = addColumnDetails("your_id", "your_id", objectSchemaInfo);
            this.rncColKey = addColumnDetails("rnc", "rnc", objectSchemaInfo);
            this.cell_numberColKey = addColumnDetails("cell_number", "cell_number", objectSchemaInfo);
            this.company_nameColKey = addColumnDetails("company_name", "company_name", objectSchemaInfo);
            this.upload_limitColKey = addColumnDetails("upload_limit", "upload_limit", objectSchemaInfo);
            this.expiry_dateColKey = addColumnDetails("expiry_date", "expiry_date", objectSchemaInfo);
            this.plan_buy_dateColKey = addColumnDetails("plan_buy_date", "plan_buy_date", objectSchemaInfo);
        }

        UserInfoColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new UserInfoColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final UserInfoColumnInfo src = (UserInfoColumnInfo) rawSrc;
            final UserInfoColumnInfo dst = (UserInfoColumnInfo) rawDst;
            dst.idColKey = src.idColKey;
            dst.user_imageColKey = src.user_imageColKey;
            dst.nameColKey = src.nameColKey;
            dst.numberColKey = src.numberColKey;
            dst.emailColKey = src.emailColKey;
            dst.passwordColKey = src.passwordColKey;
            dst.addressColKey = src.addressColKey;
            dst.cityColKey = src.cityColKey;
            dst.sectorColKey = src.sectorColKey;
            dst.your_idColKey = src.your_idColKey;
            dst.rncColKey = src.rncColKey;
            dst.cell_numberColKey = src.cell_numberColKey;
            dst.company_nameColKey = src.company_nameColKey;
            dst.upload_limitColKey = src.upload_limitColKey;
            dst.expiry_dateColKey = src.expiry_dateColKey;
            dst.plan_buy_dateColKey = src.plan_buy_dateColKey;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private UserInfoColumnInfo columnInfo;
    private ProxyState<com.example.realestate.Model.UserInfo> proxyState;

    com_example_realestate_Model_UserInfoRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (UserInfoColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.example.realestate.Model.UserInfo>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public Integer realmGet$id() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNull(columnInfo.idColKey)) {
            return null;
        }
        return (int) proxyState.getRow$realm().getLong(columnInfo.idColKey);
    }

    @Override
    public void realmSet$id(Integer value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.idColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setLong(columnInfo.idColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.idColKey);
            return;
        }
        proxyState.getRow$realm().setLong(columnInfo.idColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$user_image() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.user_imageColKey);
    }

    @Override
    public void realmSet$user_image(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.user_imageColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.user_imageColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.user_imageColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.user_imageColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$name() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.nameColKey);
    }

    @Override
    public void realmSet$name(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.nameColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.nameColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.nameColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.nameColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$number() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.numberColKey);
    }

    @Override
    public void realmSet$number(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.numberColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.numberColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.numberColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.numberColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$email() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.emailColKey);
    }

    @Override
    public void realmSet$email(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.emailColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.emailColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.emailColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.emailColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$password() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.passwordColKey);
    }

    @Override
    public void realmSet$password(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.passwordColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.passwordColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.passwordColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.passwordColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$address() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.addressColKey);
    }

    @Override
    public void realmSet$address(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.addressColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.addressColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.addressColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.addressColKey, value);
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
    public String realmGet$your_id() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.your_idColKey);
    }

    @Override
    public void realmSet$your_id(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.your_idColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.your_idColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.your_idColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.your_idColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$rnc() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.rncColKey);
    }

    @Override
    public void realmSet$rnc(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.rncColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.rncColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.rncColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.rncColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$cell_number() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.cell_numberColKey);
    }

    @Override
    public void realmSet$cell_number(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.cell_numberColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.cell_numberColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.cell_numberColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.cell_numberColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$company_name() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.company_nameColKey);
    }

    @Override
    public void realmSet$company_name(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.company_nameColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.company_nameColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.company_nameColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.company_nameColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$upload_limit() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.upload_limitColKey);
    }

    @Override
    public void realmSet$upload_limit(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.upload_limitColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.upload_limitColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.upload_limitColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.upload_limitColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$expiry_date() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.expiry_dateColKey);
    }

    @Override
    public void realmSet$expiry_date(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.expiry_dateColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.expiry_dateColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.expiry_dateColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.expiry_dateColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$plan_buy_date() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.plan_buy_dateColKey);
    }

    @Override
    public void realmSet$plan_buy_date(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.plan_buy_dateColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.plan_buy_dateColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.plan_buy_dateColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.plan_buy_dateColKey, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("UserInfo", 16, 0);
        builder.addPersistedProperty("id", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("user_image", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("name", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("number", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("email", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("password", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("address", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("city", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("sector", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("your_id", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("rnc", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("cell_number", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("company_name", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("upload_limit", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("expiry_date", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("plan_buy_date", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static UserInfoColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new UserInfoColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "UserInfo";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "UserInfo";
    }

    @SuppressWarnings("cast")
    public static com.example.realestate.Model.UserInfo createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.example.realestate.Model.UserInfo obj = realm.createObjectInternal(com.example.realestate.Model.UserInfo.class, true, excludeFields);

        final com_example_realestate_Model_UserInfoRealmProxyInterface objProxy = (com_example_realestate_Model_UserInfoRealmProxyInterface) obj;
        if (json.has("id")) {
            if (json.isNull("id")) {
                objProxy.realmSet$id(null);
            } else {
                objProxy.realmSet$id((int) json.getInt("id"));
            }
        }
        if (json.has("user_image")) {
            if (json.isNull("user_image")) {
                objProxy.realmSet$user_image(null);
            } else {
                objProxy.realmSet$user_image((String) json.getString("user_image"));
            }
        }
        if (json.has("name")) {
            if (json.isNull("name")) {
                objProxy.realmSet$name(null);
            } else {
                objProxy.realmSet$name((String) json.getString("name"));
            }
        }
        if (json.has("number")) {
            if (json.isNull("number")) {
                objProxy.realmSet$number(null);
            } else {
                objProxy.realmSet$number((String) json.getString("number"));
            }
        }
        if (json.has("email")) {
            if (json.isNull("email")) {
                objProxy.realmSet$email(null);
            } else {
                objProxy.realmSet$email((String) json.getString("email"));
            }
        }
        if (json.has("password")) {
            if (json.isNull("password")) {
                objProxy.realmSet$password(null);
            } else {
                objProxy.realmSet$password((String) json.getString("password"));
            }
        }
        if (json.has("address")) {
            if (json.isNull("address")) {
                objProxy.realmSet$address(null);
            } else {
                objProxy.realmSet$address((String) json.getString("address"));
            }
        }
        if (json.has("city")) {
            if (json.isNull("city")) {
                objProxy.realmSet$city(null);
            } else {
                objProxy.realmSet$city((String) json.getString("city"));
            }
        }
        if (json.has("sector")) {
            if (json.isNull("sector")) {
                objProxy.realmSet$sector(null);
            } else {
                objProxy.realmSet$sector((String) json.getString("sector"));
            }
        }
        if (json.has("your_id")) {
            if (json.isNull("your_id")) {
                objProxy.realmSet$your_id(null);
            } else {
                objProxy.realmSet$your_id((String) json.getString("your_id"));
            }
        }
        if (json.has("rnc")) {
            if (json.isNull("rnc")) {
                objProxy.realmSet$rnc(null);
            } else {
                objProxy.realmSet$rnc((String) json.getString("rnc"));
            }
        }
        if (json.has("cell_number")) {
            if (json.isNull("cell_number")) {
                objProxy.realmSet$cell_number(null);
            } else {
                objProxy.realmSet$cell_number((String) json.getString("cell_number"));
            }
        }
        if (json.has("company_name")) {
            if (json.isNull("company_name")) {
                objProxy.realmSet$company_name(null);
            } else {
                objProxy.realmSet$company_name((String) json.getString("company_name"));
            }
        }
        if (json.has("upload_limit")) {
            if (json.isNull("upload_limit")) {
                objProxy.realmSet$upload_limit(null);
            } else {
                objProxy.realmSet$upload_limit((String) json.getString("upload_limit"));
            }
        }
        if (json.has("expiry_date")) {
            if (json.isNull("expiry_date")) {
                objProxy.realmSet$expiry_date(null);
            } else {
                objProxy.realmSet$expiry_date((String) json.getString("expiry_date"));
            }
        }
        if (json.has("plan_buy_date")) {
            if (json.isNull("plan_buy_date")) {
                objProxy.realmSet$plan_buy_date(null);
            } else {
                objProxy.realmSet$plan_buy_date((String) json.getString("plan_buy_date"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.example.realestate.Model.UserInfo createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        final com.example.realestate.Model.UserInfo obj = new com.example.realestate.Model.UserInfo();
        final com_example_realestate_Model_UserInfoRealmProxyInterface objProxy = (com_example_realestate_Model_UserInfoRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("id")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$id((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$id(null);
                }
            } else if (name.equals("user_image")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$user_image((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$user_image(null);
                }
            } else if (name.equals("name")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$name((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$name(null);
                }
            } else if (name.equals("number")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$number((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$number(null);
                }
            } else if (name.equals("email")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$email((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$email(null);
                }
            } else if (name.equals("password")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$password((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$password(null);
                }
            } else if (name.equals("address")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$address((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$address(null);
                }
            } else if (name.equals("city")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$city((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$city(null);
                }
            } else if (name.equals("sector")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$sector((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$sector(null);
                }
            } else if (name.equals("your_id")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$your_id((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$your_id(null);
                }
            } else if (name.equals("rnc")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$rnc((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$rnc(null);
                }
            } else if (name.equals("cell_number")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$cell_number((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$cell_number(null);
                }
            } else if (name.equals("company_name")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$company_name((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$company_name(null);
                }
            } else if (name.equals("upload_limit")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$upload_limit((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$upload_limit(null);
                }
            } else if (name.equals("expiry_date")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$expiry_date((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$expiry_date(null);
                }
            } else if (name.equals("plan_buy_date")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$plan_buy_date((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$plan_buy_date(null);
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return realm.copyToRealm(obj);
    }

    private static com_example_realestate_Model_UserInfoRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.example.realestate.Model.UserInfo.class), false, Collections.<String>emptyList());
        io.realm.com_example_realestate_Model_UserInfoRealmProxy obj = new io.realm.com_example_realestate_Model_UserInfoRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.example.realestate.Model.UserInfo copyOrUpdate(Realm realm, UserInfoColumnInfo columnInfo, com.example.realestate.Model.UserInfo object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
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
            return (com.example.realestate.Model.UserInfo) cachedRealmObject;
        }

        return copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.example.realestate.Model.UserInfo copy(Realm realm, UserInfoColumnInfo columnInfo, com.example.realestate.Model.UserInfo newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.example.realestate.Model.UserInfo) cachedRealmObject;
        }

        com_example_realestate_Model_UserInfoRealmProxyInterface realmObjectSource = (com_example_realestate_Model_UserInfoRealmProxyInterface) newObject;

        Table table = realm.getTable(com.example.realestate.Model.UserInfo.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, flags);

        // Add all non-"object reference" fields
        builder.addInteger(columnInfo.idColKey, realmObjectSource.realmGet$id());
        builder.addString(columnInfo.user_imageColKey, realmObjectSource.realmGet$user_image());
        builder.addString(columnInfo.nameColKey, realmObjectSource.realmGet$name());
        builder.addString(columnInfo.numberColKey, realmObjectSource.realmGet$number());
        builder.addString(columnInfo.emailColKey, realmObjectSource.realmGet$email());
        builder.addString(columnInfo.passwordColKey, realmObjectSource.realmGet$password());
        builder.addString(columnInfo.addressColKey, realmObjectSource.realmGet$address());
        builder.addString(columnInfo.cityColKey, realmObjectSource.realmGet$city());
        builder.addString(columnInfo.sectorColKey, realmObjectSource.realmGet$sector());
        builder.addString(columnInfo.your_idColKey, realmObjectSource.realmGet$your_id());
        builder.addString(columnInfo.rncColKey, realmObjectSource.realmGet$rnc());
        builder.addString(columnInfo.cell_numberColKey, realmObjectSource.realmGet$cell_number());
        builder.addString(columnInfo.company_nameColKey, realmObjectSource.realmGet$company_name());
        builder.addString(columnInfo.upload_limitColKey, realmObjectSource.realmGet$upload_limit());
        builder.addString(columnInfo.expiry_dateColKey, realmObjectSource.realmGet$expiry_date());
        builder.addString(columnInfo.plan_buy_dateColKey, realmObjectSource.realmGet$plan_buy_date());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_example_realestate_Model_UserInfoRealmProxy realmObjectCopy = newProxyInstance(realm, row);
        cache.put(newObject, realmObjectCopy);

        return realmObjectCopy;
    }

    public static long insert(Realm realm, com.example.realestate.Model.UserInfo object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.example.realestate.Model.UserInfo.class);
        long tableNativePtr = table.getNativePtr();
        UserInfoColumnInfo columnInfo = (UserInfoColumnInfo) realm.getSchema().getColumnInfo(com.example.realestate.Model.UserInfo.class);
        long colKey = OsObject.createRow(table);
        cache.put(object, colKey);
        Number realmGet$id = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$id();
        if (realmGet$id != null) {
            Table.nativeSetLong(tableNativePtr, columnInfo.idColKey, colKey, realmGet$id.longValue(), false);
        }
        String realmGet$user_image = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$user_image();
        if (realmGet$user_image != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.user_imageColKey, colKey, realmGet$user_image, false);
        }
        String realmGet$name = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameColKey, colKey, realmGet$name, false);
        }
        String realmGet$number = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$number();
        if (realmGet$number != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.numberColKey, colKey, realmGet$number, false);
        }
        String realmGet$email = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$email();
        if (realmGet$email != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.emailColKey, colKey, realmGet$email, false);
        }
        String realmGet$password = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$password();
        if (realmGet$password != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.passwordColKey, colKey, realmGet$password, false);
        }
        String realmGet$address = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$address();
        if (realmGet$address != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.addressColKey, colKey, realmGet$address, false);
        }
        String realmGet$city = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$city();
        if (realmGet$city != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.cityColKey, colKey, realmGet$city, false);
        }
        String realmGet$sector = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$sector();
        if (realmGet$sector != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.sectorColKey, colKey, realmGet$sector, false);
        }
        String realmGet$your_id = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$your_id();
        if (realmGet$your_id != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.your_idColKey, colKey, realmGet$your_id, false);
        }
        String realmGet$rnc = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$rnc();
        if (realmGet$rnc != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.rncColKey, colKey, realmGet$rnc, false);
        }
        String realmGet$cell_number = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$cell_number();
        if (realmGet$cell_number != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.cell_numberColKey, colKey, realmGet$cell_number, false);
        }
        String realmGet$company_name = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$company_name();
        if (realmGet$company_name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.company_nameColKey, colKey, realmGet$company_name, false);
        }
        String realmGet$upload_limit = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$upload_limit();
        if (realmGet$upload_limit != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.upload_limitColKey, colKey, realmGet$upload_limit, false);
        }
        String realmGet$expiry_date = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$expiry_date();
        if (realmGet$expiry_date != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.expiry_dateColKey, colKey, realmGet$expiry_date, false);
        }
        String realmGet$plan_buy_date = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$plan_buy_date();
        if (realmGet$plan_buy_date != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.plan_buy_dateColKey, colKey, realmGet$plan_buy_date, false);
        }
        return colKey;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.realestate.Model.UserInfo.class);
        long tableNativePtr = table.getNativePtr();
        UserInfoColumnInfo columnInfo = (UserInfoColumnInfo) realm.getSchema().getColumnInfo(com.example.realestate.Model.UserInfo.class);
        com.example.realestate.Model.UserInfo object = null;
        while (objects.hasNext()) {
            object = (com.example.realestate.Model.UserInfo) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            long colKey = OsObject.createRow(table);
            cache.put(object, colKey);
            Number realmGet$id = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$id();
            if (realmGet$id != null) {
                Table.nativeSetLong(tableNativePtr, columnInfo.idColKey, colKey, realmGet$id.longValue(), false);
            }
            String realmGet$user_image = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$user_image();
            if (realmGet$user_image != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.user_imageColKey, colKey, realmGet$user_image, false);
            }
            String realmGet$name = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$name();
            if (realmGet$name != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nameColKey, colKey, realmGet$name, false);
            }
            String realmGet$number = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$number();
            if (realmGet$number != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.numberColKey, colKey, realmGet$number, false);
            }
            String realmGet$email = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$email();
            if (realmGet$email != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.emailColKey, colKey, realmGet$email, false);
            }
            String realmGet$password = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$password();
            if (realmGet$password != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.passwordColKey, colKey, realmGet$password, false);
            }
            String realmGet$address = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$address();
            if (realmGet$address != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.addressColKey, colKey, realmGet$address, false);
            }
            String realmGet$city = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$city();
            if (realmGet$city != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.cityColKey, colKey, realmGet$city, false);
            }
            String realmGet$sector = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$sector();
            if (realmGet$sector != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.sectorColKey, colKey, realmGet$sector, false);
            }
            String realmGet$your_id = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$your_id();
            if (realmGet$your_id != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.your_idColKey, colKey, realmGet$your_id, false);
            }
            String realmGet$rnc = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$rnc();
            if (realmGet$rnc != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.rncColKey, colKey, realmGet$rnc, false);
            }
            String realmGet$cell_number = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$cell_number();
            if (realmGet$cell_number != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.cell_numberColKey, colKey, realmGet$cell_number, false);
            }
            String realmGet$company_name = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$company_name();
            if (realmGet$company_name != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.company_nameColKey, colKey, realmGet$company_name, false);
            }
            String realmGet$upload_limit = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$upload_limit();
            if (realmGet$upload_limit != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.upload_limitColKey, colKey, realmGet$upload_limit, false);
            }
            String realmGet$expiry_date = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$expiry_date();
            if (realmGet$expiry_date != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.expiry_dateColKey, colKey, realmGet$expiry_date, false);
            }
            String realmGet$plan_buy_date = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$plan_buy_date();
            if (realmGet$plan_buy_date != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.plan_buy_dateColKey, colKey, realmGet$plan_buy_date, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.example.realestate.Model.UserInfo object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.example.realestate.Model.UserInfo.class);
        long tableNativePtr = table.getNativePtr();
        UserInfoColumnInfo columnInfo = (UserInfoColumnInfo) realm.getSchema().getColumnInfo(com.example.realestate.Model.UserInfo.class);
        long colKey = OsObject.createRow(table);
        cache.put(object, colKey);
        Number realmGet$id = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$id();
        if (realmGet$id != null) {
            Table.nativeSetLong(tableNativePtr, columnInfo.idColKey, colKey, realmGet$id.longValue(), false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.idColKey, colKey, false);
        }
        String realmGet$user_image = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$user_image();
        if (realmGet$user_image != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.user_imageColKey, colKey, realmGet$user_image, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.user_imageColKey, colKey, false);
        }
        String realmGet$name = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameColKey, colKey, realmGet$name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameColKey, colKey, false);
        }
        String realmGet$number = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$number();
        if (realmGet$number != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.numberColKey, colKey, realmGet$number, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.numberColKey, colKey, false);
        }
        String realmGet$email = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$email();
        if (realmGet$email != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.emailColKey, colKey, realmGet$email, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.emailColKey, colKey, false);
        }
        String realmGet$password = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$password();
        if (realmGet$password != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.passwordColKey, colKey, realmGet$password, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.passwordColKey, colKey, false);
        }
        String realmGet$address = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$address();
        if (realmGet$address != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.addressColKey, colKey, realmGet$address, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.addressColKey, colKey, false);
        }
        String realmGet$city = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$city();
        if (realmGet$city != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.cityColKey, colKey, realmGet$city, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.cityColKey, colKey, false);
        }
        String realmGet$sector = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$sector();
        if (realmGet$sector != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.sectorColKey, colKey, realmGet$sector, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.sectorColKey, colKey, false);
        }
        String realmGet$your_id = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$your_id();
        if (realmGet$your_id != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.your_idColKey, colKey, realmGet$your_id, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.your_idColKey, colKey, false);
        }
        String realmGet$rnc = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$rnc();
        if (realmGet$rnc != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.rncColKey, colKey, realmGet$rnc, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.rncColKey, colKey, false);
        }
        String realmGet$cell_number = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$cell_number();
        if (realmGet$cell_number != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.cell_numberColKey, colKey, realmGet$cell_number, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.cell_numberColKey, colKey, false);
        }
        String realmGet$company_name = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$company_name();
        if (realmGet$company_name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.company_nameColKey, colKey, realmGet$company_name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.company_nameColKey, colKey, false);
        }
        String realmGet$upload_limit = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$upload_limit();
        if (realmGet$upload_limit != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.upload_limitColKey, colKey, realmGet$upload_limit, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.upload_limitColKey, colKey, false);
        }
        String realmGet$expiry_date = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$expiry_date();
        if (realmGet$expiry_date != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.expiry_dateColKey, colKey, realmGet$expiry_date, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.expiry_dateColKey, colKey, false);
        }
        String realmGet$plan_buy_date = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$plan_buy_date();
        if (realmGet$plan_buy_date != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.plan_buy_dateColKey, colKey, realmGet$plan_buy_date, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.plan_buy_dateColKey, colKey, false);
        }
        return colKey;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.realestate.Model.UserInfo.class);
        long tableNativePtr = table.getNativePtr();
        UserInfoColumnInfo columnInfo = (UserInfoColumnInfo) realm.getSchema().getColumnInfo(com.example.realestate.Model.UserInfo.class);
        com.example.realestate.Model.UserInfo object = null;
        while (objects.hasNext()) {
            object = (com.example.realestate.Model.UserInfo) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            long colKey = OsObject.createRow(table);
            cache.put(object, colKey);
            Number realmGet$id = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$id();
            if (realmGet$id != null) {
                Table.nativeSetLong(tableNativePtr, columnInfo.idColKey, colKey, realmGet$id.longValue(), false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.idColKey, colKey, false);
            }
            String realmGet$user_image = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$user_image();
            if (realmGet$user_image != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.user_imageColKey, colKey, realmGet$user_image, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.user_imageColKey, colKey, false);
            }
            String realmGet$name = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$name();
            if (realmGet$name != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nameColKey, colKey, realmGet$name, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.nameColKey, colKey, false);
            }
            String realmGet$number = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$number();
            if (realmGet$number != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.numberColKey, colKey, realmGet$number, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.numberColKey, colKey, false);
            }
            String realmGet$email = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$email();
            if (realmGet$email != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.emailColKey, colKey, realmGet$email, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.emailColKey, colKey, false);
            }
            String realmGet$password = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$password();
            if (realmGet$password != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.passwordColKey, colKey, realmGet$password, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.passwordColKey, colKey, false);
            }
            String realmGet$address = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$address();
            if (realmGet$address != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.addressColKey, colKey, realmGet$address, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.addressColKey, colKey, false);
            }
            String realmGet$city = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$city();
            if (realmGet$city != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.cityColKey, colKey, realmGet$city, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.cityColKey, colKey, false);
            }
            String realmGet$sector = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$sector();
            if (realmGet$sector != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.sectorColKey, colKey, realmGet$sector, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.sectorColKey, colKey, false);
            }
            String realmGet$your_id = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$your_id();
            if (realmGet$your_id != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.your_idColKey, colKey, realmGet$your_id, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.your_idColKey, colKey, false);
            }
            String realmGet$rnc = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$rnc();
            if (realmGet$rnc != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.rncColKey, colKey, realmGet$rnc, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.rncColKey, colKey, false);
            }
            String realmGet$cell_number = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$cell_number();
            if (realmGet$cell_number != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.cell_numberColKey, colKey, realmGet$cell_number, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.cell_numberColKey, colKey, false);
            }
            String realmGet$company_name = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$company_name();
            if (realmGet$company_name != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.company_nameColKey, colKey, realmGet$company_name, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.company_nameColKey, colKey, false);
            }
            String realmGet$upload_limit = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$upload_limit();
            if (realmGet$upload_limit != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.upload_limitColKey, colKey, realmGet$upload_limit, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.upload_limitColKey, colKey, false);
            }
            String realmGet$expiry_date = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$expiry_date();
            if (realmGet$expiry_date != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.expiry_dateColKey, colKey, realmGet$expiry_date, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.expiry_dateColKey, colKey, false);
            }
            String realmGet$plan_buy_date = ((com_example_realestate_Model_UserInfoRealmProxyInterface) object).realmGet$plan_buy_date();
            if (realmGet$plan_buy_date != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.plan_buy_dateColKey, colKey, realmGet$plan_buy_date, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.plan_buy_dateColKey, colKey, false);
            }
        }
    }

    public static com.example.realestate.Model.UserInfo createDetachedCopy(com.example.realestate.Model.UserInfo realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.example.realestate.Model.UserInfo unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.example.realestate.Model.UserInfo();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.example.realestate.Model.UserInfo) cachedObject.object;
            }
            unmanagedObject = (com.example.realestate.Model.UserInfo) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_example_realestate_Model_UserInfoRealmProxyInterface unmanagedCopy = (com_example_realestate_Model_UserInfoRealmProxyInterface) unmanagedObject;
        com_example_realestate_Model_UserInfoRealmProxyInterface realmSource = (com_example_realestate_Model_UserInfoRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$user_image(realmSource.realmGet$user_image());
        unmanagedCopy.realmSet$name(realmSource.realmGet$name());
        unmanagedCopy.realmSet$number(realmSource.realmGet$number());
        unmanagedCopy.realmSet$email(realmSource.realmGet$email());
        unmanagedCopy.realmSet$password(realmSource.realmGet$password());
        unmanagedCopy.realmSet$address(realmSource.realmGet$address());
        unmanagedCopy.realmSet$city(realmSource.realmGet$city());
        unmanagedCopy.realmSet$sector(realmSource.realmGet$sector());
        unmanagedCopy.realmSet$your_id(realmSource.realmGet$your_id());
        unmanagedCopy.realmSet$rnc(realmSource.realmGet$rnc());
        unmanagedCopy.realmSet$cell_number(realmSource.realmGet$cell_number());
        unmanagedCopy.realmSet$company_name(realmSource.realmGet$company_name());
        unmanagedCopy.realmSet$upload_limit(realmSource.realmGet$upload_limit());
        unmanagedCopy.realmSet$expiry_date(realmSource.realmGet$expiry_date());
        unmanagedCopy.realmSet$plan_buy_date(realmSource.realmGet$plan_buy_date());

        return unmanagedObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("UserInfo = proxy[");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id() != null ? realmGet$id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{user_image:");
        stringBuilder.append(realmGet$user_image() != null ? realmGet$user_image() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{name:");
        stringBuilder.append(realmGet$name() != null ? realmGet$name() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{number:");
        stringBuilder.append(realmGet$number() != null ? realmGet$number() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{email:");
        stringBuilder.append(realmGet$email() != null ? realmGet$email() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{password:");
        stringBuilder.append(realmGet$password() != null ? realmGet$password() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{address:");
        stringBuilder.append(realmGet$address() != null ? realmGet$address() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{city:");
        stringBuilder.append(realmGet$city() != null ? realmGet$city() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{sector:");
        stringBuilder.append(realmGet$sector() != null ? realmGet$sector() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{your_id:");
        stringBuilder.append(realmGet$your_id() != null ? realmGet$your_id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{rnc:");
        stringBuilder.append(realmGet$rnc() != null ? realmGet$rnc() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{cell_number:");
        stringBuilder.append(realmGet$cell_number() != null ? realmGet$cell_number() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{company_name:");
        stringBuilder.append(realmGet$company_name() != null ? realmGet$company_name() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{upload_limit:");
        stringBuilder.append(realmGet$upload_limit() != null ? realmGet$upload_limit() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{expiry_date:");
        stringBuilder.append(realmGet$expiry_date() != null ? realmGet$expiry_date() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{plan_buy_date:");
        stringBuilder.append(realmGet$plan_buy_date() != null ? realmGet$plan_buy_date() : "null");
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
        com_example_realestate_Model_UserInfoRealmProxy aUserInfo = (com_example_realestate_Model_UserInfoRealmProxy)o;

        BaseRealm realm = proxyState.getRealm$realm();
        BaseRealm otherRealm = aUserInfo.proxyState.getRealm$realm();
        String path = realm.getPath();
        String otherPath = otherRealm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;
        if (realm.isFrozen() != otherRealm.isFrozen()) return false;
        if (!realm.sharedRealm.getVersionID().equals(otherRealm.sharedRealm.getVersionID())) {
            return false;
        }

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aUserInfo.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getObjectKey() != aUserInfo.proxyState.getRow$realm().getObjectKey()) return false;

        return true;
    }
}
