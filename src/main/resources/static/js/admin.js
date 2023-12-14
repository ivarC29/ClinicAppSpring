

// References HTML
const btnGuardar = document.getElementById("btn-guardar")
const btnDoctor = document.getElementById("btn-doctor");
const btnPatient = document.getElementById("btn-patient");
const btnReceptionist = document.getElementById("btn-receptionist");
const btnAppointment = document.getElementById("btn-appointment");

const btnAddDoctor = document.getElementById("btn-add-doctor");
const btnAddReceptionist = document.getElementById("btn-add-receptionist");
const btnAddPatient = document.getElementById("btn-add-patient");

const modalBody = document.querySelector(".modal-body");
const modalTitle = document.querySelector(".modal-title");

const inputId = document.getElementById('id');

// Add object modal
let myModal = new bootstrap.Modal(document.getElementById("myModal"), {});

// Object attributes
const doctorAttributes = {
    "Id": "doctorId",
    "Nombre": "doctorName",
    "DNI": "doctorDNI",
    "Telefono": "doctorPhone",
    "Direccion": "doctorAddress",
    "Especialidad":"medicalSpeciality",
    "Clinica": "clinic"
};

const PatientAttributes = {
    "Id": "patientId",
    "Nombre": "patientName",
    "DNI": "patientDNI",
    "Telefono": "patientPhone",
    "Direccion": "patientAddress"
};

const ReceptionistAttributes = {
    "Id": "receptionistId",
    "Nombre": "receptionistName",
    "DNI": "receptionistDNI",
    "Telefono": "receptionistPhone",
    "Direccion": "receptionistAddress"
};


// input values
const fields = [
    { label: "Nombre", id: "nombre", placeholder: "" },
    { label: "DNI", id: "dni", placeholder: "" },
    { label: "Telefono", id: "telefono", placeholder: "" },
    { label: "Direccion", id: "direccion", placeholder: "" },
];

const selectLabels = [
    "Select clinic",
    "Select schedule",
    "Select speciality",
];

const tiposData = ['clinic', 'schedule', 'specialities']; 
let optionValuesForClinic;
let optionValuesForShedule;
let optionValuesForSpecialities;
let jsonObject;

// General methods
const capitalizeText = (str) => {
    return str.replace(/\b\w/g, char => char.toUpperCase());
}

const getObject = ( id, type) => {
    buildModalInput(type);
    myModal.show();
    fetch(`/${type}/get/${id}`)
        .then(response => response.json() )
        .then(data => {
            inputId.value = data[`${type}Id`];
            document.getElementById("nombre").value = data[`${type}Name`];
            document.getElementById("dni").value = data[`${type}DNI`];
            document.getElementById("telefono").value = data[`${type}Phone`];
            document.getElementById("direccion").value = data[`${type}Address`];
            /*document.getElementById("select-user").value = data.user.userId;*/
            if ( type === 'doctor' || type === 'receptionist') {
                document.getElementById("select-schedule").value = data.schedule.scheduleId;
            }
            if ( type === 'doctor') {
                document.getElementById("select-clinic").value = data.clinic.clinicID;
                document.getElementById("select-speciality").value = data.medicalSpeciality
            }

        })
        .catch(error => {
            console.error("Error al obtener los datos:", error);
        });
};

const deleteObject = (id , type) => {

    swal({
        title: " ¿Seguro? ",
        text: "Los datos se eliminara permanentemente",
        icon: "warning",
        buttons: true,
        dangerMode: true,
      })
      .then((willDelete) => {
        if (willDelete) {
            fetch(`/${type}/delete/${id}`)
            .then(response => {
                if ( response.ok ) {
                    swal("Poof! dato eliminado", {
                        icon: "success",
                      });
                } else {
                    swal("Error al borrar datos");
                }
            })
            .catch(error => {
                console.error("Error al eliminar los datos:", error);
            });
          
        } else {
          swal("Los datos se mantienen intactos");
        }
      });

    
};


/**
 * Recibe la lista de objetos, y genera una lista html
 * @param {List<Object>} listaObjetos
 * @param {String} type 
 */
