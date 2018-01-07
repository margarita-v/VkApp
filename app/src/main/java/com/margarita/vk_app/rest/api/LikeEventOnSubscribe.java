package com.margarita.vk_app.rest.api;

import com.margarita.vk_app.models.countable.Likes;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKRequest.VKRequestListener;
import com.vk.sdk.api.VKResponse;

import org.json.JSONException;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class LikeEventOnSubscribe implements ObservableOnSubscribe<Integer> {

    private Likes likes;
    private String type;

    private int ownerId;
    private int itemId;

    //region Keys for objects fields
    private static final String RESPONSE_KEY = "response";
    private static final String LIKES_KEY = "likes";
    private static final String TYPE_KEY = "type";
    private static final String ITEM_ID_KEY = "item_id";
    //endregion

    /**
     * Count of response attempts
     */
    private static final int ATTEMPTS_COUNT = 10;

    public LikeEventOnSubscribe(String type, int ownerId, int itemId, Likes likes) {
        this.likes = likes;
        this.type = type;
        this.ownerId = ownerId;
        this.itemId = itemId;
    }

    @Override
    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
        VKRequestListener vkRequestListener = new VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                try {
                    int likesCount = response.json
                            .getJSONObject(RESPONSE_KEY)
                            .getInt(LIKES_KEY);
                    emitter.onNext(likesCount);
                    emitter.onComplete();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
                emitter.onError(error.httpError);
            }
        };

        if (likes.canLike())
            performLikeAction(vkRequestListener, ApiMethods.ADD_LIKE);
        else if (likes.isUserPerformed())
            performLikeAction(vkRequestListener, ApiMethods.DELETE_LIKE);
        else
            emitter.onComplete();
    }

    /**
     * Function for performing request for like action
     * @param listener Listener for request's result
     * @param apiMethod API method for like addition or deletion
     */
    private void performLikeAction(VKRequestListener listener, String apiMethod) {
        VKRequest request = new VKRequest(apiMethod, VKParameters.from(
                TYPE_KEY, type,
                VKApiConst.OWNER_ID, ownerId,
                ITEM_ID_KEY, itemId));
        request.attempts = ATTEMPTS_COUNT;
        request.executeWithListener(listener);

    }
}
