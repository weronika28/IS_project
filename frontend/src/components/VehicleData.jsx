import VehiclesBrand from "./VehiclesBrand";

const VehicleData = () => {
    return (
        <div>
            <hn>Dane dotyczące pojazdów</hn>
            <div className="chart-container">
                <VehiclesBrand />
            </div>
        </div>
    );
};

export default VehicleData;
