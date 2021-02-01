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
public class com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_DataRealmProxy extends com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data
    implements RealmObjectProxy, com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_DataRealmProxyInterface {

    static final class FavProperties_DataColumnInfo extends ColumnInfo {
        long propertiesArrayListColKey;

        FavProperties_DataColumnInfo(OsSchemaInfo schemaInfo) {
            super(1);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("FavProperties_Data");
            this.propertiesArrayListColKey = addColumnDetails("propertiesArrayList", "propertiesArrayList", objectSchemaInfo);
        }

        FavProperties_DataColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new FavProperties_DataColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final FavProperties_DataColumnInfo src = (FavProperties_DataColumnInfo) rawSrc;
            final FavProperties_DataColumnInfo dst = (FavProperties_DataColumnInfo) rawDst;
            dst.propertiesArrayListColKey = src.propertiesArrayListColKey;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private FavProperties_DataColumnInfo columnInfo;
    private ProxyState<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data> proxyState;
    private RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties> propertiesArrayListRealmList;

    com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_DataRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (FavProperties_DataColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    public RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties> realmGet$propertiesArrayList() {
        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (propertiesArrayListRealmList != null) {
            return propertiesArrayListRealmList;
        } else {
            OsList osList = proxyState.getRow$realm().getModelList(columnInfo.propertiesArrayListColKey);
            propertiesArrayListRealmList = new RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties>(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties.class, osList, proxyState.getRealm$realm());
            return propertiesArrayListRealmList;
        }
    }

    @Override
    public void realmSet$propertiesArrayList(RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties> value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("propertiesArrayList")) {
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
        OsList osList = proxyState.getRow$realm().getModelList(columnInfo.propertiesArrayListColKey);
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

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("FavProperties_Data", 1, 0);
        builder.addPersistedLinkProperty("propertiesArrayList", RealmFieldType.LIST, "FavProperties");
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static FavProperties_DataColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new FavProperties_DataColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "FavProperties_Data";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "FavProperties_Data";
    }

    @SuppressWarnings("cast")
    public static com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = new ArrayList<String>(1);
        if (json.has("propertiesArrayList")) {
            excludeFields.add("propertiesArrayList");
        }
        com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data obj = realm.createObjectInternal(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data.class, true, excludeFields);

        final com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_DataRealmProxyInterface objProxy = (com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_DataRealmProxyInterface) obj;
        if (json.has("propertiesArrayList")) {
            if (json.isNull("propertiesArrayList")) {
                objProxy.realmSet$propertiesArrayList(null);
            } else {
                objProxy.realmGet$propertiesArrayList().clear();
                JSONArray array = json.getJSONArray("propertiesArrayList");
                for (int i = 0; i < array.length(); i++) {
                    com.example.realestate.Model.REST.Properties.FavProperties.FavProperties item = com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    objProxy.realmGet$propertiesArrayList().add(item);
                }
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        final com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data obj = new com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data();
        final com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_DataRealmProxyInterface objProxy = (com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_DataRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("propertiesArrayList")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$propertiesArrayList(null);
                } else {
                    objProxy.realmSet$propertiesArrayList(new RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties>());
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.example.realestate.Model.REST.Properties.FavProperties.FavProperties item = com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy.createUsingJsonStream(realm, reader);
                        objProxy.realmGet$propertiesArrayList().add(item);
                    }
                    reader.endArray();
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return realm.copyToRealm(obj);
    }

    private static com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_DataRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data.class), false, Collections.<String>emptyList());
        io.realm.com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_DataRealmProxy obj = new io.realm.com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_DataRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data copyOrUpdate(Realm realm, FavProperties_DataColumnInfo columnInfo, com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
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
            return (com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data) cachedRealmObject;
        }

        return copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data copy(Realm realm, FavProperties_DataColumnInfo columnInfo, com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data) cachedRealmObject;
        }

        com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_DataRealmProxyInterface realmObjectSource = (com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_DataRealmProxyInterface) newObject;

        Table table = realm.getTable(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, flags);

        // Add all non-"object reference" fields

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_DataRealmProxy realmObjectCopy = newProxyInstance(realm, row);
        cache.put(newObject, realmObjectCopy);

        // Finally add all fields that reference other Realm Objects, either directly or through a list
        RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties> propertiesArrayListList = realmObjectSource.realmGet$propertiesArrayList();
        if (propertiesArrayListList != null) {
            RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties> propertiesArrayListRealmList = realmObjectCopy.realmGet$propertiesArrayList();
            propertiesArrayListRealmList.clear();
            for (int i = 0; i < propertiesArrayListList.size(); i++) {
                com.example.realestate.Model.REST.Properties.FavProperties.FavProperties propertiesArrayListItem = propertiesArrayListList.get(i);
                com.example.realestate.Model.REST.Properties.FavProperties.FavProperties cachepropertiesArrayList = (com.example.realestate.Model.REST.Properties.FavProperties.FavProperties) cache.get(propertiesArrayListItem);
                if (cachepropertiesArrayList != null) {
                    propertiesArrayListRealmList.add(cachepropertiesArrayList);
                } else {
                    propertiesArrayListRealmList.add(com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy.copyOrUpdate(realm, (com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy.FavPropertiesColumnInfo) realm.getSchema().getColumnInfo(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties.class), propertiesArrayListItem, update, cache, flags));
                }
            }
        }

        return realmObjectCopy;
    }

    public static long insert(Realm realm, com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data.class);
        long tableNativePtr = table.getNativePtr();
        FavProperties_DataColumnInfo columnInfo = (FavProperties_DataColumnInfo) realm.getSchema().getColumnInfo(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data.class);
        long colKey = OsObject.createRow(table);
        cache.put(object, colKey);

        RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties> propertiesArrayListList = ((com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_DataRealmProxyInterface) object).realmGet$propertiesArrayList();
        if (propertiesArrayListList != null) {
            OsList propertiesArrayListOsList = new OsList(table.getUncheckedRow(colKey), columnInfo.propertiesArrayListColKey);
            for (com.example.realestate.Model.REST.Properties.FavProperties.FavProperties propertiesArrayListItem : propertiesArrayListList) {
                Long cacheItemIndexpropertiesArrayList = cache.get(propertiesArrayListItem);
                if (cacheItemIndexpropertiesArrayList == null) {
                    cacheItemIndexpropertiesArrayList = com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy.insert(realm, propertiesArrayListItem, cache);
                }
                propertiesArrayListOsList.addRow(cacheItemIndexpropertiesArrayList);
            }
        }
        return colKey;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data.class);
        long tableNativePtr = table.getNativePtr();
        FavProperties_DataColumnInfo columnInfo = (FavProperties_DataColumnInfo) realm.getSchema().getColumnInfo(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data.class);
        com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data object = null;
        while (objects.hasNext()) {
            object = (com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            long colKey = OsObject.createRow(table);
            cache.put(object, colKey);

            RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties> propertiesArrayListList = ((com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_DataRealmProxyInterface) object).realmGet$propertiesArrayList();
            if (propertiesArrayListList != null) {
                OsList propertiesArrayListOsList = new OsList(table.getUncheckedRow(colKey), columnInfo.propertiesArrayListColKey);
                for (com.example.realestate.Model.REST.Properties.FavProperties.FavProperties propertiesArrayListItem : propertiesArrayListList) {
                    Long cacheItemIndexpropertiesArrayList = cache.get(propertiesArrayListItem);
                    if (cacheItemIndexpropertiesArrayList == null) {
                        cacheItemIndexpropertiesArrayList = com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy.insert(realm, propertiesArrayListItem, cache);
                    }
                    propertiesArrayListOsList.addRow(cacheItemIndexpropertiesArrayList);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey();
        }
        Table table = realm.getTable(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data.class);
        long tableNativePtr = table.getNativePtr();
        FavProperties_DataColumnInfo columnInfo = (FavProperties_DataColumnInfo) realm.getSchema().getColumnInfo(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data.class);
        long colKey = OsObject.createRow(table);
        cache.put(object, colKey);

        OsList propertiesArrayListOsList = new OsList(table.getUncheckedRow(colKey), columnInfo.propertiesArrayListColKey);
        RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties> propertiesArrayListList = ((com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_DataRealmProxyInterface) object).realmGet$propertiesArrayList();
        if (propertiesArrayListList != null && propertiesArrayListList.size() == propertiesArrayListOsList.size()) {
            // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
            int objects = propertiesArrayListList.size();
            for (int i = 0; i < objects; i++) {
                com.example.realestate.Model.REST.Properties.FavProperties.FavProperties propertiesArrayListItem = propertiesArrayListList.get(i);
                Long cacheItemIndexpropertiesArrayList = cache.get(propertiesArrayListItem);
                if (cacheItemIndexpropertiesArrayList == null) {
                    cacheItemIndexpropertiesArrayList = com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy.insertOrUpdate(realm, propertiesArrayListItem, cache);
                }
                propertiesArrayListOsList.setRow(i, cacheItemIndexpropertiesArrayList);
            }
        } else {
            propertiesArrayListOsList.removeAll();
            if (propertiesArrayListList != null) {
                for (com.example.realestate.Model.REST.Properties.FavProperties.FavProperties propertiesArrayListItem : propertiesArrayListList) {
                    Long cacheItemIndexpropertiesArrayList = cache.get(propertiesArrayListItem);
                    if (cacheItemIndexpropertiesArrayList == null) {
                        cacheItemIndexpropertiesArrayList = com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy.insertOrUpdate(realm, propertiesArrayListItem, cache);
                    }
                    propertiesArrayListOsList.addRow(cacheItemIndexpropertiesArrayList);
                }
            }
        }

        return colKey;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data.class);
        long tableNativePtr = table.getNativePtr();
        FavProperties_DataColumnInfo columnInfo = (FavProperties_DataColumnInfo) realm.getSchema().getColumnInfo(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data.class);
        com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data object = null;
        while (objects.hasNext()) {
            object = (com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && !RealmObject.isFrozen(object) && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getObjectKey());
                continue;
            }
            long colKey = OsObject.createRow(table);
            cache.put(object, colKey);

            OsList propertiesArrayListOsList = new OsList(table.getUncheckedRow(colKey), columnInfo.propertiesArrayListColKey);
            RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties> propertiesArrayListList = ((com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_DataRealmProxyInterface) object).realmGet$propertiesArrayList();
            if (propertiesArrayListList != null && propertiesArrayListList.size() == propertiesArrayListOsList.size()) {
                // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
                int objectCount = propertiesArrayListList.size();
                for (int i = 0; i < objectCount; i++) {
                    com.example.realestate.Model.REST.Properties.FavProperties.FavProperties propertiesArrayListItem = propertiesArrayListList.get(i);
                    Long cacheItemIndexpropertiesArrayList = cache.get(propertiesArrayListItem);
                    if (cacheItemIndexpropertiesArrayList == null) {
                        cacheItemIndexpropertiesArrayList = com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy.insertOrUpdate(realm, propertiesArrayListItem, cache);
                    }
                    propertiesArrayListOsList.setRow(i, cacheItemIndexpropertiesArrayList);
                }
            } else {
                propertiesArrayListOsList.removeAll();
                if (propertiesArrayListList != null) {
                    for (com.example.realestate.Model.REST.Properties.FavProperties.FavProperties propertiesArrayListItem : propertiesArrayListList) {
                        Long cacheItemIndexpropertiesArrayList = cache.get(propertiesArrayListItem);
                        if (cacheItemIndexpropertiesArrayList == null) {
                            cacheItemIndexpropertiesArrayList = com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy.insertOrUpdate(realm, propertiesArrayListItem, cache);
                        }
                        propertiesArrayListOsList.addRow(cacheItemIndexpropertiesArrayList);
                    }
                }
            }

        }
    }

    public static com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data createDetachedCopy(com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data) cachedObject.object;
            }
            unmanagedObject = (com.example.realestate.Model.REST.Properties.FavProperties.FavProperties_Data) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_DataRealmProxyInterface unmanagedCopy = (com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_DataRealmProxyInterface) unmanagedObject;
        com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_DataRealmProxyInterface realmSource = (com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_DataRealmProxyInterface) realmObject;

        // Deep copy of propertiesArrayList
        if (currentDepth == maxDepth) {
            unmanagedCopy.realmSet$propertiesArrayList(null);
        } else {
            RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties> managedpropertiesArrayListList = realmSource.realmGet$propertiesArrayList();
            RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties> unmanagedpropertiesArrayListList = new RealmList<com.example.realestate.Model.REST.Properties.FavProperties.FavProperties>();
            unmanagedCopy.realmSet$propertiesArrayList(unmanagedpropertiesArrayListList);
            int nextDepth = currentDepth + 1;
            int size = managedpropertiesArrayListList.size();
            for (int i = 0; i < size; i++) {
                com.example.realestate.Model.REST.Properties.FavProperties.FavProperties item = com_example_realestate_Model_REST_Properties_FavProperties_FavPropertiesRealmProxy.createDetachedCopy(managedpropertiesArrayListList.get(i), nextDepth, maxDepth, cache);
                unmanagedpropertiesArrayListList.add(item);
            }
        }

        return unmanagedObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("FavProperties_Data = proxy[");
        stringBuilder.append("{propertiesArrayList:");
        stringBuilder.append("RealmList<FavProperties>[").append(realmGet$propertiesArrayList().size()).append("]");
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
        com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_DataRealmProxy aFavProperties_Data = (com_example_realestate_Model_REST_Properties_FavProperties_FavProperties_DataRealmProxy)o;

        BaseRealm realm = proxyState.getRealm$realm();
        BaseRealm otherRealm = aFavProperties_Data.proxyState.getRealm$realm();
        String path = realm.getPath();
        String otherPath = otherRealm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;
        if (realm.isFrozen() != otherRealm.isFrozen()) return false;
        if (!realm.sharedRealm.getVersionID().equals(otherRealm.sharedRealm.getVersionID())) {
            return false;
        }

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aFavProperties_Data.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getObjectKey() != aFavProperties_Data.proxyState.getRow$realm().getObjectKey()) return false;

        return true;
    }
}
