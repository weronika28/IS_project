import React from 'react';
import './App.css';
import ImportDataFile from './components/ImportDataFile'; 
import './index.css';

function App() {
    return (
        <div className="app-container">
            <h1>Zaimportuj dane</h1>
            <ImportDataFile/>
        </div>
    );
}

export default App;
