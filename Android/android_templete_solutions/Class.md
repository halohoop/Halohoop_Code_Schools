# Halohoop_Code_Schools

# Templete Class

## 001.自定义控件高效SurfaceView模板，保持模板不动，然后直接和普通控件在onDraw等方法开发就好了
* [MySurfaceView.java](./classes/MySurfaceView.java)

## 002.自定义控件对话框
* [SimpleCustomDialog.java](./classes/SimpleCustomDialog.java)

## 003.禁止滑动的ViewPager
* [NoScrollViewPager.java](./classes/NoScrollViewPager.java)

## 004.RecyclerView分隔线
* [RecyclerViewDivider](./classes/RecyclerViewDivider/)
* 使用:

        ItemDecoration mDecoration1 = new SpaceDividerDecoration(10);//just space
        ItemDecoration mDecoration2 = new VerticalDividerDecoration.Builder
                            ().showStart(true).showEnd(true).paddingLeft(50).paddingRight(20)
                            .color(Color.BLUE).size(10).build();
                    rcv.removeItemDecoration(mDecoration1);
        ItemDecoration mDecoration3 = new VerticalDividerDecoration
                            .Builder().showStart(true).showEnd(true).paddingLeft(100)
                            .paddingRight(100).color(Color.parseColor("#33ff0000")).size(50)
                            .build();
        RecyclerView实例.addItemDecoration(mDecoration1);
        RecyclerView实例.addItemDecoration(mDecoration2);
        RecyclerView实例.addItemDecoration(mDecoration3);

## 005.Android通话模块的动画工具类 AnimUtils.java
* [AnimUtils.java](./classes/AnimUtils.java)
