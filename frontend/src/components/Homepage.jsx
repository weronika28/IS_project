import React from 'react';
import styled from 'styled-components';
import { FaFileImport, FaCar, FaFire, FaChartLine, FaUserCheck } from 'react-icons/fa';

const HomepageContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 20px;
  background-color: #f5f5f5;
`;

const TitleWrapper = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: 20px;
`;

const Title = styled.h2`
  font-size: 2rem;
  margin-left: 20px;
  color: #333;
`;

const LargeImage = styled.img`
  width: 120px;
  height: 120px;
  border-radius: 50%;
`;

const CardGrid = styled.div`
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  justify-content: center;
`;

const Card = styled.div`
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  padding: 20px;
  width: 300px;
  text-align: center;
  transition: transform 0.3s;
  
  &:hover {
    transform: translateY(-5px);
  }
`;

const CardText = styled.p`
  font-size: 1rem;
  color: #555;
`;

const IconWrapper = styled.div`
  font-size: 2rem;
  margin-bottom: 10px;
  color: #007bff;
`;

function Homepage() {
  return (
    <HomepageContainer>
      <TitleWrapper>
        <LargeImage src="/analiza.jpg" alt="Analiza" />
        <Title>Witaj, na naszej stronie:</Title>
      </TitleWrapper>
      <CardGrid>
        <Card>
          <IconWrapper><FaFileImport /></IconWrapper>
          <CardText>Zaimportujesz dane straży pożarnej z pliku CSV</CardText>
        </Card>
        <Card>
          <IconWrapper><FaCar /></IconWrapper>
          <CardText>Zaimportujesz dane dotyczące zarejestrowanych pojazdów korzystając z API</CardText>
        </Card>
        <Card>
          <IconWrapper><FaFire /></IconWrapper>
          <CardText>Sprawdzisz analizę danych opublikowanych przez straż pożarną</CardText>
        </Card>
        <Card>
          <IconWrapper><FaChartLine /></IconWrapper>
          <CardText>Sprawdzisz analizę zarejestrowanych pojazdów</CardText>
        </Card>
        <Card>
          <IconWrapper><FaUserCheck /></IconWrapper>
          <CardText>Będąc zalogowanym użytkownikiem sprawdzisz, czy istnieje związek między czasem dojazdu strażaków, a liczbą nowo zarejestrowanych pojazdów.</CardText>
        </Card>
      </CardGrid>
    </HomepageContainer>
  );
}

export default Homepage;
