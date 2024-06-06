import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import ImportDataFile from './components/ImportDataFile'; 
import FireDepartmentData from './components/FireDepartmentData';
import Login from './components/login/Login';
import Signup from './components/signup/Signup';
import TopNav from './components/TopNav'; 
import 'bootstrap/dist/css/bootstrap.min.css';
import VehicleData from './components/VehicleData';
import Correlation from './components/Correlation'; 

function App() {
    return (
        <Router>
            <div className="app-container">
                <TopNav />
                <Routes>
                    <Route path="/" element={<Homepage />} />
                    <Route path="/import" element={<ImportDataFile />} />
                    <Route path="/data-summary" element={<FireDepartmentData />} />
                    <Route path="/vehicle-data" element={<VehicleData />} /> 
                    <Route path="/correlation" element={<Correlation />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/register" element={<Signup />} />
                </Routes>
            </div>
        </Router>
    );
}

function Homepage() {
    return (
        <div className="homepage-container">
             <h2>Hej, na naszej stronie:</h2>
                 <div>
                    - zaimportujesz dane<br />
                    - sprawdzisz analizę danych opublikowanych przez PSP<br />
                    - sprawdzisz analizę zarejestrowanych pojazdów<br />
                    - oraz będąc zalogowanym użytkownikiem sprawdzisz, czy istnieje związek między czasem dojazdu strażaków, a liczbą zarejestrowanych pojazdów.
                </div>
            <img src="/analiza.jpg" alt="Analiza" className="homepage-image" />
        </div>
    );
}


export default App;
