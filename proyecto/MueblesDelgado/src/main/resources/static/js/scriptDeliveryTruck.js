// Función para registrar inventario
async function registerInventory() {
    const productName = document.getElementById('productName').value;
    const quantity = document.getElementById('quantity').value;
    const location = document.getElementById('location').value;

    const payload = { productName, quantity, location };

    const response = await fetch('/api/inventory', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
    });

    if (response.ok) {
        alert("Inventario registrado exitosamente");
        getAvailableInventory(); // Actualizar la lista después de registrar
    } else {
        alert("Error al registrar el inventario. Inténtalo de nuevo.");
    }

    // Limpiar campos de entrada
    document.getElementById('productName').value = '';
    document.getElementById('quantity').value = '';
    document.getElementById('location').value = '';
}

// Función para registrar una ruta
async function registerRoute() {
    const routeName = document.getElementById('routeName').value;
    const distance = document.getElementById('distance').value;

    const payload = { routeName, distance };

    const response = await fetch('/api/routes', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
    });

    if (response.ok) {
        alert("Ruta registrada exitosamente");
        getAvailableRoutes(); // Actualizar la lista después de registrar
    } else {
        alert("Error al registrar la ruta. Inténtalo de nuevo.");
    }

    // Limpiar campos de entrada
    document.getElementById('routeName').value = '';
    document.getElementById('distance').value = '';
}

// Función para registrar una orden de envío
async function registerOrder() {
    const orderID = document.getElementById('orderID').value;
    const destination = document.getElementById('destination').value;
    const assignedRoute = document.getElementById('assignedRoute').value;

    const payload = { orderID, destination, assignedRoute };

    const response = await fetch('/api/orders', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
    });

    if (response.ok) {
        alert("Orden registrada exitosamente");
        getActiveOrders(); // Actualizar la lista después de registrar
    } else {
        alert("Error al registrar la orden. Inténtalo de nuevo.");
    }

    // Limpiar campos de entrada
    document.getElementById('orderID').value = '';
    document.getElementById('destination').value = '';
    document.getElementById('assignedRoute').value = '';
}

// Función para obtener el inventario disponible
async function getAvailableInventory() {
    const response = await fetch('/api/inventory');
    const data = await response.json();

    const inventoryList = document.getElementById('inventoryList');
    inventoryList.innerHTML = ''; // Limpiar la lista anterior
    data.forEach(item => {
        const li = document.createElement('li');
        li.textContent = `Producto: ${item.productName}, Cantidad: ${item.quantity}, Ubicación: ${item.location}`;
        inventoryList.appendChild(li);
    });
}

// Función para obtener las rutas disponibles
/*async function getAvailableRoutes() {
    const response = await fetch('/api/routes');
    const data = await response.json();

    const routeList = document.getElementById('routeList');
    routeList.innerHTML = ''; // Limpiar la lista anterior
    data.forEach(route => {
        const li = document.createElement('li');
        li.textContent = `Ruta: ${route.routeName}, Distancia: ${route.distance} km`;
        routeList.appendChild(li);
    });
} */

// Función para obtener las órdenes de envío activas
async function getActiveOrders() {
    const response = await fetch('/api/orders');
    const data = await response.json();

    const orderList = document.getElementById('orderList');
    orderList.innerHTML = ''; // Limpiar la lista anterior
    data.forEach(order => {
        const li = document.createElement('li');
        li.textContent = `Orden ID: ${order.orderID}, Destino: ${order.destination}, Ruta: ${order.assignedRoute}`;
        orderList.appendChild(li);
    });
}

// Inicialización de eventos al cargar el documento
document.addEventListener('DOMContentLoaded', () => {
    // Cargar listas disponibles al inicio
    getAvailableInventory();
    getAvailableRoutes();
    getActiveOrders();
});
