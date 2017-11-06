package com.margarita.vk_app.rest.model.response;

import com.margarita.vk_app.models.Owner;
import com.margarita.vk_app.models.common.Group;
import com.margarita.vk_app.models.common.Profile;

import java.util.ArrayList;
import java.util.List;

public class ItemWithSendersResponse<T> extends BaseItemResponse<T> {

    private List<Profile> profiles = new ArrayList<>();

    private List<Group> groups = new ArrayList<>();

    public List<Profile> getProfiles() {
        return profiles;
    }

    public List<Group> getGroups() {
        return groups;
    }

    /**
     * Get list of all senders
     * @return List of all item's senders
     */
    private List<Owner> getAllSenders() {
        List<Owner> result = new ArrayList<>();
        result.addAll(profiles);
        result.addAll(groups);
        return result;
    }

    /**
     * Get sender by id
     * @param id ID of the wanted sender
     * @return Sender with the wanted ID
     */
    public Owner getSender(int id) {
        for (Owner owner: getAllSenders()) {
            if (owner.getId() == Math.abs(id))
                return owner;
        }
        return null;
    }
}
