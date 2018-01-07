package com.margarita.vk_app.models.countable;

import com.margarita.vk_app.models.Countable;

/**
 * Interface for all countable models
 * with checking for the current user's ability to perform some action
 */
public interface BaseAction extends Countable {

    /**
     * Flag for a user's ability to do some activities
     */
    int USER_PERFORMED_ACTION = 1;

    /**
     * Function which checks if the current user is able to perform some action.
     * This function provides its default implementation
     * which will be the same for all interface implementations.
     * @param field Field which value will be checked
     * @return True if the current user is able to perform action, False otherwise
     */
    default boolean checkAction(int field) {
        return field == USER_PERFORMED_ACTION;
    }

    /**
     * Function which checks if the current user had performed some action
     * @return True if the current user had performed action, False otherwise
     */
    default boolean isUserPerformed() {
        return false;
    }
}
