package com.yeungkc.itemdelegate;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hannes Dorfmann
 */
public class AbsListItemItemDelegateTest {

  @Test public void invokeMethods() {

    List<Animal> dataSets = new ArrayList<>();
    dataSets.add(new Cat());

    CatAbsListItemItemDelegate delegate = new CatAbsListItemItemDelegate();

    delegate.isForViewType(dataSets, 0);
    Assert.assertTrue(delegate.isForViewTypeCalled);

    ViewGroup parent = Mockito.mock(ViewGroup.class);
    CatViewHolder vh = delegate.onCreateViewHolder(parent);
    Assert.assertTrue(delegate.onCreateViewHolderCalled);

    delegate.onBindViewHolder(dataSets, 0, vh, new ArrayList<Object>());
    Assert.assertTrue(delegate.onBindViewHolderCalled);


  }

  interface Animal {
  }

  class Cat implements Animal {
  }

  class CatViewHolder extends RecyclerView.ViewHolder {
    public CatViewHolder(View itemView) {
      super(itemView);
    }
  }

  class CatAbsListItemItemDelegate
      extends AbsListItemItemDelegate<Cat, Animal, CatViewHolder> {
    public boolean isForViewTypeCalled = false;
    public boolean onCreateViewHolderCalled = false;
    public boolean onBindViewHolderCalled = false;
    public boolean onViewDetachedFromWindow = false;


    @Override
    protected boolean isForViewType(@NonNull Animal data, @NonNull List<Animal> dataSets, int position) {
      isForViewTypeCalled = true;
      return false;
    }

    @NonNull @Override public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
      onCreateViewHolderCalled = true;
      return new CatViewHolder(Mockito.mock(View.class));
    }

    @Override
    protected void onBindViewHolder(@NonNull Cat data, @NonNull CatViewHolder viewHolder, @NonNull List payloads) {
      onBindViewHolderCalled = true;
    }

    @Override public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
      super.onViewDetachedFromWindow(holder);
      onViewDetachedFromWindow = true;
    }
  }
}
