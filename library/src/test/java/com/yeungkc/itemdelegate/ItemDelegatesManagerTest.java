package com.yeungkc.itemdelegate;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import junit.framework.Assert;
import org.junit.Test;

/**
 * @author Hannes Dorfmann
 */
public class ItemDelegatesManagerTest {

  @Test public void addRemove() {

    ItemDelegate d1 = new ItemDelegate() {
      @Override public boolean isForViewType(Object items, int position) {
        return false;
      }

      @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return null;
      }

	    @Override
	    public void onBindViewHolder(@NonNull Object items, int position, @NonNull RecyclerView.ViewHolder holder, @Nullable List payloads) {

	    }
    };

    ItemDelegate d2 = new ItemDelegate() {
      @Override public boolean isForViewType(Object items, int position) {
        return false;
      }

      @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return null;
      }

	    @Override
	    public void onBindViewHolder(@NonNull Object items, int position, @NonNull RecyclerView.ViewHolder holder, @Nullable List payloads) {

	    }

    };

    ItemDelegatesManager manager = new ItemDelegatesManager();
    manager.addDelegate(d1);

    Assert.assertTrue(manager.delegates.get(0) == d1);
    Assert.assertEquals(0, manager.getViewType(d1));

    try {
      // replacing no allowed
      manager.addDelegate(0, d2);
      Assert.fail("Replacing delegate should fail");
    } catch (IllegalArgumentException e) {
      Assert.assertTrue(manager.delegates.get(0) == d1);
      Assert.assertEquals(0, manager.getViewType(d1));
      Assert.assertEquals(1, manager.delegates.size());
    }

    // replacing allowed
    manager.addDelegate(0, true, d2);
    Assert.assertTrue(manager.delegates.get(0) == d2);
    Assert.assertEquals(0, manager.getViewType(d2));
    Assert.assertEquals(-1, manager.getViewType(d1));

    // Remove a delegate should have no impact, because its already removed
    manager.removeDelegate(d1);
    Assert.assertEquals(-1, manager.getViewType(d1));
    Assert.assertTrue(manager.delegates.get(0) == d2);
    Assert.assertEquals(1, manager.delegates.size());

    // Should remove d2
    manager.removeDelegate(0);
    Assert.assertNull(manager.delegates.get(0));
    Assert.assertEquals(0, manager.delegates.size());
    Assert.assertEquals(-1, manager.getViewType(d2));
  }

  @Test public void isForViewType() {

    // 3 elements and each element has it's own viewtype and hence own delegate
    List<Object> items = Arrays.asList(new Object(), new Object(), new Object());
    SpyableItemDelegate<List<Object>> d0 = new SpyableItemDelegate<>(0);
    SpyableItemDelegate<List<Object>> d1 = new SpyableItemDelegate<>(1);
    SpyableItemDelegate<List<Object>> d2 = new SpyableItemDelegate<>(2);

    ItemDelegatesManager<List<Object>> manager = new ItemDelegatesManager<>();
    manager.addDelegate(d0);
    manager.addDelegate(d1);
    manager.addDelegate(d2);

    // Test first item
    int viewType = manager.getItemViewType(items, 0);
    Assert.assertEquals(viewType, 0);
    Assert.assertTrue(d0.isForViewTypeReturnedYes);
    Assert.assertFalse(d1.isForViewTypeReturnedYes);
    Assert.assertFalse(d2.isForViewTypeReturnedYes);
    resetDelegates(d0, d1, d2);

    // Test second item
    viewType = manager.getItemViewType(items, 1);
    Assert.assertEquals(viewType, 1);
    Assert.assertTrue(d1.isForViewTypeReturnedYes);
    Assert.assertFalse(d0.isForViewTypeReturnedYes);
    Assert.assertFalse(d2.isForViewTypeReturnedYes);
    resetDelegates(d0, d1, d2);

    // Test third item
    viewType = manager.getItemViewType(items, 2);
    Assert.assertEquals(viewType, 2);
    Assert.assertTrue(d2.isForViewTypeReturnedYes);
    Assert.assertFalse(d0.isForViewTypeReturnedYes);
    Assert.assertFalse(d1.isForViewTypeReturnedYes);
    resetDelegates(d0, d1, d2);
  }

  @Test public void onCreateViewHolder() {

    // 3 elements and each element has it's own viewtype and hence own delegate
    List<Object> items = Arrays.asList(new Object(), new Object(), new Object());
    SpyableItemDelegate<List<Object>> d0 = new SpyableItemDelegate<>(0);
    SpyableItemDelegate<List<Object>> d1 = new SpyableItemDelegate<>(1);
    SpyableItemDelegate<List<Object>> d2 = new SpyableItemDelegate<>(2);

    ItemDelegatesManager<List<Object>> manager = new ItemDelegatesManager<>();
    manager.addDelegate(d0);
    manager.addDelegate(d1);
    manager.addDelegate(d2);

    // Test first item
    RecyclerView.ViewHolder vh = manager.onCreateViewHolder(null, 0);
    Assert.assertSame(vh, d0.viewHolder);
    Assert.assertTrue(d0.onCreateViewHolderCalled);
    Assert.assertFalse(d1.onCreateViewHolderCalled);
    Assert.assertFalse(d2.onCreateViewHolderCalled);

    resetDelegates(d0, d1, d2);

    // Test second item
    vh = manager.onCreateViewHolder(null, 1);
    Assert.assertSame(vh, d1.viewHolder);
    Assert.assertTrue(d1.onCreateViewHolderCalled);
    Assert.assertFalse(d0.onCreateViewHolderCalled);
    Assert.assertFalse(d2.onCreateViewHolderCalled);

    resetDelegates(d0, d1, d2);

    // Test second item
    vh = manager.onCreateViewHolder(null, 2);
    Assert.assertSame(vh, d2.viewHolder);
    Assert.assertTrue(d2.onCreateViewHolderCalled);
    Assert.assertFalse(d0.onCreateViewHolderCalled);
    Assert.assertFalse(d1.onCreateViewHolderCalled);
  }

  @Test public void onBindViewHolder() {

    // 3 elements and each element has it's own viewtype and hence own delegate
    List<Object> items = Arrays.asList(new Object(), new Object(), new Object());
    SpyableItemDelegate<List<Object>> d0 = new SpyableItemDelegate<>(0);
    SpyableItemDelegate<List<Object>> d1 = new SpyableItemDelegate<>(1);
    SpyableItemDelegate<List<Object>> d2 = new SpyableItemDelegate<>(2);

    ItemDelegatesManager<List<Object>> manager = new ItemDelegatesManager<>();
    manager.addDelegate(d0);
    manager.addDelegate(d1);
    manager.addDelegate(d2);

    // Test first item
    manager.onBindViewHolder(items, 0, d0.viewHolder);
    Assert.assertTrue(d0.onBindViewHolderCalled);
    Assert.assertFalse(d1.onBindViewHolderCalled);
    Assert.assertFalse(d2.onBindViewHolderCalled);

    resetDelegates(d0, d1, d2);

    // Test second item
    manager.onBindViewHolder(items, 1, d1.viewHolder);
    Assert.assertTrue(d1.onBindViewHolderCalled);
    Assert.assertFalse(d0.onBindViewHolderCalled);
    Assert.assertFalse(d2.onBindViewHolderCalled);

    resetDelegates(d0, d1, d2);

    // Test third item
    manager.onBindViewHolder(items, 2, d2.viewHolder);
    Assert.assertTrue(d2.onBindViewHolderCalled);
    Assert.assertFalse(d1.onBindViewHolderCalled);
    Assert.assertFalse(d0.onBindViewHolderCalled);

    resetDelegates(d0, d1, d2);
  }

  @Test public void onViewDetachedFromWindow(){


    // 3 elements and each element has it's own viewtype and hence own delegate
    List<Object> items = Arrays.asList(new Object(), new Object(), new Object());
    SpyableItemDelegate<List<Object>> d0 = new SpyableItemDelegate<>(0);
    SpyableItemDelegate<List<Object>> d1 = new SpyableItemDelegate<>(1);
    SpyableItemDelegate<List<Object>> d2 = new SpyableItemDelegate<>(2);

    ItemDelegatesManager<List<Object>> manager = new ItemDelegatesManager<>();
    manager.addDelegate(d0);
    manager.addDelegate(d1);
    manager.addDelegate(d2);

    // Test first item
    manager.onViewDetachedFromWindow(d0.viewHolder);
    Assert.assertTrue(d0.onViewDetachedFromWindowCalled);
    Assert.assertFalse(d1.onViewDetachedFromWindowCalled);
    Assert.assertFalse(d2.onViewDetachedFromWindowCalled);

    resetDelegates(d0, d1, d2);

    // Test second item
    manager.onViewDetachedFromWindow(d1.viewHolder);
    Assert.assertTrue(d1.onViewDetachedFromWindowCalled);
    Assert.assertFalse(d0.onViewDetachedFromWindowCalled);
    Assert.assertFalse(d2.onViewDetachedFromWindowCalled);

    resetDelegates(d0, d1, d2);

    // Test third item
    manager.onViewDetachedFromWindow(d2.viewHolder);
    Assert.assertTrue(d2.onViewDetachedFromWindowCalled);
    Assert.assertFalse(d1.onViewDetachedFromWindowCalled);
    Assert.assertFalse(d0.onViewDetachedFromWindowCalled);

    resetDelegates(d0, d1, d2);
  }


  @Test public void onViewAttachedToWindow(){


    // 3 elements and each element has it's own viewtype and hence own delegate
    List<Object> items = Arrays.asList(new Object(), new Object(), new Object());
    SpyableItemDelegate<List<Object>> d0 = new SpyableItemDelegate<>(0);
    SpyableItemDelegate<List<Object>> d1 = new SpyableItemDelegate<>(1);
    SpyableItemDelegate<List<Object>> d2 = new SpyableItemDelegate<>(2);

    ItemDelegatesManager<List<Object>> manager = new ItemDelegatesManager<>();
    manager.addDelegate(d0);
    manager.addDelegate(d1);
    manager.addDelegate(d2);

    // Test first item
    manager.onViewAttachedToWindow(d0.viewHolder);
    Assert.assertTrue(d0.onViewAtachedToWindowCalled);
    Assert.assertFalse(d1.onViewAtachedToWindowCalled);
    Assert.assertFalse(d2.onViewAtachedToWindowCalled);

    resetDelegates(d0, d1, d2);

    // Test second item
    manager.onViewAttachedToWindow(d1.viewHolder);
    Assert.assertTrue(d1.onViewAtachedToWindowCalled);
    Assert.assertFalse(d0.onViewAtachedToWindowCalled);
    Assert.assertFalse(d2.onViewAtachedToWindowCalled);

    resetDelegates(d0, d1, d2);

    // Test third item
    manager.onViewAttachedToWindow(d2.viewHolder);
    Assert.assertTrue(d2.onViewAtachedToWindowCalled);
    Assert.assertFalse(d1.onViewAtachedToWindowCalled);
    Assert.assertFalse(d0.onViewAtachedToWindowCalled);

    resetDelegates(d0, d1, d2);
  }

  @Test public void onViewRecycled(){


    // 3 elements and each element has it's own viewtype and hence own delegate
    List<Object> items = Arrays.asList(new Object(), new Object(), new Object());
    SpyableItemDelegate<List<Object>> d0 = new SpyableItemDelegate<>(0);
    SpyableItemDelegate<List<Object>> d1 = new SpyableItemDelegate<>(1);
    SpyableItemDelegate<List<Object>> d2 = new SpyableItemDelegate<>(2);

    ItemDelegatesManager<List<Object>> manager = new ItemDelegatesManager<>();
    manager.addDelegate(d0);
    manager.addDelegate(d1);
    manager.addDelegate(d2);

    // Test first item
    manager.onViewRecycled(d0.viewHolder);
    Assert.assertTrue(d0.onViewRecycledCalled);
    Assert.assertFalse(d1.onViewRecycledCalled);
    Assert.assertFalse(d2.onViewRecycledCalled);

    resetDelegates(d0, d1, d2);

    // Test second item
    manager.onViewRecycled(d1.viewHolder);
    Assert.assertTrue(d1.onViewRecycledCalled);
    Assert.assertFalse(d0.onViewRecycledCalled);
    Assert.assertFalse(d2.onViewRecycledCalled);

    resetDelegates(d0, d1, d2);

    // Test third item
    manager.onViewRecycled(d2.viewHolder);
    Assert.assertTrue(d2.onViewRecycledCalled);
    Assert.assertFalse(d1.onViewRecycledCalled);
    Assert.assertFalse(d0.onViewRecycledCalled);

    resetDelegates(d0, d1, d2);
  }

  @Test public void onFailedToRecycleViewCalled(){


    // 3 elements and each element has it's own viewtype and hence own delegate
    List<Object> items = Arrays.asList(new Object(), new Object(), new Object());
    SpyableItemDelegate<List<Object>> d0 = new SpyableItemDelegate<>(0);
    SpyableItemDelegate<List<Object>> d1 = new SpyableItemDelegate<>(1);
    SpyableItemDelegate<List<Object>> d2 = new SpyableItemDelegate<>(2);

    ItemDelegatesManager<List<Object>> manager = new ItemDelegatesManager<>();
    manager.addDelegate(d0);
    manager.addDelegate(d1);
    manager.addDelegate(d2);

    // Test first item
    manager.onFailedToRecycleView(d0.viewHolder);
    Assert.assertTrue(d0.onFailedToRecycleViewCalled);
    Assert.assertFalse(d1.onFailedToRecycleViewCalled);
    Assert.assertFalse(d2.onFailedToRecycleViewCalled);

    resetDelegates(d0, d1, d2);

    // Test second item
    manager.onFailedToRecycleView(d1.viewHolder);
    Assert.assertTrue(d1.onFailedToRecycleViewCalled);
    Assert.assertFalse(d0.onFailedToRecycleViewCalled);
    Assert.assertFalse(d2.onFailedToRecycleViewCalled);

    resetDelegates(d0, d1, d2);

    // Test third item
    manager.onFailedToRecycleView(d2.viewHolder);
    Assert.assertTrue(d2.onFailedToRecycleViewCalled);
    Assert.assertFalse(d1.onFailedToRecycleViewCalled);
    Assert.assertFalse(d0.onFailedToRecycleViewCalled);

    resetDelegates(d0, d1, d2);
  }

  @Test public void allMethodsTest() {

    // 3 elements and each element has it's own viewtype and hence own delegate
    List<Object> items = Arrays.asList(new Object(), new Object(), new Object());
    SpyableItemDelegate<List<Object>> d0 = new SpyableItemDelegate<>(0);
    SpyableItemDelegate<List<Object>> d1 = new SpyableItemDelegate<>(1);
    SpyableItemDelegate<List<Object>> d2 = new SpyableItemDelegate<>(2);

    ItemDelegatesManager<List<Object>> manager = new ItemDelegatesManager<>();
    manager.addDelegate(d0);
    manager.addDelegate(d1);
    manager.addDelegate(d2);

    SpyableItemDelegate<List<Object>>[] delegates = new SpyableItemDelegate[] {
        d0, d1, d2
    };

    for (int i = 0; i < items.size(); i++) {
      SpyableItemDelegate<List<Object>> expectedDelegate = delegates[i];

      int viewType = manager.getItemViewType(items, i);

      // Test view type
      Assert.assertEquals(viewType, expectedDelegate.viewType);
      for (SpyableItemDelegate d : delegates) {
        if (d == expectedDelegate) {
          Assert.assertTrue(d.isForViewTypeReturnedYes);
        } else {
          Assert.assertFalse(d.isForViewTypeReturnedYes);
        }
      }

      // Test create viewHolder
      RecyclerView.ViewHolder vh = manager.onCreateViewHolder(null, viewType);
      Assert.assertSame(vh, expectedDelegate.viewHolder);

      for (SpyableItemDelegate d : delegates) {
        if (d == expectedDelegate) {
          Assert.assertTrue(d.onCreateViewHolderCalled);
        } else {
          Assert.assertFalse(d.onCreateViewHolderCalled);
        }
      }

      // Test bind viewHolder
      manager.onBindViewHolder(items, i, vh);
      for (SpyableItemDelegate d : delegates) {
        if (d == expectedDelegate) {
          Assert.assertTrue(d.onBindViewHolderCalled);
        } else {
          Assert.assertFalse(d.onBindViewHolderCalled);
        }
      }

      // Test onViewAtachedToWindow
      manager.onViewAttachedToWindow(vh);
      for (SpyableItemDelegate d : delegates) {
        if (d == expectedDelegate) {
          Assert.assertTrue(d.onViewAtachedToWindowCalled);
        } else {
          Assert.assertFalse(d.onViewAtachedToWindowCalled);
        }
      }


      // Test onViewDetachedFromWindow
      manager.onViewDetachedFromWindow(vh);
      for (SpyableItemDelegate d : delegates) {
        if (d == expectedDelegate) {
          Assert.assertTrue(d.onViewDetachedFromWindowCalled);
        } else {
          Assert.assertFalse(d.onViewDetachedFromWindowCalled);
        }
      }


      // Test onViewRecycled
      manager.onViewRecycled(vh);
      for (SpyableItemDelegate d : delegates) {
        if (d == expectedDelegate) {
          Assert.assertTrue(d.onViewRecycledCalled);
        } else {
          Assert.assertFalse(d.onViewRecycledCalled);
        }
      }

      // Test onViewRecycled
      manager.onFailedToRecycleView(vh);
      for (SpyableItemDelegate d : delegates) {
        if (d == expectedDelegate) {
          Assert.assertTrue(d.onFailedToRecycleViewCalled);
        } else {
          Assert.assertFalse(d.onFailedToRecycleViewCalled);
        }
      }

      resetDelegates(delegates);
    }
  }

  private void resetDelegates(SpyableItemDelegate<?>... delegates) {
    for (SpyableItemDelegate d : delegates) {
      d.reset();
    }
  }

  @Test(expected = NullPointerException.class) public void testNoDelegates() {
    ItemDelegatesManager<Object> delegatesManager = new ItemDelegatesManager<>();
    delegatesManager.onCreateViewHolder(null, 1); // No Delegate --> throw Exception
  }

  @Test public void testUnknownDelegate() {
    ItemDelegatesManager<Object> delegatesManager = new ItemDelegatesManager<>();
    delegatesManager.addDelegate(new SpyableItemDelegate<Object>(0));

    delegatesManager.onCreateViewHolder(null, 0); // NO exception
    try {
      delegatesManager.onCreateViewHolder(null,
          1); // There is no delegates manager for ViewType == 1,
      Assert.fail(
          "Exception should be thrown because no Delegate for given ViewType is registered");
    } catch (NullPointerException e) {
      // No delegate for view type 1 registered --> Nullpointer exception will be thrown
    }
  }

  @Test public void fallbackUnknownDelegate() {

    int fallbackViewType = Integer.MAX_VALUE - 1;
    int itemPosition = 1;
    List<Object> items = new ArrayList<>();
    items.add(new Object());

    ItemDelegatesManager<List<Object>> delegatesManager = new ItemDelegatesManager<>();
    SpyableItemDelegate fallbackDelegate = new SpyableItemDelegate(fallbackViewType);

    SpyableItemDelegate<List<Object>> otherDelegate = new SpyableItemDelegate<>(0);
    delegatesManager.setFallbackDelegate(fallbackDelegate);
    delegatesManager.addDelegate(otherDelegate);

    RecyclerView.ViewHolder vh = delegatesManager.onCreateViewHolder(null,
        fallbackViewType); // There is no delegates manager for ViewType == 1

    Assert.assertSame(vh, fallbackDelegate.viewHolder);
    Assert.assertTrue(fallbackDelegate.onCreateViewHolderCalled);
    Assert.assertFalse(otherDelegate.onCreateViewHolderCalled);

    // Test bind viewHolder
    delegatesManager.onBindViewHolder(items, itemPosition, vh);
    Assert.assertTrue(fallbackDelegate.onBindViewHolderCalled);
    Assert.assertFalse(otherDelegate.onBindViewHolderCalled);
  }

  @Test public void viewTypeInConflictWithFallbackDelegate() {
    try {
      ItemDelegatesManager<List> manager = new ItemDelegatesManager<>();
      manager.addDelegate(ItemDelegatesManager.FALLBACK_DELEGATE_VIEW_TYPE,
          new SpyableItemDelegate<List>(0));
      Assert.fail(
          "An exception should be thrown because view type integer is already reserved for fallback delegate");
    } catch (IllegalArgumentException e) {
      Assert.assertEquals("The view type = "
              + ItemDelegatesManager.FALLBACK_DELEGATE_VIEW_TYPE
              + " is reserved for fallback adapter delegate (see setFallbackDelegate() ). Please use another view type.",
          e.getMessage());
    }
  }

  @Test public void getViewType() {
    ItemDelegatesManager<List> manager = new ItemDelegatesManager<>();

    try {
      manager.getViewType(null);
      Assert.fail("Nullpointer Exception expected");
    } catch (NullPointerException e) {
    }

    SpyableItemDelegate<List> delegate1 = new SpyableItemDelegate<>(0);
    SpyableItemDelegate<List> delegate2 = new SpyableItemDelegate<>(1);
    Assert.assertEquals(-1, manager.getViewType(delegate1));
    Assert.assertEquals(-1, manager.getViewType(delegate2));

    manager.addDelegate(delegate1);
    manager.addDelegate(delegate2);

    Assert.assertEquals(0, manager.getViewType(delegate1));
    Assert.assertEquals(1, manager.getViewType(delegate2));

    SpyableItemDelegate<List> delegate3 = new SpyableItemDelegate<>(2);
    SpyableItemDelegate<List> delegate4 = new SpyableItemDelegate<>(3);

    manager.addDelegate(4, delegate4);
    Assert.assertEquals(4, manager.getViewType(delegate4));

    manager.addDelegate(delegate3);
    Assert.assertEquals(3, manager.getViewType(delegate3));
  }

  @Test public void numberOverflow() {
    ItemDelegatesManager<List> manager = new ItemDelegatesManager<>();
    SpyableItemDelegate<List> delegate1 = new SpyableItemDelegate<>(0);

    manager.addDelegate(Integer.MAX_VALUE + 1, delegate1);
    Assert.assertEquals(Integer.MAX_VALUE + 1, manager.getViewType(delegate1));
  }

  @Test public void delegateForViewType() {
    ItemDelegatesManager<List> manager = new ItemDelegatesManager<>();
    SpyableItemDelegate<List> delegate1 = new SpyableItemDelegate<>(0);
    SpyableItemDelegate<List> delegate2 = new SpyableItemDelegate<>(1);

    SpyableItemDelegate<List> fallbackDelegate = new SpyableItemDelegate<>(3);
    manager.setFallbackDelegate(fallbackDelegate);
    Assert.assertEquals(fallbackDelegate, manager.getDelegateForViewType(1));

    manager.addDelegate(delegate1);
    manager.addDelegate(delegate2);
    Assert.assertEquals(delegate1, manager.getDelegateForViewType(0));
    Assert.assertEquals(delegate2, manager.getDelegateForViewType(1));
  }

  @Test public void delegateForViewTypeNoFallback() {
    ItemDelegatesManager<List> manager = new ItemDelegatesManager<>();
    SpyableItemDelegate<List> delegate1 = new SpyableItemDelegate<>(0);
    SpyableItemDelegate<List> delegate2 = new SpyableItemDelegate<>(1);

    manager.addDelegate(delegate1);
    manager.addDelegate(delegate2);
    Assert.assertEquals(delegate1, manager.getDelegateForViewType(0));
    Assert.assertEquals(delegate2, manager.getDelegateForViewType(1));
    Assert.assertNull(manager.getDelegateForViewType(2));
  }

  @Test public void setGetFallbackDelegate() {
    ItemDelegatesManager<List> manager = new ItemDelegatesManager<>();
    Assert.assertNull(manager.getFallbackDelegate());
    SpyableItemDelegate<List> fallbackDelegate = new SpyableItemDelegate<>(3);
    manager.setFallbackDelegate(fallbackDelegate);
    Assert.assertEquals(fallbackDelegate, manager.getFallbackDelegate());
  }
}
