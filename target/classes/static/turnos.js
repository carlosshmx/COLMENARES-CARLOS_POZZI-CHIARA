

//   TURNOS

async function obtenerSelectPacientes() {
    try {
        const response = await fetch('http://localhost:8080/pacientes/listar');
        
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
  
        const listadoPacientes = await response.json();

        const paciente_select = document.querySelector(".paciente-select")
        let pacientesSelectHtml = "<option selected>Seleccione...</option>";
        listadoPacientes.forEach(paciente=>{
            pacientesSelectHtml+=`<option value="${paciente.id}">${paciente.dni} - ${paciente.nombre} ${paciente.apellido}</option>`
        })
        paciente_select.innerHTML=pacientesSelectHtml;

        return listadoPacientes;
    } catch (error) {
        console.error('There was a problem with the fetch operation:', error);
    }
  }

  async function obtenerSelectOdontologos() {
    try {
        const response = await fetch('http://localhost:8080/odontologos/listar');
        
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
  
        const listadoOdontologos = await response.json();

        const odontologo_select = document.querySelector(".odontologo_select")
        let OdontologosSelectHtml = "<option selected>Seleccione...</option>";
        listadoOdontologos.forEach(odontologo=>{
            OdontologosSelectHtml+=`<option value="${odontologo.id}">${odontologo.matricula} - ${odontologo.nombre} ${odontologo.apellido}</option>`
        })
        odontologo_select.innerHTML=OdontologosSelectHtml;
      
        return listadoOdontologos;
    } catch (error) {
        console.error('There was a problem with the fetch operation:', error);
    }
  }


async function obtenerListadoTurnos(){
    const tablaTurnos = document.querySelector(".tablaTurnos")
    tablaTurnos.innerHTML="";
    try {
        const response = await fetch('http://localhost:8080/turnos/listar');
        
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
  
        const listadoTurnos = await response.json();
        console.log(listadoTurnos); // Mostrar el listado en la consola
  
        let turnosHtml = "";
        listadoTurnos.forEach(turno => {
            const dateTimeString = turno.fechaYHora;
            const date = new Date(dateTimeString);
            const options = { year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit' };
            const readableDate = date.toLocaleDateString('es-ES', options);

          turnosHtml += `<tr>
                              <th scope="row">${turno.id}</th>
                              <td>${turno.paciente.nombre}</td>
                              <td>Dr. ${turno.odontologo.nombre}</td>
                              <td>${readableDate}</td>
                              <td>  
                                  <a class="text-primary px-3" href="" >
                                      <i class="fas fa-edit"></i>
                                  </a>
                                  <a class="text-primary px-3" href="" onclick="eliminarTurno(${turno.id})">
                                      <i class="fas fa-trash-alt"></i>
                                  </a>
                              </td>                  
                          </tr>`
        });
      
       
      tablaTurnos.innerHTML = turnosHtml;
      
        return listadoTurnos;
    } catch (error) {
        console.error('There was a problem with the fetch operation:', error);
    }
  }

obtenerSelectPacientes();
obtenerSelectOdontologos();
obtenerListadoTurnos();

  // Registrar Turno


  async function registrarTurno(){
    const fecha = document.querySelector("#fecha").value;
    const hora = document.querySelector("#hora").value;
    const fechaYHora = `${fecha}T${hora}:00`
    const datosTurno = {
      odontologoId : document.querySelector("#pacienteSelect").value,
      pacienteId : document.querySelector("#odontologoSelect").value,
      fechaYHora : fechaYHora
    };
    console.log(datosTurno);
    try {
        const response = await fetch('http://localhost:8080/turnos/registrar', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(datosTurno)
        });
  
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
  
        const resultado = await response.json();
        console.log(resultado); // Mostrar la respuesta en la consola
        obtenerListadoTurnos();
        return resultado;
    } catch (error) {
        console.error('There was a problem with the fetch operation:', error);
    } 
  }

  document.querySelector("#botonRegistrarTurno").addEventListener("click", registrarTurno);

  // Eliminar Turno

  async function eliminarTurno(id) {
    const url = `http://localhost:8080/turnos/eliminar?id=${id}`;
  
    try {
        const response = await fetch(url, {
            method: 'DELETE'
        });
  
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        obtenerListadoTurnos();
        console.log('Turno eliminado correctamente');
    } catch (error) {
        console.error('There was a problem with the fetch operation:', error);
    }
  }
  