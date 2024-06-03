import React from 'react';
import FireDepartmentDataSummary from './FireDepartmentDataSummary';
import FireDepartmentCountByWojewodztwo from './FireDepartmentCountByWojewodztwo';
import FireDepartmentOperationTypePercentage from './FireDepartmentOperationTypePercentage';

const FireDepartmentData = () => {
    return (
        <div>
            <h1>Podsumowanie Danych Straży Pożarnej</h1>
                <FireDepartmentDataSummary />
                <FireDepartmentCountByWojewodztwo />
                <FireDepartmentOperationTypePercentage />
        </div>
    );
};

export default FireDepartmentData;
