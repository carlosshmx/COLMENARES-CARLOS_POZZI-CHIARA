
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
                                <a class="text-primary px-3" href="" >
                                    <i class="fas fa-edit"></i>
                                </a>
                                <a class="text-primary px-3" href="" onclick="eliminarOdontologo(${odontologo.id})">
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



async function registrarOdontologo() {
    const datosOdontologo = {
        matricula: document.querySelector("#matricula_odontologo").value,
        nombre: document.querySelector("#nombre_odontologo").value,
        apellido: document.querySelector("#apellido_odontologo").value,
       
    };
  
    console.log(JSON.stringify(datosOdontologo));

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
        console.log(resultado); // Mostrar la respuesta en la consola
        obtenerListadoOdontologos();
        return resultado;
    } catch (error) {
        console.error('There was a problem with the fetch operation:', error);
    }
}

document.querySelector("#botonRegistroOdontologo").addEventListener("click", registrarOdontologo)

// Eliminar Odontologo

async function eliminarOdontologo(id) {
    const url = `http://localhost:8080/odontologos/eliminar?id=${id}`;
  
    try {
        const response = await fetch(url, {
            method: 'DELETE'
        });
  
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        obtenerListadoOdontologos();
        console.log('Odontologo eliminado correctamente');
    } catch (error) {
        console.error('There was a problem with the fetch operation:', error);
    }
  }
  