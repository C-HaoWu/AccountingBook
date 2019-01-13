package com.example.hoa.accountingbook;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hoa.accountingbook.dummy.DummyContent;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ChartFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ChartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChartFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ChartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChartFragment newInstance(String param1, String param2) {
        ChartFragment fragment = new ChartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        // Inflate the layout for this fragment

        BarChart chart_bar = (BarChart)view.findViewById(R.id.chart_bar);
        chart_bar.setData(getBarData());
        chart_bar.setDescription(null);
        configChartAxis(chart_bar);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed() {
        if (mListener != null) {
            mListener.onFragmentInteraction();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction();
    }


    private BarData getBarData(){
        BarDataSet dataSetA = new BarDataSet(getChartData(), null);
        //設定顏色
        dataSetA.setColors(getChartColors());
        dataSetA.setValueTextSize(14f);

        return new BarData(dataSetA);
    }

    // TODO: 從資料庫抓資料
    private List<BarEntry> getChartData(){
        int DATA_COUNT = 8;
        List<BarEntry> chartData = new ArrayList<>();
//        List<Integer> costlist = DataTable.Category_Cost();
        List<Integer> costlist = new ArrayList<>();
        for(int i = 0; i < costlist.size(); i++){
            chartData.add(new BarEntry(i, costlist.get(i)));
        }
        return chartData;
    }

    private List<String> getLabels(){
        List<String> chartLabels = new ArrayList<>();
        String[] lables = new String[]{"食物", "交通", "衣服", "生活", "育樂", "3C", "醫藥", "其他"};
        for(int i=0;i<lables.length;i++){
            chartLabels.add(lables[i]);
        }
        return chartLabels;
    }

    private void configChartAxis(BarChart chart_bar){
        XAxis xAxis = chart_bar.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(formatter);
        xAxis.setTextSize(14f);

        YAxis LeftYAxis = chart_bar.getAxisLeft();
        LeftYAxis.setDrawGridLines(false);
        LeftYAxis.setEnabled(false);   //不顯示左側
        YAxis RightYAxis = chart_bar.getAxisRight();
        RightYAxis.setEnabled(false);   //不顯示右側
    }

    private int[] getChartColors() {
        int[] colors = new int[]{
                getResourceColor(R.color.chart_color_food),
                getResourceColor(R.color.chart_color_traffic),
                getResourceColor(R.color.chart_color_clothes),
                getResourceColor(R.color.chart_color_life),
                getResourceColor(R.color.chart_color_entertainment),
                getResourceColor(R.color.chart_color_ccc),
                getResourceColor(R.color.chart_color_medicine),
                getResourceColor(R.color.chart_color_others)
        };
        return colors;
    }

    private int getResourceColor(int resID){
        return getResources().getColor(resID);
    }

    IAxisValueFormatter formatter = new IAxisValueFormatter() {
        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            List<String> chartLabels = getLabels();
            return chartLabels.get((int) value);
        }
    };
}
