// import { ComposableMap, Geographies, Geography, ZoomableGroup } from 'react-simple-maps';
//
// const geoUrl = "/woj_maps.json";
//
// // eslint-disable-next-line react/prop-types
// const PolandMap = ({ onSelectVoivodeship }) => {
//     const handleGeographyClick = (geo) => {
//         const voivodeship = geo.properties.NAME_1.toUpperCase();
//         onSelectVoivodeship(voivodeship);
//     };
//
//     return (
//         <div className="map-container">
//             <ComposableMap width={1000} height={1000}>
//                 <ZoomableGroup>
//                     <Geographies geography={geoUrl}>
//                         {({ geographies }) =>
//                             geographies.map(geo => (
//                                 <Geography
//                                     key={geo.rsmKey}
//                                     geography={geo}
//                                     onClick={() => handleGeographyClick(geo)}
//                                     style={{
//                                         default: { fill: "#D6D6DA", outline: "none" },
//                                         hover: { fill: "#F53", outline: "none" },
//                                         pressed: { fill: "#E42", outline: "none" }
//                                     }}
//                                 />
//                             ))
//                         }
//                     </Geographies>
//                 </ZoomableGroup>
//             </ComposableMap>
//         </div>
//     );
// };
//
// export default PolandMap;
