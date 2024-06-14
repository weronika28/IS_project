import React from 'react';
import FireDepartmentDataSummary from './FireDepartmentDataSummary';
import FireDepartmentCountByWojewodztwo from './FireDepartmentCountByWojewodztwo';
import FireDepartmentOperationTypePercentage from './FireDepartmentOperationTypePercentage';
import FireDepartmentActionTime from './FireDepartmentActionTime';
import '../index.css';

const FireDepartmentData = () => {
    return (
        <div>
            <hn>Podsumowanie danych straży pożarnej</hn>
            <div className="chart-container">
                <FireDepartmentDataSummary />
            </div>
            <div className="chart-container">
                <FireDepartmentCountByWojewodztwo />
            </div>
            <div className="chart-container2">
                <FireDepartmentOperationTypePercentage />
            </div>
            <div className="chart-container">
                <FireDepartmentActionTime />
            </div>
        </div>
    );
};

export default FireDepartmentData;
