//   PACIENTES 
//Listar pacientes

const tabla_pacientes = document.querySelector(".pacientes_tabla");
tabla_pacientes.innerHTML = '';

async function obtenerListadoPacientes() {
  try {
      const response = await fetch('http://localhost:8080/pacientes/listar');
      
      if (!response.ok) {
          throw new Error('Network response was not ok ' + response.statusText);
      }

      const listadoPacientes = await response.json();
      console.log(listadoPacientes); // Mostrar el listado en la consola

      let pacientesHtml = "";
      listadoPacientes.forEach(paciente => {
        let domicilioText = `${paciente.domicilio.calle} ${paciente.domicilio.numero} - ${paciente.domicilio.localidad}, ${paciente.domicilio.provincia}`
        pacientesHtml += `<tr>
                            <th scope="row">${paciente.id}</th>
                            <td>${paciente.dni}</td>
                            <td>${paciente.nombre}</td>
                            <td>${paciente.apellido}</td>
                            <td>${paciente.fechaIngreso}</td>
                            <td>${domicilioText}</td>
                            <td>  
                                <a class="text-primary px-3" href="" >
                                    <i class="fas fa-edit"></i>
                                </a>
                                <a class="text-primary px-3" href="" onclick="eliminarPaciente(${paciente.id})">
                                    <i class="fas fa-trash-alt"></i>
                                </a>
                            </td>                  
                        </tr>`
      });
    
     
    tabla_pacientes.innerHTML = pacientesHtml;
    
      return listadoPacientes;
  } catch (error) {
      console.error('There was a problem with the fetch operation:', error);
  }
}

// Llamar a la función para obtener el listado de pacientes
obtenerListadoPacientes();



// Registrar pacientes

function obtenerFechaActual() {
  const fecha = new Date();
  const anio = fecha.getFullYear();
  const mes = String(fecha.getMonth() + 1).padStart(2, '0');
  const dia = String(fecha.getDate()).padStart(2, '0');

  return `${anio}-${mes}-${dia}`;
}

async function registrarPaciente() {
  const datosPaciente = {
      nombre: document.querySelector("#nombre_paciente").value,
      apellido: document.querySelector("#apellido_paciente").value,
      dni: document.querySelector("#dni_paciente").value,
      fechaIngreso: obtenerFechaActual(),
      domicilio: {
          calle: document.querySelector("#calle_domicilio").value,
          numero: document.querySelector("#numero_domicilio").value,
          localidad: document.querySelector("#localidad_domicilio").value,
          provincia: document.querySelector("#provincia_domicilio").value
      }
  };

  console.log(JSON.stringify(datosPaciente));

  try {
      const response = await fetch('http://localhost:8080/pacientes/registrar', {
          method: 'POST',
          headers: {
              'Content-Type': 'application/json'
          },
          body: JSON.stringify(datosPaciente)
      });

      if (!response.ok) {
          throw new Error('Network response was not ok ' + response.statusText);
      }

      const resultado = await response.json();
      console.log(resultado); // Mostrar la respuesta en la consola
      obtenerListadoPacientes();
      return resultado;
  } catch (error) {
      console.error('There was a problem with the fetch operation:', error);
  }
}

document.querySelector("#botonRegistroPaciente").addEventListener("click", registrarPaciente)

// Eliminar Paciente

async function eliminarPaciente(id) {
  const url = `http://localhost:8080/pacientes/eliminar?id=${id}`;

  try {
      const response = await fetch(url, {
          method: 'DELETE'
      });

      if (!response.ok) {
          throw new Error('Network response was not ok ' + response.statusText);
      }
      obtenerListadoPacientes();
      console.log('Paciente eliminado correctamente');
  } catch (error) {
      console.error('There was a problem with the fetch operation:', error);
  }
}


