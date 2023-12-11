let myModal;
let data = [];
const modal_label = document.getElementById("appointment-modal-label");
const appointmentDate = document.querySelector('.appointment-date');
const appointmentTime = document.querySelector('.appointment-time');
const selectMedicalSpeciality = document.querySelector('.medical-speciality');
const selectDoctor = document.querySelector('.select-doctor');
const inputPatientName = document.querySelector('.patient-name');
const inputPatientId = document.querySelector('.patient-id');
const datalist = document.getElementById('pacientes');

const btnSaveAppointment = document.querySelector('.btn-save-appointment');

let medicalSpecialities;
let patientList;

const getSpecialities = async() => {
	if(medicalSpecialities)  return;
	const res = await fetch(`/doctor/specialities`);
	const data = await res.json();
	medicalSpecialities = data;		
	
	medicalSpecialities.forEach((value) => {
      const option = document.createElement("option");
      option.setAttribute("value", value.id);
      option.textContent = value.label;
      selectMedicalSpeciality.appendChild(option);
    });
}

const getDoctorBySpeciality = async( speciality ) => {
	selectDoctor.innerHTML = '';
	const res = await fetch(`/doctor/getBySpeciality/${speciality}`);
	const data = await res.json();
	
	data.forEach((doctor) => {
      const option = document.createElement("option");
      option.setAttribute("value", doctor.doctorId);
      option.textContent = doctor.doctorName;
      selectDoctor.appendChild(option);
    });
	
}

const getPatients = async() => {
	if (patientList) return;
	
	const res = await fetch(`/patient/toList`);
	const data = await res.json();
	patientList = data;
	
}

const fillDataListPatient = async() => {
	await getPatients();
	datalist.innerHTML = '';
	patientList.forEach(paciente => {
	    const option = document.createElement('option');
	    option.value = paciente.patientName;
	    datalist.appendChild(option);
  	});
}

const fillModal = ( title, date,  time = "00:00:00" ) => {
	modal_label.innerHTML = title;
    appointmentDate.value = date;
    appointmentTime.value = time;
	getSpecialities();
	fillDataListPatient();
}


const renderCalendar = (data, element, modal) => {
	element.innerHTML = `
		<div class="text-center">
		<i class="fa-solid fa-spinner fa-spin fa-xl" style="color: #809cd0;"></i>
		<h4>Espere un momento</h4>.
		</div>
	`;
	const calendar = new FullCalendar.Calendar(element, {
    		// timeZone: 'UTC',
    		initialView: 'dayGridMonth',
    		themeSystem: 'bootstrap5',
    		headerToolbar: {
    		      left: 'prev,next today',
    		      center: 'title',
    		      right: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth'
    		},
    		events: data,
    		editable: true,
    		dateClick: function(info) {
    		    modal.show();
    		    if ( info.allDay ) {
	    		    fillModal( info.view.title, info.dateStr);					
				} else {
	    		    const [fecha, hora] = info.dateStr.split('T');

	    		    fillModal( info.view.title, fecha, hora.split('-')[0]);
				}
    		},
    		eventClick: function(info) {
				const now = new Date();
				const reservedDate = new Date(info.event.start);
				reservedDate.toUTCString();
				
				console.table({now, reservedDate});
    			if ( now > reservedDate ) 
					swal("Expiro", "La fecha de esta cita expiro.", "error");
				else 
					swal("Vigente", "Esta cita aun esta pendiente.", "success");

    		},
    		locales: [
    			'es',
    			'en'
    		],
    		eventTimeFormat: { // like '14:30:00'
    		hour: '2-digit',
    		minute: '2-digit',
    		second: '2-digit',
    		meridiem: false
    		}
    });
	calendar.render();
}

const saveAppointment = () => {

	const apppointmentDateTime = appointmentDate.value + ' ' + ((appointmentTime.value === '00:00:00') ? appointmentTime.value : appointmentTime.value+':00');

	const appointment= {
		'appointmentId': 0,
		'patient': {
			'patientId': inputPatientId.value
		},
		'doctor': {
			'doctorId': selectDoctor.value
		},
		'receptionist': {
			'receptionistId': 4
		},
		'diagnosis': {},
		'recipe': {},
		'appointmentDate': apppointmentDateTime
	};

	fetch('/appointment/add', {
		method: 'PUT',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(appointment)
	}).then( res => {
		if ( !res.ok ) throw new Error('Error al guardar cita !.');
		selectMedicalSpeciality.value = '0';
		selectDoctor.innerHTML = '<option value="0" selected>Seleccionar medico</option>';
		inputPatientName.value = '';
		inputPatientId.value = '0';
		myModal.hide();
		swal('Guardado!', 'Guardado con exito !', 'success');
	}).catch( console.log );
};

const getAppointments = async() => {

	const res = await fetch('/appointment/fillCalendar');
	const data = await res.json();

	return data;

}

// Listeners

document.addEventListener('DOMContentLoaded', async function() {
	const calendarElement = document.getElementById('calendar');
	myModal = new bootstrap.Modal(document.getElementById("modalAppointment"), {});
	data = await getAppointments();
	
	renderCalendar(data, calendarElement, myModal);
	
});

selectMedicalSpeciality.addEventListener( 'change', () => {
	if (selectMedicalSpeciality.value === '0') return;
	getDoctorBySpeciality( selectMedicalSpeciality.value );
});

inputPatientName.addEventListener('input', () => {
	const selectedName = inputPatientName.value;
	const selectedPatient = patientList.find(paciente => paciente.patientName === selectedName);
	if (selectedPatient) {
		inputPatientId.value = selectedPatient.patientId;
	} else {
		inputPatientId.value = '';
	}
});

btnSaveAppointment.addEventListener( 'click', saveAppointment );