const listarObjetos = ( listaObjetos, type ) => {
    let attributes;
    let idType = `${type}Id`;

    if (type === 'doctor') {
        attributes = doctorAttributes;
    } else if ( type === 'receptionist') {
        attributes = ReceptionistAttributes;
    } else if ( type === 'patient') {
        attributes = PatientAttributes;
    } else {
        return;
    }

    const thead = document.getElementById("data-table").getElementsByTagName("thead")[0];
    const tbody = document.getElementById("data-table").getElementsByTagName("tbody")[0];
    thead.innerHTML = "";
    let headerRow = "<tr>";
    for (const title of Object.keys(attributes)) {
        headerRow += "<th>" + title + "</th>";
    }
    headerRow += "<th colspan='2'>Accion</th></tr>";
    thead.insertAdjacentHTML("beforeend", headerRow);
    tbody.innerHTML = "";
    listaObjetos.forEach(item => {
        let row = "<tr>";
        for (const attribute of Object.values(attributes)) {
            if (typeof item[attribute] === 'object' && item[attribute] !== null)
                row += "<td>" + item[attribute].clinicName + "</td>";
			else
                row += "<td>" + item[attribute] + "</td>";
        }
        row += `<td><div class="btn-group"><a class="btn btn-outline-success" onclick="getObject(${item[idType]}, '${type}')"><i class="fa-solid fa-pen-to-square"></i></a>`;
        row += `<a class="btn btn-outline-danger" onclick="deleteObject(${item[idType]}, '${type}')"><i class="fa-solid fa-trash"></i></a></div></td>`;
        row += '</tr>';
        tbody.insertAdjacentHTML("beforeend", row);
    });
};

const getOptionValues = ( tipo ) => {
    if ( !tiposData.includes(tipo) ) return;
    
    if (tipo === tiposData[2]) {
    	fetch(`/doctor/${tipo}`)
    		.then(response => response.json() )
    		.then(data => {
    			optionValuesForSpecialities = data;
    	})
    	.catch(error => {
        	console.error("Error al obtener los datos:", error);
    	});
	} else {
        fetch(`/${tipo}/toList`)
        .then(response => response.json() )
        .then(data => {
            if (tipo === tiposData[0]) {
    
                optionValuesForClinic = data.map( obj => {
                    const dataModel = {
                        id: obj.clinicID,
                        label: obj.clinicName
                    }
                    return dataModel;
                });
    
            } else if ( tipo === tiposData[1]) {
    
                optionValuesForShedule = data.map( obj => {
                    const dataModel = {
                        id: obj.scheduleId,
                        label: obj.startTime + '-' + obj.endTime
                    }
                    return dataModel;
                });
    
            } 
        })
        .catch(error => {
            console.error("Error al obtener los datos:", error);
        });
    }
    
}
getOptionValues('clinic');
getOptionValues('schedule');
getOptionValues('specialities');

/**
 * Funcion para generar el input modal
 * @param {String} tipo tipo de objeto para el cual pertenece este input 
 */
const buildModalInput = ( tipo ) => {
    inputId.value = 0;
    if ( tipo === 'receptionist' ) {
        modalTitle.innerHTML = "Registro de recepcionista";
        btnGuardar.setAttribute('data-type', 'receptionist');
    } else if ( tipo === "patient") {
        modalTitle.innerHTML = "Registro de paciente";
        btnGuardar.setAttribute('data-type', 'patient');
    } else if ( tipo === 'doctor') {
        modalTitle.innerHTML = "Registro de medico";
        btnGuardar.setAttribute('data-type', 'doctor');
    }
    modalBody.innerHTML = "";
    fields.forEach((field) => {
        const fieldDiv = document.createElement("div");
        fieldDiv.classList.add("mb-3");
        
        const label = document.createElement("label");
        label.setAttribute("for", field.id);
        label.classList.add("form-label");
        label.textContent = field.label;
        
        const input = document.createElement("input");
        input.setAttribute("type", "text");
        input.classList.add("form-control");
        input.setAttribute("id", field.id);
        input.setAttribute("placeholder", field.placeholder);
        
        fieldDiv.appendChild(label);
        fieldDiv.appendChild(input);
        
        modalBody.appendChild(fieldDiv);
    });
    selectLabels.forEach((labelText) => {
        if (tipo === 'patient')
            return;

        if ((labelText === 'Select clinic' || labelText === 'Select speciality') && tipo === 'receptionist')
            return;

        const selectDiv = document.createElement("div");
        selectDiv.classList.add("mb-3");
      
        const select = document.createElement("select");
        select.classList.add("form-select");
        select.setAttribute("id", labelText.toLowerCase().replace(/\s+/g, '-'));
        select.setAttribute("aria-label", "Default select example");
      
        const defaultOption = document.createElement("option");
        defaultOption.setAttribute("selected", "selected");
        defaultOption.textContent = labelText;
      
        let optionValues = [];

        if (labelText === 'Select clinic') {
            optionValues = optionValuesForClinic;
        } else if (labelText === 'Select schedule') {
            optionValues = optionValuesForShedule;
        } else if (labelText === 'Select speciality') {
            optionValues = optionValuesForSpecialities;
        }

        optionValues.forEach((value) => {
          const option = document.createElement("option");
          option.setAttribute("value", value.id);
          option.textContent = value.label;
          select.appendChild(option);
        });
      
        select.appendChild(defaultOption);
        selectDiv.appendChild(select);
      
        modalBody.appendChild(selectDiv);
    });
}

