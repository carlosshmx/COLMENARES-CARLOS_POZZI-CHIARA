
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
                                <a class="text-primary px-3" href="#" onclick="cargarInputsPaciente(${paciente.id})" >
                                    <i class="fas fa-edit"></i>
                                </a>
                                <a class="text-primary px-3" href="#" onclick="eliminarPaciente(${paciente.id})">
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

function validarFormulario() {
    const formulario = document.querySelector('#pacienteForm');
    console.log(formulario)
    const inputs = formulario.querySelectorAll('input, textarea, select');
    let formularioValido = true;

    inputs.forEach(input => {
        if (!input.value.trim()) {
            formularioValido = false;
            input.style.border = "1px solid red";
        } else {
            input.style.border = ""; 
          
        }
    });

    return formularioValido;
}

function resetearFormulario(){
    document.querySelector("#pacienteForm").reset();
    document.querySelector("#id_paciente").innerHTML = "";
    const formulario = document.querySelector('#pacienteForm');
    const inputs = formulario.querySelectorAll('input, textarea, select');
    inputs.forEach(input => {
            input.style.border = ""; 
    });
}


function obtenerFechaActual() {
  const fecha = new Date();
  const anio = fecha.getFullYear();
  const mes = String(fecha.getMonth() + 1).padStart(2, '0');
  const dia = String(fecha.getDate()).padStart(2, '0');

  return `${anio}-${mes}-${dia}`;
}

async function registrarPaciente() {
    if(!validarFormulario()){
        Swal.fire({
            icon: "error",
            title: "Complete todos los campos del formulario",
          });
    }else{

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

  Swal.fire({
    title: "¿Deseas guardar este paciente?",
    text: `${datosPaciente.nombre} ${datosPaciente.apellido}`,
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    confirmButtonText: "Si",
    cancelButtonText: "No"
  }).then(async(result) => {
    if (result.isConfirmed) {
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
            console.log(resultado); 

            Swal.fire({
                title: "Paciente Registrado",
                icon: "success"
              });

            obtenerListadoPacientes();
            resetearFormulario();
            return resultado;
        } catch (error) {
            Swal.fire({
                title: "Hubo un error, intente mas tarde",
                icon: "danger"
              });
            console.error('There was a problem with the fetch operation:', error);
        }

    }
  });

 
}
}




// Eliminar Paciente

async function eliminarPaciente(id) {
    Swal.fire({
        title: `Estas seguro de eliminar este paciente ID: ${id}`,
        text: "Esta acción no se puede revertir",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Si, Eliminar",
        cancelButtonText: "Cancelar"
      }).then(async(result) => {
        if ( result.isConfirmed) {
            const url = `http://localhost:8080/pacientes/eliminar?id=${id}`;
            try {
                const response = await fetch(url, {
                    method: 'DELETE'
                });

                if (!response.ok) {
                    Swal.fire({
                        title: `No se pudo eliminar el paciente`,
                        text: response,
                        icon: "error"
                      });
                    throw new Error('Network response was not ok ' + response.statusText);
                }
                Swal.fire({
                    title: `Eliminado paciente ID: ${id}`,
                    icon: "success"
                  });
                obtenerListadoPacientes();
                console.log('Paciente eliminado correctamente');
            } catch (error) {
                Swal.fire({
                    title: `No se pudo eliminar el paciente`,
                    icon: "error"
                  });
                console.error('There was a problem with the fetch operation:', error);
            }
         
        }
      });

  
}


// Actualizar Paciente 

async function cargarInputsPaciente(id){
    // resetearFormulario();
    try {
        const response = await fetch(`http://localhost:8080/pacientes/${id}`);
        
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
  
        const pacienteAEditar = await response.json();

        document.querySelector("#id_paciente").innerHTML = pacienteAEditar.id
        document.querySelector("#dni_paciente").value = pacienteAEditar.dni;
        document.querySelector("#nombre_paciente").value = pacienteAEditar.nombre;
        document.querySelector("#apellido_paciente").value = pacienteAEditar.apellido;
        document.querySelector("#calle_domicilio").value = pacienteAEditar.domicilio.calle;
        document.querySelector("#numero_domicilio").value = pacienteAEditar.domicilio.numero;
        document.querySelector("#localidad_domicilio").value = pacienteAEditar.domicilio.localidad;
        document.querySelector("#provincia_domicilio").value =pacienteAEditar.domicilio.provincia;

      
        return pacienteAEditar;
    } catch (error) {
        console.error('There was a problem with the fetch operation:', error);
    }
}

// Actualizar Paciente
    
async function actulizarPaciente(id){
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

    Swal.fire({
        title: `¿Cofirmas la edición de este paciente?`,
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Confirmar",
        cancelButtonText: "Cancelar"
      }).then(async(result) => {
        if ( result.isConfirmed) {
            try {
                const response = await fetch(`http://localhost:8080/pacientes/actualizar/${id}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(datosPaciente)
                });
        
            
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            
            const resultado = await response.json();
            Swal.fire({
                title: `Paciente guardado`,
                icon: "success"
              });
            obtenerListadoPacientes();
            resetearFormulario();
            return resultado;
            } catch (error) {
                Swal.fire({
                    title: `No se pudo guardar el paciente`,
                    icon: "error"
                  });
                console.error('There was a problem with the fetch operation:', error);
            }
        }
    });
}

function decisionRegistrarOEditar(){
    const idRegistro = document.querySelector("#id_paciente").innerHTML

    idRegistro.trim() !== '' ? actulizarPaciente(parseInt(idRegistro)) : registrarPaciente();
}

document.querySelector("#botonGuardarPaciente").addEventListener("click", decisionRegistrarOEditar);

document.querySelector("#botonCancelar").addEventListener("click", resetearFormulario);

