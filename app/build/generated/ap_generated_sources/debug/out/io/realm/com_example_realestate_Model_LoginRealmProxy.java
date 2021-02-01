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
public class com_example_realestate_Model_LoginRealmProxy extends com.example.realestate.Model.Login
    implements RealmObjectProxy, com_example_realestate_Model_LoginRealmProxyInterface {

    static final class LoginColumnInfo extends ColumnInfo {
        long statusColKey;
        long messageColKey;
        long userInfoColKey;

        LoginColumnInfo(OsSchemaInfo schemaInfo) {
            super(3);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Login");
            this.statusColKey = addColumnDetails("status", "status", objectSchemaInfo);
            this.messageColKey = addColumnDetails("message", "message", objectSchemaInfo);
            this.userInfoColKey = addColumnDetails("userInfo", "userInfo", objectSchemaInfo);
        }

        LoginColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new LoginColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final LoginColumnInfo src = (LoginColumnInfo) rawSrc;
            final LoginColumnInfo dst = (LoginColumnInfo) rawDst;
            dst.statusColKey = src.statusColKey;
            dst.messageColKey = src.messageColKey;
            dst.userInfoColKey = src.userInfoColKey;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private LoginColumnInfo columnInfo;
    private ProxyState<com.example.realestate.Model.Login> proxyState;

    com_example_realestate_Model_LoginRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (LoginColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.example.realestate.Model.Login>(this);
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
    public com.example.realestate.Model.UserInfo realmGet$userInfo() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNullLink(columnInfo.userInfoColKey)) {
            return null;
        }
        return proxyState.getRealm$realm().get(com.example.realestate.Model.UserInfo.class, proxyState.getRow$realm().getLink(columnInfo.userInfoColKey), false, Collections.<String>emptyList());
    }

    @Override
    public void realmSet$userInfo(com.example.realestate.Model.UserInfo value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("userInfo")) {
                return;
            }
            if (value != null && !RealmObject.isManaged(value)) {
                value = ((Realm) proxyState.getRealm$realm()).copyToRealm(value);
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                // Table#nullifyLink() does not support default value. Just using Row.
                row.nullifyLink(columnInfo.userInfoColKey);
                return;
            }
            proxyState.checkValidObject(value);
            row.getTable().setLink(columnInfo.userInfoColKey, row.getObjectKey(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getObjectKey(), true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().nullifyLink(columnInfo.userInfoColKey);
            return;
        }
        proxyState.checkValidObject(value);
        proxyState.getRow$realm().setLink(columnInfo.userInfoColKey, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getObjectKey());
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("Login", 3, 0);
        builder.addPersistedProperty("status", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("message", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedLinkProperty("userInfo", RealmFieldType.OBJECT, "UserInfo");
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static LoginColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new LoginColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "Login";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "Login";
    }

    @SuppressWarnings("cast")
    public static com.example.realestate.Model.Login createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = new ArrayList<String>(1);
        if (json.has("userInfo")) {
            excludeFields.add("userInfo");
        }
        com.example.realestate.Model.Login obj = realm.createObjectInternal(com.example.realestate.Model.Login.class, true, excludeFields);

        final com_example_realestate_Model_LoginRealmProxyInterface objProxy = (com_example_realestate_Model_LoginRealmProxyInterface) obj;
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
        if (json.has("userInfo")) {
            if (json.isNull("userInfo")) {
                objProxy.realmSet$userInfo(null);
            } else {
                com.example.realestate.Model.UserInfo userInfoObj = com_example_realestate_Model_UserInfoRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("userInfo"), update);
                objProxy.realmSet$userInfo(userInfoObj);
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.example.realestate.Model.Login createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        final com.example.realestate.Model.Login obj = new com.example.realestate.Model.Login();
        final com_example_realestate_Model_LoginRealmProxyInterface objProxy = (com_example_realestate_Model_LoginRealmProxyInterface) obj;
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
            } else if (name.equals("userInfo")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$userInfo(null);
                } else {
                    com.example.realestate.Model.UserInfo userInfoObj = com_example_realestate_Model_UserInfoRealmProxy.createUsingJsonStream(realm, reader);
                    objProxy.realmSet$userInfo(userInfoObj);
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return realm.copyToRealm(obj);
    }

    private static com_example_realestate_Model_LoginRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.example.realestate.Model.Login.class), false, Collections.<String>emptyList());
        io.realm.com_example_realestate_Model_LoginRealmProxy obj = new io.realm.com_example_realestate_Model_LoginRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.example.realestate.Model.Login copyOrUpdate(Realm realm, LoginColumnInfo columnInfo, com.example.realestate.Model.Login object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
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
            return (com.example.realestate.Model.Login) cachedRealmObject;
        }

        return copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.example.realestate.Model.Login copy(Realm realm, LoginColumnInfo columnInfo, com.example.realestate.Model.Login newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.example.realestate.Model.Login) cachedRealmObject;
        }

        com_example_realestate_Model_LoginRealmProxyInterface realmObjectSource = (com_example_realestate_Model_LoginRealmProxyInterface) newObject;

        Table table = realm.getTable(com.example.realestate.Model.Login.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, flags);

        // Add all non-"object reference" fields
        builder.addString(columnInfo.statusColKey, realmObjectSource.realmGet$status());
        builder.addString(columnInfo.messageColKey, realmObjectSource.realmGet$message());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_example_realestate_Model_LoginRealmProxy realmObjectCopy = newProxyInstance(realm, row);
        cache.put(newObject, realmObjectCopy);

        // Finally add all fields that reference other Realm Objects, either directly or through a list
        com.example.realestate.Model.UserInfo userInfoObj = realmObjectSource.realmGet$userInfo();
        if (userInfoObj == null) {
            realmObjectCopy.realmSet$userInfo(null);
        } else {
            com.example.realestate.Model.UserInfo cacheuserInfo = (com.example.realestate.Model.UserInfo) cache.get(userInfoObj);
            if (cacheuserInfo != null) {
                realmObjectCopy.realmSet$userInfo(cacheuserInfo);
            } else {
                realmObjectCopy.realmSet$userInfo(com_example_realestate_Model_UserInfoRealmProxy.copyOrUpdate(realm, (com_example_realestate_Model_UserInfoRealmProxy.UserInfoColumnInfo) realm.getSchema().getColumnInfo(com.example.realestate.Model.UserInfo.class), userInfoObj, update, cache, flags));
            }
        }

        return realmObjectCopy;
    }

    public static long insert(Realm realm, com.example.realestate.Model.Login object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.example.realestate.Model.Login.class);
        long tableNativePtr = table.getNativePtr();
        LoginColumnInfo columnInfo = (LoginColumnInfo) realm.getSchema().getColumnInfo(com.example.realestate.Model.Login.class);
        long colKey = OsObject.createRow(table);
        cache.put(object, colKey);
        String realmGet$status = ((com_example_realestate_Model_LoginRealmProxyInterface) object).realmGet$status();
        if (realmGet$status != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.statusColKey, colKey, realmGet$status, false);
        }
        String realmGet$message = ((com_example_realestate_Model_LoginRealmProxyInterface) object).realmGet$message();
        if (realmGet$message != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.messageColKey, colKey, realmGet$message, false);
        }

        com.example.realestate.Model.UserInfo userInfoObj = ((com_example_realestate_Model_LoginRealmProxyInterface) object).realmGet$userInfo();
        if (userInfoObj != null) {
            Long cacheuserInfo = cache.get(userInfoObj);
            if (cacheuserInfo == null) {
                cacheuserInfo = com_example_realestate_Model_UserInfoRealmProxy.insert(realm, userInfoObj, cache);
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.userInfoColKey, colKey, cacheuserInfo, false);
        }
        return colKey;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.realestate.Model.Login.class);
        long tableNativePtr = table.getNativePtr();
        LoginColumnInfo columnInfo = (LoginColumnInfo) realm.getSchema().getColumnInfo(com.example.realestate.Model.Login.class);
        com.example.realestate.Model.Login object = null;
        while (objects.hasNext()) {
            object = (com.example.realestate.Model.Login) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            long colKey = OsObject.createRow(table);
            cache.put(object, colKey);
            String realmGet$status = ((com_example_realestate_Model_LoginRealmProxyInterface) object).realmGet$status();
            if (realmGet$status != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.statusColKey, colKey, realmGet$status, false);
            }
            String realmGet$message = ((com_example_realestate_Model_LoginRealmProxyInterface) object).realmGet$message();
            if (realmGet$message != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.messageColKey, colKey, realmGet$message, false);
            }

            com.example.realestate.Model.UserInfo userInfoObj = ((com_example_realestate_Model_LoginRealmProxyInterface) object).realmGet$userInfo();
            if (userInfoObj != null) {
                Long cacheuserInfo = cache.get(userInfoObj);
                if (cacheuserInfo == null) {
                    cacheuserInfo = com_example_realestate_Model_UserInfoRealmProxy.insert(realm, userInfoObj, cache);
                }
                table.setLink(columnInfo.userInfoColKey, colKey, cacheuserInfo, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.example.realestate.Model.Login object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.example.realestate.Model.Login.class);
        long tableNativePtr = table.getNativePtr();
        LoginColumnInfo columnInfo = (LoginColumnInfo) realm.getSchema().getColumnInfo(com.example.realestate.Model.Login.class);
        long colKey = OsObject.createRow(table);
        cache.put(object, colKey);
        String realmGet$status = ((com_example_realestate_Model_LoginRealmProxyInterface) object).realmGet$status();
        if (realmGet$status != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.statusColKey, colKey, realmGet$status, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.statusColKey, colKey, false);
        }
        String realmGet$message = ((com_example_realestate_Model_LoginRealmProxyInterface) object).realmGet$message();
        if (realmGet$message != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.messageColKey, colKey, realmGet$message, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.messageColKey, colKey, false);
        }

        com.example.realestate.Model.UserInfo userInfoObj = ((com_example_realestate_Model_LoginRealmProxyInterface) object).realmGet$userInfo();
        if (userInfoObj != null) {
            Long cacheuserInfo = cache.get(userInfoObj);
            if (cacheuserInfo == null) {
                cacheuserInfo = com_example_realestate_Model_UserInfoRealmProxy.insertOrUpdate(realm, userInfoObj, cache);
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.userInfoColKey, colKey, cacheuserInfo, false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.userInfoColKey, colKey);
        }
        return colKey;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.realestate.Model.Login.class);
        long tableNativePtr = table.getNativePtr();
        LoginColumnInfo columnInfo = (LoginColumnInfo) realm.getSchema().getColumnInfo(com.example.realestate.Model.Login.class);
        com.example.realestate.Model.Login object = null;
        while (objects.hasNext()) {
            object = (com.example.realestate.Model.Login) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            long colKey = OsObject.createRow(table);
            cache.put(object, colKey);
            String realmGet$status = ((com_example_realestate_Model_LoginRealmProxyInterface) object).realmGet$status();
            if (realmGet$status != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.statusColKey, colKey, realmGet$status, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.statusColKey, colKey, false);
            }
            String realmGet$message = ((com_example_realestate_Model_LoginRealmProxyInterface) object).realmGet$message();
            if (realmGet$message != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.messageColKey, colKey, realmGet$message, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.messageColKey, colKey, false);
            }

            com.example.realestate.Model.UserInfo userInfoObj = ((com_example_realestate_Model_LoginRealmProxyInterface) object).realmGet$userInfo();
            if (userInfoObj != null) {
                Long cacheuserInfo = cache.get(userInfoObj);
                if (cacheuserInfo == null) {
                    cacheuserInfo = com_example_realestate_Model_UserInfoRealmProxy.insertOrUpdate(realm, userInfoObj, cache);
                }
                Table.nativeSetLink(tableNativePtr, columnInfo.userInfoColKey, colKey, cacheuserInfo, false);
            } else {
                Table.nativeNullifyLink(tableNativePtr, columnInfo.userInfoColKey, colKey);
            }
        }
    }

    public static com.example.realestate.Model.Login createDetachedCopy(com.example.realestate.Model.Login realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.example.realestate.Model.Login unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.example.realestate.Model.Login();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.example.realestate.Model.Login) cachedObject.object;
            }
            unmanagedObject = (com.example.realestate.Model.Login) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_example_realestate_Model_LoginRealmProxyInterface unmanagedCopy = (com_example_realestate_Model_LoginRealmProxyInterface) unmanagedObject;
        com_example_realestate_Model_LoginRealmProxyInterface realmSource = (com_example_realestate_Model_LoginRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$status(realmSource.realmGet$status());
        unmanagedCopy.realmSet$message(realmSource.realmGet$message());

        // Deep copy of userInfo
        unmanagedCopy.realmSet$userInfo(com_example_realestate_Model_UserInfoRealmProxy.createDetachedCopy(realmSource.realmGet$userInfo(), currentDepth + 1, maxDepth, cache));

        return unmanagedObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Login = proxy[");
        stringBuilder.append("{status:");
        stringBuilder.append(realmGet$status() != null ? realmGet$status() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{message:");
        stringBuilder.append(realmGet$message() != null ? realmGet$message() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{userInfo:");
        stringBuilder.append(realmGet$userInfo() != null ? "UserInfo" : "null");
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
        com_example_realestate_Model_LoginRealmProxy aLogin = (com_example_realestate_Model_LoginRealmProxy)o;

        BaseRealm realm = proxyState.getRealm$realm();
        BaseRealm otherRealm = aLogin.proxyState.getRealm$realm();
        String path = realm.getPath();
        String otherPath = otherRealm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;
        if (realm.isFrozen() != otherRealm.isFrozen()) return false;
        if (!realm.sharedRealm.getVersionID().equals(otherRealm.sharedRealm.getVersionID())) {
            return false;
        }

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aLogin.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getObjectKey() != aLogin.proxyState.getRow$realm().getObjectKey()) return false;

        return true;
    }
}
