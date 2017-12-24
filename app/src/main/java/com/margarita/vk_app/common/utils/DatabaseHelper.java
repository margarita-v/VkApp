package com.margarita.vk_app.common.utils;

import java.util.List;
import java.util.concurrent.Callable;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Class for performing operations to the local database
 * @param <T> Type of item for query results
 */
public abstract class DatabaseHelper<T extends RealmObject> {

    /**
     * Fields for query to the database
     */
    private static final String SORT_FIELD = "id";
    private static final String FIELD_NAME = "id";

    /**
     * Function for saving some object to the local database
     * @param realmObject Object which will be saved to the database
     */
    public static void saveToDatabase(RealmObject realmObject) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.copyToRealmOrUpdate(realmObject));
    }

    /**
     * Get list of items from the local database as Callable
     * @param sort Sort order for result items
     * @return List of items as Callable
     */
    public Callable<List<T>> getListFromRealmCallable(Sort sort) {
        return () -> {
            Realm realm = Realm.getDefaultInstance();
            // Perform the query which depends on item's type
            RealmResults<T> results = getListItems(realm)
                    .findAllSorted(getSortField(), sort);
            return realm.copyFromRealm(results);
        };
    }

    /**
     * Get single item from the local database as Callable
     * @param value Value for "where" condition in query
     * @return Single item as Callable
     */
    public Callable<T> getItemFromRealmCallable(Integer value) {
        return () -> getItemFromRealm(value);
    }

    /**
     * Get single item from the local database
     * @param value Value for "where" condition in query
     * @return Single item
     */
    public T getItemFromRealm(Integer value) {
        Realm realm = Realm.getDefaultInstance();
        T result = getSingleItem(realm, value)
                .findFirst();
        return realm.copyFromRealm(result);
    }

    /**
     * Perform common query to the database
     * @param realm Realm instance for access to the database
     * @return Set of query result
     */
    public abstract RealmQuery<T> performQuery(Realm realm);

    /**
     * Get a list of items as a subquery
     * @param realm Realm instance for access to the database
     * @return List of realm query items
     */
    public RealmQuery<T> getListItems(Realm realm) {
        return performQuery(realm);
    }

    /**
     * Get items as a subquery with "where" condition
     * @param realm Realm instance for access to the database
     * @param value Value for "where" condition in query
     * @return Query result for "where" condition
     */
    protected RealmQuery<T> getSingleItem(Realm realm, Integer value) {
        return performQuery(realm)
                .equalTo(getFieldName(), value);
    }


    /**
     * Function which returns a name of sort field
     * @return Sort field's name
     */
    protected String getSortField() {
        return SORT_FIELD;
    }

    /**
     * Function which returns a name of field for "where" condition
     * @return Field name for "where" condition
     */
    protected String getFieldName() {
        return FIELD_NAME;
    }
}
