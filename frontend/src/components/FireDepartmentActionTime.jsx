import React, { useEffect, useState } from 'react';
import axios from 'axios';

const FireDepartmentActionTime = ({ wojewodztwo }) => {
  const [data, setData] = useState([]);

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const response = await axios.get(`/api/fire-department/action-time/${wojewodztwo}`);
      setData(response.data);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };

  return (
    <div>
      <h2>Czas akcji dla {wojewodztwo}</h2>
      <ul>
        {data.map(item => (
          <li key={item.wojewodztwo}>
            {item.wojewodztwo} - {item.sumaCzasu} minut
          </li>
        ))}
      </ul>
    </div>
  );
};

export default FireDepartmentActionTime;
