# CustomAdapter
将LinearLayout或GridLayout变成ListView的一款邪教Adapter
仿照BaseQuickAdapter的写法，只不过把界面更新item、删除item、添加item和item全部刷新的逻辑全部替换成了addView或removeView系列的方法

优点：可以直接将一个列表或网格嵌套在一个ScrollView或者RecyclerView的Item中，并且不会发生滑动冲突、高度计算异常或者一些复用性导致的BUG。

缺点：没有RecyclerView、GridView之类的复用性。如果加载数据量超过100条的话不推荐使用这种方式写列表布局

## 导入方式
`implementation 'com.suika:CustomAdapter:1.0.2'`

## 使用方法
和[BaseRecyclerViewAdapterHelper库](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)的使用方式一样,只不过基于LinearLayout或者GridLayout

使用案例(app 那个module就是demo)：

`
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val linearAdapter = MyLinearAdapter(R.layout.item_linear)
        linearAdapter.bindToAdaptedView(list_linear)

        val listData1 = arrayListOf("java", "python", "php", "lua", "c++", "swift")
        linearAdapter.setNewData(listData1)
        val listData2 = arrayListOf("Android", "AI", "server", "game", "framework", "ios")
        linearAdapter.addData(listData2)
        linearAdapter.loadMoreEnd()

        val gridAdapter = MyGridAdapter(this, grid_bottom)
        GridListHelper.setAdapter(grid_bottom,gridAdapter)
        val gridData1 = arrayListOf("泉こなた", "泉此方", "いずみこなた", "i zu mi ko na ta", "泉こなた", "泉こなた", "泉こなた", "泉こなた", "泉こなた", "泉こなた", "泉こなた", "泉こなた", "泉こなた", "泉こなた")
        gridAdapter.refreshData(gridData1)
        val gridData2 = arrayListOf("柊？")
        gridAdapter.loadMore(gridData2)
    }

    class MyLinearAdapter(layoutResId: Int) : BaseLinearAdapter<String, BCViewHolder>(layoutResId) {

        override fun convert(helper: BCViewHolder, item: String) {
            helper.setText(R.id.tv_content, item)
            helper.setText(R.id.tv_num, "No.${helper.adapterPosition}")

        }
    }

    class MyGridAdapter(context: Context, gridLayout: GridLayout) : GridListHelper.SimpleGridAdapter<MyGridViewHolder, String>(context, gridLayout) {
        override fun onCreateViewHolder(parent: GridLayout?, type: Int): MyGridViewHolder {
            return MyGridViewHolder(inflate(R.layout.item_grid))
        }

        @SuppressLint("SetTextI18n")
        override fun onSimpleBindView(holder: MyGridViewHolder?, t: String?, position: Int) {
            val tvName = holder!!.getView<TextView>(R.id.tv_name)
            tvName.text = "$t No.$position"
        }
    }

    class MyGridViewHolder(itemView: View) : GridListHelper.ViewHolder(itemView) {

        @Suppress("UNCHECKED_CAST")
        fun <T : View> getView(@IdRes viewId: Int): T {
            val view: View = itemView.findViewById(viewId)
            return view as T
        }
    }
}
`
