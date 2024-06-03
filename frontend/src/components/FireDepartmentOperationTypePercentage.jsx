import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Pie } from 'react-chartjs-2';

const FireDepartmentOperationTypePercentage = () => {
    const [operationTypePercentage, setOperationTypePercentage] = useState({});
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        axios.get('/api/fire-department/operation-type-percentage')
            .then(response => {
                setOperationTypePercentage(response.data);
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
        labels: Object.keys(operationTypePercentage),
        datasets: [{
            data: Object.values(operationTypePercentage),
            backgroundColor: ['#FF6384', '#36A2EB'],
            hoverBackgroundColor: ['#FF6384', '#36A2EB']
        }]
    };

    return (
        <div>
            <h2>Procentowy udział typów operacji</h2>
            <Pie data={chartData} />
        </div>
    );
};

export default FireDepartmentOperationTypePercentage;
