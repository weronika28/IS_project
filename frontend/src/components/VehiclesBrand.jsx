import { useEffect, useState } from 'react';
import axios from 'axios';

// eslint-disable-next-line react/prop-types
const VehiclesBrand = ({ selectedVoivodeship }) => {
    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        if (selectedVoivodeship) {
            setLoading(true);
            setError(null);
            console.log(`Fetching data for voivodeship: ${selectedVoivodeship}`);
            axios.get(`/api/vehicle-data/voivodeship/${selectedVoivodeship}`)
                .then(response => {
                    console.log('Data fetched:', response.data);
                    setData(response.data);
                    setLoading(false);
                })
                .catch(error => {
                    console.error('Error fetching data:', error);
                    setError(error);
                    setLoading(false);
                });
        }
    }, [selectedVoivodeship]);

    if (!selectedVoivodeship) {
        return <div>Wybierz województwo, żeby zobaczyć dane</div>;
    }

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>There was an error loading the data: {error.message}</div>;
    }


    return (
        <div>
            <h2>Dane dla województwa: {selectedVoivodeship}</h2>
            <ul>
                <ul>Liczba nowo zarejestrowanych pojazdów: {data.totalVehicles}</ul>
                <ul>Najpopularniejsza marka: {data.mostPopularBrand}</ul>
                <ul>Najpopularniejszy rodzaj paliwa: {data.mostPopularFuelType}</ul>
                <ul>Średnia pojemność skokowa silnika: {data.averageEngineCapacity.toFixed(2)}</ul>
            </ul>
        </div>
    );
};

export default VehiclesBrand;
