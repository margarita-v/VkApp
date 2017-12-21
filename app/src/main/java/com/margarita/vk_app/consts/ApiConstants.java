package com.margarita.vk_app.consts;

import com.vk.sdk.VKScope;

public class ApiConstants {

    public static final Double DEFAULT_VERSION = 5.67;

    /**
     * To getting permission for sending requests to Vk server.
     * Allows us to request user's email to show it in navigation drawer
     */
    public static final String[] DEFAULT_LOGIN_SCOPE = {
            VKScope.AUDIO, VKScope.DIRECT, VKScope.VIDEO,
            VKScope.WALL, VKScope.MESSAGES, VKScope.PHOTOS,
            VKScope.PAGES, VKScope.GROUPS, VKScope.EMAIL,
            VKScope.STATS, VKScope.DOCS};

    public static final String VIDEOS = "videos";
    public static final String POSTS = "posts";
    public static final String EXTENDED = "extended";
    public static final String OWNER_ID = "owner_id";
    public static final String POST_ID = "post_id";
    public static final String COUNT = "count";
    public static final String OFFSET = "offset";
    public static final String NEED_LIKES = "need_likes";

    /**
     * To getting a concrete count of items
     */
    public static final int DEFAULT_COUNT = 10;

    /**
     * ID of group which data will be loaded
     */
    public static final int GROUP_ID = -86529522;

    /**
     * For getting additional user's fields from server
     */
    public static final String DEFAULT_USER_FIELDS = "photo_100";

    /**
     * For getting additional member's fields
     */
    public static final String DEFAULT_MEMBERS_FIELDS = "name,photo_100";

    /**
     * For getting additional group fields
     */
    public static final String DEFAULT_GROUP_FIELDS =
            "status,description,site,links,contacts";
}
