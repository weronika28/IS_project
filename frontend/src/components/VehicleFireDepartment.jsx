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
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [vehicleCounts, setVehicleCounts] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await axios.get('/api/vehicle-data/voivodeships');
                setVoivodeships(response.data);
                setLoading(false);
            } catch (error) {
                console.error('Error fetching voivodeships:', error);
                setError('Unable to fetch voivodeships. Please try again later.');
                setLoading(false);
            }
        };

        fetchData();
    }, []);

    const fetchVehicleCounts = async () => {
        const counts = await Promise.all(
            voivodeships.map(async (voivodeship) => {
                try {
                    const response = await axios.get(`/api/vehicle-data/vehicle-count/${voivodeship}`);
                    return {
                        voivodeship,
                        count: response.data
                    };
                } catch (error) {
                    console.error(`Error fetching count for ${voivodeship}:`, error);
                    return {
                        voivodeship,
                        count: 0 
                    };
                }
            })
        );
        return counts;
    };

    useEffect(() => {
        const fetchCounts = async () => {
            try {
                const counts = await fetchVehicleCounts();
                setVehicleCounts(counts);
            } catch (error) {
                console.error('Error fetching vehicle counts:', error);
            }
        };

        if (voivodeships.length > 0) {
            fetchCounts();
        }
    }, [voivodeships]);

    if (loading) {
        return <div>Loading data...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }

    return (
        <div style={{ width: '100%', height: 600 }}>
            <h2>Ilość zarejestrowanych pojazdów dla danego województwa</h2>
            <ResponsiveContainer>
                <LineChart data={vehicleCounts}>
                    <CartesianGrid strokeDasharray="3 3" />
                    <XAxis
                        dataKey="voivodeship"
                        angle={-45}
                        textAnchor="end"
                        interval={0}
                        height={120}
                        tick={<CustomXAxisTick />}
                    />
                    <YAxis />
                    <Tooltip />
                    <Legend />
                    <Line type="monotone" dataKey="count" stroke="#8884d8" activeDot={{ r: 8 }} />
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
                {payload.value}
            </text>
        </g>
    );
};

export default VehicleFireDepartment;
