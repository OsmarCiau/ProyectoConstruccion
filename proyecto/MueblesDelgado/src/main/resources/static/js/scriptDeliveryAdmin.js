// URL base de la API
const API_BASE_URL = 'http://localhost:8080/api/delivery';

// Registrar un camión
document.getElementById('truckForm').addEventListener('submit', async (event) => {
    event.preventDefault();

    const truckTrackingNumber = document.getElementById('truckTrackingNumber').value;
    const truckCapacity = document.getElementById('truckCapacity').value;
    const truckMileage = document.getElementById('truckMileage').value;

    const truckData = {
        trackingNumber: truckTrackingNumber,
        capacity: truckCapacity,
        mileage: truckMileage
    };

    const response = await fetch(`${API_BASE_URL}/truck`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(truckData)
    });

    if (response.ok) {
        alert('Camión registrado exitosamente');
        document.getElementById('truckForm').reset();
    } else {
        alert('Error al registrar el camión');
    }
});

// Registrar un conductor
document.getElementById('driverForm').addEventListener('submit', async (event) => {
    event.preventDefault();

    const driverName = document.getElementById('driverName').value;
    const driverLicense = document.getElementById('driverLicense').value;

    const driverData = {
        name: driverName,
        licenseNumber: driverLicense
    };

    const response = await fetch(`${API_BASE_URL}/driver`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(driverData)
    });

    if (response.ok) {
        alert('Conductor registrado exitosamente');
        document.getElementById('driverForm').reset();
    } else {
        alert('Error al registrar el conductor');
    }
});

// Asignar un conductor a un camión
document.getElementById('assignForm').addEventListener('submit', async (event) => {
    event.preventDefault();

    const truckTrackingNumber = document.getElementById('assignTruckTrackingNumber').value;
    const driverName = document.getElementById('assignDriverName').value;

    const response = await fetch(`${API_BASE_URL}/assign?p_trackingNumber=${truckTrackingNumber}&p_name=${driverName}`, {
        method: 'POST'
    });

    if (response.ok) {
        alert('Conductor asignado exitosamente');
        document.getElementById('assignForm').reset();
    } else {
        alert('Error al asignar el conductor');
    }
});

// Obtener y mostrar los camiones disponibles
document.getElementById('getTrucksButton').addEventListener('click', async () => {
    const response = await fetch(`${API_BASE_URL}/trucks`);
    const trucks = await response.json();

    const truckList = document.getElementById('truckList');
    truckList.innerHTML = ''; // Limpiar la lista antes de mostrar los nuevos resultados

    trucks.forEach((truck) => {
        const li = document.createElement('li');
        li.textContent = `Tracking Number: ${truck.trackingNumber}, Capacidad: ${truck.capacity}, Kilometraje: ${truck.mileage}`;
        truckList.appendChild(li);
    });
});

// Obtener y mostrar los conductores disponibles
document.getElementById('getDriversButton').addEventListener('click', async () => {
    const response = await fetch(`${API_BASE_URL}/drivers`);
    const drivers = await response.json();

    const driverList = document.getElementById('driverList');
    driverList.innerHTML = ''; // Limpiar la lista antes de mostrar los nuevos resultados

    drivers.forEach((driver) => {
        const li = document.createElement('li');
        li.textContent = `Nombre: ${driver.name}, Licencia: ${driver.licenseNumber}`;
        driverList.appendChild(li);
    });
});

document.getElementById('getAssignmentsButton').addEventListener('click', async () => {
    try {
        const response = await fetch(`${API_BASE_URL}/assignments`);

        if (!response.ok) {
            throw new Error('No se pudieron obtener las asignaciones');
        }

        const assignments = await response.json();

        const assignmentList = document.getElementById('assignmentList');
        assignmentList.innerHTML = ''; // Limpiar la lista antes de mostrar los nuevos resultados

        assignments.forEach((assignment) => {
            // Acceder a los datos anidados
            const truckTrackingNumber = assignment.deliveryTruck.trackingNumber;
            const driverName = assignment.truckDriver.name;

            const li = document.createElement('li');
            li.textContent = `Camión: ${truckTrackingNumber}, Conductor: ${driverName}`;
            assignmentList.appendChild(li);
        });
    } catch (error) {
        console.error('Error al obtener las asignaciones:', error);
    }
});
