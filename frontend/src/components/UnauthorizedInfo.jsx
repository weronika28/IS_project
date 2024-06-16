import React from 'react';
import styled from 'styled-components';

const InfoContainer = styled.div`
    margin: 20px;
    padding: 20px;
    background-color: #f8d7da;
    color: #721c24;
    border: 1px solid #f5c6cb;
    border-radius: 5px;
`;

const UnauthorizedInfo = () => {
    return (
        <InfoContainer>
            Musisz być zalogowanym użytkownikiem, aby sprawdzić czy istnieje związek między liczbą nowo zarejestrowanych pojazdów, a czasem dojazdu straży pożarnej na miejsce zdarzenia.
        </InfoContainer>
    );
};

export default UnauthorizedInfo;
