import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import ImportDataFile from './components/ImportDataFile'; 
import FireDepartmentData from './components/FireDepartmentData';
import Login from './components/login/Login';
import Signup from './components/signup/Signup';
import TopNav from './components/TopNav'; 
import 'bootstrap/dist/css/bootstrap.min.css';
import VehicleData from './components/VehicleData'; 
import AuthContextProvider from './auth/AuthContext';
import VehicleFireDepartment from './components/VehicleFireDepartment';
import Homepage from './components/Homepage'; 

function App() {
    return (
        <AuthContextProvider>
            <Router>
                <div className="app-container">
                    <TopNav />
                    <Routes>
                        <Route path="/" element={<Homepage />} />
                        <Route path="/import" element={<ImportDataFile />} />
                        <Route path="/data-summary" element={<FireDepartmentData />} />
                        <Route path="/vehicle-data" element={<VehicleData />} /> 
                        <Route path="/correlation" element={<VehicleFireDepartment />} />
                        <Route path="/login" element={<Login />} />
                        <Route path="/register" element={<Signup />} />
                    </Routes>
                </div>
            </Router>
        </AuthContextProvider>
    );
}

export default App;
