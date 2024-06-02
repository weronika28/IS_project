import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link, Navigate } from 'react-router-dom';
import './App.css';
import ImportDataFile from './components/ImportDataFile';
import FireDepartmentDataSummary from './components/FireDepartmentDataSummary';
import './index.css';

function App() {
    return (
        <Router>
            <div className="app-container">
                <Routes>
                    <Route path="/" element={<Homepage />} />
                    <Route path="/data-summary" element={<FireDepartmentDataSummary />} />
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
                <button className="chart-button">Zobacz Wykres</button>
            </Link>
        </>
    );
}

export default App;
