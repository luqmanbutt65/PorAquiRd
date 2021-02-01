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
public class com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_ResponseRealmProxy extends com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Response
    implements RealmObjectProxy, com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_ResponseRealmProxyInterface {

    static final class FavProperties_ResponseColumnInfo extends ColumnInfo {
        long statusColKey;
        long messageColKey;
        long dataColKey;

        FavProperties_ResponseColumnInfo(OsSchemaInfo schemaInfo) {
            super(3);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("FavProperties_Response");
            this.statusColKey = addColumnDetails("status", "status", objectSchemaInfo);
            this.messageColKey = addColumnDetails("message", "message", objectSchemaInfo);
            this.dataColKey = addColumnDetails("data", "data", objectSchemaInfo);
        }

        FavProperties_ResponseColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new FavProperties_ResponseColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final FavProperties_ResponseColumnInfo src = (FavProperties_ResponseColumnInfo) rawSrc;
            final FavProperties_ResponseColumnInfo dst = (FavProperties_ResponseColumnInfo) rawDst;
            dst.statusColKey = src.statusColKey;
            dst.messageColKey = src.messageColKey;
            dst.dataColKey = src.dataColKey;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private FavProperties_ResponseColumnInfo columnInfo;
    private ProxyState<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Response> proxyState;

    com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_ResponseRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (FavProperties_ResponseColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Response>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$status() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.statusColKey);
    }

    @Override
    public void realmSet$status(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.statusColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.statusColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.statusColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.statusColKey, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$message() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.messageColKey);
    }

    @Override
    public void realmSet$message(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.messageColKey, row.getObjectKey(), true);
                return;
            }
            row.getTable().setString(columnInfo.messageColKey, row.getObjectKey(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.messageColKey);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.messageColKey, value);
    }

    @Override
    public com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data realmGet$data() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNullLink(columnInfo.dataColKey)) {
            return null;
        }
        return proxyState.getRealm$realm().get(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data.class, proxyState.getRow$realm().getLink(columnInfo.dataColKey), false, Collections.<String>emptyList());
    }

    @Override
    public void realmSet$data(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("data")) {
                return;
            }
            if (value != null && !RealmObject.isManaged(value)) {
                value = ((Realm) proxyState.getRealm$realm()).copyToRealm(value);
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                // Table#nullifyLink() does not support default value. Just using Row.
                row.nullifyLink(columnInfo.dataColKey);
                return;
            }
            proxyState.checkValidObject(value);
            row.getTable().setLink(columnInfo.dataColKey, row.getObjectKey(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getObjectKey(), true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().nullifyLink(columnInfo.dataColKey);
            return;
        }
        proxyState.checkValidObject(value);
        proxyState.getRow$realm().setLink(columnInfo.dataColKey, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getObjectKey());
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("FavProperties_Response", 3, 0);
        builder.addPersistedProperty("status", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("message", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedLinkProperty("data", RealmFieldType.OBJECT, "FavProperties_Data");
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static FavProperties_ResponseColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new FavProperties_ResponseColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "FavProperties_Response";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "FavProperties_Response";
    }

    @SuppressWarnings("cast")
    public static com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Response createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = new ArrayList<String>(1);
        if (json.has("data")) {
            excludeFields.add("data");
        }
        com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Response obj = realm.createObjectInternal(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Response.class, true, excludeFields);

        final com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_ResponseRealmProxyInterface objProxy = (com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_ResponseRealmProxyInterface) obj;
        if (json.has("status")) {
            if (json.isNull("status")) {
                objProxy.realmSet$status(null);
            } else {
                objProxy.realmSet$status((String) json.getString("status"));
            }
        }
        if (json.has("message")) {
            if (json.isNull("message")) {
                objProxy.realmSet$message(null);
            } else {
                objProxy.realmSet$message((String) json.getString("message"));
            }
        }
        if (json.has("data")) {
            if (json.isNull("data")) {
                objProxy.realmSet$data(null);
            } else {
                com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data dataObj = com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_DataRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("data"), update);
                objProxy.realmSet$data(dataObj);
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Response createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        final com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Response obj = new com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Response();
        final com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_ResponseRealmProxyInterface objProxy = (com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_ResponseRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("status")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$status((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$status(null);
                }
            } else if (name.equals("message")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$message((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$message(null);
                }
            } else if (name.equals("data")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$data(null);
                } else {
                    com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data dataObj = com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_DataRealmProxy.createUsingJsonStream(realm, reader);
                    objProxy.realmSet$data(dataObj);
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return realm.copyToRealm(obj);
    }

    private static com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_ResponseRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Response.class), false, Collections.<String>emptyList());
        io.realm.com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_ResponseRealmProxy obj = new io.realm.com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_ResponseRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Response copyOrUpdate(Realm realm, FavProperties_ResponseColumnInfo columnInfo, com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Response object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
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
            return (com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Response) cachedRealmObject;
        }

        return copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Response copy(Realm realm, FavProperties_ResponseColumnInfo columnInfo, com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Response newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Response) cachedRealmObject;
        }

        com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_ResponseRealmProxyInterface realmObjectSource = (com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_ResponseRealmProxyInterface) newObject;

        Table table = realm.getTable(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Response.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, flags);

        // Add all non-"object reference" fields
        builder.addString(columnInfo.statusColKey, realmObjectSource.realmGet$status());
        builder.addString(columnInfo.messageColKey, realmObjectSource.realmGet$message());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_ResponseRealmProxy realmObjectCopy = newProxyInstance(realm, row);
        cache.put(newObject, realmObjectCopy);

        // Finally add all fields that reference other Realm Objects, either directly or through a list
        com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data dataObj = realmObjectSource.realmGet$data();
        if (dataObj == null) {
            realmObjectCopy.realmSet$data(null);
        } else {
            com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data cachedata = (com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data) cache.get(dataObj);
            if (cachedata != null) {
                realmObjectCopy.realmSet$data(cachedata);
            } else {
                realmObjectCopy.realmSet$data(com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_DataRealmProxy.copyOrUpdate(realm, (com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_DataRealmProxy.FavProperties_DataColumnInfo) realm.getSchema().getColumnInfo(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data.class), dataObj, update, cache, flags));
            }
        }

        return realmObjectCopy;
    }

    public static long insert(Realm realm, com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Response object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Response.class);
        long tableNativePtr = table.getNativePtr();
        FavProperties_ResponseColumnInfo columnInfo = (FavProperties_ResponseColumnInfo) realm.getSchema().getColumnInfo(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Response.class);
        long colKey = OsObject.createRow(table);
        cache.put(object, colKey);
        String realmGet$status = ((com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_ResponseRealmProxyInterface) object).realmGet$status();
        if (realmGet$status != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.statusColKey, colKey, realmGet$status, false);
        }
        String realmGet$message = ((com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_ResponseRealmProxyInterface) object).realmGet$message();
        if (realmGet$message != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.messageColKey, colKey, realmGet$message, false);
        }

        com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data dataObj = ((com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_ResponseRealmProxyInterface) object).realmGet$data();
        if (dataObj != null) {
            Long cachedata = cache.get(dataObj);
            if (cachedata == null) {
                cachedata = com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_DataRealmProxy.insert(realm, dataObj, cache);
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.dataColKey, colKey, cachedata, false);
        }
        return colKey;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Response.class);
        long tableNativePtr = table.getNativePtr();
        FavProperties_ResponseColumnInfo columnInfo = (FavProperties_ResponseColumnInfo) realm.getSchema().getColumnInfo(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Response.class);
        com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Response object = null;
        while (objects.hasNext()) {
            object = (com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Response) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            long colKey = OsObject.createRow(table);
            cache.put(object, colKey);
            String realmGet$status = ((com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_ResponseRealmProxyInterface) object).realmGet$status();
            if (realmGet$status != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.statusColKey, colKey, realmGet$status, false);
            }
            String realmGet$message = ((com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_ResponseRealmProxyInterface) object).realmGet$message();
            if (realmGet$message != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.messageColKey, colKey, realmGet$message, false);
            }

            com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data dataObj = ((com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_ResponseRealmProxyInterface) object).realmGet$data();
            if (dataObj != null) {
                Long cachedata = cache.get(dataObj);
                if (cachedata == null) {
                    cachedata = com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_DataRealmProxy.insert(realm, dataObj, cache);
                }
                table.setLink(columnInfo.dataColKey, colKey, cachedata, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Response object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Response.class);
        long tableNativePtr = table.getNativePtr();
        FavProperties_ResponseColumnInfo columnInfo = (FavProperties_ResponseColumnInfo) realm.getSchema().getColumnInfo(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Response.class);
        long colKey = OsObject.createRow(table);
        cache.put(object, colKey);
        String realmGet$status = ((com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_ResponseRealmProxyInterface) object).realmGet$status();
        if (realmGet$status != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.statusColKey, colKey, realmGet$status, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.statusColKey, colKey, false);
        }
        String realmGet$message = ((com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_ResponseRealmProxyInterface) object).realmGet$message();
        if (realmGet$message != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.messageColKey, colKey, realmGet$message, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.messageColKey, colKey, false);
        }

        com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data dataObj = ((com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_ResponseRealmProxyInterface) object).realmGet$data();
        if (dataObj != null) {
            Long cachedata = cache.get(dataObj);
            if (cachedata == null) {
                cachedata = com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_DataRealmProxy.insertOrUpdate(realm, dataObj, cache);
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.dataColKey, colKey, cachedata, false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.dataColKey, colKey);
        }
        return colKey;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Response.class);
        long tableNativePtr = table.getNativePtr();
        FavProperties_ResponseColumnInfo columnInfo = (FavProperties_ResponseColumnInfo) realm.getSchema().getColumnInfo(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Response.class);
        com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Response object = null;
        while (objects.hasNext()) {
            object = (com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Response) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            long colKey = OsObject.createRow(table);
            cache.put(object, colKey);
            String realmGet$status = ((com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_ResponseRealmProxyInterface) object).realmGet$status();
            if (realmGet$status != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.statusColKey, colKey, realmGet$status, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.statusColKey, colKey, false);
            }
            String realmGet$message = ((com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_ResponseRealmProxyInterface) object).realmGet$message();
            if (realmGet$message != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.messageColKey, colKey, realmGet$message, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.messageColKey, colKey, false);
            }

            com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data dataObj = ((com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_ResponseRealmProxyInterface) object).realmGet$data();
            if (dataObj != null) {
                Long cachedata = cache.get(dataObj);
                if (cachedata == null) {
                    cachedata = com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_DataRealmProxy.insertOrUpdate(realm, dataObj, cache);
                }
                Table.nativeSetLink(tableNativePtr, columnInfo.dataColKey, colKey, cachedata, false);
            } else {
                Table.nativeNullifyLink(tableNativePtr, columnInfo.dataColKey, colKey);
            }
        }
    }

    public static com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Response createDetachedCopy(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Response realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Response unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Response();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Response) cachedObject.object;
            }
            unmanagedObject = (com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Response) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_ResponseRealmProxyInterface unmanagedCopy = (com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_ResponseRealmProxyInterface) unmanagedObject;
        com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_ResponseRealmProxyInterface realmSource = (com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_ResponseRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$status(realmSource.realmGet$status());
        unmanagedCopy.realmSet$message(realmSource.realmGet$message());

        // Deep copy of data
        unmanagedCopy.realmSet$data(com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_DataRealmProxy.createDetachedCopy(realmSource.realmGet$data(), currentDepth + 1, maxDepth, cache));

        return unmanagedObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("FavProperties_Response = proxy[");
        stringBuilder.append("{status:");
        stringBuilder.append(realmGet$status() != null ? realmGet$status() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{message:");
        stringBuilder.append(realmGet$message() != null ? realmGet$message() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{data:");
        stringBuilder.append(realmGet$data() != null ? "FavProperties_Data" : "null");
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
        com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_ResponseRealmProxy aFavProperties_Response = (com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_ResponseRealmProxy)o;

        BaseRealm realm = proxyState.getRealm$realm();
        BaseRealm otherRealm = aFavProperties_Response.proxyState.getRealm$realm();
        String path = realm.getPath();
        String otherPath = otherRealm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;
        if (realm.isFrozen() != otherRealm.isFrozen()) return false;
        if (!realm.sharedRealm.getVersionID().equals(otherRealm.sharedRealm.getVersionID())) {
            return false;
        }

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aFavProperties_Response.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getObjectKey() != aFavProperties_Response.proxyState.getRow$realm().getObjectKey()) return false;

        return true;
    }
}
