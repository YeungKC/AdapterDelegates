package com.yeungkc.itemdelegate;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Objects;

/**
 * @author Hannes Dorfmann
 */
public class ListDelegationAdapterTest {

  @Test public void delegatesManagerNull() {
    try {
      ListDelegationAdapter<List<Object>> adapter = new ListDelegationAdapter<List<Object>>(null) {
        @Override public int getItemCount() {
          return 0;
        }
      };
      Assert.fail("Expected NullPointerException");
    } catch (NullPointerException e) {
      Assert.assertEquals("ItemDelegatesManager is null", e.getMessage());
    }
  }

  @Test public void checkDelegatesManagerInstance() {

    final ItemDelegatesManager<List<Object>> manager = new ItemDelegatesManager<>();

    ListDelegationAdapter<List<Object>> adapter = new ListDelegationAdapter<List<Object>>(manager) {
      @Override public int getItemCount() {
        // Hacky but does the job
        Assert.assertTrue(manager == this.delegatesManager);
        return 0;
      }
    };

    adapter.getItemCount();
  }

  @Test public void checkNewAdapterDelegatesManagerInstanceNotNull() {

    // Empty constructor should produce a new instance of AdapterDelegatesManager
    ListDelegationAdapter<List<Object>> adapter = new ListDelegationAdapter<List<Object>>() {
      @Override public int getItemCount() {
        // Hacky but does the job
        Assert.assertNotNull(this.delegatesManager);
        return 0;
      }
    };

    adapter.getItemCount();
  }

}
