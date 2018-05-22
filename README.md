# CustomAdapter
将LinearLayout或GridLayout变成ListView的一款邪教Adapter
仿照BaseQuickAdapter的写法，只不过把界面更新item、删除item、添加item和item全部刷新的逻辑全部替换成了addView或removeView系列的方法

主要的好处就是可以直接将一个列表或网格嵌套在一个ScrollView或者RecyclerView的Item中，并且不会发生滑动冲突。
缺点就是没有RecyclerView、GridView之类的复用性。如果加载数据量超过100条的话不推荐使用这种方式写列表布局

## 导入方式
`implementation 'com.suika:CustomAdapter:1.0.2'`

## 使用方法
和[BaseRecyclerViewAdapterHelper库](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)的使用方式一样,只不过基于LinearLayout或者GridLayout

Adapter:

`待ってください`

Activity or Fragment:

`待ってください`

刷新或加载第一页：

`待ってください`

上拉加载：

`待ってください`



