import { useState } from 'react';
import axios from 'axios';
import { FaSpinner } from 'react-icons/fa';

const ImportDataFile = () => {
    const [fireDepartmentFile, setFireDepartmentFile] = useState(null);
    const [vehicleFile, setVehicleFile] = useState(null);
    const [loadingFireDepartment, setLoadingFireDepartment] = useState(false);
    const [loadingVehicle, setLoadingVehicle] = useState(false);
    const [loadingVehicleApi, setLoadingVehicleApi] = useState(false);
    const [wojewodztwo, setWojewodztwo] = useState('02');

    const handleFireDepartmentFileChange = (e) => {
        const file = e.target.files[0];
        if (file && file.name.endsWith('.csv')) {
            setFireDepartmentFile(file);
        } else {
            alert("Proszę wybrać plik CSV.");
        }
    };

    const handleVehicleFileChange = (e) => {
        const file = e.target.files[0];
        if (file && file.name.endsWith('.csv')) {
            setVehicleFile(file);
        } else {
            alert("Proszę wybrać plik CSV.");
        }
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

    const handleImportVehicleDataFromApi = async () => {
        setLoadingVehicleApi(true);
        try {
            let url = `http://localhost:5000/api/vehicles/import/api?wojewodztwo=${wojewodztwo}`;
            const response = await axios.post(url);
            alert(response.data);
        } catch (error) {
            console.error("Wystąpił błąd podczas importowania danych pojazdów z API!", error);
            alert("Błąd podczas importowania danych pojazdów z API.");
        } finally {
            setLoadingVehicleApi(false);
        }
    };

    return (
        <div className="import-data-container">
            <h1 className={"mb-5"}>Tu zaimportujesz dane dotyczące wyjazdów strażackich oraz dane dotyczące zarejestrowanych pojazdów</h1>
            <h4>
                Import danych straży pożarnej
            </h4>
            <div className="import-section mb-5">
                <input type="file" accept=".csv" onChange={handleFireDepartmentFileChange}/>
                <br/>
                <button className="import-button" onClick={handleImportFireDepartmentData}
                        disabled={loadingFireDepartment}>
                    {loadingFireDepartment ? <FaSpinner className="spinner"/> : "Importuj dane straży pożarnej"}
                </button>
            </div>
            <div className="import-section">
                <h4>
                    Import danych pojazdów
                </h4>
                <label className="">
                    Wybierz województwo: <br/>
                    <select value={wojewodztwo} onChange={(e) => setWojewodztwo(e.target.value)}>
                        <option value="02">Dolnośląskie</option>
                        <option value="04">Kujawsko-pomorskie</option>
                        <option value="06">Lubelskie</option>
                        <option value="08">Lubuskie</option>
                        <option value="10">Łódzkie</option>
                        <option value="12">Małopolskie</option>
                        <option value="14">Mazowieckie</option>
                        <option value="16">Opolskie</option>
                        <option value="18">Podkarpackie</option>
                        <option value="20">Podlaskie</option>
                        <option value="22">Pomorskie</option>
                        <option value="24">Śląskie</option>
                        <option value="26">Świętokrzyskie</option>
                        <option value="28">Warmińsko-mazurskie</option>
                        <option value="30">Wielkopolskie</option>
                        <option value="32">Zachodniopomorskie</option>
                    </select>
                </label>
                <br/>
                <button className="import-button" onClick={handleImportVehicleDataFromApi} disabled={loadingVehicleApi}>
                    {loadingVehicleApi ? <FaSpinner className="spinner"/> : "Importuj dane pojazdów z API"}
                </button>
            </div>
        </div>
    );
};

export default ImportDataFile;
