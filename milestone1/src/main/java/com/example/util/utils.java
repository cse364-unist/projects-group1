package com.example.util;

public class utils {

    private static final double EARTH_RADIUS = 6371.0; //지구반지름
    //하버사인 공식
    public static double haversineDistance(double lat1, double lon1, double lat2, double lon2) {
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double temp = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double distance = 2 * EARTH_RADIUS * Math.asin(Math.sqrt(temp));
        return distance;
    }

    public static int HammingSimilarity(int bits1, int bits2) {
        return Integer.bitCount(bits1 ^ bits2);
    }
}
