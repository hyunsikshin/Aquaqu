package com.nexters.aquaqu.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nexters.aquaqu.R;
import com.nexters.aquaqu.activity.ArticleActivity;
import com.nexters.aquaqu.config.CConstant;
import com.nexters.aquaqu.helper.OceanHelper;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by HyunSik on 2015-10-25.
 */
public class ThreeFragment extends Fragment {

    private Source source;
    OceanHelper helper = new OceanHelper();
    TextView info, weather, temp, wind, sea, water, height;

    private static final String TAG = "ThreeFragment";

    public static ThreeFragment newInstance() {
        ThreeFragment fragment = new ThreeFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new GetHtml(CConstant.SUNG_OSERVE).execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.l_activity_viewpager, container, false);
        rootView.setTag(TAG);

        TextView name = (TextView) rootView.findViewById(R.id.pager01_name);
        info = (TextView) rootView.findViewById(R.id.pager01_desc);
        weather = (TextView) rootView.findViewById(R.id.aqu_weather01);
        temp = (TextView) rootView.findViewById(R.id.aqu_temp01);
        wind = (TextView) rootView.findViewById(R.id.aqu_wind01);
        sea = (TextView) rootView.findViewById(R.id.aqu_sea01);
        water = (TextView) rootView.findViewById(R.id.aqu_water01);
        height = (TextView) rootView.findViewById(R.id.aqu_height01);

        name.setText(getResources().getString(R.string.three));
        info.setText("만장굴과 가까이 위치한 코발트빛 바다가 있는 해수욕장");

        rootView.findViewById(R.id.pager_news_item1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ArticleActivity.class);
                intent.putExtra("img_", "@drawable/article1");
                getActivity().startActivity(intent);
            }
        });

        rootView.findViewById(R.id.pager_news_item2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ArticleActivity.class);
                intent.putExtra("img_", "@drawable/article2");
                getActivity().startActivity(intent);
            }
        });

        rootView.findViewById(R.id.pager_news_item3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ArticleActivity.class);
                intent.putExtra("img_", "@drawable/article3");
                getActivity().startActivity(intent);
            }
        });

        return rootView;
    }


    private class GetHtml extends AsyncTask<String, String, Void> {

        String url;

        GetHtml(String url) {
            this.url = url;
        }

        @Override
        protected Void doInBackground(String... strings) {

            try {
                URL URL = new URL(url);
                InputStream html = URL.openStream();
                source = new Source(new InputStreamReader(html, "utf-8"));
                source.fullSequentialParse();


                Element BBS_DIV = (Element) source.getAllElements(HTMLElementName.DIV).get(31); //BBSlocate 번째 의 DIV 를 모두 가져온다.
                Element BBS_UL = (Element) BBS_DIV.getAllElements(HTMLElementName.UL).get(0); //ul 접속

                Element BBS_ITEM = (Element) BBS_UL.getAllElements(HTMLElementName.LI).get(2); //데이터가 있는 li에 접속
                Element BBS_SPAN = (Element) BBS_ITEM.getAllElements(HTMLElementName.SPAN).get(0);
                String BBS_SPAN_ITEM = BBS_SPAN.getContent().toString();


                Element BBS_SPAN1 = (Element) BBS_ITEM.getAllElements(HTMLElementName.SPAN).get(1);
                String BBS_SAPN_ITEM1 = BBS_SPAN1.getContent().toString();

                helper.setW_temp(BBS_SPAN_ITEM.trim() + BBS_SAPN_ITEM1.trim());


                Element BBS_DIV1 = (Element) source.getAllElements(HTMLElementName.DIV).get(33); //BBSlocate 번째 의 DIV 를 모두 가져온다.
                Element BBS_UL1 = (Element) BBS_DIV1.getAllElements(HTMLElementName.UL).get(0); //ul 접속

                Element BBS_ITEM1 = (Element) BBS_UL1.getAllElements(HTMLElementName.LI).get(2); //데이터가 있는 li에 접속
                Element BBS_SPAN0 = (Element) BBS_ITEM1.getAllElements(HTMLElementName.SPAN).get(0);
                String BBS_SPAN_ITEM11 = BBS_SPAN0.getContent().toString();


                Element BBS_SPAN11 = (Element) BBS_ITEM1.getAllElements(HTMLElementName.SPAN).get(1);
                String BBS_SAPN_ITEM11 = BBS_SPAN11.getContent().toString();

                helper.setA_temp(BBS_SPAN_ITEM11.trim() + BBS_SAPN_ITEM11.trim());

                Element BBS_DIV2 = (Element) source.getAllElements(HTMLElementName.DIV).get(35); //BBSlocate 번째 의 DIV 를 모두 가져온다.
                Element BBS_UL2 = (Element) BBS_DIV2.getAllElements(HTMLElementName.UL).get(0); //ul 접속

                Element BBS_ITEM2 = (Element) BBS_UL2.getAllElements(HTMLElementName.LI).get(2); //데이터가 있는 li에 접속
                Element SPAN1 = (Element) BBS_ITEM2.getAllElements(HTMLElementName.SPAN).get(1);
                Element SPAN2 = (Element) BBS_ITEM2.getAllElements(HTMLElementName.SPAN).get(2);
                Element SPAN3 = (Element) BBS_ITEM2.getAllElements(HTMLElementName.SPAN).get(3);
                helper.setWind(SPAN1.getContent().toString().trim() + SPAN2.getContent().toString().trim() + SPAN3.getContent().toString().trim());


                Element BBS_DIV21 = (Element) source.getAllElements(HTMLElementName.DIV).get(22); //BBSlocate 번째 의 DIV 를 모두 가져온다.
                Element BBS_UL21 = (Element) BBS_DIV21.getAllElements(HTMLElementName.UL).get(0); //ul 접속

                Element BBS_1 = (Element) BBS_UL21.getAllElements(HTMLElementName.LI).get(1); //데이터가 있는 li에 접속
                Element BBS_2 = (Element) BBS_UL21.getAllElements(HTMLElementName.LI).get(2); //데이터가 있는 li에 접속

                helper.setTide(BBS_1.getContent().toString().trim() + BBS_2.getContent().toString().trim());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            sea.setText(helper.getTide());
            height.setText("0.45m");
            weather.setText("17 ℃");
            temp.setText(helper.getA_temp());
            water.setText(helper.getW_temp());
            wind.setText(helper.getWind());

            super.onPostExecute(aVoid);
        }
    }
}
