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
public class com_example_realestate_Model_GetUpdateData_User_DataRealmProxy extends com.example.realestate.Model.GetUpdateData.User_Data
    implements RealmObjectProxy, com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface {

    static final class User_DataColumnInfo extends ColumnInfo {
        long idColKey;
        long rncColKey;
        long photo_idColKey;
        long phone_numberColKey;
        long work_permitColKey;
        long company_nameColKey;
        long verifiedColKey;
        long your_idColKey;

        User_DataColumnInfo(OsSchemaInfo schemaInfo) {
            super(8);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("User_Data");
            this.idColKey = addColumnDetails("id", "id", objectSchemaInfo);
            this.rncColKey = addColumnDetails("rnc", "rnc", objectSchemaInfo);
            this.photo_idColKey = addColumnDetails("photo_id", "photo_id", objectSchemaInfo);
            this.phone_numberColKey = addColumnDetails("phone_number", "phone_number", objectSchemaInfo);
            this.work_permitColKey = addColumnDetails("work_permit", "work_permit", objectSchemaInfo);
            this.company_nameColKey = addColumnDetails("company_name", "company_name", objectSchemaInfo);
            this.verifiedColKey = addColumnDetails("verified", "verified", objectSchemaInfo);
            this.your_idColKey = addColumnDetails("your_id", "your_id", objectSchemaInfo);
        }

        User_DataColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new User_DataColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final User_DataColumnInfo src = (User_DataColumnInfo) rawSrc;
            final User_DataColumnInfo dst = (User_DataColumnInfo) rawDst;
            dst.idColKey = src.idColKey;
            dst.rncColKey = src.rncColKey;
            dst.photo_idColKey = src.photo_idColKey;
            dst.phone_numberColKey = src.phone_numberColKey;
            dst.work_permitColKey = src.work_permitColKey;
            dst.company_nameColKey = src.company_nameColKey;
            dst.verifiedColKey = src.verifiedColKey;
            dst.your_idColKey = src.your_idColKey;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private User_DataColumnInfo columnInfo;
    private ProxyState<com.example.realestate.Model.GetUpdateData.User_Data> proxyState;

    com_example_realestate_Model_GetUpdateData_User_DataRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (User_DataColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.example.realestate.Model.GetUpdateData.User_Data>(this);
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
    public String realmGet$photo_id() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.photo_idColKey);
    }

    @Override
    public void realmSet$photo_id(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.photo_idColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.photo_idColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.photo_idColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.photo_idColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$phone_number() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.phone_numberColKey);
    }

    @Override
    public void realmSet$phone_number(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.phone_numberColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.phone_numberColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.phone_numberColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.phone_numberColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$work_permit() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.work_permitColKey);
    }

    @Override
    public void realmSet$work_permit(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.work_permitColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.work_permitColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.work_permitColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.work_permitColKey, value);
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
    public String realmGet$verified() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.verifiedColKey);
    }

    @Override
    public void realmSet$verified(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.verifiedColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.verifiedColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.verifiedColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.verifiedColKey, value);
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

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("User_Data", 8, 0);
        builder.addPersistedProperty("id", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("rnc", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("photo_id", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("phone_number", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("work_permit", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("company_name", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("verified", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("your_id", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static User_DataColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new User_DataColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "User_Data";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "User_Data";
    }

    @SuppressWarnings("cast")
    public static com.example.realestate.Model.GetUpdateData.User_Data createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.example.realestate.Model.GetUpdateData.User_Data obj = realm.createObjectInternal(com.example.realestate.Model.GetUpdateData.User_Data.class, true, excludeFields);

        final com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface objProxy = (com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface) obj;
        if (json.has("id")) {
            if (json.isNull("id")) {
                objProxy.realmSet$id(null);
            } else {
                objProxy.realmSet$id((int) json.getInt("id"));
            }
        }
        if (json.has("rnc")) {
            if (json.isNull("rnc")) {
                objProxy.realmSet$rnc(null);
            } else {
                objProxy.realmSet$rnc((String) json.getString("rnc"));
            }
        }
        if (json.has("photo_id")) {
            if (json.isNull("photo_id")) {
                objProxy.realmSet$photo_id(null);
            } else {
                objProxy.realmSet$photo_id((String) json.getString("photo_id"));
            }
        }
        if (json.has("phone_number")) {
            if (json.isNull("phone_number")) {
                objProxy.realmSet$phone_number(null);
            } else {
                objProxy.realmSet$phone_number((String) json.getString("phone_number"));
            }
        }
        if (json.has("work_permit")) {
            if (json.isNull("work_permit")) {
                objProxy.realmSet$work_permit(null);
            } else {
                objProxy.realmSet$work_permit((String) json.getString("work_permit"));
            }
        }
        if (json.has("company_name")) {
            if (json.isNull("company_name")) {
                objProxy.realmSet$company_name(null);
            } else {
                objProxy.realmSet$company_name((String) json.getString("company_name"));
            }
        }
        if (json.has("verified")) {
            if (json.isNull("verified")) {
                objProxy.realmSet$verified(null);
            } else {
                objProxy.realmSet$verified((String) json.getString("verified"));
            }
        }
        if (json.has("your_id")) {
            if (json.isNull("your_id")) {
                objProxy.realmSet$your_id(null);
            } else {
                objProxy.realmSet$your_id((String) json.getString("your_id"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.example.realestate.Model.GetUpdateData.User_Data createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        final com.example.realestate.Model.GetUpdateData.User_Data obj = new com.example.realestate.Model.GetUpdateData.User_Data();
        final com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface objProxy = (com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface) obj;
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
            } else if (name.equals("rnc")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$rnc((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$rnc(null);
                }
            } else if (name.equals("photo_id")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$photo_id((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$photo_id(null);
                }
            } else if (name.equals("phone_number")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$phone_number((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$phone_number(null);
                }
            } else if (name.equals("work_permit")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$work_permit((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$work_permit(null);
                }
            } else if (name.equals("company_name")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$company_name((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$company_name(null);
                }
            } else if (name.equals("verified")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$verified((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$verified(null);
                }
            } else if (name.equals("your_id")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$your_id((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$your_id(null);
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return realm.copyToRealm(obj);
    }

    private static com_example_realestate_Model_GetUpdateData_User_DataRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.example.realestate.Model.GetUpdateData.User_Data.class), false, Collections.<String>emptyList());
        io.realm.com_example_realestate_Model_GetUpdateData_User_DataRealmProxy obj = new io.realm.com_example_realestate_Model_GetUpdateData_User_DataRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.example.realestate.Model.GetUpdateData.User_Data copyOrUpdate(Realm realm, User_DataColumnInfo columnInfo, com.example.realestate.Model.GetUpdateData.User_Data object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
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
            return (com.example.realestate.Model.GetUpdateData.User_Data) cachedRealmObject;
        }

        return copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.example.realestate.Model.GetUpdateData.User_Data copy(Realm realm, User_DataColumnInfo columnInfo, com.example.realestate.Model.GetUpdateData.User_Data newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.example.realestate.Model.GetUpdateData.User_Data) cachedRealmObject;
        }

        com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface realmObjectSource = (com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface) newObject;

        Table table = realm.getTable(com.example.realestate.Model.GetUpdateData.User_Data.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, flags);

        // Add all non-"object reference" fields
        builder.addInteger(columnInfo.idColKey, realmObjectSource.realmGet$id());
        builder.addString(columnInfo.rncColKey, realmObjectSource.realmGet$rnc());
        builder.addString(columnInfo.photo_idColKey, realmObjectSource.realmGet$photo_id());
        builder.addString(columnInfo.phone_numberColKey, realmObjectSource.realmGet$phone_number());
        builder.addString(columnInfo.work_permitColKey, realmObjectSource.realmGet$work_permit());
        builder.addString(columnInfo.company_nameColKey, realmObjectSource.realmGet$company_name());
        builder.addString(columnInfo.verifiedColKey, realmObjectSource.realmGet$verified());
        builder.addString(columnInfo.your_idColKey, realmObjectSource.realmGet$your_id());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_example_realestate_Model_GetUpdateData_User_DataRealmProxy realmObjectCopy = newProxyInstance(realm, row);
        cache.put(newObject, realmObjectCopy);

        return realmObjectCopy;
    }

    public static long insert(Realm realm, com.example.realestate.Model.GetUpdateData.User_Data object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.example.realestate.Model.GetUpdateData.User_Data.class);
        long tableNativePtr = table.getNativePtr();
        User_DataColumnInfo columnInfo = (User_DataColumnInfo) realm.getSchema().getColumnInfo(com.example.realestate.Model.GetUpdateData.User_Data.class);
        long colKey = OsObject.createRow(table);
        cache.put(object, colKey);
        Number realmGet$id = ((com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface) object).realmGet$id();
        if (realmGet$id != null) {
            Table.nativeSetLong(tableNativePtr, columnInfo.idColKey, colKey, realmGet$id.longValue(), false);
        }
        String realmGet$rnc = ((com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface) object).realmGet$rnc();
        if (realmGet$rnc != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.rncColKey, colKey, realmGet$rnc, false);
        }
        String realmGet$photo_id = ((com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface) object).realmGet$photo_id();
        if (realmGet$photo_id != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.photo_idColKey, colKey, realmGet$photo_id, false);
        }
        String realmGet$phone_number = ((com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface) object).realmGet$phone_number();
        if (realmGet$phone_number != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.phone_numberColKey, colKey, realmGet$phone_number, false);
        }
        String realmGet$work_permit = ((com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface) object).realmGet$work_permit();
        if (realmGet$work_permit != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.work_permitColKey, colKey, realmGet$work_permit, false);
        }
        String realmGet$company_name = ((com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface) object).realmGet$company_name();
        if (realmGet$company_name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.company_nameColKey, colKey, realmGet$company_name, false);
        }
        String realmGet$verified = ((com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface) object).realmGet$verified();
        if (realmGet$verified != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.verifiedColKey, colKey, realmGet$verified, false);
        }
        String realmGet$your_id = ((com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface) object).realmGet$your_id();
        if (realmGet$your_id != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.your_idColKey, colKey, realmGet$your_id, false);
        }
        return colKey;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.realestate.Model.GetUpdateData.User_Data.class);
        long tableNativePtr = table.getNativePtr();
        User_DataColumnInfo columnInfo = (User_DataColumnInfo) realm.getSchema().getColumnInfo(com.example.realestate.Model.GetUpdateData.User_Data.class);
        com.example.realestate.Model.GetUpdateData.User_Data object = null;
        while (objects.hasNext()) {
            object = (com.example.realestate.Model.GetUpdateData.User_Data) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            long colKey = OsObject.createRow(table);
            cache.put(object, colKey);
            Number realmGet$id = ((com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface) object).realmGet$id();
            if (realmGet$id != null) {
                Table.nativeSetLong(tableNativePtr, columnInfo.idColKey, colKey, realmGet$id.longValue(), false);
            }
            String realmGet$rnc = ((com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface) object).realmGet$rnc();
            if (realmGet$rnc != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.rncColKey, colKey, realmGet$rnc, false);
            }
            String realmGet$photo_id = ((com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface) object).realmGet$photo_id();
            if (realmGet$photo_id != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.photo_idColKey, colKey, realmGet$photo_id, false);
            }
            String realmGet$phone_number = ((com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface) object).realmGet$phone_number();
            if (realmGet$phone_number != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.phone_numberColKey, colKey, realmGet$phone_number, false);
            }
            String realmGet$work_permit = ((com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface) object).realmGet$work_permit();
            if (realmGet$work_permit != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.work_permitColKey, colKey, realmGet$work_permit, false);
            }
            String realmGet$company_name = ((com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface) object).realmGet$company_name();
            if (realmGet$company_name != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.company_nameColKey, colKey, realmGet$company_name, false);
            }
            String realmGet$verified = ((com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface) object).realmGet$verified();
            if (realmGet$verified != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.verifiedColKey, colKey, realmGet$verified, false);
            }
            String realmGet$your_id = ((com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface) object).realmGet$your_id();
            if (realmGet$your_id != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.your_idColKey, colKey, realmGet$your_id, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.example.realestate.Model.GetUpdateData.User_Data object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.example.realestate.Model.GetUpdateData.User_Data.class);
        long tableNativePtr = table.getNativePtr();
        User_DataColumnInfo columnInfo = (User_DataColumnInfo) realm.getSchema().getColumnInfo(com.example.realestate.Model.GetUpdateData.User_Data.class);
        long colKey = OsObject.createRow(table);
        cache.put(object, colKey);
        Number realmGet$id = ((com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface) object).realmGet$id();
        if (realmGet$id != null) {
            Table.nativeSetLong(tableNativePtr, columnInfo.idColKey, colKey, realmGet$id.longValue(), false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.idColKey, colKey, false);
        }
        String realmGet$rnc = ((com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface) object).realmGet$rnc();
        if (realmGet$rnc != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.rncColKey, colKey, realmGet$rnc, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.rncColKey, colKey, false);
        }
        String realmGet$photo_id = ((com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface) object).realmGet$photo_id();
        if (realmGet$photo_id != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.photo_idColKey, colKey, realmGet$photo_id, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.photo_idColKey, colKey, false);
        }
        String realmGet$phone_number = ((com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface) object).realmGet$phone_number();
        if (realmGet$phone_number != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.phone_numberColKey, colKey, realmGet$phone_number, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.phone_numberColKey, colKey, false);
        }
        String realmGet$work_permit = ((com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface) object).realmGet$work_permit();
        if (realmGet$work_permit != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.work_permitColKey, colKey, realmGet$work_permit, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.work_permitColKey, colKey, false);
        }
        String realmGet$company_name = ((com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface) object).realmGet$company_name();
        if (realmGet$company_name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.company_nameColKey, colKey, realmGet$company_name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.company_nameColKey, colKey, false);
        }
        String realmGet$verified = ((com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface) object).realmGet$verified();
        if (realmGet$verified != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.verifiedColKey, colKey, realmGet$verified, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.verifiedColKey, colKey, false);
        }
        String realmGet$your_id = ((com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface) object).realmGet$your_id();
        if (realmGet$your_id != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.your_idColKey, colKey, realmGet$your_id, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.your_idColKey, colKey, false);
        }
        return colKey;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.realestate.Model.GetUpdateData.User_Data.class);
        long tableNativePtr = table.getNativePtr();
        User_DataColumnInfo columnInfo = (User_DataColumnInfo) realm.getSchema().getColumnInfo(com.example.realestate.Model.GetUpdateData.User_Data.class);
        com.example.realestate.Model.GetUpdateData.User_Data object = null;
        while (objects.hasNext()) {
            object = (com.example.realestate.Model.GetUpdateData.User_Data) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            long colKey = OsObject.createRow(table);
            cache.put(object, colKey);
            Number realmGet$id = ((com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface) object).realmGet$id();
            if (realmGet$id != null) {
                Table.nativeSetLong(tableNativePtr, columnInfo.idColKey, colKey, realmGet$id.longValue(), false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.idColKey, colKey, false);
            }
            String realmGet$rnc = ((com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface) object).realmGet$rnc();
            if (realmGet$rnc != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.rncColKey, colKey, realmGet$rnc, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.rncColKey, colKey, false);
            }
            String realmGet$photo_id = ((com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface) object).realmGet$photo_id();
            if (realmGet$photo_id != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.photo_idColKey, colKey, realmGet$photo_id, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.photo_idColKey, colKey, false);
            }
            String realmGet$phone_number = ((com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface) object).realmGet$phone_number();
            if (realmGet$phone_number != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.phone_numberColKey, colKey, realmGet$phone_number, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.phone_numberColKey, colKey, false);
            }
            String realmGet$work_permit = ((com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface) object).realmGet$work_permit();
            if (realmGet$work_permit != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.work_permitColKey, colKey, realmGet$work_permit, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.work_permitColKey, colKey, false);
            }
            String realmGet$company_name = ((com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface) object).realmGet$company_name();
            if (realmGet$company_name != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.company_nameColKey, colKey, realmGet$company_name, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.company_nameColKey, colKey, false);
            }
            String realmGet$verified = ((com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface) object).realmGet$verified();
            if (realmGet$verified != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.verifiedColKey, colKey, realmGet$verified, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.verifiedColKey, colKey, false);
            }
            String realmGet$your_id = ((com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface) object).realmGet$your_id();
            if (realmGet$your_id != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.your_idColKey, colKey, realmGet$your_id, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.your_idColKey, colKey, false);
            }
        }
    }

    public static com.example.realestate.Model.GetUpdateData.User_Data createDetachedCopy(com.example.realestate.Model.GetUpdateData.User_Data realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.example.realestate.Model.GetUpdateData.User_Data unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.example.realestate.Model.GetUpdateData.User_Data();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.example.realestate.Model.GetUpdateData.User_Data) cachedObject.object;
            }
            unmanagedObject = (com.example.realestate.Model.GetUpdateData.User_Data) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface unmanagedCopy = (com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface) unmanagedObject;
        com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface realmSource = (com_example_realestate_Model_GetUpdateData_User_DataRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$rnc(realmSource.realmGet$rnc());
        unmanagedCopy.realmSet$photo_id(realmSource.realmGet$photo_id());
        unmanagedCopy.realmSet$phone_number(realmSource.realmGet$phone_number());
        unmanagedCopy.realmSet$work_permit(realmSource.realmGet$work_permit());
        unmanagedCopy.realmSet$company_name(realmSource.realmGet$company_name());
        unmanagedCopy.realmSet$verified(realmSource.realmGet$verified());
        unmanagedCopy.realmSet$your_id(realmSource.realmGet$your_id());

        return unmanagedObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("User_Data = proxy[");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id() != null ? realmGet$id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{rnc:");
        stringBuilder.append(realmGet$rnc() != null ? realmGet$rnc() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{photo_id:");
        stringBuilder.append(realmGet$photo_id() != null ? realmGet$photo_id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{phone_number:");
        stringBuilder.append(realmGet$phone_number() != null ? realmGet$phone_number() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{work_permit:");
        stringBuilder.append(realmGet$work_permit() != null ? realmGet$work_permit() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{company_name:");
        stringBuilder.append(realmGet$company_name() != null ? realmGet$company_name() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{verified:");
        stringBuilder.append(realmGet$verified() != null ? realmGet$verified() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{your_id:");
        stringBuilder.append(realmGet$your_id() != null ? realmGet$your_id() : "null");
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
        com_example_realestate_Model_GetUpdateData_User_DataRealmProxy aUser_Data = (com_example_realestate_Model_GetUpdateData_User_DataRealmProxy)o;

        BaseRealm realm = proxyState.getRealm$realm();
        BaseRealm otherRealm = aUser_Data.proxyState.getRealm$realm();
        String path = realm.getPath();
        String otherPath = otherRealm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;
        if (realm.isFrozen() != otherRealm.isFrozen()) return false;
        if (!realm.sharedRealm.getVersionID().equals(otherRealm.sharedRealm.getVersionID())) {
            return false;
        }

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aUser_Data.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getObjectKey() != aUser_Data.proxyState.getRow$realm().getObjectKey()) return false;

        return true;
    }
}
