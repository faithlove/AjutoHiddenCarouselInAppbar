package gank.douya.com.ajutohiddencarouselinappbar;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewScrollingActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> mLists = new ArrayList();
    private List<String> mImageUrlList = new ArrayList<>();
    private Banner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        /**
         * 生产数据
         * 正确写法不应该在onCreate方法里写
         */
        for (int i = 0; i < 15; i++) {
            mLists.add(i + "");
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setAdapter(new MyRecyclerViewAdapter(mLists));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        mImageUrlList.add("http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-17-17332809_1277469728986540_3201752429582352384_n.jpg");
        mImageUrlList.add("http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-16-17333221_108837802984751_2789267558635667456_n.jpg");
        mImageUrlList.add("http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-15-17126482_115753765623699_4225645012014071808_n.jpg");

        banner = (Banner) findViewById(R.id.banner);
        banner.setImages(mImageUrlList)
                .setImageLoader(new MyGlideImageLoader())
                .start();
    }

    public class MyRecyclerViewAdapter extends RecyclerView.Adapter {

        private final List<String> mDatas;


        public MyRecyclerViewAdapter(List<String> mDatas) {

            this.mDatas = mDatas;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            MyViewHolder mHolder = (MyViewHolder) holder;
            mHolder.mTv.setText(mDatas.get(position));
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            private final TextView mTv;
            private final View mView;

            public MyViewHolder(View itemView) {
                super(itemView);
                mView = itemView;
                mTv = (TextView) itemView.findViewById(R.id.tv);
            }
        }
    }
}
