# CustomAdapter
将LinearLayout或GridLayout变成ListView的一款邪教Adapter
仿照BaseQuickAdapter的写法，只不过把界面更新item、删除item、添加item和item全部刷新的逻辑全部替换成了addView或removeView系列的方法

## 导入方式
`implementation 'com.suika:CustomAdapter:1.0.2'`

## 使用方法
和[BaseRecyclerViewAdapterHelper库](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)的使用方式一样,只不过基于LinearLayout或者GridLayout

Adapter:
`
public class HBCommentAdapter2 extends BaseLinearAdapter<CommentInfo.ListBean, BaseCustomViewHolder> {
    public HBCommentAdapter2(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseCustomViewHolder helper, CommentInfo.ListBean item) {
        helper.setText(R.id.tv_nickname, item.getNickname())
                .setText(R.id.tv_comment_content, item.getContent())
                .setGone(R.id.tv_comment_time, false)
                .setGone(R.id.tv_praise_num, false);
        G.load(helper.getView(R.id.iv_avatar), item.getAvatar());
    }
}
`
Activity or Fragment:
`
    protected BaseCustomAdapter mAdapter;
    protected LinearLayout mParent;

    private void init() {
        mAdapter = new HBCommentAdapter2(R.layout.item_hb_comment)
        mAdapter.bindToAdaptedView(mParent);
    }
`
刷新或加载第一页：
`
        mAdapter.setNewData(newData);
`
上拉加载：
`
        mAdapter.addData(newData);
        if (mPage == mTotalPage) {//如果拉到最后一页就上拉结束否则执行上拉完成
            mAdapter.loadMoreEnd();
        } else {
            mAdapter.loadMoreComplete();
        }
`



