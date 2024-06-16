// mask.js
export const maskGeoJson = {
    "type": "FeatureCollection",
    "features": [
        {
            "type": "Feature",
            "geometry": {
                "type": "Polygon",
                "coordinates": [
                    [
                        [14.0, 54.9],
                        [24.2, 54.9],
                        [24.2, 49.0],
                        [14.0, 49.0],
                        [14.0, 54.9]
                    ],
                    [
                        [14.0, 54.9],  // Northwest corner
                        [14.0, 49.0],  // Southwest corner
                        [24.2, 49.0],  // Southeast corner
                        [24.2, 54.9],  // Northeast corner
                        [14.0, 54.9]   // Northwest corner again
                    ]
                ]
            }
        }
    ]
};
