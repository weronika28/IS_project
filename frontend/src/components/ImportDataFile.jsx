import React, { useState } from 'react';
import axios from 'axios';
import { FaSpinner } from 'react-icons/fa';

const ImportDataFile = () => {
    const [fireDepartmentFile, setFireDepartmentFile] = useState(null);
    const [vehicleFile, setVehicleFile] = useState(null);
    const [loadingFireDepartment, setLoadingFireDepartment] = useState(false);
    const [loadingVehicle, setLoadingVehicle] = useState(false);

    const handleFireDepartmentFileChange = (e) => {
        setFireDepartmentFile(e.target.files[0]);
    };

    const handleVehicleFileChange = (e) => {
        setVehicleFile(e.target.files[0]);
    };

    const handleImportFireDepartmentData = async () => {
        if (!fireDepartmentFile) {
            alert("Proszę najpierw wybrać plik.");
            return;
        }

        const formData = new FormData();
        formData.append('file', fireDepartmentFile);

        setLoadingFireDepartment(true);
        try {
            const response = await axios.post('http://localhost:5000/api/fire-department/import/csv', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            });
            alert(response.data);
        } catch (error) {
            console.error("Wystąpił błąd podczas importowania danych straży pożarnej!", error);
            alert("Błąd podczas importowania danych straży pożarnej.");
        } finally {
            setLoadingFireDepartment(false);
        }
    };

    const handleImportVehicleData = async () => {
        if (!vehicleFile) {
            alert("Proszę najpierw wybrać plik.");
            return;
        }

        const formData = new FormData();
        formData.append('file', vehicleFile);

        setLoadingVehicle(true);
        try {
            const response = await axios.post('http://localhost:5000/api/vehicles/import/csv', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            });
            alert(response.data);
        } catch (error) {
            console.error("Wystąpił błąd podczas importowania danych pojazdów!", error);
            alert("Błąd podczas importowania danych pojazdów.");
        } finally {
            setLoadingVehicle(false);
        }
    };

    return (
        <div className="import-data-container">
            <h2>Tu zaimportujesz dane dotyczące wyjazdów strażackich oraz dane dotyczące zarejestrowanych pojazdów</h2>
            <div className="import-section">
                <input type="file" onChange={handleFireDepartmentFileChange} />
                <button className="import-button" onClick={handleImportFireDepartmentData} disabled={loadingFireDepartment}>
                    {loadingFireDepartment ? <FaSpinner className="spinner" /> : "Importuj dane straży pożarnej"}
                </button>
            </div>
            <div className="import-section">
                <input type="file" onChange={handleVehicleFileChange} />
                <button className="import-button" onClick={handleImportVehicleData} disabled={loadingVehicle}>
                    {loadingVehicle ? <FaSpinner className="spinner" /> : "Importuj dane pojazdów"}
                </button>
            </div>
        </div>
    );
};

export default ImportDataFile;
