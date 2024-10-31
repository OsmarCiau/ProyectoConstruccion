// Función para registrar inventario
function registerInventory() {
    const productName = document.getElementById('productName').value;
    const quantity = document.getElementById('quantity').value;
    const location = document.getElementById('location').value;
    const inventoryList = document.getElementById('inventoryList');

    if (productName === '' || quantity === '' || location === '') {
        alert('Por favor, completa todos los campos.');
        return;
    }

    const li = document.createElement('li');
    li.textContent = `Producto: ${productName}, Cantidad: ${quantity}, Ubicación: ${location}`;
    inventoryList.appendChild(li);

    // Limpiar campos de entrada
    document.getElementById('productName').value = '';
    document.getElementById('quantity').value = '';
    document.getElementById('location').value = '';
}

// Función para registrar una ruta
function registerRoute() {
    const routeName = document.getElementById('routeName').value;
    const distance = document.getElementById('distance').value;
    const routeList = document.getElementById('routeList');

    if (routeName === '' || distance === '') {
        alert('Por favor, completa todos los campos.');
        return;
    }

    const li = document.createElement('li');
    li.textContent = `Ruta: ${routeName}, Distancia: ${distance} km`;
    routeList.appendChild(li);

    // Limpiar campos de entrada
    document.getElementById('routeName').value = '';
    document.getElementById('distance').value = '';
}

// Función para registrar una orden de envío
function registerOrder() {
    const orderID = document.getElementById('orderID').value;
    const destination = document.getElementById('destination').value;
    const assignedRoute = document.getElementById('assignedRoute').value;
    const orderList = document.getElementById('orderList');

    if (orderID === '' || destination === '' || assignedRoute === '') {
        alert('Por favor, completa todos los campos.');
        return;
    }

    const li = document.createElement('li');
    li.textContent = `Orden ID: ${orderID}, Destino: ${destination}, Ruta: ${assignedRoute}`;
    orderList.appendChild(li);

    // Limpiar campos de entrada
    document.getElementById('orderID').value = '';
    document.getElementById('destination').value = '';
    document.getElementById('assignedRoute').value = '';
}

// Función para obtener el inventario disponible
function getAvailableInventory() {
    const inventoryList = document.getElementById('inventoryList');
    if (inventoryList.children.length === 0) {
        alert('No hay productos en el inventario.');
    } else {
        let inventoryItems = 'Inventario Disponible:\n';
        for (let item of inventoryList.children) {
            inventoryItems += item.textContent + '\n';
        }
        alert(inventoryItems);
    }
}

// Función para obtener las rutas disponibles
/*function getAvailableRoutes() {
    const routeList = document.getElementById('routeList');
    if (routeList.children.length === 0) {
        alert('No hay rutas disponibles.');
    } else {
        let routeItems = 'Rutas Disponibles:\n';
        for (let route of routeList.children) {
            routeItems += route.textContent + '\n';
        }
        alert(routeItems);
    }
} */

// Función para obtener las órdenes de envío activas
function getActiveOrders() {
    const orderList = document.getElementById('orderList');
    if (orderList.children.length === 0) {
        alert('No hay órdenes activas.');
    } else {
        let orderItems = 'Órdenes de Envío Activas:\n';
        for (let order of orderList.children) {
            orderItems += order.textContent + '\n';
        }
        alert(orderItems);
    }
}

// Inicialización de eventos al cargar el documento
document.addEventListener('DOMContentLoaded', () => {
    // Las funciones ya están enlazadas a los botones en el HTML
});
