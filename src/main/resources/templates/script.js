// Función para asignar trabajador a obra
function asignarTrabajador() {
  const idObra = document.getElementById("idObra").value;
  const idTrabajador = document.getElementById("idTrabajador").value;

  fetch('/asignar-trabajador', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ idObra, idTrabajador })
  })
  .then(response => response.json())
  .then(data => {
    document.getElementById("resultados").innerHTML = data.message;
  })
  .catch(error => console.error('Error al asignar trabajador:', error));
}

// Función para consultar trabajadores en obra
function consultarTrabajadores() {
  const idObra = document.getElementById("consultaObra").value;

  fetch('/consultar-trabajadores', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ idObra })
  })
  .then(response => response.json())
  .then(data => {
    document.getElementById("resultados").innerHTML = JSON.stringify(data, null, 2);
  })
  .catch(error => console.error('Error al consultar trabajadores:', error));
}

// Guardar o actualizar obra en la base de datos
document.getElementById('form-obra').addEventListener('submit', function(event) {
  event.preventDefault();
  const nombre = document.getElementById('nombreObra').value;
  const ubicacion = document.getElementById('ubicacionObra').value;
  const fechaInicio = document.getElementById('fechaInicioObra').value;

  fetch('/guardar-obra', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ nombre, ubicacion, fechaInicio })
  })
  .then(response => response.json())
  .then(data => {
    document.getElementById("resultados").innerHTML = data.message;
  })
  .catch(error => console.error('Error al guardar obra:', error));
});

// Guardar trabajador en la base de datos
document.getElementById('form-trabajador').addEventListener('submit', function(event) {
  event.preventDefault();
  const nombre = document.getElementById('nombreTrabajador').value;
  const rol = document.getElementById('rolTrabajador').value;

  fetch('/guardar-trabajador', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ nombre, rol })
  })
  .then(response => response.json())
  .then(data => {
    document.getElementById("resultados").innerHTML = data.message;
  })
  .catch(error => console.error('Error al guardar trabajador:', error));
});
