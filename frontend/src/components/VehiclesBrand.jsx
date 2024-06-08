import React, { useEffect, useState } from 'react';
import axios from 'axios';

const VehiclesBrand = () => {
    const [data, setData] = useState([]);
    const [selectedRegion, setSelectedRegion] = useState('');
    const [regions, setRegions] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        axios.get('/api/vehicles')
            .then(response => {
                setData(response.data);
                const allRegions = response.data.reduce((acc, vehicle) => {
                    if (!acc.includes(vehicle.rejestracjaWojewodztwo)) {
                        acc.push(vehicle.rejestracjaWojewodztwo);
                    }
                    return acc;
                }, []);
                setRegions(allRegions);
                setLoading(false);
            })
            .catch(error => {
                console.error('Error fetching data:', error);
                setError(error);
                setLoading(false);
            });
    }, []);

    const processData = () => {
        const brands = {};
        data.forEach(vehicle => {
            const brand = vehicle.marka;
            const region = vehicle.rejestracjaWojewodztwo;
            if (!brands[brand]) {
                brands[brand] = 0;
            }
            if (selectedRegion === '' || region === selectedRegion) {
                brands[brand]++;
            }
        });

        const sortedBrands = Object.entries(brands).sort((a, b) => b[1] - a[1]);

        return sortedBrands;
    };

    const brandsData = processData();

    const renderTable = () => {
        return (
            <table border="1">
                <thead>
                    <tr>
                        <th>Marka</th>
                        <th>Liczba pojazdów</th>
                    </tr>
                </thead>
                <tbody>
                    {brandsData.map(([brand, count]) => (
                        <tr key={brand}>
                            <td>{brand}</td>
                            <td>{count}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        );
    };

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>There was an error loading the data: {error.message}</div>;
    }

    return (
        <div>
            <h2>Marki pojazdów w danym województwie</h2>
            <label htmlFor="regionSelect">Wybierz województwo:</label>
            <select id="regionSelect" onChange={(e) => setSelectedRegion(e.target.value)} value={selectedRegion}>
                <option value="">Wszystkie</option>
                {regions.map(region => (
                    <option key={region} value={region}>{region}</option>
                ))}
            </select>
            {renderTable()}
        </div>
    );
};

export default VehiclesBrand;
