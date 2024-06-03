import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import './App.css';
import ImportDataFile from './components/ImportDataFile';
import FireDepartmentData from './components/FireDepartmentData';
import './index.css';

function App() {
    return (
        <Router>
            <div className="app-container">
                <Routes>
                    <Route path="/" element={<Homepage />} />
                    <Route path="/data-summary" element={<FireDepartmentData />} />
                </Routes>
            </div>
        </Router>
    );
}

function Homepage() {
    return (
        <>
            <h1>Zaimportuj dane</h1>
            <ImportDataFile />
            <Link to="/data-summary">
                <button className="chart-button">Sprawdź analizę danych Straży Pożarnej</button>
            </Link>
        </>
    );
}

export default App;
