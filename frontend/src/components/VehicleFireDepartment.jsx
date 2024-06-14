import React, { useEffect, useState } from 'react';
import axios from 'axios';
import {
    LineChart,
    Line,
    XAxis,
    YAxis,
    CartesianGrid,
    Tooltip,
    Legend,
    ResponsiveContainer
} from 'recharts';

const VehicleFireDepartment = () => {
    const [voivodeships, setVoivodeships] = useState([]);
    const [selectedGminas, setSelectedGminas] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [voivodeshipData, setVoivodeshipData] = useState([]);
    const [selectedGminasData, setSelectedGminasData] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                
                const voivodeshipResponse = await axios.get('/api/fire-department/voivodeship-data');
                const voivodeshipData = Object.entries(voivodeshipResponse.data).map(([wojewodztwo, values]) => ({ wojewodztwo, ...values }));
                setVoivodeships(voivodeshipData.map(entry => entry.wojewodztwo));
                setVoivodeshipData(voivodeshipData);

               
                const selectedGminasResponse = await axios.get('/api/fire-department/selected-gminas-data');
                const selectedGminasData = Object.entries(selectedGminasResponse.data).map(([gmina, values]) => ({ gmina, ...values }));
                setSelectedGminas(selectedGminasData.map(entry => entry.gmina));
                setSelectedGminasData(selectedGminasData);

                setLoading(false);
            } catch (error) {
                console.error('Error fetching data:', error);
                setError('Unable to fetch data. Please try again later.');
                setLoading(false);
            }
        };

        fetchData();
    }, []);

    if (loading) {
        return <div>Loading data...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }

    return (
        <div style={{ width: '100%', height: 1200 }}>
            <h2>Dane dla województw</h2>
            <ResponsiveContainer width="100%" height={600}>
                <LineChart data={voivodeshipData}>
                    <CartesianGrid strokeDasharray="3 3" />
                    <XAxis
                        dataKey="wojewodztwo"
                        angle={-45}
                        textAnchor="end"
                        interval={0}
                        height={120}
                        tick={<CustomXAxisTick />}
                    />
                    <YAxis yAxisId="left" stroke="#8884d8" />
                    <YAxis yAxisId="right" orientation="right" stroke="#82ca9d" />
                    <Tooltip />
                    <Legend />
                    <Line type="monotone" dataKey="count" stroke="#8884d8" activeDot={{ r: 8 }} name="Liczba nowo zarejestrowanych samochodów" yAxisId="left" />
                    <Line type="monotone" dataKey="avgTimePerKm" stroke="#82ca9d" activeDot={{ r: 8 }} name="Średni czas przejazdu 1 km" yAxisId="right" />
                </LineChart>
            </ResponsiveContainer>

            <h2>Dane dla wybranych gmin</h2>
            <ResponsiveContainer width="100%" height={600}>
                <LineChart data={selectedGminasData}>
                    <CartesianGrid strokeDasharray="3 3" />
                    <XAxis
                        dataKey="gmina"
                        angle={-45}
                        textAnchor="end"
                        interval={0}
                        height={120}
                        tick={<CustomXAxisTick />}
                    />
                    <YAxis yAxisId="left" stroke="#8884d8" />
                    <YAxis yAxisId="right" orientation="right" stroke="#82ca9d" />
                    <Tooltip />
                    <Legend />
                    <Line type="monotone" dataKey="count" stroke="#8884d8" activeDot={{ r: 8 }} name="Liczba nowo zarejestrowanych samochodów" yAxisId="left" />
                    <Line type="monotone" dataKey="avgTimePerKm" stroke="#82ca9d" activeDot={{ r: 8 }} name="Średni czas przejazdu 1 km" yAxisId="right" />
                </LineChart>
            </ResponsiveContainer>
        </div>
    );
};

const CustomXAxisTick = (props) => {
    const { x, y, payload } = props;
    return (
        <g transform={`translate(${x},${y})`}>
            <text
                x={0}
                y={0}
                dy={16}
                textAnchor="end"
                fill="#666"
                transform="rotate(-45)"
                fontSize={12}
            >
                <tspan x={0} dy="1.2em">{payload.value}</tspan>
            </text>
        </g>
    );
};

export default VehicleFireDepartment;