const createJsonObject = ( tipo ) => {
    
    if (tipo === 'doctor') {
        jsonObject= {
            'doctorId': document.getElementById("id").value,
            'doctorName': document.getElementById("nombre").value,
            'doctorDNI': document.getElementById("dni").value,
            'doctorPhone': document.getElementById("telefono").value,
            'doctorAddress': document.getElementById("direccion").value,
            'user': {
                'userId': '',
                'email': '',
                'username': '',
                'password': '',
            },
            'clinic': {
                'clinicID': document.getElementById("select-clinic").value,
                'clinicName': '',
                'clinicAddress': '',
                'clinicPhone': '',
            },
            'schedule':  {
                'scheduleId': document.getElementById("select-schedule").value,
                'startTime': '00:00:00',
                'endTime': '00:00:00'
            },
            'medicalSpeciality': document.getElementById("select-speciality").value
        };
    } else if (tipo === 'receptionist') {
        jsonObject= {
            'receptionistId': document.getElementById("id").value,
            'receptionistName': document.getElementById("nombre").value,
            'receptionistDNI': document.getElementById("dni").value,
            'receptionistPhone': document.getElementById("telefono").value,
            'receptionistAddress': document.getElementById("direccion").value,
            'user': {
                'userId': '',
                'email': '',
                'username': '',
                'password': '',
            },
            'schedule':  {
                'scheduleId': document.getElementById("select-schedule").value,
                'startTime': '00:00:00',
                'endTime': '00:00:00'
            }
        };
    } else if (tipo === 'patient') {
        jsonObject= {
            'patientId': document.getElementById("id").value,
            'patientName': document.getElementById("nombre").value,
            'patientDNI': document.getElementById("dni").value,
            'patientPhone': document.getElementById("telefono").value,
            'patientAddress': document.getElementById("direccion").value,
            'user': {
                'userId': '',
                'email': '',
                'username': '',
                'password': '',
            }
        };
    }
};

// Listeners
btnDoctor.addEventListener("click", function () {
    fetch("/doctor/toList")
        .then(response => response.json() )
        .then(data => {
            listarObjetos(data, 'doctor');
        })
        .catch(error => {
            console.error("Error al obtener los datos:", error);
        });
});
btnPatient.addEventListener("click", function () {
    fetch("/patient/toList")
        .then(response => response.json() )
        .then(data => {
            listarObjetos(data, 'patient');
        })
        .catch(error => {
            console.error("Error al obtener los datos:", error);
        });
});
btnReceptionist.addEventListener("click", function () {
    fetch("/receptionist/toList")
        .then(response => response.json() )
        .then(data => {
            listarObjetos(data, 'receptionist');
        })
        .catch(error => {
            console.error("Error al obtener los datos:", error);
        });
});

btnAddDoctor.addEventListener('click', () => {
    buildModalInput('doctor');
});

btnAddPatient.addEventListener('click', () => {
    buildModalInput('patient');
});

btnAddReceptionist.addEventListener('click', () => {
    buildModalInput('receptionist');
});

btnGuardar.addEventListener("click", (event) => {
    const typeSave = event.target.getAttribute('data-type');
    createJsonObject(typeSave);

    fetch(`/${typeSave}/add`, {
        credentials: 'include',
        method: 'POST',
        headers: {
            /* 'X-CSRF-TOKEN': token, */
            'Content-Type': 'application/json' 
        },
        body: JSON.stringify(jsonObject)
    }).then( response => {
        if( response.ok ) {
            myModal.hide();
            swal('Guardado!', 'Guardado con exito !', 'success');
        } else
            throw new Error(`Error al guardar ${typeSave}`);
    })
    .catch( console.log )
});