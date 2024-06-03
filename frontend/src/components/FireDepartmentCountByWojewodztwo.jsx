import React, { useEffect, useState } from 'react';
import axios from 'axios';
import DataTable from 'react-data-table-component';
import '../index.css';

const FireDepartmentCountByWojewodztwo = () => {
    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        axios.get('/api/fire-department/count-by-wojewodztwo')
            .then(response => {
                const formattedData = Object.entries(response.data).map(([wojewodztwo, count]) => ({
                    wojewodztwo,
                    count
                }));
                setData(formattedData);
                setLoading(false);
            })
            .catch(error => {
                console.error("There was an error fetching the data!", error);
                setError(error);
                setLoading(false);
            });
    }, []);

    const columns = [
        {
            name: 'Województwo',
            selector: row => row.wojewodztwo,
            filter: 'text',
        },
        {
            name: 'Liczba wyjazdów',
            selector: row => row.count,
            sortable: true,
        }
    ];
    

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>There was an error loading the data: {error.message}</div>;
    }

    return (
        <div>
            <h2>Liczba wyjazdów według województw w 2023 roku </h2>
            <DataTable
                columns={columns}
                data={data}
                pagination
                highlightOnHover
                responsive
                className="styled-table"
            />
        </div>
    );
};

export default FireDepartmentCountByWojewodztwo;
