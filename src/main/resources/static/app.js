let map;
let routeLine;
let routesLayer;

//inicjalizacja mapy – TYLKO RAZ
function initMap() {
    map = L.map('map');

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '© OpenStreetMap'
    }).addTo(map);

    routesLayer = L.layerGroup().addTo(map);
}

//ładowanie trasy PO KLIKNIĘCIU (bez reloadu)
function loadRoute() {
    const routeId = document.getElementById('routeId').value;

    if (!routeId) {
        alert("Podaj ID trasy");
        return;
    }

    fetch(`/routes/${routeId}`)
        .then(res => res.json())
        .then(data => drawRoute(data.geometry));
}

//rysowanie / podmiana trasy
function drawRoute(encodedPolyline) {

    const coords = polyline.decode(encodedPolyline);
    const latLngs = coords.map(p => [p[0], p[1]]);

    // usuń poprzednią linię
    if (routeLine) {
        map.removeLayer(routeLine);
    }

    // nowa trasa
    routeLine = L.polyline(latLngs, {
        color: 'blue',
        weight: 4
    }).addTo(map);

    map.fitBounds(routeLine.getBounds());
}

function loadRunnerRoutes() {
    const runnerId = document.getElementById('runnerId').value;

    if (!runnerId) {
        alert("Podaj ID runnera");
        return;
    }

    routesLayer.clearLayers();

    fetch(`/routes/runners/${runnerId}/routes`)
        .then(res => res.json())
        .then(routes => {
            if (!routes.length) {
                alert("Brak tras");
                return;
            }

            const bounds = [];

            routes.forEach(route => {
                if (!route.geometry) return;

                const coords = polyline.decode(route.geometry);

                L.polyline(coords, {
                    color: '#0077cc',
                    weight: 4,
                    opacity: 0.7
                }).addTo(routesLayer);

                bounds.push(...coords);
            });

            if (bounds.length > 0) {
                map.fitBounds(bounds);
            }
        });
}

// start aplikacji
initMap();
loadRoute(); // rysuje trasę z inputa bez reloadera

