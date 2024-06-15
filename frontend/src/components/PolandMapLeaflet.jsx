import { useEffect } from 'react';
import { MapContainer, TileLayer, GeoJSON } from 'react-leaflet';
import 'leaflet/dist/leaflet.css';
import geoData from '/src/woj_maps.json';
import L from 'leaflet'; // Fix import for leaflet icons

// eslint-disable-next-line react/prop-types
const PolandMapLeaflet = ({ onSelectVoivodeship }) => {
    useEffect(() => {
        // Fix for default marker icon issues with Leaflet
        delete L.Icon.Default.prototype._getIconUrl;

        L.Icon.Default.mergeOptions({
            iconRetinaUrl: 'https://unpkg.com/leaflet@1.7.1/dist/images/marker-icon-2x.png',
            iconUrl: 'https://unpkg.com/leaflet@1.7.1/dist/images/marker-icon.png',
            shadowUrl: 'https://unpkg.com/leaflet@1.7.1/dist/images/marker-shadow.png',
        });
    }, []);

    const onEachFeature = (feature, layer) => {
        layer.on({
            click: () => {
                console.log('Feature clicked:', feature.properties);
                onSelectVoivodeship(feature.properties.name.toUpperCase());
            }
        });
    };


    return (
        <div className="map-container">
            <MapContainer center={[52.1, 19.4]} zoom={6} style={{ height: "80vh", width: "100%" }}>
                <TileLayer
                    url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                    attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                />
                <GeoJSON data={geoData} onEachFeature={onEachFeature} />
            </MapContainer>
        </div>
    );
};

export default PolandMapLeaflet;
