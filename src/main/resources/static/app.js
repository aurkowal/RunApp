let map;
let routeLine;

// 🔹 inicjalizacja mapy – TYLKO RAZ
function initMap() {
    map = L.map('map');

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '© OpenStreetMap'
    }).addTo(map);
}

// 🔹 ładowanie trasy PO KLIKNIĘCIU (bez reloadu)
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

// 🔹 rysowanie / podmiana trasy
function drawRoute(encodedPolyline) {

    const coords = polyline.decode(encodedPolyline);
    const latLngs = coords.map(p => [p[0], p[1]]);

    // 🧹 usuń poprzednią linię (TO JEST KLUCZ)
    if (routeLine) {
        map.removeLayer(routeLine);
    }

    // 🟦 nowa trasa
    routeLine = L.polyline(latLngs, {
        color: 'blue',
        weight: 4
    }).addTo(map);

    map.fitBounds(routeLine.getBounds());
}

// 🔹 start aplikacji
initMap();
loadRoute(); // rysuje trasę z inputa (np. ID=20) bez reloadera