import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useAuth } from '../auth/AuthContext';
import { STORAGE_KEY } from '../axios/axios';

const TopNav = () => {
    const [auth, setAuth] = useAuth();

    const handleLogout = () => {
        localStorage.removeItem(STORAGE_KEY);
        setAuth(null)
    };

    return (
        <Navbar expand="lg" bg="dark" variant="dark">
            <Container>
                <Navbar.Brand as={Link} to="/">Witaj</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                        <Nav.Link as={Link} to="/import">Import</Nav.Link>
                        <Nav.Link as={Link} to="/data-summary">Dane straży pożarnej</Nav.Link>
                        <Nav.Link as={Link} to="/vehicle-data">Dane pojazdów</Nav.Link>
                        <Nav.Link as={Link} to="/correlation">Sprawdź korelacje</Nav.Link>
                    </Nav>
                    <Nav>
                        {auth ? (
                            <>
                                <div className='text-light p-2'>Użytkownik: {auth}</div>
                                <Nav.Link onClick={handleLogout}><span className="glyphicon glyphicon-log-out"></span> Wyloguj</Nav.Link>
                            </>
                        ) : (
                            <>
                                <Nav.Link as={Link} to="/register"><span className="glyphicon glyphicon-user"></span> Rejestracja</Nav.Link>
                                <Nav.Link as={Link} to="/login"><span className="glyphicon glyphicon-log-in"></span> Logowanie</Nav.Link>
                            </>
                        )}
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
};

export default TopNav;
