

//   TURNOS

async function obtenerListadoPacientes() {
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

  async function obtenerListadoOdontologos() {
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


  obtenerListadoPacientes();
  obtenerListadoOdontologos();



//   {
//     "odontologoId" : 1,
//     "pacienteId" : 1,
//     "fechaYHora" : "2024-06-14T10:30:00"
// }

