// Detectar si la página fue recargada (refreshed) en esta pestaña
const entries = performance.getEntriesByType("navigation");
if (entries.length > 0 && entries[0].type === "reload") {
    console.log("Refresco detectado en esta pestaña: notificando logout global.");

    // Escribir una clave en localStorage para avisar a todas las pestañas
    localStorage.setItem("cerrarSesion", Date.now());

    // Cerrar sesión en esta pestaña
    cerrarSesion();
}

// Escuchar cambios en localStorage desde otras pestañas
window.addEventListener("storage", function (event) {
    if (event.key === "cerrarSesion") {
        console.log("Evento de cerrar sesión recibido desde otra pestaña.");
        cerrarSesion();
    }
});

//Función que hace el redireccionamiento al login
function cerrarSesion() {
    // Opcional: limpiar la clave para futuros usos (no es obligatorio)
    localStorage.removeItem("cerrarSesion");

    // Redirigir al login dinámicamente (ajusta si necesitas otro path)
    window.location.href = window.location.origin + "/colas/login";
}
