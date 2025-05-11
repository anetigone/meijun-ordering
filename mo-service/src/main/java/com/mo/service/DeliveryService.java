package com.mo.service;

import com.alibaba.fastjson2.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

// 配送服务类
public class DeliveryService {
    // 高德API密钥，需要用户自行替换
    private static final String AMAP_KEY = "your_amap_key";

    // 计算配送距离
    public static double calculateDistance(String userLocation, String merchantLocation) throws Exception {
        String url = "https://restapi.amap.com/v3/distance?key=" + AMAP_KEY + "&origins=" + userLocation + "&destination=" + merchantLocation;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONObject jsonResponse = JSONObject.parseObject(response.toString());
        JSONObject result = jsonResponse.getJSONArray("results").getJSONObject(0);
        return result.getDouble("distance");
    }

    // 检测是否在配送范围内
    public static boolean isWithinDeliveryRange(String userLocation, String merchantLocation, double maxDistance) throws Exception {
        double distance = calculateDistance(userLocation, merchantLocation);
        return distance <= maxDistance;
    }

    // 计算配送费
    public static double calculateDeliveryFee(String userLocation, String merchantLocation, double baseFee, double feePerKilometer) throws Exception {
        double distance = calculateDistance(userLocation, merchantLocation) / 1000; // 转换为公里
        return baseFee + distance * feePerKilometer;
    }
}