import React, { useEffect, useState } from 'react';
import { Bar } from 'react-chartjs-2';
import 'chart.js/auto';
//import { axios } from '../axios/axios'; na tym nie dziala
import axios from 'axios';

const FireDepartmentDataSummary = () => {
    const [dataSummary, setDataSummary] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        axios.get('/api/fire-department/data-summary')
            .then(response => {
                console.log("Data received from server: ", response.data); 
                setDataSummary(response.data);
                setLoading(false);
            })
            .catch(error => {
                console.error("There was an error fetching the data!", error);
                setError(error);
                setLoading(false);
            });
    }, []);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>There was an error loading the data: {error.message}</div>;
    }

    const chartData = {
        labels: [
            'Zużycie wody (m3)', 'Zużycie proszku (kg)', 'Zużycie piany (dm3)',  'Zużycie sorbentu (kg)'
        ],
        datasets: [
            {
                label: 'Zużycie',
                data: [
                    dataSummary.zuzWody, dataSummary.zuzProszku,
                    dataSummary.zuzPiany, dataSummary.zuzSorb
                ],
                backgroundColor: 'rgba(75, 192, 192, 0.6)'
            }
        ]
    };

    const chartOptions = {
        scales: {
            y: {
                beginAtZero: true,
                ticks: {
                    precision: 2
                }
            }
        }
    };

    return (
        <div>
            <h2>Zużycie środków gaśniczych</h2>
            <Bar data={chartData} options={chartOptions} />
        </div>
    );
};

export default FireDepartmentDataSummary;
