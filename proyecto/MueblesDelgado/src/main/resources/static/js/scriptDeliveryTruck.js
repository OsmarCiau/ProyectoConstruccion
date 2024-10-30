// Función para registrar un camión o un conductor
async function register(entityType) {
    const trackingNumber = document.getElementById('trackingNumber')?.value; // for trucks
    const capacity = document.getElementById('capacity')?.value; // for trucks
    const mileage = document.getElementById('mileage')?.value; // for trucks
    const name = document.getElementById('driverName')?.value; // for drivers
    const licenseNumber = document.getElementById('licenseNumber')?.value; // for drivers

    const payload = entityType === 'truck'
        ? { trackingNumber, capacity, mileage }
        : { name, licenseNumber };

    const response = await fetch(`/api/delivery/${entityType}`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
    });

    const message = entityType === 'truck'
        ? "Camión registrado exitosamente"
        : "Conductor registrado exitosamente";

    if (response.ok) {
        alert(message);
    } else {
        alert("Error al registrar. Inténtalo de nuevo.");
    }
}


// Función para obtener la lista de camiones o conductores
async function getAvailable(entityType) {
    const response = await fetch(`/api/delivery/${entityType}`);
    const data = await response.json();

    const list = document.getElementById(entityType === 'trucks' ? 'truckList' : 'driverList');
    list.innerHTML = '';
    data.forEach(item => {
        const li = document.createElement('li');
        if (entityType === 'trucks') {
            li.textContent = `Camión ${item.trackingNumber} - Capacidad: ${item.capacity} - Kilometraje: ${item.mileage}`;
        } else {
            li.textContent = `Conductor: ${item.name} - Licencia: ${item.licenseNumber}`;
        }
        list.appendChild(li);
    });
}

// Función para asignar un conductor a un camión
async function assignDriver() {
    const trackingNumber = document.getElementById('assignTrackingNumber').value;
    const name = document.getElementById('assignDriverName').value;

    const response = await fetch(`/api/delivery/assign?p_trackingNumber=${trackingNumber}&p_name=${encodeURIComponent(name)}`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' }
    });

    if (response.ok) {
        alert("Conductor asignado al camión exitosamente");
    } else {
        alert("Error al asignar el conductor. Inténtalo de nuevo.");
    }
}



// Función para obtener la lista de asignaciones
async function getAssignments() {
    const response = await fetch('/api/delivery/assignments');
    const assignments = await response.json();

    const assignmentList = document.getElementById('assignmentList');
    assignmentList.innerHTML = '';
    assignments.forEach(assignment => {
        const li = document.createElement('li');
        li.textContent = `Camión ${assignment.deliveryTruck.trackingNumber} asignado a ${assignment.truckDriver.name}`;
        assignmentList.appendChild(li);
    });
}
