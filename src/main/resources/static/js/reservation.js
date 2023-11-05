const modal_label = document.getElementById("appointment-modal-label");
const appointentDate = document.querySelector('.appointment-date');
const appointentTime = document.querySelector('.appointment-time');
const selectMedicalSpeciality = document.querySelector('.medical-speciality');
const selectDoctor = document.querySelector('.select-doctor');
const inputPatientName = document.querySelector('.patient-name');
const inputPatientId = document.querySelector('.patient-id');
const datalist = document.getElementById('pacientes');

let medicalSpecialities;
let patientList;

document.addEventListener('DOMContentLoaded', function() {
	const calendarElement = document.getElementById('calendar');
	const myModal = new bootstrap.Modal(document.getElementById("modalAppointment"), {});
	let data = [];
	
	renderCalendar(data, calendarElement, myModal);
	
});

selectMedicalSpeciality.addEventListener( 'change', () => {
	getDoctorBySpeciality( selectMedicalSpeciality.value );
});

inputPatientName.addEventListener('input', () => {
	console.log('aqui')
	const selectedName = inputPatientName.value;
	const selectedPatient = patientList.find(paciente => paciente.patientName === selectedName);
	if (selectedPatient) {
		inputPatientId.value = selectedPatient.patientId;
	} else {
		inputPatientId.value = '';
	}
});

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
    appointentDate.value = date;
    appointentTime.value = time;
	getSpecialities();
	fillDataListPatient();
}

const renderCalendar = (data, element, modal) => {
	const calendar = new FullCalendar.Calendar(element, {
    		timeZone: 'UTC',
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
	    		    fillModal( info.view.title, fecha, hora.slice(0, -1));
				}
    		},
    		eventClick: function(info) {
    			let appointmentDate = info.event.start;

    			var appointmentDateUtc = appointmentDate.toUTCString().split(' ')[4];

    			info.jsEvent.preventDefault();
    			swal(info.event.title, "Fecha: " + appointmentDateUtc + " fin: " + horaFinUTC);
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