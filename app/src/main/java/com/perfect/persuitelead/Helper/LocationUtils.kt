package com.perfect.persuitelead.Helper

object LocationUtils {

    private const val EARTH_RADIUS = 6371.0 // Earth's radius in kilometers

    fun calculateDistance(
        lat1: Double,
        lon1: Double,
        lat2: Double,
        lon2: Double
    ): Double {
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = (Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + (Math.cos(Math.toRadians(lat1)) * Math.cos(
            Math.toRadians(
                lat2
            )
        )
                * Math.sin(dLon / 2) * Math.sin(dLon / 2)))
        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
        return EARTH_RADIUS * c
    }
}