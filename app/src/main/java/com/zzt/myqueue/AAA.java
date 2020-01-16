package com.zzt.myqueue;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.adapter.FragmentViewHolder;

import java.util.List;

/**
 * @author: zeting
 * @date: 2020/1/16
 */

//public class AAA {
//
//
//    FragmentStateAdapter adapter2 = new FragmentStateAdapter(ActivityViewPagerAdd.this) {
//
//        @Override
//        public long getItemId(int position) {
//            /** 这个地方，需要重写 ,让所有数据都会有一个唯一的 id  */
//            return itemList.get(position).getPage();
//        }
//
//        public void onBindViewHolder(@NonNull androidx.viewpager2.adapter.FragmentViewHolder holder,
//                                     int position, @NonNull List<Object> payloads) {
//            super.onBindViewHolder(holder, position, payloads);
//        }
//
//    };
//
//    public final class FragmentViewHolder extends RecyclerView.ViewHolder {
//        private FragmentViewHolder(@NonNull FrameLayout container) {
//            super(container);
//        }
//
//        @NonNull static androidx.viewpager2.adapter.FragmentViewHolder create(@NonNull ViewGroup parent) {
//            FrameLayout container = new FrameLayout(parent.getContext());
//            container.setLayoutParams(
//                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                            ViewGroup.LayoutParams.MATCH_PARENT));
//            container.setId(ViewCompat.generateViewId());
//            container.setSaveEnabled(false);
//            return new androidx.viewpager2.adapter.FragmentViewHolder(container);
//        }
//
//        /** ViewHodel 对应的View */
//        @NonNull FrameLayout getContainer() {
//            return (FrameLayout) itemView;
//        }
//    }
//
//    public final void onBindViewHolder(final @NonNull FragmentViewHolder holder, int position) {
//        /** 返回 ViewHodel itemId */
//        final long itemId = holder.getItemId();
//        /** 返回的 ViewHodel 对应的View id */
//        final int viewHolderId = holder.getContainer().getId();
//        /** 根据  ViewHodel 对应的View id 拿到缓存中保存的Viewid*/
//        final Long boundItemId = itemForViewHolder(viewHolderId); // item currently bound to the VH
//        /** 比较View id 和 Viewhodel id */
//        if (boundItemId != null && boundItemId != itemId) {
//            removeFragment(boundItemId);
//            mItemIdToViewHolder.remove(boundItemId);
//        }
//        /** 将View id 保存到缓存中 */
//        mItemIdToViewHolder.put(itemId, viewHolderId); // this might overwrite an existing entry
//        /** 根据当前position 确定当前fragment是否需要创建还是复用 */
//        ensureFragment(position);
//
//        /**  ViewHodel 对应的View 添加到window中 */
//        final FrameLayout container = holder.getContainer();
//        if (ViewCompat.isAttachedToWindow(container)) {
//            if (container.getParent() != null) {
//                throw new IllegalStateException("Design assumption violated.");
//            }
//            container.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
//                @Override
//                public void onLayoutChange(View v, int left, int top, int right, int bottom,
//                                           int oldLeft, int oldTop, int oldRight, int oldBottom) {
//                    if (container.getParent() != null) {
//                        container.removeOnLayoutChangeListener(this);
//                        placeFragmentInViewHolder(holder);
//                    }
//                }
//            });
//        }
//        /** gc */
//        gcFragments();
//    }
//
//    private void ensureFragment(int position) {
//        long itemId = getItemId(position);
//        /** 比较 ViewHodel id 是否已经在缓存中 */
//        if (!mFragments.containsKey(itemId)) {
//            /** 创建 fragment */
//            Fragment newFragment = createFragment(position);
//            newFragment.setInitialSavedState(mSavedStates.get(itemId));
//            mFragments.put(itemId, newFragment);
//        }
//    }
//}
