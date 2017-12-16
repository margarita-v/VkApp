package com.margarita.vk_app.common.manager;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;

import com.margarita.vk_app.ui.activity.BaseActivity;
import com.margarita.vk_app.ui.fragment.base.BaseFragment;

import java.util.Stack;

public class VkFragmentManager {

    // Minimal count of fragments in back stack
    private static final int EMPTY_FRAGMENT_STACK_SIZE = 1;

    private Stack<BaseFragment> fragmentStack = new Stack<>();

    private BaseFragment currentFragment;

    /**
     * Set fragment for a base activity's content
     * @param activity Activity which will contain a fragment
     * @param fragment Fragment which will be shown
     * @param containerId ID of container for a fragment
     */
    public void setFragment(BaseActivity activity,
                            BaseFragment fragment,
                            @IdRes int containerId) {

        if (activity != null &&
                !activity.isFinishing() &&
                !isAlreadyContains(fragment)) {

            FragmentTransaction fragmentTransaction = createAddTransaction(
                    activity, fragment, false);
            fragmentTransaction.replace(containerId, fragment);
            commitAddTransaction(activity, fragment, fragmentTransaction, false);
        }
    }

    /**
     * Add fragment at the top of the base fragment
     * @param activity Activity which contains all fragments
     * @param fragment Fragment which will be added
     * @param containerId ID of container for a fragment
     */
    public void addFragment(BaseActivity activity,
                            BaseFragment fragment,
                            @IdRes int containerId) {

        if (activity != null &&
                !activity.isFinishing() &&
                !isAlreadyContains(fragment)) {

            FragmentTransaction fragmentTransaction = createAddTransaction(
                    activity, fragment, true);
            fragmentTransaction.add(containerId, fragment);
            commitAddTransaction(activity, fragment, fragmentTransaction, true);
        }
    }

    /**
     * Remove the current fragment
     * @param activity Activity which contains the fragment
     * @return True if the fragment was removed
     */
    public boolean removeCurrentFragment(BaseActivity activity) {
        return removeFragment(activity, currentFragment);
    }

    /**
     * Remove the fragment
     * @param activity Activity which contains the fragment
     * @param fragment Fragment which will be removed
     * @return True if the fragment was removed
     */
    public boolean removeFragment(BaseActivity activity, BaseFragment fragment) {

        // Stack must contain at least one fragment!
        boolean canRemove = fragment != null &&
                fragmentStack.size() > EMPTY_FRAGMENT_STACK_SIZE;

        if (canRemove) {
            FragmentTransaction transaction = activity
                    .getSupportFragmentManager()
                    .beginTransaction();

            fragmentStack.pop();
            currentFragment = fragmentStack.lastElement();

            transaction.remove(fragment);
            commitTransaction(activity, transaction);
        }
        return canRemove;
    }

    /**
     * Create a transaction of fragment addition
     * @param activity Activity which will show a new fragment
     * @param fragment Fragment which will be added
     * @param addToBackStack True if fragment will be saved to back stack
     * @return FragmentTransaction for a fragment addition
     */
    private FragmentTransaction createAddTransaction(BaseActivity activity,
                                                   BaseFragment fragment,
                                                   boolean addToBackStack) {

        FragmentTransaction fragmentTransaction = activity
                .getSupportFragmentManager()
                .beginTransaction();
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getTag());
        }
        return fragmentTransaction;
    }

    /**
     * Commit of transaction of fragment addition
     * @param activity Activity which will show a new fragment
     * @param fragment Fragment which will be added
     * @param transaction Transaction which will be performed
     * @param addToBackStack True if fragment will be saved to back stack
     */
    private void commitAddTransaction(BaseActivity activity,
                                      BaseFragment fragment,
                                      FragmentTransaction transaction,
                                      boolean addToBackStack) {
        if (transaction != null) {
            currentFragment = fragment;

            if (!addToBackStack) {
                fragmentStack.clear();
                //fragmentStack = new Stack<>();
            }
            fragmentStack.add(fragment);
            commitTransaction(activity, transaction);
        }
    }

    /**
     * Commit of any transaction
     * @param activity Activity which will show a new fragment
     * @param transaction Transaction which will be committed
     */
    private void commitTransaction(BaseActivity activity, FragmentTransaction transaction) {
        transaction.commit();
        activity.setFragmentOnScreen(currentFragment);
    }

    /**
     * Check if stack contains fragment
     * @param fragment Fragment which will be checked
     * @return True if the current fragments's class name is equal to the fragment's name
     */
    public boolean isAlreadyContains(BaseFragment fragment) {

        return fragment != null && currentFragment != null &&
                currentFragment.getClass().getName()
                        .equals(fragment.getClass().getName());
    }
}
