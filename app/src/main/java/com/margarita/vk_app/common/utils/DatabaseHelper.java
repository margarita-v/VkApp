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

    private Realm realm;

    protected DatabaseHelper() {
        realm = Realm.getDefaultInstance();
    }

    /**
     * Function for saving some object to the local database
     * @param realmObject Object which will be saved to the database
     */
    public static void saveToDatabase(RealmObject realmObject) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.copyToRealmOrUpdate(realmObject));
    }

    /**
     * Get list of items from local database as Callable
     * @param sortField Name of sort field
     * @param sort Sort order for result items
     * @return List of items as Callable
     */
    public Callable<List<T>> getListFromRealmCallable(String sortField, Sort sort) {
        return () -> {
            // Perform the query which depends on item's type
            RealmResults<T> results = getListItems(realm)
                    .findAllSorted(sortField, sort);
            return realm.copyFromRealm(results);
        };
    }

    /**
     * Get single item from local database as Callable
     * @param fieldName Name of field int the database
     * @param value Value for "where" condition in query
     * @return Single item as Callable
     */
    public Callable<T> getItemFromRealmCallable(String fieldName, Integer value) {
        return () -> {
            T result = getSingleItem(realm, fieldName, value)
                    .findFirst();
            return realm.copyFromRealm(result);
        };
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
     * @param fieldName Name of field in database
     * @param value Value for "where" condition in query
     * @return Query result for "where" condition
     */
    public RealmQuery<T> getSingleItem(Realm realm, String fieldName, Integer value) {
        return performQuery(realm)
                .equalTo(fieldName, value);
    }
}
