
// ODONTOLOGOS

//Listar Odontologos

const tabla_odontologos = document.querySelector(".odontologos_tabla")
let odontologosHtml = "";

async function obtenerListadoOdontologos() {
  try {
      const response = await fetch('http://localhost:8080/odontologos/listar');
      
      if (!response.ok) {
          throw new Error('Network response was not ok ' + response.statusText);
      }

      const listadoOdontologos = await response.json();
      console.log(listadoOdontologos); // Mostrar el listado en la consola

      let odontologosHtml = "";
      listadoOdontologos.forEach(odontologo => {
      
          odontologosHtml += `<tr>
                            <th scope="row">${odontologo.id}</th>
                            <td>${odontologo.matricula}</td>
                            <td>${odontologo.nombre}</td>
                            <td>${odontologo.apellido}</td>
                            <td>  
                                <a class="text-primary px-3" href="#" onclick="cargarInputsOdontologo(${odontologo.id})">
                                    <i class="fas fa-edit"></i>
                                </a>
                                <a class="text-primary px-3" href="#" onclick="eliminarOdontologo(${odontologo.id})">
                                    <i class="fas fa-trash-alt"></i>
                                </a>
                            </td>                  
                        </tr>`
      });
    
        tabla_odontologos.innerHTML = odontologosHtml;
    
      return listadoOdontologos;
  } catch (error) {
      console.error('There was a problem with the fetch operation:', error);
  }
}
obtenerListadoOdontologos();

// Registrar Odontologos

function validarFormulario() {
    const formulario = document.querySelector('#odontologForm');
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
    document.querySelector("#odontologForm").reset();
    document.querySelector("#id_odontologo").innerHTML = "";
    const formulario = document.querySelector("#odontologForm");
    const inputs = formulario.querySelectorAll('input, textarea, select');
    inputs.forEach(input => {
            input.style.border = ""; 
    });
}


async function registrarOdontologo() {
    if(!validarFormulario()){
        Swal.fire({
            icon: "error",
            title: "Complete todos los campos del formulario",
          });
    }else{
        const datosOdontologo = {
            matricula: document.querySelector("#matricula_odontologo").value,
            nombre: document.querySelector("#nombre_odontologo").value,
            apellido: document.querySelector("#apellido_odontologo").value,
        
        };
    

        Swal.fire({
            title: "¿Deseas guardar este odontologo?",
            text: `${datosOdontologo.nombre} ${datosOdontologo.apellido}`,
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Si",
            cancelButtonText: "No"
          }).then(async(result) => {
            if (result.isConfirmed) {
                try {
                    const response = await fetch('http://localhost:8080/odontologos/registrar', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(datosOdontologo)
                    });

                    if (!response.ok) {
                        throw new Error('Network response was not ok ' + response.statusText);
                    }

                    const resultado = await response.json();
                    Swal.fire({
                        title: "Odontologo Registrado",
                        icon: "success"
                      });
                    obtenerListadoOdontologos();
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



// Eliminar Odontologo

async function eliminarOdontologo(id) {
    Swal.fire({
        title: `Estas seguro de eliminar este odontologo ID: ${id}`,
        text: "Esta acción no se puede revertir",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Si, Eliminar",
        cancelButtonText: "Cancelar"
      }).then(async(result) => {
            if ( result.isConfirmed) {
            const url = `http://localhost:8080/odontologos/eliminar?id=${id}`;
            try {
                const response = await fetch(url, {
                    method: 'DELETE'
                });
        
                if (!response.ok) {
                    throw new Error('Network response was not ok ' + response.statusText);
                }
                Swal.fire({
                    title: `Eliminado odontologo ID: ${id}`,
                    icon: "success"
                  });
                obtenerListadoOdontologos();
                console.log('Odontologo eliminado correctamente');
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
  
// Actualizar odontologo

async function cargarInputsOdontologo(id){
    resetearFormulario();
    try {
        const response = await fetch('http://localhost:8080/odontologos/listar');
        
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
  
        const listadoOdontologos = await response.json();
        const odontologoAEditar  = listadoOdontologos.find(odontologo => odontologo.id === id); 

        document.querySelector("#id_odontologo").innerHTML = odontologoAEditar.id
        document.querySelector("#matricula_odontologo").value = odontologoAEditar.matricula;
        document.querySelector("#nombre_odontologo").value = odontologoAEditar.nombre;
        document.querySelector("#apellido_odontologo").value = odontologoAEditar.apellido;
      
        return listadoOdontologos;
    } catch (error) {
        console.error('There was a problem with the fetch operation:', error);
    }
}

async function cargarInputsPaciente(id){
    resetearFormulario();
    try {
        const response = await fetch('http://localhost:8080/pacientes/listar');
        
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
  
        const listadoPacientes = await response.json();
        const pacienteAEditar  = listadoPacientes.find(paciente => paciente.id === id); 

        document.querySelector("#id_paciente").innerHTML = pacienteAEditar.id
        document.querySelector("#dni_paciente").value = pacienteAEditar.dni;
        document.querySelector("#nombre_paciente").value = pacienteAEditar.nombre;
        document.querySelector("#apellido_paciente").value = pacienteAEditar.apellido;
        document.querySelector("#calle_domicilio").value = pacienteAEditar.domicilio.calle;
        document.querySelector("#numero_domicilio").value = pacienteAEditar.domicilio.numero;
        document.querySelector("#localidad_domicilio").value = pacienteAEditar.domicilio.localidad;
        document.querySelector("#provincia_domicilio").value =pacienteAEditar.domicilio.provincia;

      
        return listadoPacientes;
    } catch (error) {
        console.error('There was a problem with the fetch operation:', error);
    }
}

async function actulizarOdontologo(id){
    const datosOdontologo = {
        matricula: document.querySelector("#matricula_odontologo").value,
        nombre: document.querySelector("#nombre_odontologo").value,
        apellido: document.querySelector("#apellido_odontologo").value,
    };  

    Swal.fire({
        title: `¿Cofirnas la edición de este odontologo?`,
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Confirmar",
        cancelButtonText: "Cancelar"
      }).then(async(result) => {
        if ( result.isConfirmed) {
            try {
                const response = await fetch(`http://localhost:8080/odontologos/actualizar/${id}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(datosOdontologo)
                });
        
            
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            
            const resultado = await response.json();
            Swal.fire({
                title: `Odontologo guardado`,
                icon: "success"
              });
            obtenerListadoOdontologos();
            location.reload(true);
            return resultado;
            } catch (error) {
                Swal.fire({
                    title: `No se pudo guardar el odontologo`,
                    icon: "error"
                  });
                console.error('There was a problem with the fetch operation:', error);
            }
        }
    });
}

function decisionRegistrarOEditar(){
    const idRegistro = document.querySelector("#id_odontologo").innerHTML

    idRegistro.trim() !== '' ? actulizarOdontologo(parseInt(idRegistro)) : registrarOdontologo();
}

document.querySelector("#botonGuardarOdontologo").addEventListener("click", decisionRegistrarOEditar);

document.querySelector("#botonCancelar").addEventListener("click", resetearFormulario);
