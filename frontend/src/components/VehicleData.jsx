import { useState } from 'react';
import VehiclesBrand from './VehiclesBrand';
import PolandMapLeaflet from './PolandMapLeaflet';

const VehicleData = () => {
    const [selectedVoivodeship, setSelectedVoivodeship] = useState('');

    const handleSelectVoivodeship = (voivodeship) => {
        setSelectedVoivodeship(voivodeship);
    };

    return (
        <div>
            <hn>Podsumowanie danych dotyczących pojazdów</hn>
            <PolandMapLeaflet onSelectVoivodeship={handleSelectVoivodeship} />
            <div className="chart-container">
                <VehiclesBrand selectedVoivodeship={selectedVoivodeship} />
            </div>
        </div>
    );
};

export default VehicleData;
