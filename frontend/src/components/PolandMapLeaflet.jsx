import { useEffect } from 'react';
import { MapContainer, TileLayer, GeoJSON, useMap } from 'react-leaflet';
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

    const MapSettings = () => {
        const map = useMap();

        useEffect(() => {
            map.setView([52.237049, 19.145136], 6);
            map.zoomControl.remove();
            map.dragging.disable();
            map.scrollWheelZoom.disable();
            map.doubleClickZoom.disable();
            map.boxZoom.disable();
            map.touchZoom.disable();
        }, [map]);

        return null;
    };

    return (
        <div className="map-container">
            <MapContainer
                center={[52.1, 19.4]}
                zoom={6}
                style={{ height: "80vh", width: "100%" }}
            >
                <GeoJSON data={geoData} onEachFeature={onEachFeature} />
                <MapSettings />
            </MapContainer>
        </div>
    );
};

export default PolandMapLeaflet;
