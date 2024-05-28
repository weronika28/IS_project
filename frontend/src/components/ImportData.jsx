import React from 'react';
import axios from 'axios';

const ImportData = () => {

    const handleImportFireDepartmentData = async () => {
        try {
            const response = await axios.post('http://localhost:8080/api/fire-department/import/xml');
            alert(response.data);
        } catch (error) {
            console.error("There was an error importing fire department data!", error);
            alert("Error importing fire department data.");
        }
    };

    const handleImportVehicleData = async () => {
        try {
            const response = await axios.post('http://localhost:8080/api/vehicles/import/xml');
            alert(response.data);
        } catch (error) {
            console.error("There was an error importing vehicle data!", error);
            alert("Error importing vehicle data.");
        }
    };

    return (
        <div className="import-data-container">
            <h2>Tu zaimportujesz dane dotyczące wyjazdów strażackich oraz dane dotyczące zarejestrowanych pojazdów</h2>
            <div className="import-buttons">
                <button className="import-button" onClick={handleImportFireDepartmentData}>Import Fire Department Data</button>
                <button className="import-button" onClick={handleImportVehicleData}>Import Vehicle Data</button>
            </div>
        </div>
    );
};

export default ImportData;
