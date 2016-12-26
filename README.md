### 关于

#### 关于 [AdapterDelegates](https://github.com/sockeqwe/AdapterDelegates)

[翻译版：逃离 adapter 的地狱—针对多个 View type 的组合实现方案](http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0810/3282.html)

[原版：JOE'S GREAT ADAPTER HELL ESCAPE](http://hannesdorfmann.com/android/adapter-delegates)

#### 关于 [ItemDelegate](https://github.com/YeungKC/ItemDelegate)

[ItemDelegate](https://github.com/YeungKC/ItemDelegate) 是一个从 [AdapterDelegates](https://github.com/sockeqwe/AdapterDelegates) fork 出来的项目，对于 [AdapterDelegates](https://github.com/sockeqwe/AdapterDelegates) 来说改动不大，但是思想上的理解和 [sockeqwe](https://github.com/sockeqwe) 有些不同。
我认为 [ItemDelegate](https://github.com/YeungKC/ItemDelegate) 是一种 **Item** 的代理，他将处理所有 **Item** 的事情，比如 `onCreateViewHolder()`，`onBindViewHolder()`，`getItemId()` 甚至 `getSpan()`，所以我一些名字改了，并且把包名改了，以免和 [AdapterDelegates](https://github.com/sockeqwe/AdapterDelegates) 冲突。

#### 具体改动
1. 为 `ItemDelegate` 增加了 `getItemId()`，这算是原作者 [sockeqwe](https://github.com/sockeqwe) 忽略的一个方法，主要配合 [setHasStableIds(boolean hasStableIds)](https://developer.android.com/reference/android/support/v7/widget/RecyclerView.Adapter.html#setHasStableIds(boolean)) 使用。
2. 为 `ItemDelegate` 增加了 `getSpanSize()` 方法，主要配合 [GridLayoutManager](https://developer.android.com/reference/android/support/v7/widget/GridLayoutManager.html) 使用，实现更为复杂的布局。

以上就是目前最为核心的改动，其他都是一些细节，如方法名，参数名。

### 如何依赖
1. 增加 [jitPack](https://jitpack.io/) 到你的 [repositories](https://docs.gradle.org/current/userguide/artifact_dependencies_tutorial.html#sec:repositories_tutorial)

   ```groovy
   allprojects {
     repositories {
       ...
       maven { url 'https://jitpack.io' }
     }
   }
   ```


2. 添加依赖关系

   ```groovy
   dependencies {
     compile 'com.github.YeungKC:ItemDelegate:4.4.0'
   }
   ```

### 基本使用
请查看 Demo

### 感谢
[sockeqwe](https://github.com/sockeqwe) 和他的 [AdapterDelegates](https://github.com/sockeqwe/AdapterDelegates)
