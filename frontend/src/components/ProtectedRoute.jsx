import React from 'react';
import { Route, Navigate } from 'react-router-dom';
import { useAuth } from '../auth/AuthContext';
import UnauthorizedInfo from './UnauthorizedInfo'; 

const ProtectedRoute = ({ element }) => {
    const [auth] = useAuth();

    if (!auth) {
        return <UnauthorizedInfo />;
    }

    return element;
};

export default ProtectedRoute;
